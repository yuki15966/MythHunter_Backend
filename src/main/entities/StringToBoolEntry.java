
package main.entities;

import java.io.Serializable;

public class StringToBoolEntry implements Serializable
{
	private boolean value;
	private String key;

	public StringToBoolEntry(boolean value, String key)
	{
		super();
		this.value = value;
		this.key = key;
	}
	public StringToBoolEntry()
	{

	}

	public boolean getValue()
	{
		return value;
	}
	public void setValue(boolean value)
	{
		this.value = value;
	}
	public String getKey()
	{
		return key;
	}
	public void setKey(String key)
	{
		this.key = key;
	}

}
