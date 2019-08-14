import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1244 {

	static int[] arr;
	static Integer[] comparedArr;

	public static void main(String[] args) throws Exception {

		int inputCase, transNum;
		String inputString;
		int maxValue, maxIndex;
		int duplicateNum;
		int[] duplicate;	// 중복값 개수 확인 배열
		int duplicateSize;	// 중복횟수
		List<SaveData> datas;	// 중복값과 교체되는 데이터 저장하는 배열

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		inputCase = Integer.parseInt(br.readLine());

		for (int a = 1; a <= inputCase; a++) {
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			inputString = st.nextToken();
			transNum = Integer.parseInt(st.nextToken());
			duplicate = new int[10];
			duplicateSize = 0;
			datas = new ArrayList<>();

			arr = new int[inputString.length()];
			// 내림차순으로 정렬한 배열
			comparedArr = new Integer[inputString.length()];

			for (int i = 0; i < inputString.length(); i++) {
				arr[i] = inputString.charAt(i) - '0';
				duplicate[arr[i]] += 1;
			}

			for (int i = 0; i < arr.length; i++) {
				comparedArr[i] = arr[i];
			}

			Arrays.sort(comparedArr, Collections.reverseOrder());
			duplicateNum = 0;

			lb: for (int t = 0; t < transNum; t++) {

				maxValue = Integer.MIN_VALUE;
				maxIndex = -1;

				// 기준 값 동일하다면
				while (arr[t] == comparedArr[t]) {
					t++;
					if (t == inputString.length()) {
						break lb;
					}
				}
				// 최댓값 찾기위한 포문
				for (int i = t; i < inputString.length(); i++) {
					int num = arr[i];
					if (maxValue <= num) {

						maxIndex = i;
						maxValue = num;
					}
				}

				// swap
				int temp = arr[t];
				arr[t] = arr[maxIndex];
				arr[maxIndex] = temp;

				// 중복데이터 2개 이상일 경우 배열에 추가
				if (duplicate[arr[t]] > 1) {
					duplicateSize += 1;
					datas.add(new SaveData(maxIndex, arr[maxIndex]));
				}

			}
			
			// 객체 정렬
			if (duplicateSize > 1) {
				Collections.sort(datas, new Comparator<SaveData>() {

					@Override
					public int compare(SaveData o1, SaveData o2) {
						return o1.position - o2.position;
					}
				});

				List<Integer> saveList = new ArrayList<>();
				for (int s = 0; s < datas.size(); s++) {
					saveList.add(new Integer(datas.get(s).position));
				}

				Collections.sort(datas, new Comparator<SaveData>() {

					@Override
					public int compare(SaveData o1, SaveData o2) {
						return o2.value - o1.value;
					}
				});

				for (int s = 0; s < datas.size(); s++) {
					arr[saveList.get(s)] = datas.get(s).value;
				}
			}

			if (transNum > arr.length && transNum % 2 == 0) {
				int temp = arr[arr.length - 1];
				arr[arr.length - 1] = arr[arr.length - 2];
				arr[arr.length - 2] = temp;
			}
			for (int k = 0; k < arr.length; k++) {
				sb.append(arr[k]);
			}
			System.out.println("#" + a + " " + sb);
		}

	}

	// 중복 데이터랑 교환되는 데이터 값 저장하는 클래스
	static class SaveData {
		int position;
		int value;

		public SaveData(int position, int value) {
			super();
			this.position = position;
			this.value = value;
		}
	}
}
