import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014_활주로건설 {

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

			// 가로 체크
			for (int i = 0; i < size; i++) {
				boolean flag = true;
				for (int j = 1; j < size; j++) {
					if (map[i][j - 1] == map[i][j]) {
						continue;
					} else if (map[i][j - 1] != map[i][j]) {
						// 왼쪽 값이 1 클 때
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
						// 왼쪽 값이 1작을 때
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
						// 높이가 2이상 차이날때
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
			// 세로 체크
			for (int j = 0; j < size; j++) {
				boolean flag = true;
				for (int i = 1; i < size; i++) {
					if (map[i-1][j] == map[i][j]) {
						continue;
					} else if (map[i-1][j] != map[i][j]) {
						// 위쪽 값이 1 클 때
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
						// 위쪽 값이 1작을 때
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
						// 높이가 2이상 차이날때
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
