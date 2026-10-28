import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        List<Integer> list = new ArrayList<>();
        for(int n : numbers) list.add(n);
    
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                String ab = "" + a + b; // a + b랑
                String ba = "" + b + a; // b + a 비교해서
                
                return ba.compareTo(ab); // 더 큰 숫자가 앞에 오도록
            }
        });
        
        if(list.get(0) == 0) return "0";
        
        StringBuilder sb = new StringBuilder();
        for(int n : list) sb.append(n); // 정렬된 것들 다 합치기
        
        return sb.toString();
    }
}