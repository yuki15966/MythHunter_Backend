
package main.entities;

import java.io.Serializable;

public class StringToStringEntry implements Serializable
{
	private String value;
	private String key;

	public StringToStringEntry(String value, String key)
	{
		super();
		this.value = value;
		this.key = key;
	}
	public StringToStringEntry()
	{

	}

	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
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
