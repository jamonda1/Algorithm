import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int arr[] = new int[30];
		
		int n;
		
		for(int i=0; i<28; i++){
		    n = sc.nextInt();
		    arr[n-1] = n;
		}
		for(int i=0; i<30; i++){
		   if(arr[i] == 0) System.out.print(i+1 + " ");
		}
	}
}
