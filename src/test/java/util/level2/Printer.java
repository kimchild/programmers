import java.util.Collections;
import java.util.PriorityQueue;

public class Printer {
    public static void main(String[] args) {

        // priorities	        location	return
        // [2, 1, 3, 2]	        2	        1
        // [1, 1, 9, 1, 1, 1]	0	        5
//        int[] priorities = {2, 1, 3, 2};
//        int location = 2;
        int[] priorities = {1, 1, 9, 1, 1, 1};
        int location = 0;
        int answer;

        Solution solution = new Solution();
        answer = solution.solution(priorities, location);

        System.out.println(answer);
    }

    static class Solution {
        public int solution(int[] priorities, int location) {
            int answer = 0;

            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for (int i : priorities) {
                pq.add(i);
            }

            while (!pq.isEmpty()) {
                for (int i = 0; i < priorities.length; i++) {
                    if (priorities[i] == pq.peek()) {
                        if (location == i) {
                            return ++answer;
                        }
                        answer++;
                        pq.poll();
                    }
                }
            }

            return answer;
        }
    }

}
