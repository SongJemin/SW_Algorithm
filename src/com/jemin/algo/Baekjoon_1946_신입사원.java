import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_1946_신입사원 {
	
	static PriorityQueue<Rank> pq; 
	static int juniorNum;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < testCase; i++) {
			pq = new PriorityQueue<>();
			juniorNum = Integer.parseInt(br.readLine());
			int passCnt = 1;
			int recentlyPassRank; 
			
			// 한 명일 경우
			if(juniorNum == 1) {
				System.out.println(1);
				break;
			}
			
			for (int j = 0; j < juniorNum; j++) {
				int examRank, interviewRank;
				st = new StringTokenizer(br.readLine(), " ");
				
				examRank = Integer.parseInt(st.nextToken());
				interviewRank = Integer.parseInt(st.nextToken());
				pq.offer(new Rank(examRank, interviewRank));
			}
			Rank beforeJunior = pq.poll();
			recentlyPassRank = beforeJunior.interviewRank;
			
			while(!pq.isEmpty()) {
				Rank nextJunor = pq.poll();
				if(recentlyPassRank < nextJunor.interviewRank) continue;
				else {
					recentlyPassRank = nextJunor.interviewRank;
					passCnt++;
				}
			}
			sb.append(passCnt + "\n");
		}
		System.out.println(sb.toString());
	}
	
	public static class Rank implements Comparable<Rank> {
		int examRank;
		int interviewRank;
		
		public Rank(int examRank, int interviewRank) {
			this.examRank = examRank;
			this.interviewRank = interviewRank;
		}

		@Override
		public int compareTo(Rank o) {
			if(this.examRank < o.examRank) return -1;
			else return 1;
		}
	}
}
