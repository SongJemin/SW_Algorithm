import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2468 {
	
	static int[] moveX = {-1, 1, 0, 0};	// 상, 하, 좌, 우
	static int[] moveY = {0, 0, -1, 1};	// 상, 하, 좌, 우
	
	static int inputSize;
	static int[][] arr;
	static boolean[][] check;
	static boolean[] height;
	static int max, region;
	static int rainHeight;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		inputSize = Integer.parseInt(br.readLine());
		arr = new int[inputSize][inputSize];
		check = new boolean[inputSize][inputSize];
		height = new boolean[101];
		max = Integer.MIN_VALUE;
		
		for (int i = 0; i < inputSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < inputSize; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				height[arr[i][j]] = true;
			}
		}
		
		for(int a = 0; a < 101; a++) {
			if(height[a]) {
				region = 0;
				rainHeight = a-1;
				init(); // boolean 값 초기화
				for (int i = 0; i < inputSize; i++) {
					for (int j = 0; j < inputSize; j++) {
						if(arr[i][j] > rainHeight && check[i][j]) {
							region += 1;
							dfs(i, j);
						}
					}
				}
				
				if(max < region) {
					max = region;
				}
			}
		}
		System.out.println(max);
		
	}
	
	static void dfs(int inputX, int inputY) {
		int x = inputX;
		int y = inputY;
		check[x][y] = false;
		
		for(int i=0; i<4; i++) {
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];
			
			if(nextX >= 0 && nextX < inputSize && nextY >= 0 && nextY < inputSize && check[nextX][nextY]){
				dfs(nextX, nextY);
			}
		}
	}
	
	static void init() {
		for (int i = 0; i < inputSize; i++) {
			for (int j = 0; j < inputSize; j++) {
				if(arr[i][j] > rainHeight) {
					check[i][j] = true;
				}else {
					check[i][j] = false;
				}				
			}
		}
	}
}
