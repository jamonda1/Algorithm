import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken()); // 배열의 길이
        // k의 최대값이 
        long k = Integer.parseInt(st.nextToken()); // 목표
        
        long[] sum = new long[n + 1];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
        	sum[i] = sum[i - 1] + Long.parseLong(st.nextToken());
        } // sum에는 이전의 자신 값과 현재 입력되는 값을 더한다.
        
        Map<Long, Long> map = new HashMap<>();
        map.put(0L, 1L);
        
        long result = 0;
        for(int i = 1; i <= n; i++) {
        	long target = sum[i] - k; // 여기서 target은 기존의 sum[j]가 된다.
        	
        	if(map.containsKey(target)) {
        		result += map.get(target);
        	}
        	
        	map.put(sum[i], map.getOrDefault(sum[i], 0L) + 1L);
        } // getOrDefault는 sum[i]가 맵에 없으면 0L을 반환, 있으면 해당 값에 +1을 해서 반환
        
        System.out.println(result);
    }
}