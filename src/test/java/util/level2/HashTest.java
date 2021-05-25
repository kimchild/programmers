package util.level2;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class HashTest {

	@Test
		// @checkstyle:off
	void 전화번호_목록() {
		// @checkstyle:on

		// phone_book							return
		// ["119", "97674223", "1195524421"]	false
		// ["123","456","789"]					true
		// ["12","123","1235","567","88"]		false

		boolean answer = true;

		/*/
		String[] phone_book = {"119", "97674223", "1195524421"};
		boolean resultEqualsValue = false;
		/**/

		/*/
		String[] phone_book = {"123","456","789"};
		boolean resultEqualsValue = true;
		/**/

		/*/
		String[] phone_book = {"12","123","1235","567","88"};
		boolean resultEqualsValue = false;
		/**/

		/*/
		String[] phone_book = {"77", "55", "89", "12", "123", "1235", "567", "88"};
		boolean resultEqualsValue = false;
		/**/

		/**/
		String[] phone_book = {"77", "1", "55", "01", "89", "12", "012", "123", "1235", "567", "0123", "88", "10", "0"};
		boolean resultEqualsValue = false;
		/**/

		List<String> list = new ArrayList<>(Arrays.asList(phone_book));


		Map<Character, List<String>> map = new HashMap<>();
		for (String s : list) {

			char hashCode = s.charAt(0);
			if (!map.containsKey(hashCode)) {
				map.put(hashCode, new ArrayList<>());
			}
			map.get(hashCode).add(s);
		}

		for (String s1 : phone_book) {

			final char findHash = s1.charAt(0);
			if (map.containsKey(findHash)) {

				for (String entry : map.get(findHash)) {
					if (entry.startsWith(s1) && !s1.equals(entry)) {
						answer = false;
						break;
					}
				}
			}

			if(!answer) break;
		}


		assertThat(answer).isEqualTo(resultEqualsValue);
	}

}
