import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14889_스타트와링크 {
	
	static int playerNum;
	static int[][] map;
	static int[] team1, team2;
	static int min = Integer.MAX_VALUE;
	static int count;
	static int[] linkTeam;
	static int sum, startSum, linkSum;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] arr, output;
		
		playerNum = Integer.parseInt(br.readLine());
		map = new int[playerNum][playerNum];
		arr = new int[playerNum];
		visited = new boolean[playerNum];
		output = new int[playerNum/2];


		// 값 입력
		for (int i = 0; i < playerNum; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < playerNum; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < playerNum; i++) {
			arr[i] = i;
		}
		
		count = 0;
		comb(arr, output, 0, playerNum, playerNum/2, 0);
		System.out.println(min);
	}
	
	public static void comb(int[] arr, int[] startTeam, int step, int n, int r, int target) {
		if(min == 0) return;
		if(r == 0) {
			for (int i = 0; i < startTeam.length; i++) {
				visited[startTeam[i]] = true;
			}
			// 나머지는 반대 팀으로
			linkTeam = new int[startTeam.length];
			int cnt = 0;
			for (int i = 0; i < playerNum; i++) {
				if(!visited[i]) {
					linkTeam[cnt] = i;
					cnt += 1;
				}
				else {
					// 방문 초기화
					visited[i] = false;
				}
			}
			
			// 시작팀 값 계산
			startSum = 0;
			for (int i = 0; i < startTeam.length-1; i++) {
				for (int j = i+1; j < startTeam.length; j++) {
					startSum += map[startTeam[i]][startTeam[j]];
					startSum += map[startTeam[j]][startTeam[i]];
				}
			}
			
			// 링크팀 계산
			linkSum = 0;
			for (int i = 0; i < linkTeam.length-1; i++) {
				for (int j = i+1; j < linkTeam.length; j++) {
					linkSum += map[linkTeam[i]][linkTeam[j]];
					linkSum += map[linkTeam[j]][linkTeam[i]];
				}
			}
			// 최소값 비교
			if(min > Math.abs(startSum - linkSum)) {
				min = Math.abs(startSum - linkSum);
			}
		}
		else if(target == n) return;
		else {
			startTeam[step] = arr[target];
			comb(arr, startTeam, step+1, n, r-1, target+1);
			comb(arr, startTeam, step, n, r, target+1);
		}
	}
	
}
