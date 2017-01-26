package RandomCode;

public class kmp {
	
	public static int[] genArray(char[] arr){
		int j=0,i=1;
		int [] temp=new int[arr.length];
		for(;i<arr.length&&j+2<arr.length;){
			if(arr[i]==arr[j]){
				temp[i]=j+1;
				i++;
				j++;
				
			}else{
				if(j==0){
					temp[i]=0;
					i++;
				}
				else{
					
					j=temp[j-1];
				}
			}
		}
		
		return temp;
	}
	
	public static int KMP(char[] text, char[] pattern){
		int[] temp=genArray(pattern);
		int i=0,j=0;
		for(;i<text.length&&j<pattern.length;){
			if(text[i]==pattern[j]){
				i++;
				j++;
			}else{
				if(j==0){
					i++;
				}else{
					j=temp[j-1];
				}				
			}		
		}
		if(j==pattern.length){
			return i-pattern.length;
		}else return -1;
	}
	

	public static void main(String[] args) {
		
		String s="aabaabaaa";
		String text="aabpmqaabxabaaalm";		
		System.out.print(KMP(text.toCharArray(),s.toCharArray()));
		
	}

}
