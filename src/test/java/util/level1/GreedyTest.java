package util.level1;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class GreedyTest {

	@Test
		// @checkstyle:off
	void 체육복() {
		// @checkstyle:on

		// n	lost	reserve		return
		// 5	[2, 4]	[1, 3, 5]	5
		// 5	[2, 4]	[3]			4
		// 3	[3]		[1]			2
		// 5	[2, 3]	[1, 3, 5]	4

		/**/
		int n = 5;
		int[] lost = {2, 4};
		int[] reserve = {1, 3, 5};
		int returnValue = 5;
		// (5 + (rentCount.대여받은수.lost.size=2)) - (lost.잃어버린수.lost.size=2)
		/**/

		/*/
		int n = 5;
		int[] lost = {2, 4};
		int[] reserve = {3};
		int returnValue = 4;
		/**/

		/*/
		int n = 3;
		int[] lost = {3};
		int[] reserve = {1};
		int returnValue = 2;
		/**/

		/*/
		int n = 5;
		int[] lost = {2, 3};
		int[] reserve = {1, 3, 5};
		int returnValue = 5;
		/**/

		/*/
		// 30 - 2;
		// = 28;
		// 28 - 4;
		// = 24;
		// 5 -> 4;
		// 24 + 1
		// = 25;

		int n = 30;
		int[] lost = {1,2,4,12,29,30};
		int[] reserve = {1,5,6,7,8,9,30};
		int returnValue = 27;
		/**/

		/*/
		//addNormalCount
		// 30 - (6 + 9);
		// = 28;
		// 28 - 4;
		// = 24;
		// 5 -> 4;
		// 24 + 1;
		// = 25;
		// 11 -> 12;
		// 25 + 1;
		// = 26;
		// 28 -> 29;
		// 26 + 1;
		// = 27;

		int n = 30;
		int[] lost = {1, 2, 4, 12, 29, 30};
		int[] reserve = {1, 5, 6, 7, 8, 9, 11, 28, 30};
		int returnValue = 29;
		/**/

		int answer;

		List<Integer> lostList = Arrays.stream(lost).boxed().collect(Collectors.toList());
		List<Integer> reserveList = Arrays.stream(reserve).boxed().collect(Collectors.toList());

		GymClothes gymClothes = new GymClothes(n, reserveList, lostList);
		gymClothes.removeDuplicate();
		gymClothes.eatLost();

		answer = gymClothes.getTotalCount();
		System.out.println(answer);

		assertThat(answer).isEqualTo(returnValue);
	}

	class GymClothes {
		private List<Integer> reserveList;
		private List<Integer> lostList;
		private int totalCount;

		public GymClothes(int totalCount, List<Integer> reserveList, List<Integer> lostList) {
			this.totalCount = totalCount;
			this.reserveList = reserveList;
			this.lostList = lostList;
		}

		private void rentReserve(int beforeLost, int lost) {
			int index = this.reserveList.indexOf(beforeLost);
			if (-1 != index) {
				this.reserveList.remove(index);
				this.lostList.remove((Integer)lost);
			}
		}

		private void eatLostRemove(int lost) {
			int beforeLost = lost - 1;
			int afterLost = lost + 1;
			if (beforeLost >= 0 && this.reserveList.contains(beforeLost)) {
				this.rentReserve(beforeLost, lost);
				return;
			}
			if (this.reserveList.contains(afterLost)) {
				this.rentReserve(afterLost, lost);
			}
		}

		public int getTotalCount() {
			return this.totalCount  - this.lostList.size();
		}

		public void removeDuplicate() {
			List<Integer> lostTemp = new ArrayList<>(this.lostList);
			List<Integer> reserveTemp = new ArrayList<>(this.reserveList);
			this.reserveList.removeAll(lostTemp);
			this.lostList.removeAll(reserveTemp);
		}

		public void eatLost() {
			for (int i : new ArrayList<>(this.lostList)) {
				this.eatLostRemove(i);
			}
		}

	}
}
