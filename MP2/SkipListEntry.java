

public class SkipListEntry <E extends Comparable<E>>
{  
  public E key;
  public SkipListEntry[] next;  
  public int[] width;    
  
  public SkipListEntry(E k, int level){ 
     key = k;     
     next=new SkipListEntry[level+1];     
     width=new int[level+1];
     for(int x:width)x=1;
     for(SkipListEntry x:next)x=null;
  }  
  
  public boolean equals(SkipListEntry o){	    
	    return this.key==o.key;
  }

  
}