package MP3;
/*
 * Driver Program for Prim1
 * @author : Vaishnavi Bhat
 * 
 * */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DriverForb {

	public static void main(String[] args) throws FileNotFoundException {
			Scanner in;
			if (args.length > 0) {
	            File inputFile = new File(args[0]);
	            in = new Scanner(inputFile);
	        } else {
	            in = new Scanner(System.in);
	        }		
	
			Graph g = Graph.readGraph(in);
	        int src = in.nextInt();
	        int target = in.nextInt();
	        Vertex s = g.getVertex(src);
	        Vertex t = g.getVertex(target);	
	        Timer timer = new Timer();
	        int ans= MST.MSTUsingPrims(g, s);
	        System.out.println("MST: "+ans);
	        System.out.println(timer.end());
	}

}
