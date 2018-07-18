
package main.entities;

import gen.entities.DbCardImage;
import java.io.Serializable;
import javax.xml.bind.DatatypeConverter;

public class CardImage implements Serializable
{
	private long id;
	private String image;
	private String originalImageSrc;
	private String type;
	private float offsetTop;
	private float offsetLeft;
	private float width;
	private float height;

	public CardImage()
	{

	}

	public CardImage(DbCardImage cardImage)
	{
		this.id = cardImage.getId();
		this.originalImageSrc = cardImage.getOriginalImageSrc();
		this.type = cardImage.getType();
		this.offsetTop = cardImage.getOffsetTop();
		this.offsetLeft = cardImage.getOffsetLeft();
		this.width = cardImage.getWidth();
		this.height = cardImage.getHeight();
		this.image = DatatypeConverter.printBase64Binary(cardImage.getImage());
	}

	public DbCardImage asDbCardImage()
	{
		byte[] imageAsByteArray = DatatypeConverter.parseBase64Binary(image);
		DbCardImage cardImage = new DbCardImage(imageAsByteArray, originalImageSrc, type, offsetTop, offsetLeft, width,
			height);
		cardImage.setId(id);
		return cardImage;
	}

	public CardImage(long id, String image, String originalImageSrc, String type, float offsetTop, float offsetLeft,
		float width, float height)
	{
		super();
		this.id = id;
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
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public String getOriginalImageSrc()
	{
		return originalImageSrc;
	}

	public void setOriginalImageSrc(String originalImageSrc)
	{
		this.originalImageSrc = originalImageSrc;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public float getOffsetTop()
	{
		return offsetTop;
	}

	public void setOffsetTop(float offsetTop)
	{
		this.offsetTop = offsetTop;
	}

	public float getOffsetLeft()
	{
		return offsetLeft;
	}

	public void setOffsetLeft(float offsetLeft)
	{
		this.offsetLeft = offsetLeft;
	}

	public float getWidth()
	{
		return width;
	}

	public void setWidth(float width)
	{
		this.width = width;
	}

	public float getHeight()
	{
		return height;
	}

	public void setHeight(float height)
	{
		this.height = height;
	}

	@Override
	public String toString()
	{
		return "CardImage [id=" + id + ", originalImageSrc=" + originalImageSrc + ", type=" + type + ", offsetTop="
			+ offsetTop + ", offsetLeft=" + offsetLeft + ", width=" + width + ", height=" + height + "]";
	}

}
