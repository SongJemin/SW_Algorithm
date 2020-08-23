import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_6118_¼û¹Ù²ÀÁú {
	
	static ArrayList<Integer>[] wareHouseConnect;
	static int[] wareHouseArr;
	static Queue<WareHouse> que = new LinkedList<>();
	static boolean[] visited;
	static int maxLength = Integer.MIN_VALUE;
	static int sameLength;
	static int minVal = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int wareHouse, route;
		st = new StringTokenizer(br.readLine());
		
		wareHouse = Integer.parseInt(st.nextToken());
		route = Integer.parseInt(st.nextToken());
		wareHouseConnect = new ArrayList[wareHouse+1];
		visited = new boolean[wareHouse+1];
		
		for (int i = 0; i < wareHouseConnect.length; i++) {
			wareHouseConnect[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < route; i++) {
			int lNum, rNum;
			st = new StringTokenizer(br.readLine(), " ");
			lNum = Integer.parseInt(st.nextToken());
			rNum = Integer.parseInt(st.nextToken());
			
			wareHouseConnect[lNum].add(rNum);
			wareHouseConnect[rNum].add(lNum);
		}
		
		que.add(new WareHouse(1, 0));
		visited[1] = true;
		start();
		
		System.out.println(minVal + " " + maxLength + " " + sameLength);
	}
	
	public static void start() {
		int index, cnt;
				
		while(!que.isEmpty()) {
			WareHouse wh = que.poll();
			index = wh.index;
			cnt = wh.cnt;
			
			if(maxLength < cnt) {
				maxLength = cnt;
				sameLength = 1;
				minVal = index;
			}
			else if(maxLength == cnt) {
				sameLength += 1;
				if(minVal > index) minVal = index;
			}
			
			for (int i = 0; i < wareHouseConnect[index].size(); i++) {
				int nextIndex = wareHouseConnect[index].get(i);
				if(visited[nextIndex]) continue;
				
				que.add(new WareHouse(nextIndex, cnt+1));
				visited[nextIndex] = true;
			}
		}
	}
	
	public static class WareHouse {
		int index;
		int cnt;
		
		public WareHouse(int index, int cnt) {
			this.index = index;
			this.cnt = cnt;
		}
	}
}
