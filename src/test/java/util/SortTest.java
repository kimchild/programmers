package util;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class SortTest {

	public int[] solution(int[] array, int[][] commands) {
		int[] answer = {};
		return answer;
	}

	@Test
		// @checkstyle:off
	void K번째수() {
		// @checkstyle:on

		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

		// [1, 5, 2, 6, 3, 7, 4]를 2번째부터 5번째까지 자른 후 정렬합니다. [2, 3, 5, 6]의 세 번째 숫자는 5입니다.
		// [1, 5, 2, 6, 3, 7, 4]를 4번째부터 4번째까지 자른 후 정렬합니다. [6]의 첫 번째 숫자는 6입니다.
		// [1, 5, 2, 6, 3, 7, 4]를 1번째부터 7번째까지 자릅니다. [1, 2, 3, 4, 5, 6, 7]의 세 번째 숫자는 3입니다.
		// array	commands	return
		// [1, 5, 2, 6, 3, 7, 4]	[[2, 5, 3], [4, 4, 1], [1, 7, 3]]	[5, 6, 3]

		int[] answer = new int[commands.length];

		int start;
		int end;
		int target;
		int index = 0;
		for (int[] command : commands) {
			start = command[0]-1;
			end = command[1];
			target = command[2]-1;

			int[] sortArray = Arrays.copyOfRange(array, start, end);
			Arrays.sort(sortArray);
			answer[index++] = sortArray[target];
		}

		System.out.println(answer);
		int[] testEqualToArray = {5, 6, 3};
		assertThat(answer).isEqualTo(testEqualToArray);
	}
}
