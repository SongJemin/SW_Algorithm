import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jungol_1733_���� {

	public static int[] moveX = { 1, 0, 1, 1 }; // ��, ��, ����, ����
	public static int[] moveY = { 0, 1, -1, 1 }; // ��, ��, ����, ����

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

				// ���� ���ɼ� ���� �ľ�
				if (nextX - moveX[i] >= 0 && nextX - moveX[i] < 19 && nextY - moveY[i] >= 0 && nextY - moveY[i] < 19) {
					if (map[nextX - moveX[i]][nextY - moveY[i]] == color) {
						continue;
					}
				}
				while (true) {

					nextX += moveX[i];
					nextY += moveY[i];

					// ���� ��
					if (nextX >= 0 && nextX < 19 && nextY >= 0 && nextY < 19) {
						// �̹� �湮�� ���� �ƴϰ� && ���� ���� ���̸�
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
							// �������� üũ
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
