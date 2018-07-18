
package main.integration;

import java.io.Serializable;

/***
 * The data container class to configure the image integration process
 * 
 * @author ???
 */
public class IntegrationConfigParams implements Serializable
{
	private int topX;
	private int topY;
	private int width;
	private int height;

	private String inName;
	private String outName;

	/***
	 * Default constructor, requiring the most necessary parameters for the process.
	 * 
	 * @param inputFilename
	 *            the relative or absolute path to the image.
	 * @param topHorizontal
	 *            the horizontal distance from the top left corner of the input image to the top left corner of the
	 *            output image.
	 * @param topVertical
	 *            the vertical distance from the top left corner of the input image to the top left corner of the output
	 *            image.
	 * @param width
	 *            the width of the selected detain in the input image.
	 * @param height
	 *            the height of the selected detain in the input image.
	 */
	public IntegrationConfigParams()
	{

	}

	public int getTopX()
	{
		return topX;
	}

	public void setTopX(int topX)
	{
		this.topX = topX;
	}

	public int getTopY()
	{
		return topY;
	}

	public void setTopY(int topY)
	{
		this.topY = topY;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public String getInName()
	{
		return inName;
	}

	public void setInName(String inName)
	{
		this.inName = inName;
	}

	public String getOutName()
	{
		return outName;
	}

	public void setOutName(String outName)
	{
		this.outName = outName;
	}

}
