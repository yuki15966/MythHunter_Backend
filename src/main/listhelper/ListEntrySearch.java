package main.listhelper;

import java.util.List;

public class ListEntrySearch<V>
{
	public ListEntrySearchResult<V> FindFirst(List<V> list, IListEntryPicker<V> picker)
	{
		for (int index = 0; index < list.size(); index++) 
		{
			V element = list.get(index);
			if (picker.WannaPick(element))
			{
				return ListEntrySearchResult.createPair(index, element);
			}
		}
		
		return null;
	}
}
