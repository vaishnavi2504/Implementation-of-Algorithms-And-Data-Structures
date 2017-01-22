/
 * @author Vaishnavi Bhat
  Date 9/14/2016
  Calculate Power(x,n) with time complexity O(log n)
 *
 */
public class PowerOfn {

	public static int power(int x, int n){
		int p;
		if(n==1)return x;
		p=power(x,n/2);
		if(n%2==1)return p*p*x;
		else return p*p;
	}
	
	public static void main(String[] args) {
			System.out.println(power(10,4));
	}

}
