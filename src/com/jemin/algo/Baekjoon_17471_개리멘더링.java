import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_17471_개리멘더링 {
	
	static int[] villagePeopleCnt;
	static int villageNum;
	static int[] arr;
	static int[] result;
	static int[] oppositeTeam;
	static boolean[] visited;
	static int aTeamVal, bTeamVal;
	static int teamVal;
	static ArrayList<Integer>[] connection;
	static int count;
	static boolean connectionFlag;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		villageNum = Integer.parseInt(br.readLine());
		
		villagePeopleCnt = new int[villageNum];
		arr = new int[villageNum];
		
		visited = new boolean[villageNum];
		connection = new ArrayList[villageNum];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 각 마을 인원들 조사
		for (int i = 0; i < villageNum; i++) {
			villagePeopleCnt[i] = Integer.parseInt(st.nextToken());
			connection[i] = new ArrayList<Integer>();
			arr[i] = i;
		}
		
		// 마을별로 반복
		for (int i = 0; i < villageNum; i++) {
			// 해당하는 마을 연결(배열에 추가)
			st = new StringTokenizer(br.readLine(), " ");
			// 연결되는 마을 개수
			int connectionNum = Integer.parseInt(st.nextToken());

			// 동적으로 연결
			for (int j = 0; j < connectionNum; j++) {
				connection[i].add(Integer.parseInt(st.nextToken())-1);
			}
		}
		
		// 1~마을개수/2 조합
		for (int i = 1; i <= villageNum/2; i++) {
			result = new int[i];
			comb(arr, result, 0, villageNum, i, 0);
		}
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {			
			System.out.println(min);
		}
	}
	
	public static void comb(int[] arr, int[] result, int step, int n, int r, int target) {
		if(r == 0) {
			if(min == 0) {
				return;
			}
			connectionFlag = false;
			// a 팀 다 연결되어있는지 확인
			count = 0;
			teamVal = 0;
			visited[result[0]] = true;
			// dfs 시작
			dfs(result[0], result);
			// 방문 초기화
			for (int j = 0; j < villageNum; j++) {
				visited[j] = false;
			}
			// a팀 총합
			aTeamVal = teamVal;
			// 초기화
			teamVal = 0;
			count = 0;
			
			// a팀이 모두 연결되어있음
			if(connectionFlag) {
				for (int i = 0; i < result.length; i++) {
					visited[result[i]] = true;
				}
				int cnt = 0;
				// b팀 인원만큼 배열 크기 할당
				oppositeTeam = new int[villageNum - result.length];
				for (int i = 0; i < villageNum; i++) {
					if(visited[i]) {
						visited[i] = false;
					}
					else {
						oppositeTeam[cnt] = i;
						cnt++;
					}
				}
				connectionFlag = false;
				// 반대편도 시작
				visited[oppositeTeam[0]] = true;
				// dfs 시작
				dfs(oppositeTeam[0], oppositeTeam);
				// 방문 초기화
				for (int j = 0; j < villageNum; j++) {
					visited[j] = false;
				}
				// b팀 총합
				bTeamVal = teamVal;
				if(connectionFlag) {
					if(min > Math.abs(aTeamVal - bTeamVal)) {
						min = Math.abs(aTeamVal - bTeamVal);
					}
				}
			}

		}
		else if(target == n) return;
		else {
			result[step] = arr[target];
			comb(arr, result, step+1, n, r-1, target+1);
			comb(arr, result, step, n, r, target+1);
		}
	}
	
	public static void dfs(int index, int[] result) {

		count += 1;
		teamVal += villagePeopleCnt[index];
		// 모두 연결되어있음
		if(count == result.length) {
			connectionFlag = true;
		}

		else {
			// 연결된 개수만큼
			for (int i = 0; i < result.length; i++) {
				// a팀의 마을이 연결되어있는 마을인지
				// 연결이 되어있다면 && 방문한적이 없다면
				if(connection[index].contains(result[i]) && !visited[result[i]]) {
					// 방문 위치 true
					visited[result[i]] = true;
					dfs(result[i], result);
				}
			}		
		}
	}
}