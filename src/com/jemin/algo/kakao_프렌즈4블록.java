package exam2018;

import java.util.*;

public class kakao_프렌즈4블록 {

	static int[] moveX = {0, 0, 1, 1};		// 0, 우, 하, 우하
	static int[] moveY = {0, 1, 0, 1};		// 0, 우, 하, 우하

	static char[][] map;
	static boolean[][] visited;
	static int[][] passed;
	static int count = 0;

	public static void main(String[] args) {

		int height1 = 4;
		int width1 = 5;
		String[] board1 = {"CCBDE", "AAADE", "AAABF", "CCBBF"};

		int height2 = 6;
		int width2 = 6;
		String[] board2 = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};

		int answer = solution(height1, width1, board1);
		System.out.println(answer);
	}

	public static int solution(int m, int n, String[] board) {
		int answer = 0;
		map  = new char[m][n];
		passed = new int[m][n];

		for (int i = 0; i < board.length; i++) {
			map[i] = board[i].toCharArray();
		}


		while(true) {
			visited = new boolean[m][n];
			int val = 0;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if(map[i][j] != '.') val += check(i, j, map[i][j]);
				}
			}
			if(val == 0) break;
			else {
				count += val;
			}
			// 라인 내리기
			for (int i = m-1; i >= 0; i--) {
				for (int j = 0; j < n; j++) {
					// 빈 공간일시
					if(passed[i][j] == 1) {
						int nextX = i;
						while(true) {
							nextX -= 1;
							if(nextX >= 0) {
								if(passed[nextX][j] == 1) continue;

								map[i][j] = map[nextX][j];
								map[nextX][j] = '.';
								passed[i][j] = 0;
								passed[nextX][j] = 1;
								break;
							}
							else {
								map[i][j] = '.';
								break;
							}
						}
					}
				}
			}
		}
		answer = count;
		return answer;
	}

	public static int check(int startX, int startY, int val) {
		int x = startX;
		int y = startY;

		int m = map.length;
		int n = map[0].length;
		int cnt = 0;

		boolean allChecked = true;

		for (int i = 0; i < 4; i++) {
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];

			// 맵 범위 안 && 같은 값일 경우
			if(nextX < m && nextY < n && (val == map[nextX][nextY])) {
			}
			else {
				allChecked = false;
			}
		}

		// 2*2 같은 값 성립할 경우
		if(allChecked) {
			for (int i = 0; i < 4; i++) {
				int nextX = x + moveX[i];
				int nextY = y + moveY[i];

				if(!visited[nextX][nextY]) cnt++;

				visited[nextX][nextY] = true;
				passed[nextX][nextY] = 1;
			}
		}
		return cnt;
	}
}
