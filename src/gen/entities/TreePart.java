
package gen.entities;

// Generated Jul 4, 2017 3:25:15 PM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.entities.TreePartType;

/**
 * TreePart generated by hbm2java
 */
public class TreePart implements java.io.Serializable
{

	private long id;
	private Integer version;
	private Position position;
	private boolean finished;
	private boolean active;
	private boolean opened;
	private Date executedAt;
	private List<TreePart> successors = new ArrayList<TreePart>(0);
	private Marker marker;
	private long questInstanceId;
	private TreePartType type;
	private boolean highlightedInvisibeMarker;

	public TreePart()
	{
	}

	public TreePart(Position position, boolean finished, boolean active, boolean opened, Date executedAt,
		List<TreePart> successors, Marker marker, long questInstanceId, TreePartType type,
		boolean highlightedInvisibeMarker)
	{
		this.position = position;
		this.finished = finished;
		this.active = active;
		this.opened = opened;
		this.executedAt = executedAt;
		this.successors = successors;
		this.marker = marker;
		this.questInstanceId = questInstanceId;
		this.type = type;
		this.highlightedInvisibeMarker = highlightedInvisibeMarker;
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
	public Position getPosition()
	{
		return this.position;
	}

	public void setPosition(Position position)
	{
		this.position = position;
	}
	public boolean isFinished()
	{
		return this.finished;
	}

	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}
	public boolean isActive()
	{
		return this.active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}
	public boolean isOpened()
	{
		return this.opened;
	}

	public void setOpened(boolean opened)
	{
		this.opened = opened;
	}
	public Date getExecutedAt()
	{
		return this.executedAt;
	}

	public void setExecutedAt(Date executedAt)
	{
		this.executedAt = executedAt;
	}
	public List<TreePart> getSuccessors()
	{
		return this.successors;
	}

	public void setSuccessors(List<TreePart> successors)
	{
		this.successors = successors;
	}
	public Marker getMarker()
	{
		return this.marker;
	}

	public void setMarker(Marker marker)
	{
		this.marker = marker;
	}
	public long getQuestInstanceId()
	{
		return this.questInstanceId;
	}

	public void setQuestInstanceId(long questInstanceId)
	{
		this.questInstanceId = questInstanceId;
	}
	public TreePartType getType()
	{
		return this.type;
	}

	public void setType(TreePartType type)
	{
		this.type = type;
	}
	public boolean isHighlightedInvisibeMarker()
	{
		return this.highlightedInvisibeMarker;
	}

	public void setHighlightedInvisibeMarker(boolean highlightedInvisibeMarker)
	{
		this.highlightedInvisibeMarker = highlightedInvisibeMarker;
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
		buffer.append("finished").append("='").append(isFinished()).append("' ");
		buffer.append("active").append("='").append(isActive()).append("' ");
		buffer.append("opened").append("='").append(isOpened()).append("' ");
		buffer.append("executedAt").append("='").append(getExecutedAt()).append("' ");
		buffer.append("questInstanceId").append("='").append(getQuestInstanceId()).append("' ");
		buffer.append("highlightedInvisibeMarker").append("='").append(isHighlightedInvisibeMarker()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}

}
