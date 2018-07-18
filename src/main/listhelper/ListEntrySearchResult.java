package main.listhelper;

public class ListEntrySearchResult<V> 
{
   private final int index;
   private final V element;

   public static <V> ListEntrySearchResult<V> createPair(int index, V element) {
       return new ListEntrySearchResult<V>(index, element);
   }
   
	public ListEntrySearchResult(int index, V element) {
	    this.index = index;
	    this.element = element;
	}
	
	public int getIndex() {
	    return index;
	}
	
	public V getElement() {
	    return element;
	}
}