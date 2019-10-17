import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_17472_다리만들기2 {
	
	static int[] moveX = {-1, 1, 0, 0};		// 상, 하, 좌, 우
	static int[] moveY = {0, 0, -1, 1};		// 상, 하, 좌, 우
	
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
		
		// 값 입력
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬 조사
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
		
		// 다리 연결 가능한지 체크
		connectionCheck();
		
		// 1번 섬 큐에 추가
		nodeQue.add(1);
		
		// 최단 거리 계산 시작
		start();
		
		if(connectIsland == count) System.out.println(result);
		else System.out.println(-1);

	}
	
	// 다리 구하기
	public static void start() {
		// 큐 반복
		while(!nodeQue.isEmpty()) {
			int index = nodeQue.remove();
			nodeVisited[index] = true;
			
			for (int i = 0; i < nodes[index].size(); i++) {
				pq.add(new Node(index, nodes[index].get(i).end, nodes[index].get(i).val));
			}
			
			// 가지 않았던 점 다시 큐에 넣기
			while(!pq.isEmpty()) {
				Node tempNode = pq.remove();
				// 방문한 점이라면
				if(nodeVisited[tempNode.end]) continue;
				// 방문하지 않은 점이라면 큐에 추가
				connectIsland++;
				nodeQue.add(tempNode.end);
				result += tempNode.val;
				break;
			}
		}
	}
	
	// 섬 간의 다리 연결
	public static void connectionCheck() {
		while(!edgeQue.isEmpty()) {
			Pair pair = edgeQue.remove();
			int x = pair.x;
			int y = pair.y;
			int direction = pair.direction;
			int distance = pair.distance;
			int startVal = pair.startVal;
			
			// 위쪽 방향일 경우
			if(direction == 0) {
				int nextX = x - 1;
				// 범위 안일 경우
				if(nextX >= 0) {
					// 다른 섬일 경우
					if(map[nextX][y] != 0) {
						if(distance >= 2) nodes[startVal].add(new Node(startVal, map[nextX][y], distance));
					}
					// 바다일 경우
					else {
						edgeQue.add(new Pair(nextX, y, 0, distance+1, startVal));
					}
				}
			}
			// 아래 방향일 경우
			else if(direction == 1) {
				int nextX = x + 1;
				// 범위 안일 경우
				if(nextX < row) {
					// 다른 섬일 경우
					if(map[nextX][y] != 0) {
						if(distance >= 2) nodes[startVal].add(new Node(startVal, map[nextX][y], distance));
					}
					// 바다일 경우
					else {	
						edgeQue.add(new Pair(nextX, y, 1, distance+1, startVal));
					}
				}
			}
			
			// 왼쪽 방향일 경우
			else if(direction == 2) {
				int nextY = y - 1;
				// 범위 안일 경우
				if(nextY >= 0) {
					// 다른 섬일 경우
					if(map[x][nextY] != 0) {
						if(distance >= 2) nodes[startVal].add(new Node(startVal, map[x][nextY], distance));
					}
					// 바다일 경우
					else {	
						edgeQue.add(new Pair(x, nextY, 2, distance+1, startVal));
					}
				}
			}	
			
			// 오른쪽 방향일 경우
			else {
				int nextY = y + 1;
				// 범위 안일 경우
				if(nextY < col) {
					// 다른 섬일 경우
					if(map[x][nextY] != 0) {
						if(distance >= 2) nodes[startVal].add(new Node(startVal, map[x][nextY], distance));
					}
					// 바다일 경우
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
					// 주변이 섬일때 && 방문X
					if(map[nextX][nextY] == 1 && !visited[nextX][nextY]) {
						visited[nextX][nextY] = true;
						map[nextX][nextY] = count;
						que.add(new Pair(nextX, nextY, 0, 0, 0));
					}
					
					// 주변이 바다일 때
					else if(map[nextX][nextY] == 0) {
						// 모서리 위치 큐에 저장(i = 0 : 위가 바다, 1 : 아래가 바다, 2 : 왼쪽이 바다, 3: 오른쪽이 바다)
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
