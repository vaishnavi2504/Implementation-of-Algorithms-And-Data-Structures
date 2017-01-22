/** Implementation of SkipLists
 *  @author Vaishnavi Bhat
 *Date 11/04/2016 
 * 
 */


import java.util.*;

//public class SkipListImpl <E extends Comparable<? super E>> implements Iterable<E>{
public class SkipListImpl<E extends Comparable<? super E>> implements SkipList<E>,Iterable<E>{
//public class SkipListImpl<E> implements Iterable<E>,Comparable<E> {
  public SkipListEntry head;    // First element of the top level
  public SkipListEntry tail;    // Last element of the top level
  public SkipListEntry last;
  public int size; 		//Size of Skip list   
  public int maxLevel;
  
  public SkipListImpl(){          
     maxLevel=10;    
     head=new SkipListEntry(0,maxLevel-1);     
     tail=new SkipListEntry(Long.MAX_VALUE,maxLevel-1); 
     last=head;
     for(int i=0;i<maxLevel;i++){    	
    	 head.next[i]=tail;
     }     
     size = 0;          
  }
  
  public Iterator<E> iterator() { 
  	return new SKipListIterator<>(head); 
  }
  
  private class SKipListIterator<E> implements Iterator<E> {
		SkipListEntry<Integer> cursor, prev;
		
		//Constructor to initialize current and prev pointers
		SKipListIterator(SkipListEntry<Integer> head) {
		    cursor = head;		   
		}
	
		public boolean hasNext() {
		    return cursor.next[0] != tail;
		}
		
		public E next() {
		    E ans= (E)cursor.next[0].key;
		    cursor=cursor.next[0];
		    return ans;
		}
  }
  
  //True heads; False tails
  public static boolean coinFlip(){
    	Random rand = new Random(); 
    	int value = rand.nextInt(2);
    	if(value==0)return true;
    	else return false;
  }
  
  //This method decides the number of levels 
  //up to which a node needs to promoted
  //l=0 implies that a node is added only at the lowest level
  public int choice(int maxLevel){
	  int l=0;
	  while(l<maxLevel){
		  if(coinFlip())l++;
		  else break;
	  }
	  if(l==maxLevel)return l-1;
	  else return l;
  }
  
    
  //Checks if a given node is present in the skiplit
  public boolean contains(E x){
	  SkipListEntry[] a=find(x);	  
	  return a[0].next[0].key.compareTo(x)==0;
  }
  
  //Returns the size of the list
  public int size(){
	  return size;
  }	 
	  
  public E findIndex(int n){
	  SkipListEntry p=head.next[0];
	  int count=0;
	  while(count!=n&&p!=tail){
		  p=p.next[0];
		  count++;
	  }
	  return (E)p.key;
  }
  
  public E floor(E x){
	  if(head.next[0].key.compareTo(x)>0)return null;	  
	  SkipListEntry[] a=find(x);
	  if(a[0]==head)return null;
	  if(a[0].next[0].key.compareTo(x)==0)return x;
	  else return (E)(a[0].key);	  
  }
  
  //
  public E ceiling(E x){	
	  if(last.key.compareTo(x)<0)return null;
	  SkipListEntry[] a=find(x);
	  if(a[0].next[0]==tail)return null;
	  if(a[0].next[0].key.compareTo(x)==0)return x;
	  else return  (E)(a[0].next[0].key);	  
  }

  //Checks if the Skiplist is empty
  public boolean isEmpty(){ 
    return (size == 0); 
  }

  //Returns an array of pointers which has the address of  nodes at
  //every level where the search stops. It's either lower than or equal to the level of k
  SkipListEntry[] find(E k){	  
	  SkipListEntry[] prev=new SkipListEntry[maxLevel];	     
	  SkipListEntry p=head;		  
	  int i;
	     for(i=maxLevel-1;i>=0;i--){	    	   	 
	    	 while(p.next[i]!=tail&&(p.next[i].key).compareTo(k)<0){
	    		p=p.next[i];
	    	 }
	    	 prev[i]=p;
	     }    	    
     return prev;
  }	 
  
  E getnodeByPos(int index){
	  // -1 since nodes are 0 based 
	  int stepsSkipped = -1;
	  SkipListEntry current = head;
	  for(int i = maxLevel-1; i >= 0; i--) {
	    SkipListEntry nxt = current.next[i];	    
	    //When you reach the end of the level, you get down a level
	    if(nxt == tail){	      
	      continue;
	    }	    
	    //w contains width of the next node at level i
	    int w= nxt.width[i];
	    
	    //When you move little too ahead you step down a level
	    if(stepsSkipped + w > index){	      
	      continue;
	    }	    
	    current = nxt;	    
		while(current!=tail){
			stepsSkipped += current.width[i];
			  // We have reached the correct node
			  if(stepsSkipped== index){
				return (E)current.key;
			  }
			  nxt = current.next[i];
			  if(nxt == tail || nxt.width[i] + stepsSkipped > index){				
				break;
			  }
			  current = current.next[i];	    }
	  }
	  return null;
	}  
  
