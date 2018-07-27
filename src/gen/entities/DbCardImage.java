
package gen.entities;

// Generated Jul 27, 2018 10:14:50 AM by Hibernate Tools 3.4.0.CR1

/**
 * DbCardImage generated by hbm2java
 */
public class DbCardImage implements java.io.Serializable
{

	private long id;
	private byte[] image;
	private String originalImageSrc;
	private String type;
	private float offsetTop;
	private float offsetLeft;
	private float width;
	private float height;

	public DbCardImage()
	{
	}

	public DbCardImage(byte[] image, String originalImageSrc, String type, float offsetTop, float offsetLeft,
		float width, float height)
	{
		this.image = image;
		this.originalImageSrc = originalImageSrc;
		this.type = type;
		this.offsetTop = offsetTop;
		this.offsetLeft = offsetLeft;
		this.width = width;
		this.height = height;
	}

	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
	public byte[] getImage()
	{
		return this.image;
	}

	public void setImage(byte[] image)
	{
		this.image = image;
	}
	public String getOriginalImageSrc()
	{
		return this.originalImageSrc;
	}

	public void setOriginalImageSrc(String originalImageSrc)
	{
		this.originalImageSrc = originalImageSrc;
	}
	public String getType()
	{
		return this.type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
	public float getOffsetTop()
	{
		return this.offsetTop;
	}

	public void setOffsetTop(float offsetTop)
	{
		this.offsetTop = offsetTop;
	}
	public float getOffsetLeft()
	{
		return this.offsetLeft;
	}

	public void setOffsetLeft(float offsetLeft)
	{
		this.offsetLeft = offsetLeft;
	}
	public float getWidth()
	{
		return this.width;
	}

	public void setWidth(float width)
	{
		this.width = width;
	}
	public float getHeight()
	{
		return this.height;
	}

	public void setHeight(float height)
	{
		this.height = height;
	}

	/**
	 * toString
	 * @return String
	 */
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("id").append("='").append(getId()).append("' ");
		buffer.append("originalImageSrc").append("='").append(getOriginalImageSrc()).append("' ");
		buffer.append("type").append("='").append(getType()).append("' ");
		buffer.append("offsetTop").append("='").append(getOffsetTop()).append("' ");
		buffer.append("offsetLeft").append("='").append(getOffsetLeft()).append("' ");
		buffer.append("width").append("='").append(getWidth()).append("' ");
		buffer.append("height").append("='").append(getHeight()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}

}
