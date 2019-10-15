import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_1103_경비원 {

	public static int row, col;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int time;
		int dongLeft, oppositeLeft;
		int result, leftResult, rightResult;
		int dongGuenDir, dongGeunVal;
		int[] marketDir;
		int[] marketVal;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		col = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());

		int ans = 0;
		time = Integer.parseInt(br.readLine());
		marketDir = new int[time];
		marketVal = new int[time];
		
		for (int i = 0; i < time; i++) {
			st = new StringTokenizer(br.readLine());

			marketDir[i] = Integer.parseInt(st.nextToken());
			marketVal[i] = Integer.parseInt(st.nextToken());
		}
				// 동근이의 위치
		st = new StringTokenizer(br.readLine());
		dongGuenDir = Integer.parseInt(st.nextToken());
		dongGeunVal = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < time; i++) {
			// 같은 라인에 있을 경우
			if(dongGuenDir == marketDir[i]) {
				result = Math.abs(marketVal[i] - dongGeunVal);
			}
			// 다른 라인일 경우
			else {
				// 동근이가 북쪽 or 남쪽에 있을 경우
				if(dongGuenDir <= 2) {
					// 기준 상점도 북쪽 or 남쪽에 있을 경우
					if(marketDir[i] <= 2) {
						
						dongLeft = dongGeunVal;
						oppositeLeft = marketVal[i];
						leftResult = dongLeft + oppositeLeft + row;
						
						rightResult = 2 * (row + col) - leftResult;
						if(leftResult < rightResult) result = leftResult;
						else result = rightResult;

					}
					// 기준 상점이 서쪽 or 동쪽에 있을 경우
					else {
						// 서쪽일 경우
						if(marketDir[i] == 3) {
							// 동근이가 북쪽일 경우
							if(dongGuenDir == 1) {
								leftResult = dongGeunVal + marketVal[i];
							}
							// 동근이가 남쪽일 경우
							else {
								leftResult = dongGeunVal + (row - marketVal[i]);
							}
							result = leftResult;
						}
						// 동쪽일 경우
						else {
							// 동근이가 북쪽일 경우
							if(dongGuenDir == 1) {
								rightResult = (col - dongGeunVal) + marketVal[i];
							}
							// 동근이가 남쪽일 경우
							else {
								rightResult = (col - dongGeunVal) + (row - marketVal[i]);
							}
							result = rightResult;
						}
					}
				}
				// 동근이가 서쪽 or 동쪽에 있을 경우
				else {
					// 기준 상점도 서쪽 or 동쪽에 있을 경우
					if(marketDir[i] > 2) {
						
						dongLeft = dongGeunVal;
						oppositeLeft = marketVal[i];
						leftResult = dongLeft + oppositeLeft + col;
						rightResult = row*col - leftResult;
						if(leftResult < rightResult) result = leftResult;
						else result = rightResult;
					}
					// 기준 상점이 북쪽 or 남쪽에 있을 경우
					else {
						// 기준 상점이 북쪽일 경우
						if(marketDir[i] == 1) {
							// 동근이가 서쪽일 경우
							if(dongGuenDir == 3) {
								leftResult = dongGeunVal + marketVal[i];
							}
							// 동근이가 동쪽일 경우
							else {
								leftResult = dongGeunVal + (col - marketVal[i]);
							}
							result = leftResult;
						}
						// 남쪽일 경우
						else {
							// 동근이가 서쪽일 경우
							if(dongGuenDir == 3) {
								rightResult = (row - dongGeunVal) + marketVal[i];
							}
							// 동근이가 동쪽일 경우
							else {
								rightResult = (row - dongGeunVal) + (col - marketVal[i]);
							}
							result = rightResult;
						}
					}
				}
			}
			ans += result;
		}
		System.out.println(ans);
	}
}
