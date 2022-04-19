import java.util.LinkedList;
import java.util.Queue;

public class Truck {
    public static void main(String[] args) {

        // bridge_length	weight	    truck_weights	return
        // 2	            10	        [7,4,5,6]	    8
        // 100	            100	        [10]	        101
        // 100	            100	        [10,10,10,10,10,10,10,10,10,10]	110
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7,4,5,6};
//        int bridge_length = 100;
//        int weight = 100;
//        int[] truck_weights = {10};
//        int bridge_length = 100;
//        int weight = 100;
//        int[] truck_weights = {10,10,10,10,10,10,10,10,10,10};
        int answer;

        Solution solution = new Solution();
        answer = solution.solution(bridge_length, weight, truck_weights);

        System.out.println(answer);
    }

    static class Solution {
        public int solution(int bridge_length, int weight, int[] truck_weights) {
            int answer = 0;

            int idx = 0;
            int temp_weight = 0;
            Queue<Integer> queue = new LinkedList<>();

            while (true) {
                // 모든 트럭이 탑승하면 종료한다.
                if (idx == truck_weights.length) {
                    break;
                }

                // 다리길이가 가득자면 맨앞에 트럭을 빼준다.
                if (queue.size() == bridge_length) {
                    temp_weight -= queue.poll();
                }

                if ((temp_weight + truck_weights[idx]) <= weight) {
                    queue.add(truck_weights[idx]);
                    temp_weight += truck_weights[idx];
                    answer++;
                    idx++;
                } else {
                    queue.add(0);
                    answer++;
                }

            }

            // 트럭이 탑승한 시간에 다리길이를 더해준다.
            return answer + bridge_length;
        }
    }

}
