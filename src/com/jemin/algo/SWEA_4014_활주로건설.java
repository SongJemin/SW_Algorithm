import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014_Ȱ�ַΰǼ� {

	static int size, airStripLength;
	static int[][] map;
	static boolean[][] visited;
	static int result;

	public static void main(String[] args) throws Exception {
		int inputCase;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		inputCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= inputCase; t++) {
			st = new StringTokenizer(br.readLine());
			result = 0;

			size = Integer.parseInt(st.nextToken());
			airStripLength = Integer.parseInt(st.nextToken());
			map = new int[size][size];
			visited = new boolean[size][size];

			for (int i = 0; i < size; i++) {

				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < size; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// ���� üũ
			for (int i = 0; i < size; i++) {
				boolean flag = true;
				for (int j = 1; j < size; j++) {
					if (map[i][j - 1] == map[i][j]) {
						continue;
					} else if (map[i][j - 1] != map[i][j]) {
						// ���� ���� 1 Ŭ ��
						if (map[i][j - 1] - map[i][j] == 1) {
							for (int k = 0; k < airStripLength; k++) {
								if (j + k < size) {
									if (map[i][j + k] != map[i][j] || visited[i][j + k]) {
										flag = false;
										break;
									}
								} else {
									flag = false;
									break;
								}
							}
							if (flag) {
								for (int k = 0; k < airStripLength; k++) {
									visited[i][j + k] = true;
								}
							}
						}
						// ���� ���� 1���� ��
						else if (map[i][j] - map[i][j - 1] == 1) {
							for (int k = 0; k < airStripLength; k++) {
								if (j - 1 - k >= 0) {
									if (map[i][j - 1 - k] != map[i][j - 1] || visited[i][j - 1 - k]) {
										flag = false;
										break;
									}
								} else {
									flag = false;
									break;
								}
							}
							if (flag) {
								for (int k = 0; k < airStripLength; k++) {
									visited[i][j - k - 1] = true;
								}
							}
						}
						// ���̰� 2�̻� ���̳���
						else {
							flag = false;
						}
					}
				}
				if (flag) {
					result += 1;
				}
			}

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					visited[i][j] = false;
				}
			}
			// ���� üũ
			for (int j = 0; j < size; j++) {
				boolean flag = true;
				for (int i = 1; i < size; i++) {
					if (map[i-1][j] == map[i][j]) {
						continue;
					} else if (map[i-1][j] != map[i][j]) {
						// ���� ���� 1 Ŭ ��
						if (map[i-1][j] - map[i][j] == 1) {
							for (int k = 0; k < airStripLength; k++) {
								if (i + k < size) {
									if (map[i+k][j] != map[i][j] || visited[i+k][j]) {
										flag = false;
										break;
									}
								} else {
									flag = false;
									break;
								}
							}
							if (flag) {
								for (int k = 0; k < airStripLength; k++) {
									visited[i+k][j] = true;
								}
							}
						}
						// ���� ���� 1���� ��
						else if (map[i][j] - map[i-1][j] == 1) {
							for (int k = 0; k < airStripLength; k++) {
								if (i-1-k >= 0) {
									if (map[i-1-k][j] != map[i-1][j] || visited[i-1-k][j]) {
										flag = false;
										break;
									}
								} else {
									flag = false;
									break;
								}
							}
							if (flag) {
								for (int k = 0; k < airStripLength; k++) {
									visited[i-1-k][j] = true;
								}
							}
						}
						// ���̰� 2�̻� ���̳���
						else {
							flag = false;
						}
					}
				}
				if (flag) {
					result += 1;
				}
			}
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}
}
