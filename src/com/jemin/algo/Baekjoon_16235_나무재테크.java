import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_16235_나무재테크 {
	
	static PriorityQueue<Tree>[][] pq;
	static Queue<Tree> aliveTreeQue, deathTreeQue;
	static int mapSize, treeNum, requiredTime;
	static int[][] map;
	static int[][] nutMap;
	
	static int[] moveX = {-1, 1, 0, 0, -1, -1, 1, 1}; // 상, 하, 좌, 우, 상좌, 상우, 하좌, 하우
	static int[] moveY = {0, 0, -1, 1, -1, 1, -1, 1}; // 상, 하, 좌, 우, 상좌, 상우, 하좌, 하우
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		aliveTreeQue = new LinkedList<>();	// 영양소 흡수 가능한 나무 저장하는 큐
		deathTreeQue = new LinkedList<>();  // 영양소 흡수가 안되어 죽는 나무 저장하는 큐
		int treeX, treeY, treeAge;
		int ans = 0;
		int tempTreeX, tempTreeY, tempTreeAge;
		
		mapSize = Integer.parseInt(st.nextToken());
		treeNum = Integer.parseInt(st.nextToken());
		requiredTime = Integer.parseInt(st.nextToken());
		
		pq = new PriorityQueue[mapSize][mapSize];			// 살아있는 나무 저장하는 우선순위큐(나이 적은 순으로)
		
		map = new int[mapSize][mapSize];
		nutMap = new int[mapSize][mapSize];
		
		for (int i = 0; i < mapSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < mapSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 영양소 5씩 저장
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

		// requiredTime만큼 진행
		for (int t = 0; t < requiredTime; t++) {
			// 봄
			// 1. 양분 섭취
			// 1-1. 나무들 전부 꺼내기
			for (int i = 0; i < mapSize; i++) {
				for (int j = 0; j < mapSize; j++) {
					while(!pq[i][j].isEmpty()) {
						Tree tempTree = pq[i][j].remove();
						tempTreeX = tempTree.x;
						tempTreeY = tempTree.y;
						tempTreeAge = tempTree.age;
						
						// (양분 >= 나이) -> 성장
						if(nutMap[tempTreeX][tempTreeY] >= tempTreeAge) {
							// 나무 나이 증가 && 영양소 빼기
							int nut = nutMap[tempTreeX][tempTreeY] - tempTreeAge;
							// 영양소 뺀 값 저장
							nutMap[tempTreeX][tempTreeY] = nut;
							// 나이 1살 증가
							aliveTreeQue.add(new Tree(tempTreeX, tempTreeY, tempTreeAge + 1));
						}
						
						// (양분 < 나이) -> 죽음
						else {
							deathTreeQue.add(new Tree(tempTreeX, tempTreeY, tempTreeAge));
						}
					}
				}
			}
			
			
			// 여름
			// 2. 죽은 나무들 영양분 뿌리기
			while(!deathTreeQue.isEmpty()) {
				Tree deathTree = deathTreeQue.remove();
				tempTreeX = deathTree.x;
				tempTreeY = deathTree.y;
				int nut = deathTree.age/2;
				
				nutMap[tempTreeX][tempTreeY] += nut;
			}
			ans = 0;
			
			// 가을
			// 1. 나무 나이가 5의 배수라면 번식
			while(!aliveTreeQue.isEmpty()) {
				Tree aliveTree = aliveTreeQue.remove();
				tempTreeX = aliveTree.x;
				tempTreeY = aliveTree.y;
				tempTreeAge = aliveTree.age;
				
				// 나이가 5의 배수라면
				if(tempTreeAge % 5 == 0) {
					for (int i = 0; i < 8; i++) {
						int nextX = tempTreeX + moveX[i];
						int nextY = tempTreeY + moveY[i];
						
						if(nextX >= 0 && nextX < mapSize && nextY >= 0 && nextY < mapSize) {
							// 우선순위큐에 나무 추가
							ans += 1;
							pq[nextX][nextY].add(new Tree(nextX, nextY, 1));
						}
					}
				}
				// 자기 자신도 추가
				ans += 1;
				pq[tempTreeX][tempTreeY].add(new Tree(tempTreeX, tempTreeY, tempTreeAge));
			}
			
			// 겨울
			for (int i = 0; i < mapSize; i++) {
				for (int j = 0; j < mapSize; j++) {
					// 1. 영양소 추가
					nutMap[i][j] += map[i][j];
				}
			}
		}
		// 답 출력
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

		// 나이 적을수록 앞으로
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
