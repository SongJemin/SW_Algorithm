import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1012번_유기농배추
public class Baekjoon_1012 {
	
	static int[] moveX = {-1, 1, 0, 0};		// 상,하,좌,우
	static int[] moveY = {0, 0, -1, 1};		// 상,하,좌,우
	
	static int[][] arr;
	static boolean[][] checkArr;
	static int inputRow, inputCol;
	static int bugNum;
	static int count;
	
	public static void main(String[] args) throws Exception {
		int inputCase;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		inputCase = Integer.parseInt(br.readLine());
		
		for(int a=1; a<=inputCase; a++){
			count = 0;
			st = new StringTokenizer(br.readLine());
			inputCol = Integer.parseInt(st.nextToken());
			inputRow = Integer.parseInt(st.nextToken());
			bugNum = Integer.parseInt(st.nextToken());
			
			arr = new int[inputRow][inputCol];
			checkArr = new boolean[inputRow][inputCol];
			
			for(int i=0; i<bugNum; i++){
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				arr[x][y] = 1;
			}
			
			for (int i = 0; i < inputRow; i++) {
				for (int j = 0; j < inputCol; j++) {
					if(arr[i][j] == 1 && !checkArr[i][j]){
						count += 1;
						dfs(i, j);
						
					}
				}
			}
			
			System.out.println(count);
		}
	}
	
	static public void dfs(int inputX, int inputY){
		int x = inputX;
		int y = inputY;
		
		for(int i=0; i<4; i++){
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];
			
			if(nextX >= 0 && nextX < inputRow && nextY >= 0 && nextY < inputCol && arr[nextX][nextY] == 1){
				if(!checkArr[nextX][nextY]){
					checkArr[nextX][nextY] = true;
					dfs(nextX, nextY);
				}
			}
		}
	}
}
