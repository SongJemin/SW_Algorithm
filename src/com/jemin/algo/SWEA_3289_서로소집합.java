import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3289_���μ����� {
	
	static int[] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int inputCase = Integer.parseInt(br.readLine());
		int setNum, operNum, oper, firstNum, secondNum;
		
		for (int t = 1; t <= inputCase; t++) {
			StringBuilder result = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			
			setNum = Integer.parseInt(st.nextToken());
			operNum = Integer.parseInt(st.nextToken());
			
			p = new int[setNum+1];
			
			// ���� ����
			for (int i = 1; i <= setNum; i++) {
				makeSet(i);
			}
			
			// ���� ������ŭ �ݺ�
			for (int i = 0; i < operNum; i++) {
				st = new StringTokenizer(br.readLine());
				
				oper = Integer.parseInt(st.nextToken());
				firstNum = Integer.parseInt(st.nextToken());
				secondNum = Integer.parseInt(st.nextToken());
				
				// union
				if(oper == 0) {
					union(firstNum, secondNum);
				}
				
				// find
				else {
					if(findSet(firstNum) == findSet(secondNum)) {
						result.append("1");
					}
					else {
						result.append("0");
					}
				}
			}
			
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}
	
	// ���� �����
	public static void makeSet(int num) {
		p[num] = num;
	}
	
	// ��ǥ�� ã��
	public static int findSet(int num) {
		if(p[num] == num) {
			return num;
		}
		else {
			p[num] = findSet(p[num]);
			return p[num];
		}
	}
	
	// ��ġ��
	public static void union(int firstNum, int secondNum) {
		int px = findSet(firstNum);
		int py = findSet(secondNum);
		
		// ��ǥ�ڰ� �ٸ� ���
		if(px != py) {
			// ��ġ��
			p[px] = py;
		}
	}
}
