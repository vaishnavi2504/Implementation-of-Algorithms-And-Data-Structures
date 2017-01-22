
import java.util.*;

public class Comparison {

	public static void main(String[] args) {
		Timer t1 = new Timer();
		Timer t2 = new Timer();
		Timer t3 = new Timer();
		Timer t11 = new Timer();
		Timer t22 = new Timer();
		Timer t33 = new Timer();
		
		
		 TreeMap tm = new TreeMap();
		 int randomNum;
		 int min=1;
		 int max=2000000;
		 
		 t1.start();
		 for(int i=0;i<200000;i++){			 
			 randomNum=(int)(Math.random()*((max - min) + 1));
			 tm.put(randomNum, 1);
		 }
		 t1.end();
		 
		 t2.start();
		 tm.containsValue(5);
		 t2.end();
		 
		 t3.start();
		 tm.remove(100);
		 t3.end();
		 
		 System.out.println("Tree map stats");
		 System.out.println("Add "+t1);
		 System.out.println("Contains "+t2);
		 System.out.println("Delete "+t3);	 	 
		 System.out.println("-----------------");	
		 
		 SkipListImpl<Integer>sl=new SkipListImpl(); 
			 
		 t11.start();
		 for(int i=0;i<200000;i++){			 
			 randomNum=(int)(Math.random()*((max - min) + 1));
			 sl.add(randomNum);
		 }
		 
		 t11.end();
		 
		 t22.start();
		 sl.contains(7918);
		 t22.end();
		 
		 t33.start();
		 sl.remove(100);
		 t33.end();
		 
		 System.out.println("-----------------");
		 System.out.println("Skip Lists stats");
		 System.out.println();
		 System.out.println("Add "+t11);
		 System.out.println("Contains "+t22);
		 System.out.println("Delete "+t33);		 
		 
		 	
		
		 

	}

}
