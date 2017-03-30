package OptionalProjects;
/**
 * @authors Vaishnavi Bhat - vxv150130
 *  * 
 * 
 * Set Operations : union, intersection, difference on Lists
 * Used Fail safe iterators
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class SetOpsOnLists<T>{
		
	//Set Difference a-b Elements of a that are not in b
	public static<T extends Comparable<? super T>>
        void difference(List<T> a, List<T> b,List<T> c) {
		List<T> l1 = new ArrayList<T>(a);
	    List<T> l2 = new ArrayList<T>(b);
	    List<T> res = new ArrayList<T>(c);    
	    
	    Iterator<T> it1=l1.iterator();
	    Iterator<T> it2=l2.iterator();
	    
	    T x=it1.next();
    	T y=it2.next();    	
	    
	    while(x!=null){	    	
	    	
	    	//If the element of first list is smaller than the one in the second list then
	    	// add that element to the resultant list & move the first iterator
	    	if(x.compareTo(y)<0){				  
			  res.add(x);
			  if(it1.hasNext())x=(T)it1.next();
			  else x=null;
			}
	    	
	    	//If the first element is larger than the corresponding element in the second list then 
	    	//move both the iterators
	    	else if(x.compareTo(y)>0){
	    		y=(it2.hasNext())?(T)it2.next():null;
			}
	    	
	    	//increment both the iterators
	    	else{
	    		x=(it1.hasNext())?(T)it1.next():null;
	    		y=(it2.hasNext())?(T)it2.next():null;	    		
	    	}
	    	
	    	//if second list is shorter i.e. it2 has reached end
	    	// and there are still elements in it1
	    	while(y==null&&x!=null){
	    		res.add(x);
	    		x=(it1.hasNext())?(T)it1.next():null;
	    	}
	    	
	    }	    
	    
	    for(T v:res){	    	
	    	System.out.print(v+" ");
	    }
	    
	}
	
	//Union of list l1 and list l2
	public static<T extends Comparable<? super T>>void union(List<T> l1, List<T> l2,List<T> outList) {
	    List<T> x1 = new ArrayList<T>(l1);
	    List<T> x2 = new ArrayList<T>(l2);
	    List<T> res = new ArrayList<T>(outList);  
	    	        
	    Iterator<T> it1=x1.iterator();
	    Iterator<T> it2=x2.iterator();	    
	    T h=it1.next();
    	T i=it2.next();
    	
	    while(h!=null&& i!=null){	    	
			  if(h.compareTo(i)==0){
				  res.add(h);
				  h=(it1.hasNext())?it1.next():null;
				  i=(it2.hasNext())?it2.next():null;				  
			  }
			  else if(h.compareTo(i)>0){
				  res.add(i);
				  i=(it2.hasNext())?it2.next():null;
			  }
			  else{
				  res.add(h);
				  h=(it1.hasNext())?it1.next():null;
			  }
	    }
	    
	    //If there are more elements in list2
	    while(h==null&&i!=null){
	    	res.add(i);
			i=(it2.hasNext())?it2.next():null;
	    }
	    
	  //If there are more elements in list1
	    while(h!=null&&i==null){
	    	res.add(h);
			h=(it2.hasNext())?it1.next():null;
	    }      
	   
	    Iterator it3=res.iterator();
	    while(it3.hasNext()){			  
		  System.out.print(it3.next()+" ");
	    }
	    
     }
	
	public static<T extends Comparable<? super T>>void intersect(List<T> l1, List<T> l2,List<T> outList) {
	    List<T> x1 = new ArrayList<T>(l1);
	    List<T> x2 = new ArrayList<T>(l2);
	    List<T> res = new ArrayList<T>(outList);   
	    
	    Iterator<T> it1=x1.iterator();
	    Iterator<T> it2=x2.iterator();
	    
	    T ele1=it1.next();
	    T ele2=it2.next();    
	    
	    while(ele1!=null&&ele2!=null){			   
			  if(ele1.compareTo(ele2)<0){	
				  ele1=(it1.hasNext())?it1.next():null;				  	  
			  }
			  
			  else if(ele1.compareTo(ele2)>0){
				  ele2=(it1.hasNext())?it2.next():null;			  
			  }			  
			  else{
				  res.add(ele1);
				  ele1=(it1.hasNext())?it1.next():null;
				  ele2=(it1.hasNext())?it2.next():null;				  
			  }
		 }	      
	   	    
	    for(T l:res){			  
		  System.out.print(l+" ");
	    }
   }
	  
  public static void main(String[] args){
		  SetOpsOnLists s1=new SetOpsOnLists();	  
		 
		  List<Integer> m1=new ArrayList<>();
		  List<Integer> m2=new ArrayList<>();
		  List<Integer> resdiff=new ArrayList<>();
		  List<Integer> resintr=new ArrayList<>();
		  List<Integer> resuni=new ArrayList<>();
		  
		  m1.add(1);
		  m1.add(2);
		  m1.add(4);
		  m1.add(8);
		  m1.add(10);
		  m1.add(13);
		  //m1.add(100);
		  
//		  m2.add(133);
		  m2.add(13);
		  m2.add(15);	  
		  m2.add(18);
		  
		  System.out.println("Union of Lists");
		  s1.union(m1,m2,resuni);
		  System.out.println();
		  		  
		  System.out.println("List1-List2");
		  s1.difference(m1, m2, resdiff);
		  
		  System.out.println("Intersection of Lists");
		  s1.intersect(m1,m2,resintr);
		  
		  //s1.check(m1,m2);
		  
		  
	  }

}
