import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_13460_����Ż��2 {
	
	static int[] moveX = {-1, 1, 0, 0};		// ��, ��, ��, ��
	static int[] moveY = {0, 0, -1, 1};		// ��, ��, ��, ��
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
		
		// �� ���
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
				
				// ���� ���� ���� ��ġ�� ���� ���� �ȿ� ������ && �湮 ���ߴ��� üũ
				while(redNextX + moveX[i] >= 0 && redNextX + moveX[i] < row && redNextY + moveY[i] >= 0
						&& redNextY + moveY[i] < col && map[redNextX+moveX[i]][redNextY+moveY[i]] != '#') {
				
					redNextX += moveX[i];
					redNextY += moveY[i];
					
					// ���� ���� ���ۿ� ���� 
					if(map[redNextX][redNextY] == 'O') {
						redBallFlag = true;
						break;
					}
				}
					
				
				// �Ķ� ���� ���� ��ġ�� ���� ���� �ȿ� ������ && �湮 ���ߴ��� üũ
				while(blueNextX + moveX[i] >= 0 && blueNextX + moveX[i] < row && blueNextY + moveY[i] >= 0
						&& blueNextY + moveY[i] < col && map[blueNextX+moveX[i]][blueNextY+moveY[i]] != '#') {
				
					blueNextX += moveX[i];
					blueNextY += moveY[i];
					
					// ���� ���� ���ۿ� ���� 
					if(map[blueNextX][blueNextY] == 'O') {
						blueBallFlag = true;
						break;
					}
					
				}
				
				// ���� ���� ��ġ�� ���� && . ��ġ ==> ��ġ ����
				if(redNextX == blueNextX && redNextY == blueNextY && map[redNextX][redNextY] == '.') {
					// ���� ����
					if(i == 0) {
						// ���� ���� �Ʒ��� �Ķ� ����
						if(redX < blueX) {
							blueNextX = redNextX+1;
						}
						// �Ķ� ���� �����ʿ� ���� ����
						else {
							redNextX = blueNextX+1;
						}
					}
					// �Ʒ��� ����
					else if(i == 1) {
						// ���� ���� �Ʒ��� �Ķ� ����
						if(redX < blueX) {
							redNextX = blueNextX-1;
						}
						// �Ķ� ���� �Ʒ��� ���� ����
						else {
							blueNextX = redNextX-1;
						}
					}
					// ���� ����
					else if(i == 2) {
						// ���� ���� �����ʿ� �Ķ� ����
						if(redY < blueY) {
							blueNextY = redNextY+1;;
						}
						// �Ķ� ���� �����ʿ� ���� ����
						else {
							redNextY = blueNextY+1;
						}
					}
					// ������ ����
					else {
						// ���� ���� �����ʿ� �Ķ� ����
						if(redY < blueY) {
							redNextY = blueNextY-1;
						}
						// �Ķ� ���� �����ʿ� ���� ����
						else {
							blueNextY = redNextY-1;
						}
					}
				}
				
				// �Ѵ� ���ÿ� ��
				if(redBallFlag && blueBallFlag) {
					
				}
				// ������ ������ ��
				else if(redBallFlag && !blueBallFlag) {
					resultFlag = true;
					result = time;
					break lb;
				}
				// �Ķ��� ������ ��
				else if(!redBallFlag && blueBallFlag){
				}
				// �� �� �ȵ�
				else {
					// �߰�
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
