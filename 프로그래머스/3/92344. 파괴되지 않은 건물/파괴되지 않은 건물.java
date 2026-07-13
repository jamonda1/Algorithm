/*
각 칸마다 내구도를 가진 건물이 하나씩 있다. 적은 이 건물들을 공격헤서 파괴하려고 한다.
공격을 받으면 내구도가 감소하고, 0 이하가 되면 파괴된다. 반대로 아군은 회복 스킬을 통해 건물의 내구도를 높이려고 한다.
모든 skill 종료 후에 파괴되지 않은 건물의 수를 리턴해라
*/
class Solution {
    
    int[][] board, sum; // 전역으로 활용
    int x, y;
    
    public int solution(int[][] board, int[][] skill) {
        this.board = board;
        
        x = board.length; // 행의 수
        y = board[0].length; // 열의 수
        sum = new int[x+1][y+1]; // 누적합을 저장할 배열
        
        for(int[] s : skill) {
            play(s[1], s[2], s[3], s[4], s[5] * ((s[0] == 1) ? -1 : 1)); // skill 처리
        }
        
        cal(); // 누적합 계산
        int result = check(0); // 생존 건물 카운트 메서드
        
        return result;
    }
    
    void play(int r1, int c1, int r2, int c2, int degree) {
        // (r1, c1)부터 (r2, c2)까지의 degree를 공격하거나 회복한다
        sum[r1][c1] += degree; // 기준점
        sum[r1][c2 + 1] += degree * -1; // 도착점
        sum[r2 + 1][c1] += degree * -1; // 도착점
        sum[r2 + 1][c2 + 1] += degree; // 기준점
    }
    
    void cal() { // 상하방향으로 합산한 후에 좌우방향으로 합산 수행
        for(int i = 0; i < y; i++) { // 상하
            for(int j = 1; j < x; j++) {
                sum[j][i] += sum[j-1][i];
            }
        }
        for(int i = 0; i < x; i++) { // 좌우
            for(int j = 1; j < y; j++) {
                sum[i][j] += sum[i][j-1];
            }
        }
    }
    
    int check(int count) {
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                // 초기값에 누적된 결과를 더해서 0보다 크면 생존 건물
                if(board[i][j] + sum[i][j] > 0) count++;
            }
        }
        
        return count;
    }
}