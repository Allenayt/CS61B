public class Deffun_Drawtriangle{
	public static void DrawTriangle(int N){
		int x = 1;
		while (x < N){
			int i = x;
			while(i > 0){
				System.out.print("*");
				i--;
			}
			System.out.println(" ");
			x++;
		}
	}

	public static void main(String[] args){
		int num = 20;
		DrawTriangle(num);
	}
}

