import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_17070_파이프옮기기1 {
	
	static int[] moveX = {0, 1, 1};		// 우, 대각(우하), 하
	static int[] moveY = {1, 1, 0};		// 우, 대각(우하), 하
	
	static int size;
	static int[][] map;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		size = Integer.parseInt(br.readLine());
		map = new int[size][size];
		StringTokenizer st;
		
		// 지도에 값 입력
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// dfs 시작
		dfs(0, 1, 0);
		System.out.println(result);
	}
	
	public static void dfs(int inputX, int inputY, int inputStatus) {

		int x = inputX;
		int y = inputY;
		int status = inputStatus;
		
		// 마지막점 도착 O
		if(x == (size-1) && y == (size-1)) result += 1;
		
		// 마지막점 도착 X
		else {
			// 오른쪽 모양
			if(status == 0) {
				// 오른쪽 방향 검사
				checkRight(x, y);
				// 대각선 방향 검사
				checkDiag(x, y);
			}
			// 대각선 모양
			else if(status == 1) {
				
				// 오른쪽 방향 검사
				checkRight(x, y);
				// 대각선 방향 검사
				checkDiag(x, y);
				// 아래쪽 방향 검사
				checkBottom(x,y);
				
			}
			// 아래 모양
			else {
			
				// 대각선 방향 검사
				checkDiag(x, y);
				// 아래쪽 방향 검사
				checkBottom(x,y);
			}
		}

	}
	
	// 오른쪽 검사
	public static void checkRight(int inputX, int inputY) {
		int x = inputX;
		int y = inputY;
		
		int nextX = x;
		int nextY = y+1;
		
		// 범위 조사
		if(nextY < size) {
			// 해당 범위에 1값이 아니라면
			if(map[nextX][nextY] != 1) {
				dfs(nextX, nextY, 0);							
			}
		}
	}
	
	// 대각선 검사
	public static void checkDiag(int inputX, int inputY) {
		int x = inputX;
		int y = inputY;
		
		int nextX = x+1;
		int nextY = y+1;
		
		// 범위 조사
		if(nextX < size && nextY < size) {
			// 해당 범위에 1값이 아니라면
			if(map[nextX][nextY] != 1 && map[nextX-1][nextY] != 1 && map[nextX][nextY-1] != 1) {
				dfs(nextX, nextY, 1);							
			}
		}
	}
	
	// 아래쪽 검사
	public static void checkBottom(int inputX, int inputY) {
		int x = inputX;
		int y = inputY;
			
		int nextX = x+1;
		int nextY = y;
			
		// 범위 조사
		if(nextX < size) {
			// 해당 범위에 1값이 아니라면
			if(map[nextX][nextY] != 1) {
				dfs(nextX, nextY, 2);							
			}
		}
	}
}
