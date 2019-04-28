public class Array_MaximumNum{
	public static int max(int[] m){
		int k = m.length;
		int maxnum = m[0];
		int i = 0;
		int temp;
		while(i < k){
			if(m[i] > maxnum){
				maxnum = m[i];
			}
			i++;
		}
		System.out.println(maxnum);
		return 0;
	}

	public static void main(String[] args){
		int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
		max(numbers);
		
	}
}