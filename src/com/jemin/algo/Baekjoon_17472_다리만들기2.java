import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_17472_�ٸ������2 {
	
	static int[] moveX = {-1, 1, 0, 0};		// ��, ��, ��, ��
	static int[] moveY = {0, 0, -1, 1};		// ��, ��, ��, ��
	
	static Queue<Pair> que;
	static Queue<Pair> edgeQue;
	static int[][] map;
	static boolean[][] visited;
	static int row, col;
	static int count = 0;
	static List<Node>[] nodes;
	static int result = 0;
	static int connectIsland = 1;
	
	static boolean[] nodeVisited;
    static PriorityQueue<Node> pq;
    static Queue<Integer> nodeQue;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		que = new LinkedList<Pair>();
		edgeQue = new LinkedList<Pair>();
		
		pq = new PriorityQueue<>();
		nodeQue = new LinkedList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		map = new int[row][col];
		visited = new boolean[row][col];
		
		// �� �Է�
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// �� ����
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					que.add(new Pair(i, j, 0, 0, 0));
					visited[i][j] = true;
					count++;
					map[i][j] = count;
					bfs(count);
				}
			}
		}
		
		nodes = new ArrayList[count+1];
		nodeVisited = new boolean[count+1];
		
		for (int i = 1; i <= count; i++) {
			nodes[i] = new ArrayList<Node>();
		}
		
		// �ٸ� ���� �������� üũ
		connectionCheck();
		
		// 1�� �� ť�� �߰�
		nodeQue.add(1);
		
		// �ִ� �Ÿ� ��� ����
		start();
		
		if(connectIsland == count) System.out.println(result);
		else System.out.println(-1);

	}
	
	// �ٸ� ���ϱ�
	public static void start() {
		// ť �ݺ�
		while(!nodeQue.isEmpty()) {
			int index = nodeQue.remove();
			nodeVisited[index] = true;
			
			for (int i = 0; i < nodes[index].size(); i++) {
				pq.add(new Node(index, nodes[index].get(i).end, nodes[index].get(i).val));
			}
			
			// ���� �ʾҴ� �� �ٽ� ť�� �ֱ�
			while(!pq.isEmpty()) {
				Node tempNode = pq.remove();
				// �湮�� ���̶��
				if(nodeVisited[tempNode.end]) continue;
				// �湮���� ���� ���̶�� ť�� �߰�
				connectIsland++;
				nodeQue.add(tempNode.end);
				result += tempNode.val;
				break;
			}
		}
	}
	
	// �� ���� �ٸ� ����
	public static void connectionCheck() {
		while(!edgeQue.isEmpty()) {
			Pair pair = edgeQue.remove();
			int x = pair.x;
			int y = pair.y;
			int direction = pair.direction;
			int distance = pair.distance;
			int startVal = pair.startVal;
			
			// ���� ������ ���
			if(direction == 0) {
				int nextX = x - 1;
				// ���� ���� ���
				if(nextX >= 0) {
					// �ٸ� ���� ���
					if(map[nextX][y] != 0) {
						if(distance >= 2) nodes[startVal].add(new Node(startVal, map[nextX][y], distance));
					}
					// �ٴ��� ���
					else {
						edgeQue.add(new Pair(nextX, y, 0, distance+1, startVal));
					}
				}
			}
			// �Ʒ� ������ ���
			else if(direction == 1) {
				int nextX = x + 1;
				// ���� ���� ���
				if(nextX < row) {
					// �ٸ� ���� ���
					if(map[nextX][y] != 0) {
						if(distance >= 2) nodes[startVal].add(new Node(startVal, map[nextX][y], distance));
					}
					// �ٴ��� ���
					else {	
						edgeQue.add(new Pair(nextX, y, 1, distance+1, startVal));
					}
				}
			}
			
			// ���� ������ ���
			else if(direction == 2) {
				int nextY = y - 1;
				// ���� ���� ���
				if(nextY >= 0) {
					// �ٸ� ���� ���
					if(map[x][nextY] != 0) {
						if(distance >= 2) nodes[startVal].add(new Node(startVal, map[x][nextY], distance));
					}
					// �ٴ��� ���
					else {	
						edgeQue.add(new Pair(x, nextY, 2, distance+1, startVal));
					}
				}
			}	
			
			// ������ ������ ���
			else {
				int nextY = y + 1;
				// ���� ���� ���
				if(nextY < col) {
					// �ٸ� ���� ���
					if(map[x][nextY] != 0) {
						if(distance >= 2) nodes[startVal].add(new Node(startVal, map[x][nextY], distance));
					}
					// �ٴ��� ���
					else {	
						edgeQue.add(new Pair(x, nextY, 3, distance+1, startVal));
					}
				}
			}	
			
		}
	}
	
	public static void bfs(int count) {
		while(!que.isEmpty()) {
			
			Pair pair = que.remove();
			int x = pair.x;
			int y = pair.y;
			
			for (int i = 0; i < 4; i++) {
				int nextX = x + moveX[i];
				int nextY = y + moveY[i];
				
				if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col) {
					// �ֺ��� ���϶� && �湮X
					if(map[nextX][nextY] == 1 && !visited[nextX][nextY]) {
						visited[nextX][nextY] = true;
						map[nextX][nextY] = count;
						que.add(new Pair(nextX, nextY, 0, 0, 0));
					}
					
					// �ֺ��� �ٴ��� ��
					else if(map[nextX][nextY] == 0) {
						// �𼭸� ��ġ ť�� ����(i = 0 : ���� �ٴ�, 1 : �Ʒ��� �ٴ�, 2 : ������ �ٴ�, 3: �������� �ٴ�)
						edgeQue.add(new Pair(x, y, i, 0, map[x][y]));
					}
				}
			}
		}
	}
	
	public static class Node implements Comparable<Node>{
		int start;
		int end;
		int val;

		public Node(int start, int end, int val) {
			this.start = start;
			this.end = end;
			this.val = val;
		}

		@Override
		public int compareTo(Node o) {
			if(this.val < o.val){
				return -1;
			}
			else if(this.val == o.val) {
				return 0;
			}
			else {
				return 1;
			}
		}
	}

	public static class Pair{
		int x;
		int y;
		int direction;
		int distance;
		int startVal;
		
		public Pair(int x, int y, int direction, int distance, int startVal) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.distance = distance;
			this.startVal = startVal;
		}
	}
}
