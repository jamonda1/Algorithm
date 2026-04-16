import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().toUpperCase(); // 입력받아 대문자로 변환

        // 각 문자의 빈도를 저장할 맵
        Map<Character, Integer> charCountMap = new HashMap<>();

        // 1. 문자열을 순회하며 각 문자의 빈도 계산
        for (char c : input.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        int maxCount = 0;
        List<Character> mostFrequentChars = new ArrayList<>();

        // 2. 가장 높은 빈도 수와 해당 문자를 찾음
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            int count = entry.getValue();

            if (count > maxCount) {
                maxCount = count;
                // 새로운 최대 빈도수를 찾았으므로 리스트를 초기화하고 현재 문자를 추가
                mostFrequentChars.clear();
                mostFrequentChars.add(entry.getKey());
            } else if (count == maxCount) {
                // 동일한 최대 빈도수를 가진 문자가 있다면 리스트에 추가
                mostFrequentChars.add(entry.getKey());
            }
        }

        // 3. 결과 출력
        if (mostFrequentChars.size() > 1) {
            System.out.println("?"); // 최다 사용 문자가 2개 이상인 경우
        } else {
            System.out.println(mostFrequentChars.get(0)); // 유일한 최다 사용 문자인 경우
        }

        sc.close();
    }
}