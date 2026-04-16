import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/*
 * N개의 자연수로 이루어진 집합이 있다. 이 중에서 적당히 세 수를 고른 합 d도 집합에 포함되는가?
 * 포함 된다면 그 중에서 가장 큰 d를 찾아라.
 * x, y, z는 서로 같아도 된다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 자연수의 개수 N
        int[] arr = new int[N];
        List<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(arr);
        
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		list.add(arr[i] + arr[j]);
        	}
        } // 최악의 경우 1000 * 1000 = 1_000_000(백만번)
        
        Collections.sort(list);
        
        for(int i = N-1; i >= 0; i--) { // 최대 1000
        	for(int j = i; j >= 0; j--) { // 최대 1000
        		int target = arr[i] - arr[j];
        		
        		int left = 0;
        		int right = list.size() - 1;
        		while(left <= right) {
        			int mid = (left + right) / 2;
        			int curr = list.get(mid);
        			
        			if(curr == target) {
        				System.out.println(target + arr[j]);
        				System.exit(0);
        			}
        			
        			if(curr < target) {
        				left = mid + 1;
        			} else if(curr > target){
        				right = mid - 1;
        			}
        		}
        	}
        }
    }
}