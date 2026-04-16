import java.io.*;

public class Main {
	static int count = 0;
	
	public static int recursion(final String s, int l, int r) {
		count++;
		if (l >= r) return 1;
	    else if (s.charAt(l) != s.charAt(r)) return 0;
	    else return recursion(s, l + 1, r - 1);
		
	}
	public static int isPalindrome(final String s) {
		return recursion(s, 0, s.length() - 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());

		while(T --> 0) {
			String s = br.readLine();
			bw.write(isPalindrome(s) + " " + count + "\n");
			count = 0;
		}
		bw.flush();
		bw.close();
	}
}
