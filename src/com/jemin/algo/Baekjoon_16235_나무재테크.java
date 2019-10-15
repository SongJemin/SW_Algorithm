import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_16235_��������ũ {
	
	static PriorityQueue<Tree>[][] pq;
	static Queue<Tree> aliveTreeQue, deathTreeQue;
	static int mapSize, treeNum, requiredTime;
	static int[][] map;
	static int[][] nutMap;
	
	static int[] moveX = {-1, 1, 0, 0, -1, -1, 1, 1}; // ��, ��, ��, ��, ����, ���, ����, �Ͽ�
	static int[] moveY = {0, 0, -1, 1, -1, 1, -1, 1}; // ��, ��, ��, ��, ����, ���, ����, �Ͽ�
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		aliveTreeQue = new LinkedList<>();	// ����� ��� ������ ���� �����ϴ� ť
		deathTreeQue = new LinkedList<>();  // ����� ����� �ȵǾ� �״� ���� �����ϴ� ť
		int treeX, treeY, treeAge;
		int ans = 0;
		int tempTreeX, tempTreeY, tempTreeAge;
		
		mapSize = Integer.parseInt(st.nextToken());
		treeNum = Integer.parseInt(st.nextToken());
		requiredTime = Integer.parseInt(st.nextToken());
		
		pq = new PriorityQueue[mapSize][mapSize];			// ����ִ� ���� �����ϴ� �켱����ť(���� ���� ������)
		
		map = new int[mapSize][mapSize];
		nutMap = new int[mapSize][mapSize];
		
		for (int i = 0; i < mapSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < mapSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// ����� 5�� ����
				nutMap[i][j] = 5;
				pq[i][j] = new PriorityQueue<>();
			}
		}
		
		for (int i = 0; i < treeNum; i++) {
			st = new StringTokenizer(br.readLine());
			treeX = Integer.parseInt(st.nextToken()) - 1;
			treeY = Integer.parseInt(st.nextToken()) - 1;
			treeAge = Integer.parseInt(st.nextToken());
			pq[treeX][treeY].add(new Tree(treeX, treeY, treeAge));
		}

		// requiredTime��ŭ ����
		for (int t = 0; t < requiredTime; t++) {
			// ��
			// 1. ��� ����
			// 1-1. ������ ���� ������
			for (int i = 0; i < mapSize; i++) {
				for (int j = 0; j < mapSize; j++) {
					while(!pq[i][j].isEmpty()) {
						Tree tempTree = pq[i][j].remove();
						tempTreeX = tempTree.x;
						tempTreeY = tempTree.y;
						tempTreeAge = tempTree.age;
						
						// (��� >= ����) -> ����
						if(nutMap[tempTreeX][tempTreeY] >= tempTreeAge) {
							// ���� ���� ���� && ����� ����
							int nut = nutMap[tempTreeX][tempTreeY] - tempTreeAge;
							// ����� �� �� ����
							nutMap[tempTreeX][tempTreeY] = nut;
							// ���� 1�� ����
							aliveTreeQue.add(new Tree(tempTreeX, tempTreeY, tempTreeAge + 1));
						}
						
						// (��� < ����) -> ����
						else {
							deathTreeQue.add(new Tree(tempTreeX, tempTreeY, tempTreeAge));
						}
					}
				}
			}
			
			
			// ����
			// 2. ���� ������ ����� �Ѹ���
			while(!deathTreeQue.isEmpty()) {
				Tree deathTree = deathTreeQue.remove();
				tempTreeX = deathTree.x;
				tempTreeY = deathTree.y;
				int nut = deathTree.age/2;
				
				nutMap[tempTreeX][tempTreeY] += nut;
			}
			ans = 0;
			
			// ����
			// 1. ���� ���̰� 5�� ������ ����
			while(!aliveTreeQue.isEmpty()) {
				Tree aliveTree = aliveTreeQue.remove();
				tempTreeX = aliveTree.x;
				tempTreeY = aliveTree.y;
				tempTreeAge = aliveTree.age;
				
				// ���̰� 5�� ������
				if(tempTreeAge % 5 == 0) {
					for (int i = 0; i < 8; i++) {
						int nextX = tempTreeX + moveX[i];
						int nextY = tempTreeY + moveY[i];
						
						if(nextX >= 0 && nextX < mapSize && nextY >= 0 && nextY < mapSize) {
							// �켱����ť�� ���� �߰�
							ans += 1;
							pq[nextX][nextY].add(new Tree(nextX, nextY, 1));
						}
					}
				}
				// �ڱ� �ڽŵ� �߰�
				ans += 1;
				pq[tempTreeX][tempTreeY].add(new Tree(tempTreeX, tempTreeY, tempTreeAge));
			}
			
			// �ܿ�
			for (int i = 0; i < mapSize; i++) {
				for (int j = 0; j < mapSize; j++) {
					// 1. ����� �߰�
					nutMap[i][j] += map[i][j];
				}
			}
		}
		// �� ���
		System.out.println(ans);
		
	}
	
	public static class Tree implements Comparable<Tree>{
		int x;
		int y;
		int age;
		
		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}

		// ���� �������� ������
		@Override
		public int compareTo(Tree o) {
			if(this.age < o.age) {
				return -1;
			}
			else {
				return 1;
			}
		}
	}
}
