import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_17471_��������� {
	
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
		
		// �� ���� �ο��� ����
		for (int i = 0; i < villageNum; i++) {
			villagePeopleCnt[i] = Integer.parseInt(st.nextToken());
			connection[i] = new ArrayList<Integer>();
			arr[i] = i;
		}
		
		// �������� �ݺ�
		for (int i = 0; i < villageNum; i++) {
			// �ش��ϴ� ���� ����(�迭�� �߰�)
			st = new StringTokenizer(br.readLine(), " ");
			// ����Ǵ� ���� ����
			int connectionNum = Integer.parseInt(st.nextToken());

			// �������� ����
			for (int j = 0; j < connectionNum; j++) {
				connection[i].add(Integer.parseInt(st.nextToken())-1);
			}
		}
		
		// 1~��������/2 ����
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
			// a �� �� ����Ǿ��ִ��� Ȯ��
			count = 0;
			teamVal = 0;
			visited[result[0]] = true;
			// dfs ����
			dfs(result[0], result);
			// �湮 �ʱ�ȭ
			for (int j = 0; j < villageNum; j++) {
				visited[j] = false;
			}
			// a�� ����
			aTeamVal = teamVal;
			// �ʱ�ȭ
			teamVal = 0;
			count = 0;
			
			// a���� ��� ����Ǿ�����
			if(connectionFlag) {
				for (int i = 0; i < result.length; i++) {
					visited[result[i]] = true;
				}
				int cnt = 0;
				// b�� �ο���ŭ �迭 ũ�� �Ҵ�
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
				// �ݴ��� ����
				visited[oppositeTeam[0]] = true;
				// dfs ����
				dfs(oppositeTeam[0], oppositeTeam);
				// �湮 �ʱ�ȭ
				for (int j = 0; j < villageNum; j++) {
					visited[j] = false;
				}
				// b�� ����
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
		// ��� ����Ǿ�����
		if(count == result.length) {
			connectionFlag = true;
		}

		else {
			// ����� ������ŭ
			for (int i = 0; i < result.length; i++) {
				// a���� ������ ����Ǿ��ִ� ��������
				// ������ �Ǿ��ִٸ� && �湮������ ���ٸ�
				if(connection[index].contains(result[i]) && !visited[result[i]]) {
					// �湮 ��ġ true
					visited[result[i]] = true;
					dfs(result[i], result);
				}
			}		
		}
	}
}