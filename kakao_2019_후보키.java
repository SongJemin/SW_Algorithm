import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class kakao_2019_3 {

	static int row, col;
	static String[][] map;
	static boolean flag;
	static Set<String> set;
	static Set<String> minCheck;
	static ArrayList<HashSet<Integer>> setArr;
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};

		int result = solution(relation);
		System.out.println(result);
	}

	public static int solution(String[][] relation) {

		map = relation;
		setArr = new ArrayList<HashSet<Integer>>();
		
		row = map.length;
		col = map[0].length;
		int[] arr = new int[col];
		int[] output;

		for (int i = 0; i < col; i++) {
			arr[i] = i;
		}

		for (int i = 1; i <= col; i++) {
			output = new int[i];
			comb(arr, output, 0, col, i, 0);
		}
		
		return answer;
	}

	// 조합
	public static void comb(int[] arr, int[] output, int step, int n, int r, int target) {
		set = new HashSet<>();
		if(r == 0) {
			HashSet<Integer> candidateKey = new HashSet<>();
			flag = true;
			
			lb: for (int i = 0; i < row; i++) {
				String str = "";
				for (int j = 0; j < output.length; j++) {
					str += map[i][output[j]];
				}
				if(!set.contains(str)) set.add(str);
				else {
					flag = false;
					break lb;
				}
			}
			if(flag) {
				// 현재 후보키 모음에 추가
				for (int i = 0; i < output.length; i++) {
					candidateKey.add(output[i]);
				}
				for (int i = 0; i < setArr.size(); i++) {
					// 현재 후보키모음 > 이미 저장된 후보키 모음
					if(candidateKey.containsAll(setArr.get(i))) return;
				}
				// 후보키 모음에 추가
				setArr.add(candidateKey);
				answer += 1;
			}
		}
		else if(target == n) return;
		else {
			output[step] = arr[target];

			comb(arr, output, step+1, n, r-1, target+1);
			comb(arr, output, step, n, r, target+1);
		}
	}
}
