package General;

public class BitTricks {
	
	//Method to count number of 1s in a given number
	//Handles overflow too
	public static int countNumberOf1s(int n){
		int count=0;
		while(n!=0){
			count+=(n&1);//checks if the last bit is 1
			n=n>>>1;//unsigned right shift operator
			
		}
		
		return count;
	}
	
	public static void main(String[] args) {		
		System.out.print(countNumberOf1s(7));
	}


}
