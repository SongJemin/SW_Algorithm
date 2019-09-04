
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_16236_아기상어 {

	static int inputSize;
	static int[][] map;
	static boolean[][] visited;
	static int sharkSize;
	static int count, fishSize;
	static PriorityQueue<Pair> pq;

	static int[] moveX = { -1, 0, 0, 1 }; // 상, 좌, 우, 하
	static int[] moveY = { 0, -1, 1, 0 }; // 상, 좌, 우, 하
	static Queue<Pair> que;
	static List<Pair> pairList;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		inputSize = Integer.parseInt(br.readLine());
		fishSize = 0;
		pq = new PriorityQueue<>();

		map = new int[inputSize][inputSize];
		visited = new boolean[inputSize][inputSize];
		que = new LinkedList<>();
		count = 0;
		pairList = new ArrayList<>();

		for (int i = 0; i < inputSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < inputSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {

					sharkSize = 2;
					map[i][j] = 0;
					que.add(new Pair(i, j, 0));
					visited[i][j] = true;
				}
			}
		}
		bfs();
		System.out.println(count);
	}

	public static void bfs() {

		while(true){
			// 갈 곳이 없음
			if(que.isEmpty()){
				break;
			}

			// 갈 곳이 있는 경우
			while (!que.isEmpty()) {
				Pair pair = que.remove();
				int x = pair.x;
				int y = pair.y;
				int time = pair.time;

				for (int i = 0; i < 4; i++) {
					int nextX = x + moveX[i];
					int nextY = y + moveY[i];

					if (nextX >= 0 && nextX < inputSize && nextY >= 0 && nextY < inputSize) {

						if (!visited[nextX][nextY]) {
							// 못 지나감
							if (map[nextX][nextY] > sharkSize)
								continue;
							// 그냥 지나갈 경우
							if (map[nextX][nextY] == sharkSize || map[nextX][nextY] == 0) {
								pairList.add(new Pair(nextX, nextY, time + 1));
								visited[nextX][nextY] = true;
							}
							// 작은 물고기를 잡아먹을 경우
							if (map[nextX][nextY] < sharkSize && map[nextX][nextY] != 0) {
								// 우선순위 큐에 저장
								pq.add(new Pair(nextX, nextY, time + 1));
							}
						}
					}
				}
			}
			if(pq.isEmpty()){
				for (int i = 0; i < pairList.size(); i++) {
					que.add(pairList.get(i));
				}
				pairList.clear();
				continue;
			}

			else{
				Pair p = pq.remove();
				count += p.time;

				fishSize += 1;
				if(sharkSize == fishSize){
					sharkSize += 1;
					fishSize = 0;
				}

				for (int i = 0; i < inputSize; i++) {
					for (int j = 0; j < inputSize; j++) {
						visited[i][j] = false;
					}
				}
				visited[p.x][p.y] = true;
				map[p.x][p.y] = 0;

				pq.clear();
				pairList.clear();
				que.clear();
				que.add(new Pair(p.x, p.y, 0));
			}
		}
	}

	public static class Pair implements Comparable<Pair> {

		int x;
		int y;
		int time;

		public Pair(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public int compareTo(Pair p) {
			if (this.x < p.x) {
				return -1;
			} else if (this.x == p.x) {
				if (this.y < p.y) {
					return -1;
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		}
	}
}
