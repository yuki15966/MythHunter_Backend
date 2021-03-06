
package gen.entities;

// Generated Jul 27, 2018 5:45:54 PM by Hibernate Tools 3.4.0.CR1

/**
 * MapPosition generated by hbm2java
 */
public class MapPosition implements java.io.Serializable
{

	private float longitude;
	private float latitude;

	public MapPosition()
	{
	}

	public MapPosition(float longitude, float latitude)
	{
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public float getLongitude()
	{
		return this.longitude;
	}

	public void setLongitude(float longitude)
	{
		this.longitude = longitude;
	}
	public float getLatitude()
	{
		return this.latitude;
	}

	public void setLatitude(float latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * toString
	 * @return String
	 */
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("longitude").append("='").append(getLongitude()).append("' ");
		buffer.append("latitude").append("='").append(getLatitude()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}

}
