import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int count, result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 계란을 먹기 위해서는 계란을 깨야 한다.
		// 각 계란에는 내구도와 무게가 정해져있다.
		// 계란으로 계란을 치면 각 계란의 내구도는 상대 계란의 무게만큼 깎이게 된다.
		// 내구도 0 이하가 되면 계란은 깨진다.
		
		// 예를 들어 계란(1)의 내구도가7, 무게가 5 - 계란(2)의 내구도가 3, 무게가 4라고 하자
		// 계란(1)로 계란(2)를 치면? 계란(1)의 내구도는 3가 되고, 계란(2)는 깨진다.
		
		// 계란은 왼쪽부터 차례로 다른 계란을 쳐서 가장 많은 계란을 깨야 한다.
		// 1. 가장 왼쪽의 계란을 든다.
		// 2. 깨지지 않은 다른 계란을 친다. 손에 든 계란이 깨지거나, 멀쩡한 계란이 없으면 원래 자리에 놓고 넘어간다.
		// 3. 방금 전의 한 칸 오른쪽 계란을 들고 2번 과정을 진행했다. 만약 2번을 수행했는데 배열의 끝이면 과정을 종료
		
		// 가장 많이 깰 수 있는 계란은??
		
		int N = Integer.parseInt(br.readLine());
		int[] eggS = new int[N]; // 계란의 내구도 저장
		int[] eggW = new int[N]; // 계란의 무게 저장
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggS[i] = Integer.parseInt(st.nextToken()); // 계란 내구도 입력
			eggW[i] = Integer.parseInt(st.nextToken()); // 계란 무게 입력
		}
		
		eggBreak(eggS, eggW, 0, N);
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
	
	static void eggBreak(int[] eggS, int[] eggW, int depth, int n) {
		if(depth == n) {
			count = 0;
			for(int i = 0; i < n; i++) {
				if(eggS[i] <= 0) count++;
			}
			result = Math.max(count, result);
			return;
		}
		
		boolean f = false; // 계란을 쳤는지 확인
		if(eggS[depth] > 0) {
			for(int i = 0; i < n; i++) {
				// 내가 집은 계란과 이미 깨진 계란은 패스
				if(i == depth || eggS[i] <= 0) continue;
				f = true; // 계란을 쳤으면 true로 해준다.
				
				eggS[depth] -= eggW[i]; // 내가 집은 계란의 내구도 - 내가 내리친 계란의 무게
				eggS[i] -= eggW[depth]; // 내가 내리친 계란의 내구도 - 내가 집은 계란의 무게
				
				eggBreak(eggS, eggW, depth + 1, n);
				
				eggS[depth] += eggW[i]; // 다음 반복문에서는 내구도가 복구되어 있어야 한다.
				eggS[i] += eggW[depth];
			}
		}
		// 이미 집은 계란이 깨져있거나, 계란을 친 적이 없으면?
		if(!f) eggBreak(eggS, eggW, depth + 1, n);
	}
}
