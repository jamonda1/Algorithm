import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
/*
 * 장르별로 가장 많이 재생된 노래를 두 개씩 모아서 베스트 앨범을 출시하고자 한다.
 * 각 노래에는 이름이 없고, 인덱스 번호로 구분한다.
 * 
 * 1. 많이 재생된 장르를 먼저 수록한다.
 * 2. 장르 내에서 많이 재생된 노래를 먼저 수록한다.
 * 3. 같은 횟수가 있으면 인덱스 번호 우선으로 수록한다.
 */
class Solution {
    
    static class Music implements Comparable<Music> {
        int idx, p; 
        String g;
        public Music(int idx, int p, String g) {
            this.idx = idx;
            this.p = p;
            this.g = g;
        }
        @Override
        public int compareTo(Music o) {
            if(this.p == o.p) return Integer.compare(this.idx, o.idx);
            return Integer.compare(o.p, this.p);
        } // 조건에 맞게 정렬하자
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> hs = new HashMap<>();
        PriorityQueue<Music> pq = new PriorityQueue<>();
        List<Music> list = new ArrayList<>();
        
        for(int i = 0; i < genres.length; i++) { // 많이 재생된 장르를 구하기 위해 한 곳에 모으기
        	int cnt = hs.getOrDefault(genres[i], 0);
        	hs.put(genres[i], cnt + plays[i]);
        }
        
        for(String g : hs.keySet()) { // 속한 노래가 많이 재생된 장르 순으로 정렬
        	list.add(new Music(0, hs.get(g), g));
        } Collections.sort(list);
        
        List<Music> AllMusic = new ArrayList<>();
        
        for(int i = 0; i < genres.length; i++) { // 모든 노래 집어넣고, 내림차순 정렬
        	AllMusic.add(new Music(i, plays[i], genres[i]));
        } Collections.sort(AllMusic);
        
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
        	String pivot = list.get(i).g; // 피벗이랑 같은 것을 먼저 넣어야 한다.
        	
        	int count = 0;
        	for(int j = 0; j < AllMusic.size(); j++) {
        		Music m = AllMusic.get(j);
        		
        		if(m.g.equals(pivot)) {
        			result.add(m.idx);
        			count++;
        		}
        		if(count == 2) break;
        	}
        }
        
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
        	answer[i] = result.get(i);
        }
        
        return answer;
    }
}