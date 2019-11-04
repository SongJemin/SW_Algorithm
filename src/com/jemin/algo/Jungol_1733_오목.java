import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jungol_1733_오목 {

	public static int[] moveX = { 1, 0, 1, 1 }; // 하, 우, 좌하, 우하
	public static int[] moveY = { 0, 1, -1, 1 }; // 하, 우, 좌하, 우하

	public static int[][] map;
	public static Queue<Pair> que;
	static boolean flag = false;
	static int resultColor, resultX, resultY;

	public static void main(String[] args) throws Exception {

		map = new int[19][19];
		StringTokenizer st;
		que = new LinkedList<Pair>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		resultColor = 0;

		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					que.add(new Pair(i, j));
				}
			}
		}

		lb: while (!que.isEmpty()) {
			Pair p = que.remove();
			int x = p.x;
			int y = p.y;
			int color = map[x][y];

			for (int i = 0; i < 4; i++) {
				int nextX = x;
				int nextY = y;
				int count = 1;

				// 육목 가능성 여부 파악
				if (nextX - moveX[i] >= 0 && nextX - moveX[i] < 19 && nextY - moveY[i] >= 0 && nextY - moveY[i] < 19) {
					if (map[nextX - moveX[i]][nextY - moveY[i]] == color) {
						continue;
					}
				}
				while (true) {

					nextX += moveX[i];
					nextY += moveY[i];

					// 범위 안
					if (nextX >= 0 && nextX < 19 && nextY >= 0 && nextY < 19) {
						// 이미 방문한 곳이 아니고 && 같은 색깔 돌이면
						if (color == map[nextX][nextY]) {
							count++;
						} else {
							if (count == 5) {
								if (i == 2) {
									resultX = nextX - 1;
									resultY = nextY + 1;
								} else {
									resultX = x;
									resultY = y;
								}
								resultColor = color;
								flag = true;
								break lb;
							}
							break;
						}
					} else {
						if (count == 5) {
							// 육목인지 체크
							if (i == 2) {
								resultX = nextX - 1;
								resultY = nextY + 1;
							} else {
								resultX = x;
								resultY = y;
							}
							resultColor = color;
							flag = true;
							break lb;
						}
						break;
					}
				}
			}
		}

		System.out.println(resultColor);
		if (flag)
			System.out.println((resultX + 1) + " " + (resultY + 1));
	}

	public static class Pair {

		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
