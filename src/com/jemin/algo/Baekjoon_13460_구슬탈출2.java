import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_13460_구슬탈출2 {
	
	static int[] moveX = {-1, 1, 0, 0};		// 상, 하, 좌, 우
	static int[] moveY = {0, 0, -1, 1};		// 상, 하, 좌, 우
	static int row, col;
	static char[][] map;
	static boolean[][][][] visited;
	static int redBallX, redBallY;
	static int blueBallX, blueBallY;
	static boolean resultFlag, redBallFlag, blueBallFlag;
	static int redNextX, redNextY, blueNextX, blueNextY;
	static Queue<Pair> que;
	static int result = -1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		que = new LinkedList<Pair>();
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		map = new char[row][col];
		visited = new boolean[row][col][row][col];
		
		for (int i = 0; i < row; i++) {
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {

				if(str.charAt(j) == 'R') {
					redBallX = i;
					redBallY = j;
					map[i][j] = '.';
				}
				else if(str.charAt(j) == 'B') {
					blueBallX = i;
					blueBallY = j;
					map[i][j] = '.';
				}
				else {
					map[i][j] = str.charAt(j);
				}
			}
		}
		
		que.add(new Pair(redBallX, redBallY, blueBallX, blueBallY, 1));
		bfs();
		
		// 답 출력
		System.out.println(result);
		
	}
	
	public static void bfs() {
		
lb:		while(!que.isEmpty()) {
			
			Pair pair = que.remove();	
			int redX = pair.redX;
			int redY = pair.redY;
			int blueX = pair.blueX;
			int blueY = pair.blueY;
			
			int time = pair.time;
			
			if(time > 10) return;
						
			for (int i = 0; i < 4; i++) {
				redNextX = redX;
				redNextY = redY;
				blueNextX = blueX;
				blueNextY = blueY;
				
				// 빨간 구슬 다음 위치가 지도 범위 안에 들어가는지 && 방문 안했는지 체크
				while(redNextX + moveX[i] >= 0 && redNextX + moveX[i] < row && redNextY + moveY[i] >= 0
						&& redNextY + moveY[i] < col && map[redNextX+moveX[i]][redNextY+moveY[i]] != '#') {
				
					redNextX += moveX[i];
					redNextY += moveY[i];
					
					// 빨간 구슬 구멍에 퐁당 
					if(map[redNextX][redNextY] == 'O') {
						redBallFlag = true;
						break;
					}
				}
					
				
				// 파란 구슬 다음 위치가 지도 범위 안에 들어가는지 && 방문 안했는지 체크
				while(blueNextX + moveX[i] >= 0 && blueNextX + moveX[i] < row && blueNextY + moveY[i] >= 0
						&& blueNextY + moveY[i] < col && map[blueNextX+moveX[i]][blueNextY+moveY[i]] != '#') {
				
					blueNextX += moveX[i];
					blueNextY += moveY[i];
					
					// 빨간 구슬 구멍에 퐁당 
					if(map[blueNextX][blueNextY] == 'O') {
						blueBallFlag = true;
						break;
					}
					
				}
				
				// 둘이 같은 위치에 도착 && . 위치 ==> 위치 변경
				if(redNextX == blueNextX && redNextY == blueNextY && map[redNextX][redNextY] == '.') {
					// 위쪽 방향
					if(i == 0) {
						// 빨간 구슬 아래에 파란 구슬
						if(redX < blueX) {
							blueNextX = redNextX+1;
						}
						// 파란 구슬 오른쪽에 빨간 구슬
						else {
							redNextX = blueNextX+1;
						}
					}
					// 아래쪽 방향
					else if(i == 1) {
						// 빨간 구슬 아래에 파란 구슬
						if(redX < blueX) {
							redNextX = blueNextX-1;
						}
						// 파란 구슬 아래에 빨간 구슬
						else {
							blueNextX = redNextX-1;
						}
					}
					// 왼쪽 방향
					else if(i == 2) {
						// 빨간 구슬 오른쪽에 파란 구슬
						if(redY < blueY) {
							blueNextY = redNextY+1;;
						}
						// 파란 구슬 오른쪽에 빨간 구슬
						else {
							redNextY = blueNextY+1;
						}
					}
					// 오른쪽 방향
					else {
						// 빨간 구슬 오른쪽에 파란 구슬
						if(redY < blueY) {
							redNextY = blueNextY-1;
						}
						// 파란 구슬 오른쪽에 빨간 구슬
						else {
							blueNextY = redNextY-1;
						}
					}
				}
				
				// 둘다 동시에 들어감
				if(redBallFlag && blueBallFlag) {
					
				}
				// 빨간색 구슬만 들어감
				else if(redBallFlag && !blueBallFlag) {
					resultFlag = true;
					result = time;
					break lb;
				}
				// 파란색 구슬만 들어감
				else if(!redBallFlag && blueBallFlag){
				}
				// 둘 다 안들어감
				else {
					// 추가
					if(!visited[redNextX][redNextY][blueNextX][blueNextY]) {
						visited[redNextX][redNextY][blueNextX][blueNextY] = true;
						que.add(new Pair(redNextX, redNextY, blueNextX, blueNextY, time+1));
					}
					
				}

				redBallFlag = false;
				blueBallFlag = false;
			}
		}
	}
	
	public static class Pair{
		int redX;
		int redY;
		int blueX;
		int blueY;
		int time;

		public Pair(int redX, int redY, int blueX, int blueY, int time) {
			this.redX = redX;
			this.redY = redY;
			this.blueX = blueX;
			this.blueY = blueY;
			this.time = time;
		}
	}
}
