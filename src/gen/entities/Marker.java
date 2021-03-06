
package gen.entities;

// Generated Jul 27, 2018 5:45:54 PM by Hibernate Tools 3.4.0.CR1

import main.entities.MarkerType;

/**
 * Marker generated by hbm2java
 */
public class Marker implements java.io.Serializable
{

	private long id;
	private Integer version;
	private MapPosition position;
	private String name;
	private long htmlId;
	private MapPosition targetPosition;
	private long finishedHtmlId;
	private MarkerType type;
	private long enemyId;

	public Marker()
	{
	}

	public Marker(MapPosition position, String name, long htmlId, MapPosition targetPosition, long finishedHtmlId,
		MarkerType type, long enemyId)
	{
		this.position = position;
		this.name = name;
		this.htmlId = htmlId;
		this.targetPosition = targetPosition;
		this.finishedHtmlId = finishedHtmlId;
		this.type = type;
		this.enemyId = enemyId;
	}

	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
	public Integer getVersion()
	{
		return this.version;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}
	public MapPosition getPosition()
	{
		return this.position;
	}

	public void setPosition(MapPosition position)
	{
		this.position = position;
	}
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	public long getHtmlId()
	{
		return this.htmlId;
	}

	public void setHtmlId(long htmlId)
	{
		this.htmlId = htmlId;
	}
	public MapPosition getTargetPosition()
	{
		return this.targetPosition;
	}

	public void setTargetPosition(MapPosition targetPosition)
	{
		this.targetPosition = targetPosition;
	}
	public long getFinishedHtmlId()
	{
		return this.finishedHtmlId;
	}

	public void setFinishedHtmlId(long finishedHtmlId)
	{
		this.finishedHtmlId = finishedHtmlId;
	}
	public MarkerType getType()
	{
		return this.type;
	}

	public void setType(MarkerType type)
	{
		this.type = type;
	}
	public long getEnemyId()
	{
		return this.enemyId;
	}

	public void setEnemyId(long enemyId)
	{
		this.enemyId = enemyId;
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
		buffer.append("position").append("='").append(getPosition()).append("' ");
		buffer.append("name").append("='").append(getName()).append("' ");
		buffer.append("htmlId").append("='").append(getHtmlId()).append("' ");
		buffer.append("targetPosition").append("='").append(getTargetPosition()).append("' ");
		buffer.append("finishedHtmlId").append("='").append(getFinishedHtmlId()).append("' ");
		buffer.append("enemyId").append("='").append(getEnemyId()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}

}
