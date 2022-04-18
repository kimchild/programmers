import java.util.HashMap;
import java.util.Map;

public class PhoneNumber {
    public static void main(String[] args) {

        // phone_book	                        return
        // ["119", "97674223", "1195524421"]	false
        // ["123","456","789"]	                true
        String[] phone_book = {"119", "97674223", "1195524421"};
//        String[] phone_book = {"123","456","789"};
        boolean answer;

        Solution solution = new Solution();
        answer = solution.solution(phone_book);

        System.out.println(answer);
    }

    static class Solution {
        public boolean solution(String[] phone_book) {
            Map<String, Integer> map = new HashMap<>();
            for (String str : phone_book) {
                map.put(str, 1);
            }

            for (String str : phone_book) {
                for (int i = 0; i < str.length(); i++) {
                    final String sub = str.substring(0, i);
                    if (map.containsKey(sub)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

}
