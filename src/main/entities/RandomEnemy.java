
package main.entities;

import gen.entities.DbRandomEnemy;
import gen.entities.MapPosition;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class RandomEnemy extends Enemy
{
	private MapPosition position;
	private List<Long> completedByUserIds = new ArrayList<Long>(0);

	public RandomEnemy()
	{

	}
	public RandomEnemy(DbRandomEnemy enemy)
	{
		super(enemy);
		position = enemy.getPosition();
		completedByUserIds.addAll(enemy.getCompletedByUserIds());
	}
	public MapPosition getPosition()
	{
		return position;
	}
	public void setPosition(MapPosition position)
	{
		this.position = position;
	}
	@XmlElement(required = true)
	public List<Long> getCompletedByUserIds()
	{
		return completedByUserIds;
	}
	public void setCompletedByUserIds(List<Long> completedByUserIds)
	{
		this.completedByUserIds = completedByUserIds;
	}

}
