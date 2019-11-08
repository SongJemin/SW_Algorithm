package hit1;

import java.util.Arrays;

public class KAKAO_BLIND_2020_자물쇠와열쇠 {

	public static boolean solution(int[][] key, int[][] lock) {
		boolean answer = true;
		int keySize = key.length;
		int[][] lockCopy = lock;
		int lockSize = lock.length;

		int[][] largeLock = new int[lockSize + (keySize - 1) * 2][lockSize + (keySize - 1) * 2];
		for (int row = 0; row < largeLock.length; row++) {
			Arrays.fill(largeLock[row], -1);
		}
		for (int i = 0; i < lockSize; i++) {
			for (int j = 0; j < lockSize; j++) {
				largeLock[(keySize - 1) + i][(keySize - 1) + j] = lock[i][j];
			}
		}

		for (int t = 0; t < 4; t++) {

			// 반복
			for (int i = 0; i < lockSize + (key.length - 1); i++) {
				for (int j = 0; j < lockSize + (key.length - 1); j++) {
					answer = true;
					// 검사 시작
					for (int m = i; m < keySize + i; m++) {
						for (int n = j; n < keySize + j; n++) {
							// 돌기끼리 만날 때
							if (largeLock[m][n] == 1 && key[m - i][n - j] == 1) {
								largeLock[m][n] = 0;
								break;
							}
							// 자물쇠 구멍 && 키 돌기
							else if (largeLock[m][n] == 0 && key[m - i][n - j] == 1) {
								largeLock[m][n] = 1;
							}
						}
					}
					lb: for (int m = 0; m < lockSize; m++) {
						for (int n = 0; n < lockSize; n++) {
							if (largeLock[m + keySize - 1][n + keySize - 1] == 0) {
								answer = false;
								break lb;
							}
						}
					}
					// 완벽
					if (answer) {
						return true;
					}

					// 초기화
					for (int m = 0; m < lockSize; m++) {
						for (int n = 0; n < lockSize; n++) {
							largeLock[m + keySize - 1][n + keySize - 1] = lockCopy[m][n];

						}
					}
				}
			}
			// 끝까지 다 검사한 후에 회전
			key = cycle(key);
		}
		return answer;
	}

	public static int[][] cycle(int[][] key) {
		int[][] cycle = key;
		int N = key.length;
		int temp;

		for (int i = 0; i < N / 2; i++) {
			// 마지막 전까지 반복
			for (int j = i; j < N - i - 1; j++) {
				// 왼쪽 위 임시저장
				temp = cycle[i][j];

				// 위(왼->오) <- 왼쪽(아래->위)
				cycle[i][j] = cycle[N - 1 - j][i];

				// 왼쪽(아래->위) <- 아래(오->왼)
				cycle[N - 1 - j][i] = cycle[N - 1 - i][N - 1 - j];

				// 아래(오->왼) <- 오른쪽(위->아래)
				cycle[N - 1 - i][N - 1 - j] = cycle[j][N - 1 - i];

				// 오른쪽(위->아래) <- 임시 저장값
				cycle[j][N - 1 - i] = temp;
			}
		}
		return cycle;
	}

	public static void main(String[] args) {

		int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };

		boolean result = solution(key, lock);
		System.out.println(result);
	}
}
