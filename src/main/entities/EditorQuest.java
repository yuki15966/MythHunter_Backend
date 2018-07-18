
package main.entities;

import gen.entities.Marker;
import gen.entities.MarkerHome;
import gen.entities.Quest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EditorQuest implements Serializable
{
	private long id;
	private Integer version;
	private String name;
	private String shortDescription;
	private long startMarkerId;
	private long htmlId;
	private long createrId;
	private boolean approved;
	private boolean submitted;
	private long treeRootId;
	private List<Long> specialCards = new ArrayList<Long>(0);
	private DifficultyLevel difficulty;
	private Float qualityRating;
	private Float difficultyRating;

	public EditorQuest()
	{

	}

	public EditorQuest(Quest quest)
	{
		this.id = quest.getId();
		this.version = quest.getVersion();
		this.name = quest.getName();
		this.shortDescription = quest.getShortDescription();
		this.startMarkerId = quest.getStartMarker().getId();
		this.htmlId = quest.getHtmlId();
		this.createrId = quest.getCreaterId();
		this.approved = quest.isApproved();
		this.submitted = quest.isSubmitted();
		this.treeRootId = quest.getTreeRootId();
		this.specialCards = quest.getSpecialCards();
		this.difficulty = DifficultyLevel.valueOf(quest.getDifficulty());
		this.qualityRating = quest.getQualityRating();
		this.difficultyRating = quest.getDifficultyRating();

	}

	public Quest asQuest(MarkerHome markerDao)
	{
		Marker startMarker = markerDao.findById(startMarkerId);
		Quest quest = new Quest(name, shortDescription, difficulty.name(), qualityRating, difficultyRating,
			startMarker, htmlId, createrId, approved, submitted, treeRootId, specialCards);
		quest.setId(id);
		return quest;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Integer getVersion()
	{
		return version;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getShortDescription()
	{
		return shortDescription;
	}

	public void setShortDescription(String shortDescription)
	{
		this.shortDescription = shortDescription;
	}

	public long getStartMarkerId()
	{
		return startMarkerId;
	}

	public void setStartMarkerId(long startMarkerId)
	{
		this.startMarkerId = startMarkerId;
	}

	public long getHtmlId()
	{
		return htmlId;
	}

	public void setHtmlId(long htmlId)
	{
		this.htmlId = htmlId;
	}

	public long getCreaterId()
	{
		return createrId;
	}

	public void setCreaterId(long createrId)
	{
		this.createrId = createrId;
	}

	public boolean isApproved()
	{
		return approved;
	}

	public void setApproved(boolean approved)
	{
		this.approved = approved;
	}

	public boolean isSubmitted()
	{
		return submitted;
	}

	public void setSubmitted(boolean submitted)
	{
		this.submitted = submitted;
	}

	public long getTreeRootId()
	{
		return treeRootId;
	}

	public void setTreeRootId(long treeRootId)
	{
		this.treeRootId = treeRootId;
	}

	public List<Long> getSpecialCards()
	{
		return specialCards;
	}

	public void setSpecialCards(List<Long> specialCards)
	{
		this.specialCards = specialCards;
	}

	public void setQualityRating(Float qualityRating)
	{
		this.qualityRating = qualityRating;
	}

	public void setDifficultyRating(Float difficultyRating)
	{
		this.difficultyRating = difficultyRating;
	}

	public DifficultyLevel getDifficulty()
	{
		return difficulty;
	}

	public void setDifficulty(DifficultyLevel difficulty)
	{
		this.difficulty = difficulty;
	}

	public float getQualityRating()
	{
		return qualityRating;
	}

	public void setQualityRating(float qualityRating)
	{
		this.qualityRating = qualityRating;
	}

	public float getDifficultyRating()
	{
		return difficultyRating;
	}

	public void setDifficultyRating(float difficultyRating)
	{
		this.difficultyRating = difficultyRating;
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("id").append("='").append(getId()).append("' ");
		buffer.append("name").append("='").append(getName()).append("' ");
		buffer.append("shortDescription").append("='").append(getShortDescription()).append("' ");
		buffer.append("htmlId").append("='").append(getHtmlId()).append("' ");
		buffer.append("startMarkerId").append("='").append(getStartMarkerId()).append("' ");
		buffer.append("createrId").append("='").append(getCreaterId()).append("' ");
		buffer.append("approved").append("='").append(isApproved()).append("' ");
		buffer.append("submitted").append("='").append(isSubmitted()).append("' ");
		buffer.append("treeRootId").append("='").append(getTreeRootId()).append("' ");
		buffer.append("difficulty").append("='").append(getDifficulty()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}
}
