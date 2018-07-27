
package gen.entities;

// Generated Jul 27, 2018 10:14:50 AM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.List;

/**
 * Quest generated by hbm2java
 */
public class Quest implements java.io.Serializable
{

	private long id;
	private Integer version;
	private String name;
	private String shortDescription;
	private String difficulty;
	private Float qualityRating;
	private Float difficultyRating;
	private Marker startMarker;
	private long htmlId;
	private long createrId;
	private boolean approved;
	private boolean submitted;
	private long treeRootId;
	private List<Long> specialCards = new ArrayList<Long>(0);

	public Quest()
	{
	}

	public Quest(String difficulty, long htmlId)
	{
		this.difficulty = difficulty;
		this.htmlId = htmlId;
	}
	public Quest(String name, String shortDescription, String difficulty, Float qualityRating, Float difficultyRating,
		Marker startMarker, long htmlId, long createrId, boolean approved, boolean submitted, long treeRootId,
		List<Long> specialCards)
	{
		this.name = name;
		this.shortDescription = shortDescription;
		this.difficulty = difficulty;
		this.qualityRating = qualityRating;
		this.difficultyRating = difficultyRating;
		this.startMarker = startMarker;
		this.htmlId = htmlId;
		this.createrId = createrId;
		this.approved = approved;
		this.submitted = submitted;
		this.treeRootId = treeRootId;
		this.specialCards = specialCards;
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
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	public String getShortDescription()
	{
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription)
	{
		this.shortDescription = shortDescription;
	}
	public String getDifficulty()
	{
		return this.difficulty;
	}

	public void setDifficulty(String difficulty)
	{
		this.difficulty = difficulty;
	}
	public Float getQualityRating()
	{
		return this.qualityRating;
	}

	public void setQualityRating(Float qualityRating)
	{
		this.qualityRating = qualityRating;
	}
	public Float getDifficultyRating()
	{
		return this.difficultyRating;
	}

	public void setDifficultyRating(Float difficultyRating)
	{
		this.difficultyRating = difficultyRating;
	}
	public Marker getStartMarker()
	{
		return this.startMarker;
	}

	public void setStartMarker(Marker startMarker)
	{
		this.startMarker = startMarker;
	}
	public long getHtmlId()
	{
		return this.htmlId;
	}

	public void setHtmlId(long htmlId)
	{
		this.htmlId = htmlId;
	}
	public long getCreaterId()
	{
		return this.createrId;
	}

	public void setCreaterId(long createrId)
	{
		this.createrId = createrId;
	}
	public boolean isApproved()
	{
		return this.approved;
	}

	public void setApproved(boolean approved)
	{
		this.approved = approved;
	}
	public boolean isSubmitted()
	{
		return this.submitted;
	}

	public void setSubmitted(boolean submitted)
	{
		this.submitted = submitted;
	}
	public long getTreeRootId()
	{
		return this.treeRootId;
	}

	public void setTreeRootId(long treeRootId)
	{
		this.treeRootId = treeRootId;
	}
	public List<Long> getSpecialCards()
	{
		return this.specialCards;
	}

	public void setSpecialCards(List<Long> specialCards)
	{
		this.specialCards = specialCards;
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
		buffer.append("name").append("='").append(getName()).append("' ");
		buffer.append("shortDescription").append("='").append(getShortDescription()).append("' ");
		buffer.append("difficulty").append("='").append(getDifficulty()).append("' ");
		buffer.append("qualityRating").append("='").append(getQualityRating()).append("' ");
		buffer.append("difficultyRating").append("='").append(getDifficultyRating()).append("' ");
		buffer.append("htmlId").append("='").append(getHtmlId()).append("' ");
		buffer.append("createrId").append("='").append(getCreaterId()).append("' ");
		buffer.append("approved").append("='").append(isApproved()).append("' ");
		buffer.append("submitted").append("='").append(isSubmitted()).append("' ");
		buffer.append("treeRootId").append("='").append(getTreeRootId()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}

}
