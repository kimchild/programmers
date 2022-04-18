public class TargetNumber {
    public static void main(String[] args) {

        // numbers	            target	return
        // [1, 1, 1, 1, 1]	    3	    5
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        int answer;

        Solution solution = new Solution();
        answer = solution.solution(numbers, target);

        System.out.println(answer);
    }

    static class Solution {
        int count;
        public int solution(int[] numbers, int target) {
            int answer;

            this.dfs(numbers, target, 0, 0);

            answer = this.count;
            return answer;
        }

        public void dfs(int[] numbers, int target, int depth, int result) {
            if (numbers.length == depth) {
                if (target == result) {
                    this.count++;
                }
                return;
            }

            int plus = result + numbers[depth];
            int minus = result - numbers[depth];
            dfs(numbers, target, depth+1, plus);
            dfs(numbers, target, depth+1, minus);
        }
    }

}
