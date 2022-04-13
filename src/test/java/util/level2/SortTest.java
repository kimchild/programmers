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

		boolean except = true;
		List<String> list = new ArrayList<>();
		for (int i : numbers) {
		    list.add(String.valueOf(i));
		    if (i != 0) except = false;
		}

		Collections.sort(list, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));

		for (String str : list) {
		    answer += str;
		}

		if (except) answer = "0";

		assertThat(answer).isEqualTo(testEqualsValue);
	}


}
