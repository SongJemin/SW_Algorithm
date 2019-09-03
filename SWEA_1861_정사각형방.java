import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1861_정사각형방_송제민 {
	
	static int inputSize;
	static int[][] map;
	static boolean[][] visited;
	static int max, maxIndex;
	static int step;
	
	static int[] moveX = {-1, 1, 0, 0}; 	// 상, 하, 좌, 우
	static int[] moveY = {0, 0, -1, 1}; 	// 상, 하, 좌, 우
	
	public static void main(String[] args) throws Exception {
		int inputCase;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		inputCase = Integer.parseInt(br.readLine());
		
		for (int a = 1; a <= inputCase; a++) {
			
			inputSize = Integer.parseInt(br.readLine());
			map = new int[inputSize][inputSize];
			visited = new boolean[inputSize][inputSize];
			
			max = Integer.MIN_VALUE;
			
			for (int i = 0; i < inputSize; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < inputSize; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < inputSize; i++) {
				for (int j = 0; j < inputSize; j++) {
					step = 0;
					dfs(i, j);
					if(max == step) {
						if(maxIndex < map[i][j]) continue;
						maxIndex = map[i][j];
					}
					if(max < step) {
						max = step;
						maxIndex = map[i][j];
					}
				}
			}
			sb.append("#" + a + " " + maxIndex + " " + max + "\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int inputX, int inputY) {
		int x = inputX;
		int y = inputY;
		visited[x][y] = true;
		step += 1;
		
		for (int i = 0; i < 4; i++) {
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];
			
			if(nextX >= 0 && nextX < inputSize && nextY >= 0 && nextY < inputSize
					&& (map[x][y] + 1 == map[nextX][nextY]) ) {
				if(!visited[nextX][nextY]) {
					dfs(nextX, nextY);
				}
			}
		}
		visited[x][y] = false;
	}
}
