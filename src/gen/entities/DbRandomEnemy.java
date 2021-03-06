
package gen.entities;

// Generated Jul 27, 2018 5:45:54 PM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.List;

/**
 * DbRandomEnemy generated by hbm2java
 */
public class DbRandomEnemy implements java.io.Serializable
{

	private long id;
	private Integer version;
	private MapPosition position;
	private DbEnemy enemy;
	private List<Long> completedByUserIds = new ArrayList<Long>(0);

	public DbRandomEnemy()
	{
	}

	public DbRandomEnemy(MapPosition position, DbEnemy enemy, List<Long> completedByUserIds)
	{
		this.position = position;
		this.enemy = enemy;
		this.completedByUserIds = completedByUserIds;
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
	public DbEnemy getEnemy()
	{
		return this.enemy;
	}

	public void setEnemy(DbEnemy enemy)
	{
		this.enemy = enemy;
	}
	public List<Long> getCompletedByUserIds()
	{
		return this.completedByUserIds;
	}

	public void setCompletedByUserIds(List<Long> completedByUserIds)
	{
		this.completedByUserIds = completedByUserIds;
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
		buffer.append("]");

		return buffer.toString();
	}

}
