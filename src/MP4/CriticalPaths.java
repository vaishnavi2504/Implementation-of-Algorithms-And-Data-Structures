package MP4;
/**

 * Class to represent CriticalPath
 *  @author Vaishnavi Bhat
 *  Date: 11/30/2016
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.print.attribute.standard.MediaSize.Other;

public class CriticalPaths {
	
	static Vertex[] varr=new Vertex[10000];	
	static Map<Vertex, Integer>cricPath=new LinkedHashMap<>();
	static int count=0;
	
	
	public static void enumerate(Graph g,Vertex u, int index){		
		varr[index]=u;		
		if(u==g.sink){			
			visit(g,varr,index);
		}
		else{
			for(Edge e:u.adj){
				if(e.isTight){
					Vertex v=e.otherEnd(u);					
					enumerate(g,v,index+1);	
				}
							
			}
		}
	}	
		
	//To Print just one critical path
	public static void enumerate1(Graph g,Vertex u, int index){		
		varr[index]=u;		
		if(u==g.sink){			
				if(count<1)visitOnce(g,varr,index);
				else return;
		}
		else{
			for(Edge e:u.adj){
				if(e.isTight){
					Vertex v=e.otherEnd(u);					
					enumerate1(g,v,index+1);	
				}							
			}
		}
	}
		
	
	public static void visitOnce(Graph g, Vertex[] A, int index){	
		int len=0;		
		for(int i=1;i<=index;i++){	
			if(varr[i]!=g.source&&varr[i]!=g.sink&&count<1){				
					cricPath.put(varr[i],varr[i].d);				
			}			
		}	
		for(Vertex x:cricPath.keySet()){
        	len+=cricPath.get(x);
        }
        System.out.println(len);
        for(Vertex x:cricPath.keySet()){
        	if(count<1)
        	System.out.print(x+" ");
        }
       
        count++;
		return;
	}
	
	//Prints all the critical Paths
	public static void visit(Graph g, Vertex[] A, int index){		
		for(int i=1;i<=index;i++){	
			if(varr[i]!=g.source&&varr[i]!=g.sink){
				System.out.print(varr[i]+" ");
				if(count<1){
					cricPath.put(varr[i],varr[i].d);
				}
			}
			
		}		
		count++;
		System.out.println();
		return;
	}
	
	
	
	
	
    
}
