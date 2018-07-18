
package main.entities;

import gen.entities.DbAction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Action implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private List<StringToStringEntry> names = new ArrayList<StringToStringEntry>();
	private List<StringToStringEntry> descriptions = new ArrayList<StringToStringEntry>();
	private float starCosts;
	private Integer duration;
	private float amount;
	private boolean isMultipier;
	private CardType forCardType;
	private ActionType type;
	private Integer lifeCosts;
	private Integer maxUsage;
	private boolean hasMaxUsage;

	public Action()
	{

	}

	public Action(DbAction action)
	{
		this.id = action.getId();
		this.starCosts = action.getStarCosts();
		this.duration = action.getDuration();
		this.amount = action.getAmount();
		this.isMultipier = action.isIsMultipier();
		this.forCardType = action.getForCardType();
		this.type = action.getType();
		this.lifeCosts = action.getLifeCosts();
		this.maxUsage = action.getMaxUsage();
		this.hasMaxUsage = action.isHasMaxUsage();
		this.names = new ArrayList<StringToStringEntry>();
		for (Entry<String, String> entry : action.getNames().entrySet())
		{
			names.add(new StringToStringEntry(entry.getValue(), entry.getKey()));
		}
		this.descriptions = new ArrayList<StringToStringEntry>();
		for (Entry<String, String> entry : action.getDescriptions().entrySet())
		{
			descriptions.add(new StringToStringEntry(entry.getValue(), entry.getKey()));
		}
	}

	public DbAction asDbAction()
	{
		Map<String, String> namesMap = new HashMap<String, String>();
		if (names != null)
		{
			for (StringToStringEntry entry : names)
			{
				namesMap.put(entry.getKey(), entry.getValue());
			}
		}

		Map<String, String> descriptionsMap = new HashMap<String, String>();
		if (descriptions != null)
		{
			for (StringToStringEntry entry : descriptions)
			{
				descriptionsMap.put(entry.getKey(), entry.getValue());
			}
		}

		DbAction dbAction = new DbAction(namesMap, descriptionsMap, starCosts, duration, amount, isMultipier,
			forCardType, type, lifeCosts, maxUsage, hasMaxUsage);
		dbAction.setId(id);

		return dbAction;
	}

	public Action(long id, List<StringToStringEntry> names, List<StringToStringEntry> descriptions, float starCosts,
		Integer duration, float amount, boolean isMultipier, CardType forCardType, ActionType type, Integer lifeCosts,
		Integer maxUsage, boolean hasMaxUsage)
	{
		super();
		this.id = id;
		this.names = names;
		this.descriptions = descriptions;
		this.starCosts = starCosts;
		this.duration = duration;
		this.amount = amount;
		this.isMultipier = isMultipier;
		this.forCardType = forCardType;
		this.type = type;
		this.lifeCosts = lifeCosts;
		this.maxUsage = maxUsage;
		this.hasMaxUsage = hasMaxUsage;
	}

	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public List<StringToStringEntry> getNames()
	{
		return names;
	}
	public void setNames(List<StringToStringEntry> names)
	{
		this.names = names;
	}
	public List<StringToStringEntry> getDescriptions()
	{
		return descriptions;
	}
	public void setDescriptions(List<StringToStringEntry> descriptions)
	{
		this.descriptions = descriptions;
	}
	public float getStarCosts()
	{
		return starCosts;
	}
	public void setStarCosts(float starCosts)
	{
		this.starCosts = starCosts;
	}
	public Integer getDuration()
	{
		return duration;
	}
	public void setDuration(Integer duration)
	{
		this.duration = duration;
	}
	public float getAmount()
	{
		return amount;
	}
	public void setAmount(float amount)
	{
		this.amount = amount;
	}
	public boolean isMultipier()
	{
		return isMultipier;
	}
	public void setMultipier(boolean isMultipier)
	{
		this.isMultipier = isMultipier;
	}
	public CardType getForCardType()
	{
		return forCardType;
	}
	public void setForCardType(CardType forCardType)
	{
		this.forCardType = forCardType;
	}
	public ActionType getType()
	{
		return type;
	}
	public void setType(ActionType type)
	{
		this.type = type;
	}
	public Integer getLifeCosts()
	{
		return lifeCosts;
	}
	public void setLifeCosts(Integer lifeCosts)
	{
		this.lifeCosts = lifeCosts;
	}
	public Integer getMaxUsage()
	{
		return maxUsage;
	}
	public void setMaxUsage(Integer maxUsage)
	{
		this.maxUsage = maxUsage;
	}
	public boolean isHasMaxUsage()
	{
		return hasMaxUsage;
	}
	public void setHasMaxUsage(boolean hasMaxUsage)
	{
		this.hasMaxUsage = hasMaxUsage;
	}
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("id").append("='").append(getId()).append("' ");
		buffer.append("starCosts").append("='").append(getStarCosts()).append("' ");
		buffer.append("duration").append("='").append(getDuration()).append("' ");
		buffer.append("amount").append("='").append(getAmount()).append("' ");
		buffer.append("isMultipier").append("='").append(isMultipier()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}
}
