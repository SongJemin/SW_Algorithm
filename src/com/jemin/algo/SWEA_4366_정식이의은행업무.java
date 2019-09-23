import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SWEA_4366_��������������� {
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

			// 2���� ����
			for (int i = 0; i < binSb.length(); i++) {
				// 0�̶��
				if (binSb.charAt(i) == '0') {
					binSb.setCharAt(i, '1');
					binArr.add(binSb.toString());
					binSb.setCharAt(i, '0');
				}
				// 1�̶��
				else {
					binSb.setCharAt(i, '0');
					binArr.add(binSb.toString());
					binSb.setCharAt(i, '1');
				}
			}
			// 3���� ����
			for (int i = 0; i < triSb.length(); i++) {
				// 0�̶��
				if (triSb.charAt(i) == '0') {
					triSb.setCharAt(i, '1');
					triArr.add(triSb.toString());
					triSb.setCharAt(i, '2');
					triArr.add(triSb.toString());
					triSb.setCharAt(i, '0');
				}
				// 1�̶��
				else if (triSb.charAt(i) == '1') {
					triSb.setCharAt(i, '0');
					triArr.add(triSb.toString());
					triSb.setCharAt(i, '2');
					triArr.add(triSb.toString());
					triSb.setCharAt(i, '1');
				}
				// 2���
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
			// ��� 2���� -> 10���� ��ȯ
			for (int i = 0; i < binArr.size(); i++) {
				size = binArr.get(i).length();
				for (int j = 0; j < binArr.get(i).length(); j++) {
					size--;
					bins[i] += (binArr.get(i).charAt(j) - '0') * Math.pow(2, size);
				}
			}

			// ��� 3���� -> 10���� ��ȯ
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
