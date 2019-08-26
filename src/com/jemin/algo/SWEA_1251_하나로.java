import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1251 {
	
	static PriorityQueue<Node> pq;
	static Queue<Integer> que;
	static int nodeNum, edgeNum;
	static double ans;
	static List<Node>[] nodes;
	static int[] nodeX, nodeY;
	static double E;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		int inputCase;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		inputCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int a = 1; a <= inputCase; a++) {
			long val;
			nodeNum = Integer.parseInt(br.readLine());

			ans = 0;
			pq = new PriorityQueue<>();
			que = new LinkedList<>();
			nodeX = new int[nodeNum];
			nodeY = new int[nodeNum];
			visited = new boolean[nodeNum];
			nodes = new ArrayList[nodeNum];
			
			// x좌표 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < nodeNum; i++) {
				nodeX[i] = Integer.parseInt(st.nextToken());
			}
			
			// y좌표 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < nodeNum; i++) {
				nodeY[i] = Integer.parseInt(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			
			for (int i = 0; i < nodeNum; i++) {
				nodes[i] = new ArrayList<Node>();
			}

			// 거리 계산 후 배열에 추가
			for (int i = 0; i < nodeNum; i++) {
				for (int j = 0; j < nodeNum; j++) {
					if(i == j) continue;
					val = (long) (Math.pow(nodeX[i]-nodeX[j], 2)) + (long)(Math.pow(nodeY[i]-nodeY[j], 2));
					nodes[i].add(new Node(i, j , val));
				}
			}
			
			// 0번 지역부터 시작
			que.add(0);
			start();
			sb.append("#" + a + " " + Math.round(ans) + "\n");
		}
		System.out.println(sb);
		
	}
	
	public static void start(){
		while(!que.isEmpty()){
			int node = que.remove();
			visited[node] = true;
			
			for (int i = 0; i < nodeNum-1; i++) {
				pq.add(nodes[node].get(i));
			}
			
			Node tempNode;
			double cost;
			
			// 우선순위큐
			while(!pq.isEmpty()){
				tempNode = pq.remove();
				if(!visited[tempNode.end]){
					que.add(tempNode.end);
					cost = (double) (tempNode.val * E);
					ans += cost;
					break;
				}
			}
		}
	}
	
	public static class Node implements Comparable<Node> {
		int start;
		int end;
		long val;
		
		public Node(int start, int end, long val) {
			this.start = start;
			this.end = end;
			this.val = val;
		}

		@Override
		public int compareTo(Node o) {
			if(this.val > o.val){
				return 1;
			}
			else if(this.val == o.val){
				return 0;
			}
			else {
				return -1;
			}
		}
	}
}
