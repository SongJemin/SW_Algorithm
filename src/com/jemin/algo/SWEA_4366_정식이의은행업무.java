import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SWEA_4366_정식이의은행업무 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int inputCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int a = 1; a <= inputCase; a++) {
			
			int result = 0;
			StringBuilder binSb = new StringBuilder();
			StringBuilder triSb = new StringBuilder();
			
			List<String> binArr = new ArrayList<>();
			List<String> triArr = new ArrayList<>();
			
			int[] bins;
			int[] tris;

			binSb = binSb.append(br.readLine());
			triSb = triSb.append(br.readLine());

			// 2진수 저장
			for (int i = 0; i < binSb.length(); i++) {
				// 0이라면
				if (binSb.charAt(i) == '0') {
					binSb.setCharAt(i, '1');
					binArr.add(binSb.toString());
					binSb.setCharAt(i, '0');
				}
				// 1이라면
				else {
					binSb.setCharAt(i, '0');
					binArr.add(binSb.toString());
					binSb.setCharAt(i, '1');
				}
			}
			// 3진수 저장
			for (int i = 0; i < triSb.length(); i++) {
				// 0이라면
				if (triSb.charAt(i) == '0') {
					triSb.setCharAt(i, '1');
					triArr.add(triSb.toString());
					triSb.setCharAt(i, '2');
					triArr.add(triSb.toString());
					triSb.setCharAt(i, '0');
				}
				// 1이라면
				else if (triSb.charAt(i) == '1') {
					triSb.setCharAt(i, '0');
					triArr.add(triSb.toString());
					triSb.setCharAt(i, '2');
					triArr.add(triSb.toString());
					triSb.setCharAt(i, '1');
				}
				// 2라면
				else {
					triSb.setCharAt(i, '1');
					triArr.add(triSb.toString());
					triSb.setCharAt(i, '0');
					triArr.add(triSb.toString());
					triSb.setCharAt(i, '2');
				}
			}

			bins = new int[binArr.size()];
			tris = new int[triArr.size()];

			int size;
			// 모든 2진수 -> 10진수 변환
			for (int i = 0; i < binArr.size(); i++) {
				size = binArr.get(i).length();
				for (int j = 0; j < binArr.get(i).length(); j++) {
					size--;
					bins[i] += (binArr.get(i).charAt(j) - '0') * Math.pow(2, size);
				}
			}

			// 모든 3진수 -> 10진수 변환
			lb: for (int i = 0; i < triArr.size(); i++) {
				size = triArr.get(i).length();
				for (int j = 0; j < triArr.get(i).length(); j++) {
					size--;
					tris[i] += (triArr.get(i).charAt(j) - '0') * Math.pow(3, size);
				}

				for (int s = 0; s < bins.length; s++) {
					if (tris[i] == bins[s]) {
						result = tris[i];
						break lb;
					}
				}
			}
			sb.append("#" + a + " " + result + "\n");
		}
		System.out.println(sb);
	}
}
