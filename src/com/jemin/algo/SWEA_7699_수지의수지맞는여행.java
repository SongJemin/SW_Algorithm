import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[] moveX = {-1, 1, 0, 0};	// 상, 하, 좌, 우
	static int[] moveY = {0, 0, -1, 1};	// 상, 하, 좌, 우
	
	static char[][] arr;
	static boolean[][] check;
	static int inputRow, inputCol;
	static boolean[] visit;
	static int max;
	
	public static void main(String[] args) throws Exception {
		
		int inputCase;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		inputCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int a = 1; a <= inputCase; a++) {
			st = new StringTokenizer(br.readLine());
			// 알파벳 존재 체크
			visit = new boolean['Z'+1];	// 'A : 65' ~ 'Z : 90' 그대로 받음 
			max = Integer.MIN_VALUE;
			
			inputRow = Integer.parseInt(st.nextToken());
			inputCol = Integer.parseInt(st.nextToken());
			arr = new char[inputRow][inputCol];			// 알파벳 입력 배열
			check = new boolean[inputRow][inputCol];	// 알파벳 방문 체크배열
			
			for (int i = 0; i < inputRow; i++) {
				// 더 빠르다 for문 사용해 charAt로 자르는 것보다
				arr[i] = br.readLine().toCharArray();	// 1차원 배열을 통째로 받기
			}
			visit[arr[0][0]] = true;	// 방문 체크
			// dfs 시작
			dfs(0, 0, 1);
			sb.append("#" + a + " " + max + "\n");
		}
		System.out.println(sb);
	} // end of main
	
	public static void dfs(int inputX, int inputY, int inputCount) {
		if(max == 26) return;

		int x = inputX;
		int y = inputY;
		int count = inputCount;
		visit[arr[inputX][inputY]] = true;
		check[x][y] = true;
		
		for (int i = 0; i < 4; i++) {
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];

			if(nextX >= 0 && nextX < inputRow && nextY >= 0 && nextY < inputCol) {
				if(!visit[arr[nextX][nextY]] && !check[nextX][nextY]) {
					dfs(nextX, nextY, count+1);
				}
			}
		}
		check[x][y] = false;
		visit[arr[x][y]] = false;
		if(max < count) {
			max = count;
		}
		
	}
} // end of class
