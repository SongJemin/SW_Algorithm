import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_1103_���� {

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
				// �������� ��ġ
		st = new StringTokenizer(br.readLine());
		dongGuenDir = Integer.parseInt(st.nextToken());
		dongGeunVal = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < time; i++) {
			// ���� ���ο� ���� ���
			if(dongGuenDir == marketDir[i]) {
				result = Math.abs(marketVal[i] - dongGeunVal);
			}
			// �ٸ� ������ ���
			else {
				// �����̰� ���� or ���ʿ� ���� ���
				if(dongGuenDir <= 2) {
					// ���� ������ ���� or ���ʿ� ���� ���
					if(marketDir[i] <= 2) {
						
						dongLeft = dongGeunVal;
						oppositeLeft = marketVal[i];
						leftResult = dongLeft + oppositeLeft + row;
						
						rightResult = 2 * (row + col) - leftResult;
						if(leftResult < rightResult) result = leftResult;
						else result = rightResult;

					}
					// ���� ������ ���� or ���ʿ� ���� ���
					else {
						// ������ ���
						if(marketDir[i] == 3) {
							// �����̰� ������ ���
							if(dongGuenDir == 1) {
								leftResult = dongGeunVal + marketVal[i];
							}
							// �����̰� ������ ���
							else {
								leftResult = dongGeunVal + (row - marketVal[i]);
							}
							result = leftResult;
						}
						// ������ ���
						else {
							// �����̰� ������ ���
							if(dongGuenDir == 1) {
								rightResult = (col - dongGeunVal) + marketVal[i];
							}
							// �����̰� ������ ���
							else {
								rightResult = (col - dongGeunVal) + (row - marketVal[i]);
							}
							result = rightResult;
						}
					}
				}
				// �����̰� ���� or ���ʿ� ���� ���
				else {
					// ���� ������ ���� or ���ʿ� ���� ���
					if(marketDir[i] > 2) {
						
						dongLeft = dongGeunVal;
						oppositeLeft = marketVal[i];
						leftResult = dongLeft + oppositeLeft + col;
						rightResult = row*col - leftResult;
						if(leftResult < rightResult) result = leftResult;
						else result = rightResult;
					}
					// ���� ������ ���� or ���ʿ� ���� ���
					else {
						// ���� ������ ������ ���
						if(marketDir[i] == 1) {
							// �����̰� ������ ���
							if(dongGuenDir == 3) {
								leftResult = dongGeunVal + marketVal[i];
							}
							// �����̰� ������ ���
							else {
								leftResult = dongGeunVal + (col - marketVal[i]);
							}
							result = leftResult;
						}
						// ������ ���
						else {
							// �����̰� ������ ���
							if(dongGuenDir == 3) {
								rightResult = (row - dongGeunVal) + marketVal[i];
							}
							// �����̰� ������ ���
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
