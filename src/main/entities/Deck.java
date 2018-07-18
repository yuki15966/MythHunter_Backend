
package main.entities;

import gen.entities.DbDeck;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Deck implements Serializable
{
	private long id;
	private String name;
	private List<LongToIntEntry> cardIds = new ArrayList<LongToIntEntry>();

	public Deck()
	{
	}

	public Deck(DbDeck deck)
	{
		this.id = deck.getId();
		this.name = deck.getName();
		for (Entry<Long, Integer> entry : deck.getCardIds().entrySet())
		{
			cardIds.add(new LongToIntEntry(entry.getValue(), entry.getKey()));
		}
	}

	public DbDeck asDbDeck()
	{

		Map<Long, Integer> cards = new HashMap<Long, Integer>();

		for (LongToIntEntry entry : this.cardIds)
		{
			cards.put(entry.getKey(), entry.getValue());
		}

		DbDeck deck = new DbDeck(name, cards);
		deck.setId(id);

		return deck;
	}

	public Deck(String name, List<LongToIntEntry> cardIds)
	{
		this.name = name;
		this.cardIds = cardIds;
	}

	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	public List<LongToIntEntry> getCardIds()
	{
		return this.cardIds;
	}

	public void setCardIds(List<LongToIntEntry> cardIds)
	{
		this.cardIds = cardIds;
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
		buffer.append("]");

		return buffer.toString();
	}
}
