import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_15685_�巡��Ŀ�� {
	
	static int[] moveX = {1, 0, -1, 0};	// 0 : ��, 1 : ��, 2 : ��, 3 : ��
	static int[] moveY = {0, -1, 0, 1}; // 0 : ��, 1 : ��, 2 : ��, 3 : ��
	
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
		
		// �巡�� Ŀ�� �Է�
		for (int i = 0; i < inputCase; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			direction = Integer.parseInt(st.nextToken());
			gen = Integer.parseInt(st.nextToken());
			
			dragons.add(new DragonCurv(x, y, direction, gen));
		}
		
		// �巡�� Ŀ�� ������ŭ �ݺ�
		for (int i = 0; i < dragons.size(); i++) {
			// �迭 �ʱ�ȭ
			curvs = new ArrayList<>();
			// ù �̵� ���� ����
			curvs.add(dragons.get(i).direction);
			
			// �Է��� ������� �ݺ�
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
			// ��ǥ �Է�
			for (int j = 0; j <curvs.size(); j++) {
				
				// ���� 0�� ���, ����������
				if(curvs.get(j) == 0) {
					// ���� ���̸�
					if(startX + 1 <= 100) {
						startX += 1;
						map[startY][startX] = 1;
					}
					// ���� ���̸� �̵� ����
					else break;
				}
				// ���� 1�� ���, ����
				else if(curvs.get(j) == 1) {
					// ���� ���̸�
					if(startY - 1 >= 0) {
						startY -= 1;
						map[startY][startX] = 1;
					}
					// ���� ���̸� �̵� ����
					else break;
				}
				// ���� 2�� ���, ��������
				else if(curvs.get(j) == 2) {
					// ���� ���̸�
					if(startX - 1 >= 0) {
						startX -= 1;
						map[startY][startX] = 1;
					}
					// ���� ���̸� �̵� ����
					else break;
				}
				// ���� 3�� ���, �Ʒ���
				else {
					// ���� ���̸�
					if(startY + 1 <= 100) {
						startY += 1;
						map[startY][startX] = 1;
					}
					// ���� ���̸� �̵� ����
					else break;
				}
			}
		}
		// �簢�� ���� ã��
		check();

		// �� ���
		System.out.println(result);
	}
	
	// �簢�� ���� �˻�
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
