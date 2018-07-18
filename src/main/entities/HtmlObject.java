
package main.entities;

import gen.entities.DbHtmlObject;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

public class HtmlObject implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String html;
	private List<StringToBoolEntry> answers;

	public HtmlObject()
	{

	}

	public HtmlObject(long id, String html, List<StringToBoolEntry> answers)
	{
		super();
		this.id = id;
		this.html = html;
		this.answers = answers;
	}

	public HtmlObject(DbHtmlObject htmlObject)
	{
		this.id = htmlObject.getId();
		try
		{
			this.html = htmlObject.getHtml().getSubString(1, (int) htmlObject.getHtml().length());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.print(e.getMessage());
		}

		this.answers = new ArrayList<StringToBoolEntry>();
		for (Entry<String, Boolean> entry : htmlObject.getAnswers().entrySet())
		{
			this.answers.add(new StringToBoolEntry(entry.getValue(), entry.getKey()));
		}
	}

	public DbHtmlObject asDbHtmlObject() throws SerialException, SQLException
	{
		Clob clob = new SerialClob(html.toCharArray());
		Map<String, Boolean> answersMap = new HashMap<String, Boolean>();

		if (answers != null)
		{
			for (StringToBoolEntry entry : answers)
			{
				answersMap.put(entry.getKey(), entry.getValue());
			}
		}

		DbHtmlObject html = new DbHtmlObject(clob, answersMap);
		html.setId(id);
		return html;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getHtml()
	{
		return html;
	}

	public void setHtml(String html)
	{
		this.html = html;
	}

	public List<StringToBoolEntry> getAnswers()
	{
		return answers;
	}

	public void setAnswers(List<StringToBoolEntry> answers)
	{
		this.answers = answers;
	}

	@Override
	public String toString()
	{
		return "HtmlObject [id=" + id + ", html=" + html + "]";
	}

}
