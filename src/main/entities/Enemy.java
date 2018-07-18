
package main.entities;

import gen.entities.DbEnemy;
import gen.entities.DbRandomEnemy;
import java.io.Serializable;

public class Enemy implements Serializable
{
	private long id;
	private String name;
	private String description;
	private long deckId;
	private String imageUrl;
	private boolean randomEnemy;

	public Enemy()
	{
	}

	public Enemy(DbEnemy enemy)
	{
		this.id = enemy.getId();
		this.name = enemy.getName();
		this.description = enemy.getDescription();
		this.imageUrl = enemy.getImageUrl();
		this.setDeckId(enemy.getDeckId());
		this.randomEnemy = enemy.isRandomEnemy();
	}

	public Enemy(DbRandomEnemy enemy)
	{
		this.id = enemy.getId();
		this.name = enemy.getEnemy().getName();
		this.description = enemy.getEnemy().getDescription();
		this.imageUrl = enemy.getEnemy().getImageUrl();
		this.setDeckId(enemy.getEnemy().getDeckId());
		this.randomEnemy = true;
	}

	public DbEnemy asDbEnemy()
	{
		DbEnemy enemy = new DbEnemy(imageUrl, randomEnemy, name, description, deckId);
		enemy.setId(id);

		return enemy;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public long getDeckId()
	{
		return deckId;
	}

	public void setDeckId(long deckId)
	{
		this.deckId = deckId;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public boolean isRandomEnemy()
	{
		return randomEnemy;
	}

	public void setRandomEnemy(boolean randomEnemy)
	{
		this.randomEnemy = randomEnemy;
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
		buffer.append("description").append("='").append(getDescription()).append("' ");
		buffer.append("imageUrl").append("='").append(getImageUrl()).append("' ");
		buffer.append("randomEnemy").append("='").append(isRandomEnemy()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}
}
