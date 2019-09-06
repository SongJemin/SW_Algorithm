import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_17281_야구 {

	static int[][] baseball;
	static int max = 0;
	static boolean[] roo = new boolean[4];
	static int outScore;
	static int score;
	static int inning;
	static int player[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		inning = Integer.parseInt(br.readLine());
		baseball = new int[inning][10];
		player = new int[10];

		// 1번 선수를 4번타자로
		player[4] = 1;
		player[1] = 4;
		
		// 나머지 선수들 순서대로 일단 배치
		for (int i = 2; i < 10; i++) {
			if (i == 4)
				continue;
			player[i] = i;
		}

		for (int i = 0; i < inning; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				baseball[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 순열 시작
		perm(player, 1, 10);
		// 최대값 출력
		System.out.println(max);
	}

	// 순열
	static public void perm(int[] player, int step, int size) {
		if (step == size) {
			int hit;
			int index = 1;
			score = 0;
			outScore = 0;
			for (int i = 1; i < 4; i++) {
				roo[i] = false;
			}
			for (int t = 0; t < inning; t++) {
				while (true) {
					hit = baseball[t][player[index]];

					// 1루타일 경우
					if (hit == 1) {
						if (roo[3]) {
							score++;
							roo[3] = false;
						}
						if (roo[2]) {
							roo[3] = true;
							roo[2] = false;
						}
						if (roo[1]) {
							roo[2] = true;
							// 1루도 차므로 true 유지
						}
						roo[1] = true;
					}
					// 2루타일 경우
					else if (hit == 2) {

						if (roo[3]) {
							score++;
							roo[3] = false;
						}
						if (roo[2]) {
							score++;
							// 2루도 차므로 true 유지
						}
						if (roo[1]) {
							roo[3] = true;
							roo[1] = false;
						}
						roo[2] = true;
					} else if (hit == 3) {
						if (roo[3]) {
							score++;
							// 3루도 차므로 true 유지
						}
						if (roo[2]) {
							score++;
							roo[2] = false;
						}
						if (roo[1]) {
							score++;
							roo[1] = false;
						}
						roo[3] = true;
					} else if (hit == 4) {
						if (roo[3]) {
							score++;
							roo[3] = false;
						}
						if (roo[2]) {
							score++;
							roo[2] = false;
						}
						if (roo[1]) {
							score++;
							roo[1] = false;
						}
						score++;
					}
					// hit == 0(아웃)일 경우
					else {
						outScore++;
						if (outScore == 3) {
							outScore = 0;
							for (int i = 1; i < 4; i++) {
								roo[i] = false;
							}
							if (t == (inning - 1)) {
								if (max < score) {
									max = score;
								}
							}
							index += 1;
							if (index == 10) {
								index = 1;
							}
							break;
						}
					}
					index += 1;
					if (index == 10) {
						index = 1;
					}
				}
			}

		} else {
			for (int i = step; i < 10; i++) {
				// 4번 타자는 고정이므로 넘김
				if (i == 4)
					i = 5;
				int temp = player[step];
				player[step] = player[i];
				player[i] = temp;

				// 4번 타자는 고정이므로 넘김
				if (step + 1 == 4)
					perm(player, step + 2, size);
				else
					perm(player, step + 1, size);

				temp = player[step];
				player[step] = player[i];
				player[i] = temp;
			}
		}
	}
}
