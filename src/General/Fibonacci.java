package General;

//nth Fibonacci number in O(log n) time
// 1,1,2,3,5,8

import java.util.Scanner;

/**
 * @author Vaishnavi Bhat
 *
 */
public class Fibonacci {

	/**
	 * @param args
	 */
	
	//To calculate power of matrix x^n
	public static int[][] power(int[][] x, int n){
		int[][] p=new int[2][2];
		if(n==1)return x;
		p=power(x,n/2);
		if(n%2==1)return matrixMulti(matrixMulti(p,p),x);
		else return matrixMulti(p,p);
	}
	
	//Product of two 2X2 matrices
	public static int[][] matrixMulti(int[][] m, int [][] n){
		int[][] res=new int[2][2];
		int a = m[0][0] * n[0][0] +  m[0][1] * n[1][0];
        int b = m[0][0] * n[0][1] +  m[0][1] * n[1][1];
        int c = m[1][0] * n[0][0] +  m[1][1] * n[0][1];
        int d = m[1][0] * n[0][1] +  m[1][1] * n[1][1];

        res[0][0] = a;
        res[0][1] = b;
        res[1][0] = c;
        res[1][1] = d;
		
        return res;
        
	}
	
	//product of a 2X2 and 2X1 matrices
	public static int[][] matrixMultispl(int[][] m, int [][] n){
		int[][] res=new int[2][1];
		int a = m[0][0] * n[0][0] +  m[0][1] * n[1][0];       
        int d = m[1][0] * n[0][0] +  m[1][1] * n[1][0];

        res[0][0] = a;
        res[1][0] = d;        
		
        return res;
        
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] a={{0,1},{1,1}};
		int[][] v={{1},{1}};
		System.out.println("Enter the value of n");
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[][] res1=new int[2][2];
		int[][] res2=new int[2][1];
			
		
		res1=power(a,n);
		
		res2=matrixMultispl(res1,v);			
	    
		System.out.print("nth Fibonacci number is "+res2[0][0]+" ");
			 
		 
	}

}
