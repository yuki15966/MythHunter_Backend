
package gen.entities;

// Generated Jul 4, 2017 3:25:15 PM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.List;
import main.entities.CardType;

/**
 * Card generated by hbm2java
 */
public class Card implements java.io.Serializable
{

	private long id;
	private Integer version;
	private String name;
	private Integer stars;
	private long imageId;
	private String description;
	private List<Long> actionIds = new ArrayList<Long>(0);
	private Integer life;
	private Integer attack;
	private CardType type;

	public Card()
	{
	}

	public Card(String name, Integer stars, long imageId, String description, List<Long> actionIds, Integer life,
		Integer attack, CardType type)
	{
		this.name = name;
		this.stars = stars;
		this.imageId = imageId;
		this.description = description;
		this.actionIds = actionIds;
		this.life = life;
		this.attack = attack;
		this.type = type;
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
	public Integer getStars()
	{
		return this.stars;
	}

	public void setStars(Integer stars)
	{
		this.stars = stars;
	}
	public long getImageId()
	{
		return this.imageId;
	}

	public void setImageId(long imageId)
	{
		this.imageId = imageId;
	}
	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
	public List<Long> getActionIds()
	{
		return this.actionIds;
	}

	public void setActionIds(List<Long> actionIds)
	{
		this.actionIds = actionIds;
	}
	public Integer getLife()
	{
		return this.life;
	}

	public void setLife(Integer life)
	{
		this.life = life;
	}
	public Integer getAttack()
	{
		return this.attack;
	}

	public void setAttack(Integer attack)
	{
		this.attack = attack;
	}
	public CardType getType()
	{
		return this.type;
	}

	public void setType(CardType type)
	{
		this.type = type;
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
		buffer.append("stars").append("='").append(getStars()).append("' ");
		buffer.append("imageId").append("='").append(getImageId()).append("' ");
		buffer.append("description").append("='").append(getDescription()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}

}