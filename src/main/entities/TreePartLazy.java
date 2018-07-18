
package main.entities;

import gen.entities.Position;
import gen.entities.TreePart;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class TreePartLazy implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	long id;
	boolean finished;
	boolean active;
	boolean opened;
	Date executedAt;
	Long markerId = null;
	long questInstanceId;
	List<Long> successorIds;
	TreePartType type;
	private Position position;
	boolean highlightedInvisibeMarker;

	public TreePartLazy(TreePart treePart)
	{
		this.id = treePart.getId();
		this.finished = treePart.isFinished();
		this.active = treePart.isActive();
		this.successorIds = new ArrayList<Long>();
		for (TreePart successor : treePart.getSuccessors())
		{
			successorIds.add(successor.getId());
		}

		this.type = treePart.getType();
		if (this.type == TreePartType.Marker)
		{
			this.markerId = treePart.getMarker().getId();
		}
		this.questInstanceId = treePart.getQuestInstanceId();
		this.opened = treePart.isOpened();
		this.executedAt = treePart.getExecutedAt();
		this.highlightedInvisibeMarker = treePart.isHighlightedInvisibeMarker();
		this.position = treePart.getPosition();
	}

	public TreePartLazy()
	{

	}

	@XmlElement(required = true)
	public boolean isFinished()
	{
		return finished;
	}

	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}
	@XmlElement(required = true)
	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}
	@XmlElement(required = true)
	public Long getMarkerId()
	{
		return markerId;
	}

	public void setMarkerId(Long markerId)
	{
		this.markerId = markerId;
	}
	@XmlElement(required = true)
	public long getQuestInstanceId()
	{
		return questInstanceId;
	}

	public Position getPosition()
	{
		return position;
	}

	public void setPosition(Position position)
	{
		this.position = position;
	}

	public void setQuestInstanceId(long questInstanceId)
	{
		this.questInstanceId = questInstanceId;
	}
	@XmlElement(required = true)
	public List<Long> getSuccessorIds()
	{
		return successorIds;
	}

	public void setSuccessorIds(List<Long> successorIds)
	{
		this.successorIds = successorIds;
	}
	@XmlElement(required = true)
	public TreePartType getType()
	{
		return type;
	}

	public void setType(TreePartType type)
	{
		this.type = type;
	}
	@XmlElement(required = true)
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	@XmlElement(required = true)
	public boolean isOpened()
	{
		return opened;
	}

	public void setOpened(boolean opened)
	{
		this.opened = opened;
	}

	public Date getExecutedAt()
	{
		return executedAt;
	}

	public void setExecutedAt(Date executedAt)
	{
		this.executedAt = executedAt;
	}

	@XmlElement(required = true)
	public boolean isHighlightedInvisibeMarker()
	{
		return highlightedInvisibeMarker;
	}

	public void setHighlightedInvisibeMarker(boolean highlightedInvisibeMarker)
	{
		this.highlightedInvisibeMarker = highlightedInvisibeMarker;
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("id").append("='").append(getId()).append("' ");
		buffer.append("finished").append("='").append(isFinished()).append("' ");
		buffer.append("active").append("='").append(isActive()).append("' ");
		buffer.append("opened").append("='").append(isOpened()).append("' ");
		buffer.append("executedAt").append("='").append(getExecutedAt()).append("' ");
		buffer.append("markerId").append("='").append(getMarkerId()).append("' ");
		buffer.append("questInstanceId").append("='").append(getQuestInstanceId()).append("' ");
		buffer.append("type").append("='").append(getType()).append("' ");
		buffer.append("highlightedInvisibeMarker").append("='").append(isHighlightedInvisibeMarker()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}
}
