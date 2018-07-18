
package gen.entities;

// Generated Jul 4, 2017 3:25:15 PM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DbUser generated by hbm2java
 */
public class DbUser implements java.io.Serializable
{

	private long id;
	private Integer version;
	private List<Long> activeQuestIds = new ArrayList<Long>(0);
	private List<Long> createdQuestIds = new ArrayList<Long>(0);
	private String name;
	private String password;
	private long money;
	private boolean rotateMap;
	private List<Long> solvedQuestIds = new ArrayList<Long>(0);
	private List<Long> deckIds = new ArrayList<Long>(0);
	private long taskCount;
	private long answeredQuestionsCount;
	private long foundLocationsCount;
	private long startedFightsCount;
	private long wonFightsCount;
	private double kmWalked;
	private boolean tutorialPlayed;
	private String creationTutorialFlags;
	private Map<Long, Integer> cardIds = new HashMap<Long, Integer>(0);
	private List<Long> createdCardIds = new ArrayList<Long>(0);

	public DbUser()
	{
	}

	public DbUser(List<Long> activeQuestIds, List<Long> createdQuestIds, String name, String password, long money,
		boolean rotateMap, List<Long> solvedQuestIds, List<Long> deckIds, long taskCount, long answeredQuestionsCount,
		long foundLocationsCount, long startedFightsCount, long wonFightsCount, double kmWalked,
		boolean tutorialPlayed, String creationTutorialFlags, Map<Long, Integer> cardIds, List<Long> createdCardIds)
	{
		this.activeQuestIds = activeQuestIds;
		this.createdQuestIds = createdQuestIds;
		this.name = name;
		this.password = password;
		this.money = money;
		this.rotateMap = rotateMap;
		this.solvedQuestIds = solvedQuestIds;
		this.deckIds = deckIds;
		this.taskCount = taskCount;
		this.answeredQuestionsCount = answeredQuestionsCount;
		this.foundLocationsCount = foundLocationsCount;
		this.startedFightsCount = startedFightsCount;
		this.wonFightsCount = wonFightsCount;
		this.kmWalked = kmWalked;
		this.tutorialPlayed = tutorialPlayed;
		this.creationTutorialFlags = creationTutorialFlags;
		this.cardIds = cardIds;
		this.createdCardIds = createdCardIds;
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
	public List<Long> getActiveQuestIds()
	{
		return this.activeQuestIds;
	}

	public void setActiveQuestIds(List<Long> activeQuestIds)
	{
		this.activeQuestIds = activeQuestIds;
	}
	public List<Long> getCreatedQuestIds()
	{
		return this.createdQuestIds;
	}

	public void setCreatedQuestIds(List<Long> createdQuestIds)
	{
		this.createdQuestIds = createdQuestIds;
	}
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	public long getMoney()
	{
		return this.money;
	}

	public void setMoney(long money)
	{
		this.money = money;
	}
	public boolean isRotateMap()
	{
		return this.rotateMap;
	}

	public void setRotateMap(boolean rotateMap)
	{
		this.rotateMap = rotateMap;
	}
	public List<Long> getSolvedQuestIds()
	{
		return this.solvedQuestIds;
	}

	public void setSolvedQuestIds(List<Long> solvedQuestIds)
	{
		this.solvedQuestIds = solvedQuestIds;
	}
	public List<Long> getDeckIds()
	{
		return this.deckIds;
	}

	public void setDeckIds(List<Long> deckIds)
	{
		this.deckIds = deckIds;
	}
	public long getTaskCount()
	{
		return this.taskCount;
	}

	public void setTaskCount(long taskCount)
	{
		this.taskCount = taskCount;
	}
	public long getAnsweredQuestionsCount()
	{
		return this.answeredQuestionsCount;
	}

	public void setAnsweredQuestionsCount(long answeredQuestionsCount)
	{
		this.answeredQuestionsCount = answeredQuestionsCount;
	}
	public long getFoundLocationsCount()
	{
		return this.foundLocationsCount;
	}

	public void setFoundLocationsCount(long foundLocationsCount)
	{
		this.foundLocationsCount = foundLocationsCount;
	}
	public long getStartedFightsCount()
	{
		return this.startedFightsCount;
	}

	public void setStartedFightsCount(long startedFightsCount)
	{
		this.startedFightsCount = startedFightsCount;
	}
	public long getWonFightsCount()
	{
		return this.wonFightsCount;
	}

	public void setWonFightsCount(long wonFightsCount)
	{
		this.wonFightsCount = wonFightsCount;
	}
	public double getKmWalked()
	{
		return this.kmWalked;
	}

	public void setKmWalked(double kmWalked)
	{
		this.kmWalked = kmWalked;
	}
	public boolean isTutorialPlayed()
	{
		return this.tutorialPlayed;
	}

	public void setTutorialPlayed(boolean tutorialPlayed)
	{
		this.tutorialPlayed = tutorialPlayed;
	}
	public String getCreationTutorialFlags()
	{
		return this.creationTutorialFlags;
	}

	public void setCreationTutorialFlags(String creationTutorialFlags)
	{
		this.creationTutorialFlags = creationTutorialFlags;
	}
	public Map<Long, Integer> getCardIds()
	{
		return this.cardIds;
	}

	public void setCardIds(Map<Long, Integer> cardIds)
	{
		this.cardIds = cardIds;
	}
	public List<Long> getCreatedCardIds()
	{
		return this.createdCardIds;
	}

	public void setCreatedCardIds(List<Long> createdCardIds)
	{
		this.createdCardIds = createdCardIds;
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
		buffer.append("password").append("='").append(getPassword()).append("' ");
		buffer.append("money").append("='").append(getMoney()).append("' ");
		buffer.append("rotateMap").append("='").append(isRotateMap()).append("' ");
		buffer.append("taskCount").append("='").append(getTaskCount()).append("' ");
		buffer.append("answeredQuestionsCount").append("='").append(getAnsweredQuestionsCount()).append("' ");
		buffer.append("foundLocationsCount").append("='").append(getFoundLocationsCount()).append("' ");
		buffer.append("startedFightsCount").append("='").append(getStartedFightsCount()).append("' ");
		buffer.append("wonFightsCount").append("='").append(getWonFightsCount()).append("' ");
		buffer.append("kmWalked").append("='").append(getKmWalked()).append("' ");
		buffer.append("tutorialPlayed").append("='").append(isTutorialPlayed()).append("' ");
		buffer.append("creationTutorialFlags").append("='").append(getCreationTutorialFlags()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}

}