package util.level2;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		String[] phone_book = {"123", "456", "789"};
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
		List<String> list = new ArrayList<>(Arrays.asList(phone_book));

		for (String s1 : phone_book) {
			for (String s2 : list) {
				if (s2.startsWith(s1) && !s1.equals(s2)) {
					answer = false;
					break;
				}
			}
			if(!answer) break;
		}
		/**/

		/*속도문제 Pass/
		// Arrays.sort(phone_book);
		List<String> list = new ArrayList<>(Arrays.asList(phone_book));
		list.sort((o1, o2) -> s1.substring(0,1).equals(o2.substring(0,1)) ? 1 : -1);

		TrieNode trieNode = new TrieNode();
		for (String s : list) {
			trieNode.insert(s);
		}

		TrieNode node = TrieNode.RootTrieNode;

		for (String s1 : phone_book) {
			answer = node.containsValue(s1);
			if(!answer) {
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
