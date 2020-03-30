import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_16296 {

	public static void main(String[] args) throws Exception {
		int[][] map;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = br.readLine();
		
		StringTokenizer st = new StringTokenizer(inputStr, " ");
		int row, col, R;
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[row][col];
		
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				map[i][j] =Integer.parseInt(st.nextToken());
			}
		}
		
		int minValue = Math.min(row, col);
		
		for (int t = 0; t < R; t++) {
			for (int r = 0; r < minValue/2; r++) {
				int top = r;
				int bottom = row-1-r;
				int left = r;
				int right = col-1-r;

				int num = map[r][r];
				
				for (int i = left; i < right; i++) {
					map[top][i] = map[top][i+1];
				}
				for (int i = top; i < bottom; i++) {
					map[i][right] = map[i+1][right];
				}
				for (int i = right; i > left; i--) {
					map[bottom][i] = map[bottom][i-1];
				}
				for (int i = bottom; i > top; i--) {
					map[i][left] = map[i-1][left];
				}
				map[r+1][r] = num;
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
