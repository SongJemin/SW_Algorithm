
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// ���� 17142_������3
public class Baekjoon_17142 {

	static int[] moveX = { -1, 1, 0, 0 }; // ��, ��, ��, ��
	static int[] moveY = { 0, 0, -1, 1 }; // ��, ��, ��, ��

	static int inputSize, virusNum;
	static int[][] arr;
	static Virus[] virArr;
	static int[][] copyArr;
	static int[] numArr;
	static int[] tempArr;
	static boolean[][] checkArr;

	static int min = Integer.MAX_VALUE;
	static Queue<Virus> que = new LinkedList<>();

	static boolean sameFlag;
	static int time;
	static int maxTime;
	static int sameCount;
	static int cnt;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		inputSize = Integer.parseInt(st.nextToken()); // ������ ũ��
		virusNum = Integer.parseInt(st.nextToken()); // ���̷��� ����
		virArr = new Virus[10]; // ���̷��� ��ü �迭

		arr = new int[inputSize][inputSize]; // ������ ������ �迭
		copyArr = new int[inputSize][inputSize]; // ������ ������ ī�� �迭
		checkArr = new boolean[inputSize][inputSize]; // ���̷��� ���� üũ �迭, true :
														// ������
		int count = 0;

		// ������ ������ �Է�
		for (int i = 0; i < inputSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < inputSize; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i][j] = num;
				copyArr[i][j] = num;
				// ���̷����� ���, ���̷��� �迭�� �ش� ��ġ ����
				if (num == 2) {
					virArr[count++] = new Virus(i, j, 0, 0, 0);
					arr[i][j] = -1; // �ĺ�
					copyArr[i][j] = -1;
				} else if (num == 1) {
					arr[i][j] = -3; // ��
					copyArr[i][j] = -3;
				} else {
					arr[i][j] = -4; // �� ����
					copyArr[i][j] = -4;
				}
			}
		}
		// ������ ���� ���� �迭
		tempArr = new int[virusNum];
		numArr = new int[count];

		for (int i = 0; i < count; i++) {
			numArr[i] = i;
		}

		sameFlag = true;
		sameCount = 0;
		comb(count, virusNum);

		System.out.println(min);

	}

	public static void comb(int n, int r) {
		if (r == 0) { // ������Ʈ
			maxTime = Integer.MIN_VALUE;

			for (int i = 0; i < tempArr.length; i++) {
				arr[virArr[tempArr[i]].x][virArr[tempArr[i]].y] = 0; // Ȱ��ȭ

				que.add(new Virus(virArr[tempArr[i]].x, virArr[tempArr[i]].y, 0, 1, 0));
				checkArr[virArr[tempArr[i]].x][virArr[tempArr[i]].y] = true;
			}
			bfs();
			// �� ä������ Ȯ���ϴ� flag ����
			sameFlag = true;

			for (int i = 0; i < inputSize; i++) {
				for (int j = 0; j < inputSize; j++) {
					if (arr[i][j] == -4) {
						// �� ä���� ����
						sameFlag = false;
					}
				}
			}
			// �� ä���� ���
			if (sameFlag) {
				sameCount++;
				// �������� �� ä������ ���ٸ�
				if (min == -1) {
					// ���� �� ����
					min = maxTime;
				}
				// ���� ä������ �ִٸ� �� ���� ��
				else if (min > maxTime) {
					min = maxTime;
				}
			}
			// �� ��ä���� ���
			else {
				// �������� �� ä������ ���ٸ�
				if (sameCount == 0) {
					// -1�� ����
					min = -1;
				}
			}

			// �ʱ�ȭ
			for (int i = 0; i < inputSize; i++) {
				for (int j = 0; j < inputSize; j++) {
					arr[i][j] = copyArr[i][j];
					checkArr[i][j] = false;
				}
			}

		} else if (n < r) {
			return;
		} else { // �����Ʈ
			tempArr[r - 1] = numArr[n - 1];
			comb(n - 1, r - 1);
			comb(n - 1, r);
		}

	}

	static public void bfs() {

		while (!que.isEmpty()) {
			Virus virus = que.remove();

			int x = virus.x;
			int y = virus.y;
			int status = virus.status;
			int cnt = virus.cnt;

			time = virus.time;
			if (maxTime < time) {
				maxTime = time;
			}

			for (int i = 0; i < 4; i++) {
				int nextX = x + moveX[i];
				int nextY = y + moveY[i];

				if (nextX >= 0 && nextX < inputSize && nextY >= 0 && nextY < inputSize) {

					// ���� �ƴ϶��
					if (arr[nextX][nextY] != -3) {
						// �� ���� �� ���� �ƴ϶��
						if (!checkArr[nextX][nextY]) {

							// ���� ���� ��Ȱ��ȭ ���̷������
							if (status == 0) {
								// ���� ���µ� ��Ȱ��ȭ ���̷������
								if (arr[nextX][nextY] == -1) {
									// ť�� ����
									que.add(new Virus(nextX, nextY, time, 0, ++cnt));
									arr[nextX][nextY] = time;
									checkArr[nextX][nextY] = true;
								} 
								// ���� ���´� ��Ȱ��ȭ ���̷����� �ƴ϶��
								else {
									if (arr[nextX][nextY] < time + 1) {
										// ���ݱ��� ���� cnt+1 ����ŭ time�� ����
										que.add(new Virus(nextX, nextY, time + cnt + 1, 1, 0));
										arr[nextX][nextY] = time + cnt + 1;
										checkArr[nextX][nextY] = true;
									}
								}
							} 
							// ���� ���� ������̶��
							else {
								// cnt �ʱ�ȭ
								cnt = 0;
								// ���� ���°� ��Ȱ��ȭ ���̷������
								if (arr[nextX][nextY] == -1) {
									que.add(new Virus(nextX, nextY, time, 0, ++cnt));
									arr[nextX][nextY] = time;
									checkArr[nextX][nextY] = true;
								} 
								// ���� ���µ� ������̶��
								else {
									if (arr[nextX][nextY] < time + 1) {
										que.add(new Virus(nextX, nextY, time + 1, 1, 0));
										arr[nextX][nextY] = time + 1;
										checkArr[nextX][nextY] = true;
									}
								}
							}
						}
					}
				}
			}

		}

	}

	public static class Virus {
		int x;
		int y;
		int time;
		int status;
		int cnt;

		public Virus(int x, int y, int time, int status, int cnt) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.status = status;
			this.cnt = cnt;
		}
	}
}