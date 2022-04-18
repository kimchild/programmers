import java.util.*;

public class FunctionDevelopment {
    public static void main(String[] args) {

        // progresses	                speeds	            return
        // [93, 30, 55]	                [1, 30, 5]	        [2, 1]
        // [95, 90, 99, 99, 80, 99]	    [1, 1, 1, 1, 1, 1]	[1, 3, 2]
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        int[] answer = {};

        Queue<Integer> progressQueue = new LinkedList<>();
        for (int i : progresses) {
            progressQueue.add(i);
        }
        Queue<Integer> speedQueue = new LinkedList<>();
        for (int i : speeds) {
            speedQueue.add(i);
        }

        List<Integer> answerList = new ArrayList<>();
        int multiple = 0;
        while (!progressQueue.isEmpty()) {
            multiple++;
            if (progressQueue.peek() + (speedQueue.peek() * multiple) >= 100) {
                int sum = 1;
                progressQueue.poll();
                speedQueue.poll();
                while (!progressQueue.isEmpty() && progressQueue.peek() + (speedQueue.peek() * multiple) >= 100) {
                    sum++;
                    progressQueue.poll();
                    speedQueue.poll();
                }
                multiple = 0;
                answerList.add(sum);
            }
        }

        answer = answerList.stream().mapToInt(i -> i).toArray();

        System.out.println(answer);
    }

}
