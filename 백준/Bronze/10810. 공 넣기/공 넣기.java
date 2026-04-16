import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n, m, i, j, a, b, c;
		int arr[];
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[n];
		
		for(i=0; i<m; i++){
		    a = sc.nextInt(); b = sc.nextInt(); c = sc.nextInt();    //input a b c
		    
		    for(j=a-1; j<b; j++){
		            arr[j] = c;
		    }
		}
		for(i=0; i<n; i++){
		    System.out.print(arr[i] + " ");
		}
	}
}
