public class StockPrice {
    public static void main(String[] args) {

        // prices	        return
        // [1, 2, 3, 2, 3]	[4, 3, 1, 1, 0]
        int[] prices = {1, 2, 3, 2, 3};
        int[] answer;

        Solution solution = new Solution();
        answer = solution.solution(prices);

        for (int num : answer) {
            System.out.println(num);
        }
    }

    static class Solution {
        public int[] solution(int[] prices) {
            int[] answer = new int[prices.length];
            for (int i = 0; i < prices.length; i++) {
                for (int j = i+1; j < prices.length; j++) {
                    answer[i]++;
                    if (prices[i] > prices[j]) {
                        break;
                    }
                }
            }
            return answer;
        }
    }

}
