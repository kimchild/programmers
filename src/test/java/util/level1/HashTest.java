package util.level1;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class HashTest {

	@Test
		// @checkstyle:off
	void 완주하지_못한_선수() {
		// @checkstyle:on

		// String[] completion = {"eden", "kiki"};
		// String[] participant = {"leo", "kiki", "eden"};
		// String[] completion = {"stanko", "ana", "mislav"};
		// String[] participant = {"mislav", "stanko", "mislav", "ana"};
		// String[] completion = {"josipa", "filipa", "marina", "nikola"};
		// String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
		// String[] completion = {"l", "m", "n", "n", "n", "o", "p", "p", "q"};
		// String[] participant = {"l", "m", "n", "n", "n", "o", "p", "p", "q", "r"};
		String[] completion = {"h", "n", "n", "n", "o", "o", "p", "p", "q"};
		String[] participant = {"h", "n", "n", "n", "o", "o", "p", "p", "q", "f"};

		String answer = "";

		/*처음 제출완료한 로직*/
		Map<Integer, List<String>> map = new HashMap<>();

		for (String s : participant) {
			int hashCode = s.hashCode();
			map.computeIfAbsent(hashCode, k -> new ArrayList<>());
			map.get(hashCode).add(s);
		}

		for (String s : completion) {
			int hashCode = s.hashCode();
			if (map.get(hashCode).size() == 1) {
				map.remove(s.hashCode());
				continue;
			}

			map.get(hashCode).remove(0);
		}

		answer = map.entrySet().iterator().next().getValue().get(0);
		/**/


		/*/
		Arrays.sort(completion);
		Arrays.sort(participant);

		answer = participant[participant.length-1];
		for (int i = 0; i < completion.length; i++) {
			if (!completion[i].equals(participant[i])) {
				answer = participant[i];
			}
		}
		/**/

		assertThat(answer).isEqualTo("f");
	}
}
