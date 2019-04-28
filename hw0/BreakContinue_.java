public class BreakContinue_{
	public static void windowPosSum(int[] a, int n){
		int len = a.length;
		for(int i = 0; i < len; i += 1){
			if(a[i] >= 0){
				for(int j = 1; j <= n; j++){
					int subseq = i+j;
					if(subseq < len){
						a[i] += a[subseq];
					}
					else{
						break;
					}
				}
			}
			else{
				continue;			
			}
		}
	}
	public static void main(String[] args){
		int[] a = {1, 2, -3, 4, 5, 4};
		int n = 3;
		windowPosSum(a, n);
		System.out.println(java.util.Arrays.toString(a));
	}
}