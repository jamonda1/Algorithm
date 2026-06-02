/*
각 징검다리의 디딤돌에는 모두 숫자가 적혀있다. 숫자는 한 번 밟을 때마다 줄어든다.
디딤돌의 숫자가 0이되면 건널 수 없어지고, 이때는 다음 디딤돌로 한 번에 여러 칸을 뛸 수 있다.
*/
class Solution {
    
    int[] stones;
    int k, size;
    
    public int solution(int[] stones, int k) {
        this.stones = stones;
        this.k = k; // 전역으로 활용하기 위한 this
        
        size = stones.length;
        
        int result = search(1, 200_000_000); // 범위는 1부터 2억
        return result;
    }
    
    int search(int left, int right) {
        while(left <= right) {
            int mid = (left + right) / 2;
            
            if(check(mid)) { // mid로 가능하면 더 크게
                left = mid + 1;
            } else { // 불가능하면 작게
                right = mid - 1;
            }
        }
        
        return right;
    }
    
    boolean check(int pivot) {
        int cnt = 0;
        for(int i = 0; i < size; i++) {
            if(stones[i] - pivot < 0) cnt++;
            else cnt = 0;
            
            if(cnt >= k) return false;
        }
        
        return true;
    }
}