import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = "";
		while((str = br.readLine()) != null && !str.isEmpty()) {
			String[] NM = str.split(" ");
			
			int find = Integer.parseInt(NM[1]); // 몇 번째 요소를 찾을 것인가?
			
			String[] tokens = NM[0].split("");	 // 문자열 쪼개기
			String[] temp = new String[tokens.length];
			boolean[] visited = new boolean[tokens.length];
			
			count = 0;
			
			bw.write(NM[0] + " " + find + " " + "= ");
			
			permutation(visited, tokens, temp, find, tokens.length, 0);
			
			if(find > count) bw.write("No permutation\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	static void permutation(boolean[] visited, String[] tokens, String[] temp, int find, int M, int depth) throws IOException{
		if(depth == M) {
			count++;
			if(find == count) {
				for(int i = 0;  i < temp.length; i++) {
					bw.write(temp[i] + "");
				}
				bw.write("\n");
			}
			return;
		}
		
		for(int i = 0; i < tokens.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				temp[depth] = tokens[i];
				permutation(visited, tokens, temp, find, M, depth + 1);
				visited[i] = false;
			}
		}
	}
}
