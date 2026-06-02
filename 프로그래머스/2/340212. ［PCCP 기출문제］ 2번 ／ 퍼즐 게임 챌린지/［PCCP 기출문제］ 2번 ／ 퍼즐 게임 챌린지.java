/*
순서대로 n개의 퍼즐을 제한 시간 내에 풀어야 한다. 각 퍼즐은 난이도와 소요 시간이 정해져있다.
주어진 매개변수를 바탕으로 limit 이내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값을 구해라!!!
*/
class Solution {
    
    int[] diffs, times;
    long limit;
    
    public int solution(int[] diffs, int[] times, long limit) {
        this.diffs = diffs;
        this.times = times;
        this.limit = limit; // 전역으로 활용하기 위한 this
        
        int max = diffs[0]; // 최대 level
        for(int i = 1; i < diffs.length; i++) {
            max = Math.max(max, diffs[i]);
        }
        
        int result = search(1, max); // 탐색 후 결과 반환
        return result;
    }
    
    int search(int left, int right) {
        
        while(left <= right) {
            int mid = (left + right + 1) / 2;
            
            if(check(mid)) { // mid로 가능하면 right를 줄여서 확인
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    boolean check(int pivot) { // 해당 값으로 해결할 수 있는지 확인
        long time = 0;
        for(int i = 0; i < diffs.length; i++) {
            if(diffs[i] <= pivot) time += times[i]; // 한 번에 풀 수 있을 때
            else { // 한 번에 풀 수 없을 때
                long prev = (i > 0) ? times[i-1] : 0;
                // (curr + prev) * (틀리는 횟수) + curr
                time += ((times[i] + prev) * (diffs[i] - pivot) + times[i]);  
            }
        }
        
        return time <= limit;
    }
}