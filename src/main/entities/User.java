
package main.entities;

import gen.entities.DbUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class User implements Serializable
{
	private long id;
	private List<Long> activeQuestIds = new ArrayList<Long>(0);
	private List<Long> createdQuestIds = new ArrayList<Long>(0);
	private String name;
	private String password;
	private List<Long> solvedQuestIds = new ArrayList<Long>(0);
	private List<Long> deckIds = new ArrayList<Long>(0);
	private long taskCount;
	private long answeredQuestionsCount;
	private long foundLocationsCount;
	private long wonFightsCount;
	private long startedFightsCount;
	private long money;
	private double kmWalked;
	private List<LongToIntEntry> cardIds = new ArrayList<LongToIntEntry>();
	private List<Long> createdCardIds = new ArrayList<Long>(0);
	private boolean tutorialPlayed;
	private String creationTutorialFlags;
	private boolean rotateMap;

	public User()
	{
	}

	public User(DbUser user)
	{
		this.id = user.getId();
		this.activeQuestIds = user.getActiveQuestIds();
		this.createdQuestIds = user.getCreatedQuestIds();
		this.name = user.getName();
		this.password = user.getPassword();
		this.solvedQuestIds = user.getSolvedQuestIds();
		this.deckIds = user.getDeckIds();
		this.taskCount = user.getTaskCount();
		this.answeredQuestionsCount = user.getAnsweredQuestionsCount();
		this.foundLocationsCount = user.getFoundLocationsCount();
		this.wonFightsCount = user.getWonFightsCount();
		this.startedFightsCount = user.getStartedFightsCount();
		this.kmWalked = user.getKmWalked();
		this.money = user.getMoney();
		for (Entry<Long, Integer> entry : user.getCardIds().entrySet())
		{
			cardIds.add(new LongToIntEntry(entry.getValue(), entry.getKey()));
		}
		this.createdCardIds = user.getCreatedCardIds();
		this.tutorialPlayed = user.isTutorialPlayed();
		this.creationTutorialFlags = user.getCreationTutorialFlags();
		this.rotateMap = user.isRotateMap();
	}

	public DbUser asDbUser()
	{
		Map<Long, Integer> cards = new HashMap<Long, Integer>();

		for (LongToIntEntry entry : this.cardIds)
		{
			cards.put(entry.getKey(), entry.getValue());
		}
		DbUser user = new DbUser(activeQuestIds, createdQuestIds, name, password, money, rotateMap, solvedQuestIds,
			deckIds, taskCount, answeredQuestionsCount, foundLocationsCount, startedFightsCount, wonFightsCount,
			kmWalked, tutorialPlayed, creationTutorialFlags, cards, createdCardIds);
		user.setId(id);

		return user;
	}

	public User(long id, List<Long> activeQuestIds, List<Long> createdQuestIds, String name, String password,
		List<Long> solvedQuestIds, List<Long> deckIds, long taskCount, long answeredQuestionsCount,
		long foundLocationsCount, long wonFightsCount, long startedFightsCount, long money, double kmWalked,
		List<LongToIntEntry> cardIds, List<Long> createdCardIds, boolean tutorialPlayed, String creationTutorialFlags)
	{
		super();
		this.id = id;
		this.activeQuestIds = activeQuestIds;
		this.createdQuestIds = createdQuestIds;
		this.name = name;
		this.password = password;
		this.solvedQuestIds = solvedQuestIds;
		this.deckIds = deckIds;
		this.taskCount = taskCount;
		this.answeredQuestionsCount = answeredQuestionsCount;
		this.foundLocationsCount = foundLocationsCount;
		this.wonFightsCount = wonFightsCount;
		this.startedFightsCount = startedFightsCount;
		this.money = money;
		this.kmWalked = kmWalked;
		this.cardIds = cardIds;
		this.createdCardIds = createdCardIds;
		this.tutorialPlayed = tutorialPlayed;
		this.creationTutorialFlags = creationTutorialFlags;
	}

	public boolean isTutorialPlayed()
	{
		return tutorialPlayed;
	}

	public void setTutorialPlayed(boolean tutorialPlayed)
	{
		this.tutorialPlayed = tutorialPlayed;
	}

	public String getCreationTutorialFlags()
	{
		return creationTutorialFlags;
	}

	public void setCreationTutorialFlags(String creationTutorialFlags)
	{
		this.creationTutorialFlags = creationTutorialFlags;
	}

	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public boolean isRotateMap()
	{
		return rotateMap;
	}

	public void setRotateMap(boolean rotateMap)
	{
		this.rotateMap = rotateMap;
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
	public List<LongToIntEntry> getCardIds()
	{
		return this.cardIds;
	}

	public void setCardIds(List<LongToIntEntry> cardIds)
	{
		this.cardIds = cardIds;
	}

	public long getMoney()
	{
		return money;
	}

	public void setMoney(long money)
	{
		this.money = money;
	}

	public List<Long> getCreatedCardIds()
	{
		return createdCardIds;
	}

	public void setCreatedCardIds(List<Long> createdCardIds)
	{
		this.createdCardIds = createdCardIds;
	}

	public long getStartedFightsCount()
	{
		return startedFightsCount;
	}

	public void setStartedFightsCount(long startedFightsCount)
	{
		this.startedFightsCount = startedFightsCount;
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("id").append("='").append(getId()).append("' ");
		buffer.append("name").append("='").append(getName()).append("' ");
		buffer.append("password").append("='").append(getPassword()).append("' ");
		buffer.append("taskCount").append("='").append(getTaskCount()).append("' ");
		buffer.append("answeredQuestionsCount").append("='").append(getAnsweredQuestionsCount()).append("' ");
		buffer.append("foundLocationsCount").append("='").append(getFoundLocationsCount()).append("' ");
		buffer.append("startedFightsCount").append("='").append(getStartedFightsCount()).append("' ");
		buffer.append("wonFightsCount").append("='").append(getWonFightsCount()).append("' ");
		buffer.append("kmWalked").append("='").append(getKmWalked()).append("' ");
		buffer.append("money").append("='").append(getMoney()).append("' ");
		buffer.append("cardCount").append("='").append(getCardIds().size()).append("' ");
		buffer.append("deckCount").append("='").append(getDeckIds().size()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}

}
