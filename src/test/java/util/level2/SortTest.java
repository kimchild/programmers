package util.level2;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SortTest {

	@Test
		// @checkstyle:off
	void 가장_큰_수() {
		// @checkstyle:on

		// numbers				return
		// [6, 10, 2]			"6210"
		// [3, 30, 34, 5, 9]	"9534330"
		String answer = "";

		/*/
		int[] numbers = {6, 10, 2};
		String testEqualsValue = "6210";
		/**/

		/*/
		int[] numbers = {3, 30, 34, 5, 9};
		String testEqualsValue = "9534330";
		/**/

		/**/
		int[] numbers = {3, 30, 34, 999, 998};
		String testEqualsValue = "999999999834330";
		/**/

		//1. 해당 자리수의 제일 작은 자리수로 잘라서 비교및 정렬
		//2. 해당 자리수의 제일 작은 자리수끼리 비교했을때 같은 값이 있다면
		//3. 제일 작은 자리수 +1로 비교및 정렬, 2-3반복
		// 0 = "9"
		// 1 = "5"
		// 2 = "3"
		// 3 = "34"
		// 4 = "30"

		// 95;
		// 93;
		// 930;
		// 934;

		// 330;
		// 334;
		// 35;
		// 39;

		// 303;
		// 3034;
		// 305;
		// 309;

		// 343;
		// 3430;
		// 345;
		// 349;

		// 53;
		// 530;
		// 534;
		// 59;

		//순서를 보장하는 List로 값할당
		List<String> list = new ArrayList<>();
		for (int i : numbers) {
			list.add(String.valueOf(i));
		}

		// int number = 99999;
		// for (int i = 1; i < 1000; i++) {
		// 	list.add(String.valueOf(number--));
		// }

		List<String> sortAddList = new ArrayList<>();
		this.sort(sortAddList, list.get(0), list);

		StringBuilder answerBuilder = new StringBuilder();
		for (String s : sortAddList) {
			answerBuilder.append(s);
		}

		answer = answerBuilder.toString();

		assertThat(answer).isEqualTo(testEqualsValue);
	}

	private void sort(List<String> sortAddList, String before, List<String> list) {
		for (String strNumber : list) {
			if (Long.parseLong(before + strNumber) < Long.parseLong(strNumber + before)) {
				before = strNumber;
			}
		}

		list.remove(before);
		sortAddList.add(before);
		if (list.size() != 0) {
			this.sort(sortAddList, list.get(0), list);
		}
	}

}
