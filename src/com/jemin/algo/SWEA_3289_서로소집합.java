import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3289_서로소집합 {
	
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
			
			// 집합 생성
			for (int i = 1; i <= setNum; i++) {
				makeSet(i);
			}
			
			// 연산 개수만큼 반복
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
	
	// 집합 만들기
	public static void makeSet(int num) {
		p[num] = num;
	}
	
	// 대표자 찾기
	public static int findSet(int num) {
		if(p[num] == num) {
			return num;
		}
		else {
			p[num] = findSet(p[num]);
			return p[num];
		}
	}
	
	// 합치기
	public static void union(int firstNum, int secondNum) {
		int px = findSet(firstNum);
		int py = findSet(secondNum);
		
		// 대표자가 다른 경우
		if(px != py) {
			// 합치기
			p[px] = py;
		}
	}
}
