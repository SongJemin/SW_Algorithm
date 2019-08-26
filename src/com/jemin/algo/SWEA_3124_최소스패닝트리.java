import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_3124 {
	
	static PriorityQueue<Node> pq;
	static Queue<Integer> que;
	static int nodeNum, edgeNum;
	static List<Node>[] nodes;
	static boolean[] visited;
	static long ans;
	
	public static void main(String[] args) throws Exception {
		int inputCase, start, end, val;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		inputCase = Integer.parseInt(br.readLine());
		
		for (int a = 1; a <= inputCase; a++) {
			st = new StringTokenizer(br.readLine());
			pq = new PriorityQueue<Node>();
			que = new LinkedList<>();
			ans = 0;
			
			nodeNum = Integer.parseInt(st.nextToken());
			edgeNum = Integer.parseInt(st.nextToken());
			
			nodes = new ArrayList[nodeNum + 1];
			visited = new boolean[nodeNum + 1];
			
			for (int i = 0; i <= nodeNum; i++) {
				nodes[i] = new ArrayList<Node>();
			}
			
			for (int i = 0; i < edgeNum; i++) {
				st = new StringTokenizer(br.readLine());
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				val = Integer.parseInt(st.nextToken());
				
				nodes[start].add(new Node(start, end, val));
				nodes[end].add(new Node(end, start, val));
			}
			que.add(1);
			
			start();
			sb.append("#" + a + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	
	public static void start(){
		while(!que.isEmpty()){
			int nodeNum = que.remove();
			visited[nodeNum] = true;
			
			for (int i = 0; i < nodes[nodeNum].size(); i++) {
				pq.add(nodes[nodeNum].get(i));
			}
			
			
			while(!pq.isEmpty()){
				Node tempNode = pq.remove();
				if(!visited[tempNode.end]){
					que.add(tempNode.end);
					ans += tempNode.val;
					break;
				}
			}
		}
	}
	
	
	public static class Node implements Comparable<Node> {
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
			if(this.val > o.val){
				return 1;
			}
			else if(this.val == o.val){
				return 0;
			}
			else{
				return -1;
			}
		}
	}
}

