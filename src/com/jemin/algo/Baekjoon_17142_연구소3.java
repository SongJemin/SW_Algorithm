
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// 백준 17142_연구소3
public class Baekjoon_17142 {

	static int[] moveX = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
	static int[] moveY = { 0, 0, -1, 1 }; // 상, 하, 좌, 우

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

		inputSize = Integer.parseInt(st.nextToken()); // 연구소 크기
		virusNum = Integer.parseInt(st.nextToken()); // 바이러스 개수
		virArr = new Virus[10]; // 바이러스 객체 배열

		arr = new int[inputSize][inputSize]; // 연구소 이차원 배열
		copyArr = new int[inputSize][inputSize]; // 연구소 데이터 카피 배열
		checkArr = new boolean[inputSize][inputSize]; // 바이러스 감염 체크 배열, true :
														// 감염됨
		int count = 0;

		// 연구소 데이터 입력
		for (int i = 0; i < inputSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < inputSize; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i][j] = num;
				copyArr[i][j] = num;
				// 바이러스일 경우, 바이러스 배열에 해당 위치 저장
				if (num == 2) {
					virArr[count++] = new Virus(i, j, 0, 0, 0);
					arr[i][j] = -1; // 후보
					copyArr[i][j] = -1;
				} else if (num == 1) {
					arr[i][j] = -3; // 벽
					copyArr[i][j] = -3;
				} else {
					arr[i][j] = -4; // 빈 공간
					copyArr[i][j] = -4;
				}
			}
		}
		// 조합을 위해 사용될 배열
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
		if (r == 0) { // 종료파트
			maxTime = Integer.MIN_VALUE;

			for (int i = 0; i < tempArr.length; i++) {
				arr[virArr[tempArr[i]].x][virArr[tempArr[i]].y] = 0; // 활성화

				que.add(new Virus(virArr[tempArr[i]].x, virArr[tempArr[i]].y, 0, 1, 0));
				checkArr[virArr[tempArr[i]].x][virArr[tempArr[i]].y] = true;
			}
			bfs();
			// 다 채웠는지 확인하는 flag 변수
			sameFlag = true;

			for (int i = 0; i < inputSize; i++) {
				for (int j = 0; j < inputSize; j++) {
					if (arr[i][j] == -4) {
						// 다 채우지 못함
						sameFlag = false;
					}
				}
			}
			// 다 채웠을 경우
			if (sameFlag) {
				sameCount++;
				// 이전까지 다 채운적이 없다면
				if (min == -1) {
					// 지금 값 저장
					min = maxTime;
				}
				// 전에 채운적이 있다면 그 값과 비교
				else if (min > maxTime) {
					min = maxTime;
				}
			}
			// 다 못채웠을 경우
			else {
				// 이전까지 다 채운적이 없다면
				if (sameCount == 0) {
					// -1값 저장
					min = -1;
				}
			}

			// 초기화
			for (int i = 0; i < inputSize; i++) {
				for (int j = 0; j < inputSize; j++) {
					arr[i][j] = copyArr[i][j];
					checkArr[i][j] = false;
				}
			}

		} else if (n < r) {
			return;
		} else { // 재귀파트
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

					// 벽이 아니라면
					if (arr[nextX][nextY] != -3) {
						// 이 전에 간 곳이 아니라면
						if (!checkArr[nextX][nextY]) {

							// 만약 현재 비활성화 바이러스라면
							if (status == 0) {
								// 다음 상태도 비활성화 바이러스라면
								if (arr[nextX][nextY] == -1) {
									// 큐에 저장
									que.add(new Virus(nextX, nextY, time, 0, ++cnt));
									arr[nextX][nextY] = time;
									checkArr[nextX][nextY] = true;
								} 
								// 다음 상태는 비활성화 바이러스가 아니라면
								else {
									if (arr[nextX][nextY] < time + 1) {
										// 지금까지 쌓인 cnt+1 값만큼 time에 저장
										que.add(new Virus(nextX, nextY, time + cnt + 1, 1, 0));
										arr[nextX][nextY] = time + cnt + 1;
										checkArr[nextX][nextY] = true;
									}
								}
							} 
							// 만약 현재 빈공간이라면
							else {
								// cnt 초기화
								cnt = 0;
								// 다음 상태가 비활성화 바이러스라면
								if (arr[nextX][nextY] == -1) {
									que.add(new Virus(nextX, nextY, time, 0, ++cnt));
									arr[nextX][nextY] = time;
									checkArr[nextX][nextY] = true;
								} 
								// 다음 상태도 빈공간이라면
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