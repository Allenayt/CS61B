public class While_Drawtriangle {
	public static void main(String[] args){
		int x = 5; 
		int k = 1;
		while(x >= k){
			int i = k;
			while(i>0){
				System.out.print("*");
				i = i-1;
			}
			System.out.println(" ");
			k=k+1;
		}
	}
}