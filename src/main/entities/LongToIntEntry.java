
package main.entities;

import java.io.Serializable;

public class LongToIntEntry implements Serializable
{
	private int value;
	private long key;

	public LongToIntEntry()
	{

	}

	public LongToIntEntry(int value, long key)
	{
		super();
		this.value = value;
		this.key = key;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	public long getKey()
	{
		return key;
	}

	public void setKey(long key)
	{
		this.key = key;
	}

}
