package util.level1;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class FullSearchTest {

	private int[] copyPatern(int[] pattern, int length) {
		int[] answerPaper = new int[length];
		int readNowIndex = 0;
		final int patternMaxLength = pattern.length;
		int patternCopyIndex;

		do {
			patternCopyIndex = Math.min(patternMaxLength + readNowIndex, length);
			System.arraycopy(pattern, 0, answerPaper, readNowIndex, patternCopyIndex - readNowIndex);
			readNowIndex += patternMaxLength;
		} while (readNowIndex < length);

		return answerPaper;
	}

	@Test
		// @checkstyle:off
	void 모의고사() {
		// @checkstyle:on

		// 1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
		// 2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
		// 3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...

		// answers	return
		// [1,2,3,4,5]	[1]
		// [1,3,2,4,2]	[1,2,3]

		int[] answer = {};
		int[] answers = {1, 2, 3, 4, 5};
		// int[] answers = {1, 3, 2, 4, 2};
		int[] pattern1 = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
		int[] pattern2 = {2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5};
		int[] pattern3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

		//answers가 1만까지도 있다.
		int[] pattern1Answer = this.copyPatern(pattern1, answers.length);
		int[] pattern2Answer = this.copyPatern(pattern2, answers.length);
		int[] pattern3Answer = this.copyPatern(pattern3, answers.length);

		int index = 0;
		int patter1Count = 0;
		int patter2Count = 0;
		int patter3Count = 0;
		for (int i : answers) {
			if (pattern1Answer[index] == i) {
				patter1Count++;
			}
			if (pattern2Answer[index] == i) {
				patter2Count++;
			}
			if (pattern3Answer[index] == i) {
				patter3Count++;
			}
			index++;
		}

		List<Student> winners = new ArrayList<>();
		winners.add(new Student(1, patter1Count));
		winners.add(new Student(2, patter2Count));
		winners.add(new Student(3, patter3Count));
		Collections.sort(winners);
		answers = Student.getWinner(winners);
		System.out.println(Arrays.toString(answers));

		int[] testEqualToArray = {1};//{1, 2, 3};
		assertThat(answers).isEqualTo(testEqualToArray);
	}

	static class Student implements Comparable<Student> {
		int name;
		int count;

		public Student(int name, int count) {
			this.name = name;
			this.count = count;
		}

		public static int[] getWinner(List<Student> students) {
			int index = 0;
			List<Integer> winner = new ArrayList<>();
			do {
				winner.add(students.get(index).name);
				index++;
			} while ((index < students.size())
				&& students.get(index - 1).count == students.get(index).count);
			return winner.stream().mapToInt(i -> i).toArray();
		}

		@Override
		public int compareTo(Student o) {
			if (this.count < o.count) {
				return 1;
			}
			if (this.count == o.count && this.name > o.name) {
				return 1;
			}
			return -1;
		}
	}
}
