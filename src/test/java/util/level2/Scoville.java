import java.util.PriorityQueue;

public class Scoville {
    public static void main(String[] args) {

        // scoville	                K	return
        // [1, 2, 3, 9, 10, 12]	    7	2
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        int answer = 0;


        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i : scoville) {
            pq.offer(i);
        }

        while (pq.peek() <= K) {
            if (pq.size() == 1) {
                answer = -1;
                break;
            }

            int scov = pq.poll();
            pq.offer(scov + (pq.poll() * 2));
            answer++;
        }

        System.out.println(answer);
    }

}
