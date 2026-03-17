import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    
    static List<Character>[] list = new ArrayList[5]; // 톱니바퀴 저장
    static int[] check;
    static int l = 6, r = 2; // 맨 위, 왼쪽, 오른쪽을 바라보는 인덱스 번호
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for(int i = 1; i <= 4; i++) {
            list[i] = new ArrayList<>();
            String input = br.readLine();
            
            for(int j = 0; j < input.length(); j++) {
                list[i].add(input.charAt(j));
            }
        } // 톱니바퀴 정보 입력 완료
        
        int K = Integer.parseInt(br.readLine());
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken()); // 회전시킬 톱니의 번호
            int d = Integer.parseInt(st.nextToken()); // 회전 방향
            // 1 = 시계, -1 = 반시계
            
            check = new int[5];
            checkRoll(o, d); // 연쇄 회전 톱니바퀴 확인 메서드
            
            for(int j = 1; j < 5; j++) { // 회전 수행
                if(check[j] != 0) roll(j);
            }
        }
        
        int score = 0;
        for(int i = 1; i < 5; i++) { // 가장 위가 1이면, 1, 2, 4, 8점
            if(list[i].get(0) == '1') {
                score += (1 << (i - 1));
            }
        }
        
        System.out.println(score);
    }

    private static void checkRoll(int o, int d) {
        // o의 -1번과 +1번을 확인해서 왼쪽 오른쪽이 각각 다른 극일 때 재귀
        check[o] = d;
        char left = list[o].get(l); // 지금 톱니바퀴의 왼쪽
        char right = list[o].get(r);// 지금 톱니바퀴의 오른쪽
        
        if(o - 1 > 0 && check[o - 1] == 0) { // 0번 인덱스는 확인하면 안 된다.
            char mr = list[o - 1].get(r); // 왼쪽 톱니바퀴의 오른쪽 톱니만 확인
            if(left != mr) checkRoll(o - 1, d * -1);
        }
        
        if(o + 1 < 5 && check[o + 1] == 0) { // 5번 인덱스는 확인하면 안 된다.
            char ml = list[o + 1].get(l); // 오른쪽 톱니의 왼쪽 톱니만 확인
            if(right != ml) checkRoll(o + 1, d * -1);
        }
    }
    
    private static void roll(int i) {
        char pick = list[i].remove((check[i] == 1) ? 7 : 0);
        int idx = (check[i] == 1) ? 0 : 7;
        
        list[i].add(idx, pick); // 1이면 마지막 인덱스를 앞으로, -1이면 첫 번째 인덱스를 맨 뒤로
    }
}