  public void rebuild(){
	  int k=(int)(Math.log(size)/Math.log(2));	  
	  SkipListEntry[]arr=new SkipListEntry[size];	
	  SkipListEntry temp=new SkipListEntry(0,k);
	  SkipListEntry dummyHead=new SkipListEntry(0,k);
	  SkipListEntry p=head.next[0];
	  
	  helperForRebuild(arr,0,arr.length-1,k);
	  
	  for(int i=0;i<arr.length;i++){
		  arr[i].key=p.key;
		  p=p.next[0];
	  }
	  for(int i=0;i<arr[0].next.length;i++){
		  temp.next[i]=arr[0];
		  dummyHead.next[i]=arr[0];	
	  }
	  for(int i=1;i<arr.length;i++){		  
		  arr[i-1].next[0]=arr[i];	
		  int len=arr[i].next.length;
		  for(int j=1;j<len;j++){
			  temp.next[j]=arr[i];	
			  if(dummyHead.next[j]==null)dummyHead.next[j]=arr[i];
		  }		  
	  }	  
	  head=dummyHead;  
  }
  
  public void helperForRebuild(SkipListEntry[] arr, int p, int r, int k){	   
	  if(p<=r){
		  if(k==0){
			  for(int i=p;i<=r;i++){
				  arr[i]=new SkipListEntry(null,0);
			  }			  
		  }else{			  
			  int q=(p+r)/2;
			  arr[q]=new SkipListEntry(0,k);
			  helperForRebuild(arr,p,q-1,k-1);
			  helperForRebuild(arr,q+1,r,k-1);
		  }
	  }  
  } 
  	   
  //Returns the first element of the list
    public E first(){	   
	   SKipListIterator<E> st=new SKipListIterator<E>(head);
	   if(st.hasNext())return (E)st.next();
	   else return (E)head.key;	   
    }  
    
   //Returns the last element
   //TO DO: Efficient traversal starting from the topmost level
    public E last(){    
		  return (E)last.key;    	
	}  
  
    //Adds node at the correct position at every level
    //Number of levels is determined by lev variable
	public boolean add (E k){
		SkipListEntry[] prev=find(k);		 
		if(prev[0].next[0].key.equals(k)){
			prev[0].next[0].key=k;
			return false;
		}
		else{
			int lev=choice(maxLevel);
			//Node to be inserted
			SkipListEntry n=new SkipListEntry(k,lev);	
			SkipListEntry nxt=head;
			//Inserting the node at the right position at all the levels
				for(int i=0;i<=lev;i++){
					nxt=prev[i].next[i];
					n.next[i]=nxt;					
					prev[i].next[i]=n;					
				}	
				n.width[0]=1;
				int count=1;
				for(int i=1;i<=lev;i++){
					count=1;
					SkipListEntry p=prev[i];
					SkipListEntry q=p.next[0];
					while(q.key.compareTo(k)<0){
						q=q.next[0];
						count++;
					}
					n.width[i]=count;
				}				
				for(int j=1 ;j<=lev;j++){
					nxt=n.next[j];
					if(nxt!=tail){
						nxt.width[j]-=(n.width[j]-1);
					}	
				}				
				SkipListEntry a=n.next[0];
				//Find the nearest node taller than n
				while(a!=tail&&a.next.length<lev){
					a=a.next[0];
				}
				for(int i=lev+1;a!=tail&&i<a.next.length;i++){
						a.width[i]++;
				}
			if(n.next[0]==tail)last=n;
			size++;
		}	    
		return true;      
	}	
	
	//Removes node from the list
	public E remove(E x){
		SkipListEntry[] prev=find(x);
		if(prev[0].next[0].key.compareTo(x)==0){			
			//n-Node that would be deleted
			SkipListEntry n=prev[0].next[0];
			//Number of levels of node n			
			int maxLevelOfRemovedNode=n.next.length;
			SkipListEntry a=n.next[0];
			//Find the pillar node 
			while(a!=tail&&a.next.length<maxLevelOfRemovedNode){
				a=a.next[0];
			}		
		    for(int i=maxLevelOfRemovedNode+1;a!=tail&&i<a.next.length;i++){
					a.width[i]--;
			}
			for(int i=0;i<maxLevel;i++){
				if(prev[i].next[i]==n){
					prev[i].next[i]=n.next[i];
				}else break;
			}
			size--;			
			return (E)n.key;
		}else return null;
	}

}