package util.level2;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class FullSearchTest {

	@Test
		// @checkstyle:off
	void 소수찾기() {
		// @checkstyle:on

		// numbers	return
		// "17"		3		 [7, 17, 71]
		// "011"	2		 [11, 101]

		int answer = 0;

		/**/
		String numbers = "17";
		int answerEquals = 3;
		/**/
		/*/
		String numbers = "011";
		int answerEquals = 2;
		/**/

		List<String> numberList = new ArrayList<>();
		for (int i = 0; i < numbers.length(); i++) {
			numberList.add(numbers.substring(i, i + 1));
		}
		List<String> numberDescList = new ArrayList<>(numberList);
		List<String> numberAscList = new ArrayList<>(numberList);

		//위에서 쪼갠값을 기준으로 가장 작은수와 가장 큰수를 구한다.
		List<String> sortAddDescList = new ArrayList<>();
		this.limitRangeSortDesc(sortAddDescList, numberDescList.get(0), numberDescList);

		List<String> sortAddAscList = new ArrayList<>();
		this.limitRangeSortAsc(sortAddAscList, numberAscList.get(0), numberAscList);

		StringBuilder sb = new StringBuilder();
		for (String s : sortAddAscList) {
			sb.append(s);
		}
		final int numberMin = Integer.parseInt(sb.toString());
		sb = new StringBuilder();
		for (String s : sortAddDescList) {
			sb.append(s);
		}
		final int numberMax = Integer.parseInt(sb.toString());


		int[] numberCase = new int[10000001];

		int anchor;
		for (int i = 2; i < 10000000; i++) {

			if (numberCase[i] == 0) {
				numberCase[i] = i;
			}
			anchor = 0;
			for (int j = 0; j < 9999998; j++) {
				anchor = i + anchor;
				if (anchor > 10000000) {
					break;
				}
				if ((numberCase[anchor] % i) != 0
					&& numberCase[anchor] != 1) {
					numberCase[anchor] = anchor;
					break;
				}
				if (numberCase[anchor] == 0) {
					numberCase[anchor] = 1;
				}
			}

		}

		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10000000; i++) {
			if (0 != numberCase[i] && 1 != numberCase[i]) {
				String strNumber = String.valueOf(numberCase[i]);
				list.add(strNumber);
			}
		}

		Set<String> setCase = new HashSet<>();
		for (String s : list) {
			for (String value : numberList) {
				if (s.length() <= numbers.length()
					&& s.contains(value)) {

					boolean isAllWord = true;
					for (int i = 0; i < s.length(); i++) {

						if (!numbers.contains(s.substring(i, i + 1))) {
							isAllWord = false;
							break;
						}
					}
					if (isAllWord) {
						isAllWord = false;
						//실제 최대값과 최소값을 구할수 있는 값과, 한자리수 자신의 소수값을 제외하고 추가한다.
						final int rangeNumber = Integer.parseInt(s);
						for (int i = 0; i < s.length(); i++) {
							if((rangeNumber >= numberMin && rangeNumber <= numberMax && rangeNumber != 1)
								|| rangeNumber == Integer.parseInt(s.substring(i, i + 1))) {
								isAllWord = true;
								break;
							}
						}
						if (isAllWord) {
							setCase.add(s);
						}
					}

				}
			}
		}

		answer = setCase.size();
		assertThat(answer).isEqualTo(answerEquals);

	}



	private void limitRangeSortDesc(List<String> sortAddList, String before, List<String> list) {
		for (String strNumber : list) {
			if (Long.parseLong(before + strNumber) < Long.parseLong(strNumber + before)) {
				before = strNumber;
			}
		}

		list.remove(before);
		sortAddList.add(before);
		if (list.size() != 0) {
			this.limitRangeSortDesc(sortAddList, list.get(0), list);
		}
	}
	private void limitRangeSortAsc(List<String> sortAddList, String before, List<String> list) {
		for (String strNumber : list) {
			if (Long.parseLong(before + strNumber) > Long.parseLong(strNumber + before)) {
				before = strNumber;
			}
		}

		list.remove(before);
		sortAddList.add(before);
		if (list.size() != 0) {
			this.limitRangeSortAsc(sortAddList, list.get(0), list);
		}
	}


}
