package MP1;
/**
 * *  @author Vaishnavi Bhat
 *  Date 10/02/2016
 */

import java.io.FileNotFoundException;
import java.util.List;
import java.util.LinkedList;
import java.util.*;
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
	        
		Graph g = Graph.readGraph(in);	
		
		Timer timer = new Timer();
		timer.start();
		CircularList<Vertex> finalList = g.stitchTours(g.breakGraphIntoTours());
		finalList.printList();
		if(!g.verifyTour(g,finalList))System.out.println("Graph is not Eulerian");
		timer.end();
		
		//System.out.println("Time taken for generating sub tours");
		//System.out.println(timer);		
	
    }
}

