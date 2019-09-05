import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baekjoon_17136_색종이붙이기 {
	private static int[][] m;
	private static int min;
	private static int[] p = {0, 5, 5, 5, 5, 5}; // 색종이의 남은 장수, 0번은 안씀

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		m = new int[10][10];
		int sum = 0;	// 1의 개수를 누적할 변수
		for (int i = 0; i < 10; i++) {
			String str = br.readLine();
			for (int j = 0, index = 0; j < 10; j++, index += 2) {
				m[i][j] = str.charAt(index) - '0';
				sum += m[i][j];
			}
		}
		min = Integer.MAX_VALUE;
		if(sum == 0) {		  // 가지치기1
			min = 0;
		}
		else if(sum == 100) { // 가지치기2
			min = 4;
		} else {
			dfs(sum, 0);
		}
		System.out.println(min == Integer.MAX_VALUE ? -1 : min); // 색종이로 커버 불가능하면 -1
	} // end of main

	/** m[][] : 색종이를 붙이고 남은 숫자 배열, sum : 남은 1의 개수, cnt : 사용한 색종이의 개수 */
	private static void dfs(int sum, int cnt) {
		if(min < cnt) {	// 이미 최소값보다 커졌다면 하지마
			return;
		}
		else if(sum == 0) {	// m 배열에 더이상 1이 없음
			if(min > cnt) min = cnt;
			return;
		} else {	// 재귀파트
			int r = -1;
			int c = -1;
		ex: for (r = 0; r < 10; r++) {	// 맵에서 1의 위치를 체크
				for (c = 0; c < 10; c++) {
					if(m[r][c] == 1) {
						break ex;
					}
				}
			}
			int maxSize = 5;
			for (; maxSize >= 1; maxSize--) { // 큰 종이를 붙일 수 있다면, 그보다 작은 종이도 당연히 붙일 수 있음.
				if(check(r, c, maxSize)) { // r, c를 좌상단 사각형으로 maxSize 크기의 영역 안이 모두 1인지 체크
					break;
				}
			}
			for (int size = maxSize; size >= 1; size--) { // maxSize ~ 1까지의 크기 색종이를 붙여보자
				if(p [size] > 0) { // 사이즈의 색종이가 남아있으면 
					paint(r, c, size, 0); // 사이즈만큼의 사각영역을 0으로 덮자
					p[size]--; // 사용한 색종이 감소
					dfs(sum - size*size, cnt+1); // 다음 칸으로 재귀 호출
					paint(r, c, size, 1); // 사이즈만큼의 사각영역을 1으로 덮자 원복
					p[size]++;// 사용한 색종이 증가 원복
				}
			}
		}
	}

	/** (r,c) 좌표에서 시작하는  */
	public static void paint(int r, int c, int size, int val) {
		for (int i = r; i < r+size; i++) {
			for (int j = c; j < c+size; j++) {
				m[i][j] = val;
			}
		}
	}

	/** (r, c) 좌표에서 시작하는 size 크기의 정사각형 영역이 모두 1인지 검사하는 메서드 */
	public static boolean check(int r, int c, int size) {
		if(r + size > 10 || c + size > 10) { // 색종이가 영역에서 벗어남
			return false;
		}
		
		for (int i = r; i < r+size; i++) {
			for (int j = c; j < c+size; j++) {
				if(m[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
} // end of class