
package main.integration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import main.listhelper.IListEntryPicker;
import main.listhelper.ListEntrySearch;
import main.listhelper.ListEntrySearchResult;

class StringContains implements IListEntryPicker<String>
{
	private String val;

	StringContains(String val)
	{
		this.val = val;
	}

	@Override
	public boolean WannaPick(String elem)
	{
		return elem.contains(val);
	}
}

public class IntegratePicture
{
	private String templatePath;
	private String templateName;
	private File exeCompletePath;

	private boolean printLog;

	/**
	 * Default constructor, that need path information to the configuration and the external tool.
	 * 
	 * @param pL
	 *            defines if the output shall be written to System.out
	 * @param tP
	 *            defines the directory in which the template XML configuration file is located.
	 * @param tN
	 *            defines the common name of the configuration files.
	 * @param eP
	 *            defines the path to the external tool.
	 */
	public IntegratePicture(boolean pL, String tP, String tN, String eP)
	{
		printLog = pL;
		templatePath = tP;
		templateName = tN;
		exeCompletePath = new File(eP);
	}

	/**
	 * Converts a picture by preparing the configuration files and calling the external process to convert the image.
	 * 
	 * @param parameter
	 *            the data container, containing the configuration parameters for the process.
	 * @return in case of success it contains the updated parameters used in the resulting image, in case of failure
	 *         'null' is returned.
	 */
	public IntegrationConfigParams ConvertPicture(IntegrationConfigParams parameter)
	{
		try
		{
			File picturePath = new File(parameter.getInName());
			String filepath = picturePath.getCanonicalPath();
			String directory = picturePath.getParentFile().getCanonicalPath();
			String name = picturePath.getName().replaceFirst("[.][^.]+$", "");
			String ext = picturePath.getName().replace(name, "");

			String pictureOutputName = filepath.replace(picturePath.getName(), name + "_mod.png");

			Path processConfigName = Paths.get(directory, name + "_process.xml");
			Path paramConfigName = Paths.get(directory, name + "_params.xml");

			PrepareProcessConfig(processConfigName, filepath, pictureOutputName, parameter);
			PrepareParamConfig(paramConfigName);

			if (ExecuteProcess(directory, name, ext))
			{
				// Don't worry be happy, find the picture here ;)
				parameter.setOutName(pictureOutputName);
				return parameter;
			}
		}
		catch (Exception e)
		{
			if (printLog)
			{
				e.printStackTrace(System.out);
			}
		}

		// Nope something went wrong. See the log files.
		return null;
	}

	private void PrepareProcessConfig(Path configName, String filepath, String pictureOutputName,
		IntegrationConfigParams parameter) throws IOException
	{
		Path processXmlPath = Paths.get(templatePath, templateName + "_process.xml");
		List<String> processXml = Files.readAllLines(processXmlPath, Charset.forName("UTF-8"));

		ListEntrySearch<String> searcher = new ListEntrySearch<String>();
		ListEntrySearchResult<String> inputLine = searcher.FindFirst(processXml, new StringContains(
			"<imageInPath>InputImageNameHere</imageInPath>"));
		ListEntrySearchResult<String> outputLine = searcher.FindFirst(processXml, new StringContains(
			"<imageOutPath>OututImageNameHere</imageOutPath>"));

		ListEntrySearchResult<String> widthLine = searcher.FindFirst(processXml, new StringContains(
			"<width>WidthParam</width>"));
		ListEntrySearchResult<String> heightLine = searcher.FindFirst(processXml, new StringContains(
			"<height>HeightParam</height>"));
		ListEntrySearchResult<String> topXLine = searcher.FindFirst(processXml, new StringContains(
			"<topOffsetX>TopParamX</topOffsetX>"));
		ListEntrySearchResult<String> topYLine = searcher.FindFirst(processXml, new StringContains(
			"<topOffsetY>TopParamY</topOffsetY>"));

		processXml.set(inputLine.getIndex(), inputLine.getElement().replace("InputImageNameHere", filepath));
		processXml.set(outputLine.getIndex(), outputLine.getElement().replace("OututImageNameHere", pictureOutputName));

		processXml.set(widthLine.getIndex(),
			widthLine.getElement().replace("WidthParam", Integer.toString(parameter.getWidth())));
		processXml.set(heightLine.getIndex(),
			heightLine.getElement().replace("HeightParam", Integer.toString(parameter.getHeight())));
		processXml.set(topXLine.getIndex(),
			topXLine.getElement().replace("TopParamX", Integer.toString(parameter.getTopX())));
		processXml.set(topYLine.getIndex(),
			topYLine.getElement().replace("TopParamY", Integer.toString(parameter.getTopY())));

		Files.write(configName, processXml, Charset.forName("UTF-8"));
	}

	private void PrepareParamConfig(Path configName) throws IOException
	{
		Path paramXmlPath = Paths.get(templatePath, templateName + "_params.xml");
		List<String> paramXml = Files.readAllLines(paramXmlPath, Charset.forName("UTF-8"));
		Files.write(configName, paramXml, Charset.forName("UTF-8"));
	}

	private boolean ExecuteProcess(String directory, String name, String ext) throws Exception
	{
		ProcessBuilder builder = new ProcessBuilder(exeCompletePath.getPath(), directory + "/", name, ext);
		builder.directory(exeCompletePath.getParentFile());
		Process process = builder.start();

		if (printLog)
		{
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line;
			System.out.printf("Converting Image %s:", name + ext);
			while ((line = br.readLine()) != null)
			{
				System.out.println(line);
			}
		}

		if (process.waitFor() == 0)
		{
			File log = Paths.get(directory, name + ".log").toFile();
			if (log.exists())
				log.delete();

			return true;
		}

		return false;
	}
}
