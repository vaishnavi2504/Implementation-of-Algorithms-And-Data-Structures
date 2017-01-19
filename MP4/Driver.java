/**

 * Driver Program
 *  @author Vaishnavi Bhat
 *  Date: 11/30/2016
 *
 */
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
	        if (args.length > 0) {
	            File inputFile = new File(args[0]);
	            in = new Scanner(inputFile);
	        } else {
	            in = new Scanner(System.in);
	        }        
		Graph g = Graph.readDirectedGraph(in);	
		for(Vertex u: g){
			u.d = in.nextInt();
			u.inDegree=u.revAdj.size();
			u.outDegree=u.adj.size();		
		}	
		CriticalPaths c=new CriticalPaths();
		Timer timer = new Timer();
		g.detectTightEdges();
		System.out.println();
		c.enumerate1(g, g.source, 1);
		
		System.out.println();
		int npaths =g.numOfPaths();
		System.out.println();
		
		System.out.println("Task	EC	LC	Slack");
		for(Vertex u:g.v){
			if(u!=null&&u!=g.source&&u!=g.sink)
			System.out.println(u.name+"\t"+u.ec+"\t"+u.lc+"\t"+(u.lc-u.ec));
		}
		System.out.println();
		
		g.numOfCriticalNodes();
		System.out.println(npaths);	
		c.enumerate(g,g.source,1);
		System.out.println();
		System.out.println(timer.end());
    }
}

