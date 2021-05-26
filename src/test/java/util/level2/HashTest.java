package util.level2;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class HashTest {

	@Test
	void 위장() {
		// clothes																						return
		// [["yellowhat", "headgear"], ["bluesunglasses", "eyewear"], ["green_turban", "headgear"]]	5
		// [["crowmask", "face"], ["bluesunglasses", "face"], ["smoky_makeup", "face"]]				3

		/*/
		String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
		int response = 5;
		/**/

		/*/
		String[][] clothes = {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}};
		int response = 3;
		/**/

		/*/
		String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}, {"red_turban", "headgear"}};
		int response = 7;
		/**/

		/*/
		String[][] clothes = {{"red", "skin"}, {"green", "skin"}, {"yellow", "skin"}
			, {"yellowhat", "headgear"}, {"green_turban", "headgear"}, {"red_turban", "headgear"}
			, {"bluesunglasses", "eyewear"}};
		int response = 31;

		/**/
		String[][] clothes = {{"red", "skin"}, {"green", "skin"}, {"yellow", "skin"}
			, {"yellowhat", "headgear"}, {"green_turban", "headgear"}, {"red_turban", "headgear"}
			, {"red_ring", "ring"}, {"green_ring", "ring"}
			, {"bluesunglasses", "eyewear"}};
		int response = 95;

		/*/
		String[][] clothes = {
								  {"동그라미", "모자"}, {"세모", "모자"}, {"네모", "모자"}
								, {"별", "상의"}, {"하트", "상의"}
								, {"다이아몬드", "하의"}
		};
		int response = 23;
		/**/

		int answer;
		//타입별로 map에 grouping
		Map<String, List<String>> map = new HashMap<>();
		for (int i = 0; i < clothes.length; i++) {
			final String clotheHash = clothes[i][1];
			if (!map.containsKey(clotheHash)) {
				map.put(clotheHash, new ArrayList<>());
			}
			map.get(clotheHash).add(clothes[i][0]);
		}

		List<Integer> typeSizeList = new ArrayList<>();
		for (Map.Entry<String, List<String>> clothe : map.entrySet()) {
			//각항목에 투명옷(안입는경우) 수를 더함
			typeSizeList.add(clothe.getValue().size() + 1);
		}

		//각 항목별 곱을 처리함
		int addMultiply = 0;
		for (int multiply : typeSizeList.subList(1, typeSizeList.size())) {
			addMultiply = (addMultiply == 0 ? typeSizeList.get(0) : addMultiply);
			addMultiply = addMultiply * multiply;
		}

		//타입이 하나인경우는 그값 그대로 처리, 하나이상의 타임인경우는 마지막에 모두 투명옷인 경우 빼줌
		if (typeSizeList.size() == 1) {
			addMultiply = clothes.length;
		} else {
			addMultiply--;
		}

		answer = addMultiply;
		assertThat(answer).isEqualTo(response);
	}

	@Test
		// @checkstyle:off
	void 전화번호_목록() {
		// @checkstyle:on

		// phone_book							return
		// ["119", "97674223", "1195524421"]	false
		// ["123","456","789"]					true
		// ["12","123","1235","567","88"]		false

		boolean answer = true;

		/**/
		String[] phone_book = {"119", "97674223", "1195524421"};
		boolean resultEqualsValue = false;
		/**/

		/*/
		String[] phone_book = {"123", "456", "789"};
		boolean resultEqualsValue = true;
		/**/

		/*/
		String[] phone_book = {"12", "123", "1235", "567", "88"};
		boolean resultEqualsValue = false;
		/**/

		/*/
		String[] phone_book = {"77", "55", "89", "12", "123", "1235", "567", "88"};
		boolean resultEqualsValue = false;
		/**/

		/*/
		String[] phone_book = {"77", "1", "55", "01", "89", "12", "012", "123", "1235", "567", "0123", "88", "10", "0"};
		boolean resultEqualsValue = false;
		/**/

		/*/
		Arrays.sort(phone_book, (o1, o2) -> {
			int length = Math.min(o1.length(), o2.length());
			if("0".equals(o1)) {
				return -1;
			}
			return Integer.compare( Integer.parseInt(o1.substring(0, length)), Integer.parseInt(o2.substring(0, length)) );
		});
		list.sort((o1, o2) -> {
			int length = Math.min(o1.length(), o2.length());
			if("0".equals(o1)) {
				return -1;
			}
			return Integer.compare( Integer.parseInt(o1.substring(0, length)), Integer.parseInt(o2.substring(0, length)) );
		});
		/*정답 100%, 속도 FAIL로직*/
		Arrays.sort(phone_book, (o1, o2) -> {
			int length = Math.min(o1.length(), o2.length());
			if ("0".equals(o1)) {
				return -1;
			}
			return Integer.compare(Integer.parseInt(o1.substring(0, length)),
				Integer.parseInt(o2.substring(0, length)));
		});
		List<String> list = new ArrayList<>(Arrays.asList(phone_book));

		//정렬을 한 후
		//첫번째 글자기준값으로 해당 글자의 길이의 값을
		//같은 첫번째 글자들의 값들을 첫번째 값에 길이만큼 잘라서 List에 저장
		//총 길이의 값이 총 전화번호부의 길이의 값과 같은지 확인
		// Map<Character, List<String>> map = new HashMap();
		// for(String phone : list) {
		// 	map.put(phone.charAt(0), )
		// }
		Map<Integer, List<String>> map = new HashMap<>();
		int length = list.get(0).length();
		int index = 0;
		for (String phone : list) {
			int hashCode = phone.substring(0, 1).hashCode();
			if (!map.containsKey(hashCode)) {
				length = phone.length();
				map.put(hashCode, new ArrayList<>());
			}
			if (index + 1 < list.size()
				&& !phone.substring(0, length).equals(list.get(++index))) {
				map.get(hashCode).add(phone.substring(0, length));
			}
		}

		for (Map.Entry<Integer, List<String>> sizeCheck : map.entrySet()) {
			if (sizeCheck.getValue().size() > 1) {
				answer = false;
				break;
			}
		}

		// for (String s1 : phone_book) {
		// 	for (String s2 : list) {
		// 		if (s2.substring(0, s1.length()).hashCode() == s1.hashCode() && !s1.equals(s2)) {
		// 			answer = false;
		// 			break;
		// 		}
		// 	}
		// 	if (!answer) {
		// 		break;
		// 	}
		// }
		/**/

		/*속도문제 Pass/
		// Arrays.sort(phone_book);
		List<String> list = new ArrayList<>(Arrays.asList(phone_book));

		TrieNode trieNode = new TrieNode();
		for (String s : list) {
			trieNode.insert(s);
		}

		TrieNode node = TrieNode.RootTrieNode;
		for (String s2 : list) {
			if (node.containsValue(s2)) {
				answer = false;
				break;
			}
		}
		/**/

		assertThat(answer).isEqualTo(resultEqualsValue);
	}

	private static class TrieNode {
		private static TrieNode RootTrieNode = new TrieNode();
		private List<TrieNode> childrenNode = new ArrayList<>();
		private char c;

		public TrieNode() {
		}

		public char get() {
			return c;
		}

		private void set(char c) {
			this.c = c;
		}

		public void insert(String word) {
			TrieNode trieNode = RootTrieNode;
			for (int i = 0; i < word.length(); i++) {
				final char findChar = word.charAt(i);
				trieNode = trieNode.getChildrenNode(findChar);
			}
		}

		private TrieNode getChildrenNode(char c) {
			for (TrieNode trieNode : this.childrenNode) {
				if (c == trieNode.get()) {
					return trieNode;
				}
			}

			TrieNode trieNode = new TrieNode();
			trieNode.set(c);
			this.childrenNode.add(trieNode);
			return trieNode;
		}

		private String getContainsValue(String s1) {

			StringBuilder sb = new StringBuilder();
			List<TrieNode> childrenNodeList = this.childrenNode;
			final int length = s1.length();
			for (int i = 0; i < length; i++) {
				for (TrieNode trieNode : childrenNodeList) {
					if (trieNode.get() == s1.charAt(i)) {
						sb.append(s1.charAt(i));
						childrenNodeList = trieNode.childrenNode;
						break;
					}
				}
			}

			for (TrieNode findBody : childrenNodeList) {
				if (findBody.childrenNode.size() > 0) {
					sb.append(findBody.get());
					break;
				}
			}

			return sb.toString();
		}

		public boolean containsValue(String s1) {
			return this.getContainsValue(s1).length() <= s1.length();
		}

	}
}
