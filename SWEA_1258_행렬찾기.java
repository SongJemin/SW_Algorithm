import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1258_행렬찾기_송제민 {
	
	static int inputSize;
	static int[][] map;
	static boolean[][] visited;
	static int[] moveX = {1, 0};		// 하, 우
	static int[] moveY = {0, 1};		// 하, 우
	static Queue<Pair> que;
	static List<Chemi> chemArr;
	static int maxRow, maxCol, size;
	
	public static void main(String[] args) throws Exception {
		int inputCase;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		inputCase = Integer.parseInt(br.readLine());
		
		for (int a = 1; a <= inputCase; a++) {
			
			que = new LinkedList<>();
			
			inputSize = Integer.parseInt(br.readLine());
			map = new int[inputSize][inputSize];
			visited = new boolean[inputSize][inputSize];
			chemArr = new ArrayList<>();
			
			for (int i = 0; i < inputSize; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < inputSize; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < inputSize; i++) {
				for (int j = 0; j < inputSize; j++) {
					if(!visited[i][j] && map[i][j] != 0) {
						maxRow = 0;
						maxCol = 0;
						que.add(new Pair(i, j));
						bfs();
						
						// 열 구하기
						int count = 1;
						while(true) {
							if(j+count >= inputSize || map[i][j+count] == 0) {
								break;
							}
							count++;
						}
						maxCol = count;
						
						// 행 구하기
						count = 1;
						while(true) {
							if(i + count >= inputSize || map[i+count][j] == 0) {
								break;
							}
							count++;
						}
						maxRow = count;
						
						chemArr.add(new Chemi(maxRow, maxCol, maxRow * maxCol));
						
					}
				}
			}
			
			Collections.sort(chemArr);
			sb.append("#" + a + " " + chemArr.size());
			
			for (int i = 0; i < chemArr.size(); i++) {
				sb.append(" " + chemArr.get(i).x + " " + chemArr.get(i).y);
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	public static void bfs() {
		while(!que.isEmpty()) {
			Pair pair = que.remove();
			
			int x = pair.x;
			int y = pair.y;
			
			for (int i = 0; i < 2; i++) {
				int nextX = x + moveX[i];
				int nextY = y + moveY[i];
				
				if(nextX < inputSize && nextY < inputSize && map[nextX][nextY] != 0 && !visited[nextX][nextY]) {
					que.add(new Pair(nextX, nextY));
					visited[nextX][nextY] = true;
				}
			}
			
		}
	}
	
	public static class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static class Chemi implements Comparable<Chemi>{
		int x;
		int y;
		int size;
		
		public Chemi(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Chemi [x=" + x + ", y=" + y + ", size=" + size + "]";
		}

		@Override
		public int compareTo(Chemi o) {
			if(this.size < o.size) {
				return -1;
			}
			else if(this.size == o.size) {
				if(this.x < o.size) {
					return -1;
				}else {
					return 1;
				}
			}
			else {
				return 1;
			}
		}
	}
}
