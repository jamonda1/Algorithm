import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n, m, a, b, c = 0;
		
		n = sc.nextInt();
		m = sc.nextInt();
		int arr[] = new int[n];
		
		for(int i=0; i<n; i++){
		    arr[i] = 1 + i;
		}
		
		for(int i=0; i<m; i++){
		    a = sc.nextInt();
		    b = sc.nextInt();
		    
		    c = arr[a-1];
		    arr[a-1] = arr[b-1];
		    arr[b-1] = c;
		}
		
		for(int i=0; i<n; i++){
		    System.out.print(arr[i] + " ");
		}
	}
}
