
package main.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.DatatypeConverter;
import javax.xml.ws.WebServiceException;

/**
 * A web service implementation of an endpoint interface.
 * 
 * @author www.codejava.net
 */
@WebService
public class FileTransfererImpl implements FileTransferer
{

	@WebMethod
	public String upload(String filePath, String fileName, String imageBase64)
	{
		try
		{
			byte[] imageBytes = DatatypeConverter.parseBase64Binary(imageBase64);
			FileOutputStream fos = new FileOutputStream(filePath);
			BufferedOutputStream outputStream = new BufferedOutputStream(fos);
			outputStream.write(imageBytes);
			outputStream.close();

			System.out.println("Received file: " + filePath);

		}
		catch (IOException ex)
		{
			System.err.println(ex);
			throw new WebServiceException(ex);
		}

		return fileName;
	}

	@WebMethod
	public String download(String filePath)
	{
		System.out.println("Sending file: " + filePath);

		try
		{
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream inputStream = new BufferedInputStream(fis);
			byte[] fileBytes = new byte[(int) file.length()];
			inputStream.read(fileBytes);
			inputStream.close();

			return DatatypeConverter.printBase64Binary(fileBytes);
		}
		catch (IOException ex)
		{
			System.err.println(ex);
			throw new WebServiceException(ex);
		}
	}

	@WebMethod
	public void delete(String filePath)
	{
		File file = new File(filePath);
		if (file.exists() && !file.isDirectory())
		{
			file.delete();
		}
	}

}
