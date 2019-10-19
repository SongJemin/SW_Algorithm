import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_15685_드래곤커브 {
	
	static int[] moveX = {1, 0, -1, 0};	// 0 : 우, 1 : 상, 2 : 좌, 3 : 하
	static int[] moveY = {0, -1, 0, 1}; // 0 : 우, 1 : 상, 2 : 좌, 3 : 하
	
	static int inputCase;
	static List<DragonCurv> dragons;
	static int[][] map = new int[101][101];	// 0~100, 0~100
	static int result = 0;
	static List<Integer> tempList;
	static List<Integer> curvs;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		inputCase = Integer.parseInt(br.readLine());
		dragons = new ArrayList<>();
		int x, y, direction, gen;
		
		// 드래곤 커브 입력
		for (int i = 0; i < inputCase; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			direction = Integer.parseInt(st.nextToken());
			gen = Integer.parseInt(st.nextToken());
			
			dragons.add(new DragonCurv(x, y, direction, gen));
		}
		
		// 드래곤 커브 개수만큼 반복
		for (int i = 0; i < dragons.size(); i++) {
			// 배열 초기화
			curvs = new ArrayList<>();
			// 첫 이동 방향 저장
			curvs.add(dragons.get(i).direction);
			
			// 입력한 세대까지 반복
			for (int j = 1; j <= dragons.get(i).gen; j++) {

				int size = curvs.size();
				for (int k = size-1; k >= 0; k--) {
					int val = curvs.get(k) + 1;
					if(val == 4) val = 0;
					curvs.add(val);
				}
			}
			
			int startX = dragons.get(i).x;
			int startY = dragons.get(i).y;
			
			map[startY][startX] = 1;
			// 좌표 입력
			for (int j = 0; j <curvs.size(); j++) {
				
				// 방향 0일 경우, 오른쪽으로
				if(curvs.get(j) == 0) {
					// 범위 안이면
					if(startX + 1 <= 100) {
						startX += 1;
						map[startY][startX] = 1;
					}
					// 범위 밖이면 이동 중지
					else break;
				}
				// 방향 1일 경우, 위로
				else if(curvs.get(j) == 1) {
					// 범위 안이면
					if(startY - 1 >= 0) {
						startY -= 1;
						map[startY][startX] = 1;
					}
					// 범위 밖이면 이동 중지
					else break;
				}
				// 방향 2일 경우, 왼쪽으로
				else if(curvs.get(j) == 2) {
					// 범위 안이면
					if(startX - 1 >= 0) {
						startX -= 1;
						map[startY][startX] = 1;
					}
					// 범위 밖이면 이동 중지
					else break;
				}
				// 방향 3일 경우, 아래로
				else {
					// 범위 안이면
					if(startY + 1 <= 100) {
						startY += 1;
						map[startY][startX] = 1;
					}
					// 범위 밖이면 이동 중지
					else break;
				}
			}
		}
		// 사각형 개수 찾기
		check();

		// 답 출력
		System.out.println(result);
	}
	
	// 사각형 개수 검사
	public static void check() {
		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 100; j++) {
				if(map[i][j] == 1 && map[i-1][j] == 1 && map[i][j-1] == 1 && map[i-1][j-1] == 1) {
					result += 1;
				}
			}
		}
	}
	
	static class DragonCurv{
		int x;
		int y;
		int direction;
		int gen;
		
		public DragonCurv(int x, int y, int direction, int gen) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.gen = gen;
		}
	}
}
