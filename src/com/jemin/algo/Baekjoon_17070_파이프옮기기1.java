import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_17070_�������ű��1 {
	
	static int[] moveX = {0, 1, 1};		// ��, �밢(����), ��
	static int[] moveY = {1, 1, 0};		// ��, �밢(����), ��
	
	static int size;
	static int[][] map;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		size = Integer.parseInt(br.readLine());
		map = new int[size][size];
		StringTokenizer st;
		
		// ������ �� �Է�
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// dfs ����
		dfs(0, 1, 0);
		System.out.println(result);
	}
	
	public static void dfs(int inputX, int inputY, int inputStatus) {

		int x = inputX;
		int y = inputY;
		int status = inputStatus;
		
		// �������� ���� O
		if(x == (size-1) && y == (size-1)) result += 1;
		
		// �������� ���� X
		else {
			// ������ ���
			if(status == 0) {
				// ������ ���� �˻�
				checkRight(x, y);
				// �밢�� ���� �˻�
				checkDiag(x, y);
			}
			// �밢�� ���
			else if(status == 1) {
				
				// ������ ���� �˻�
				checkRight(x, y);
				// �밢�� ���� �˻�
				checkDiag(x, y);
				// �Ʒ��� ���� �˻�
				checkBottom(x,y);
				
			}
			// �Ʒ� ���
			else {
			
				// �밢�� ���� �˻�
				checkDiag(x, y);
				// �Ʒ��� ���� �˻�
				checkBottom(x,y);
			}
		}

	}
	
	// ������ �˻�
	public static void checkRight(int inputX, int inputY) {
		int x = inputX;
		int y = inputY;
		
		int nextX = x;
		int nextY = y+1;
		
		// ���� ����
		if(nextY < size) {
			// �ش� ������ 1���� �ƴ϶��
			if(map[nextX][nextY] != 1) {
				dfs(nextX, nextY, 0);							
			}
		}
	}
	
	// �밢�� �˻�
	public static void checkDiag(int inputX, int inputY) {
		int x = inputX;
		int y = inputY;
		
		int nextX = x+1;
		int nextY = y+1;
		
		// ���� ����
		if(nextX < size && nextY < size) {
			// �ش� ������ 1���� �ƴ϶��
			if(map[nextX][nextY] != 1 && map[nextX-1][nextY] != 1 && map[nextX][nextY-1] != 1) {
				dfs(nextX, nextY, 1);							
			}
		}
	}
	
	// �Ʒ��� �˻�
	public static void checkBottom(int inputX, int inputY) {
		int x = inputX;
		int y = inputY;
			
		int nextX = x+1;
		int nextY = y;
			
		// ���� ����
		if(nextX < size) {
			// �ش� ������ 1���� �ƴ϶��
			if(map[nextX][nextY] != 1) {
				dfs(nextX, nextY, 2);							
			}
		}
	}
}
