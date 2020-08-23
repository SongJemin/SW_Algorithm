import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_11404_플로이드 {
	
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int cityCnt, busCnt;
		cityCnt = Integer.parseInt(br.readLine());
		busCnt = Integer.parseInt(br.readLine());
		map = new int[cityCnt+1][cityCnt+1];
		
		for (int i = 1; i <= cityCnt; i++) {
			Arrays.fill(map[i], 10000000);
			map[i][i] = 0;
		}
		
		for (int i = 0; i < busCnt; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sCity = Integer.parseInt(st.nextToken());
			int eCity = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			if(map[sCity][eCity] > distance) map[sCity][eCity] = distance;
		}
		
		for (int t = cityCnt; t > 0; t--) {
			for (int i = 1; i <= cityCnt; i++) {
				for (int j = 1; j <= cityCnt; j++) {
					map[i][j] = Math.min(map[i][j], map[i][t] + map[t][j]);
				}
			}
		}
		
		for (int i = 1; i <= cityCnt; i++) {
			for (int j = 1; j <= cityCnt; j++) {
				if(map[i][j] == 10000000) map[i][j] = 0;
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
