import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_3459_½ÂÀÚ¿¹ÃøÇÏ±â_¼ÛÁ¦¹Î {
	public static void main(String[] args) throws Exception {
		int inputCase;
		long N;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		inputCase = Integer.parseInt(br.readLine());

		for (int a = 1; a <= inputCase; a++) {
			N = Long.parseLong(br.readLine());
			long comparedFirstNum = 1;
			long comparedLastNum = 1;
			while(true) {
				if(comparedFirstNum <= N && N <= comparedLastNum) {
					sb.append("#" + a + " Bob\n");
					break;
				}
				else if(N <= comparedFirstNum) {
					sb.append("#" + a + " Alice\n");
					break;
				}
				else {
					comparedFirstNum = ((comparedFirstNum * 2) + 1) * 2;
					comparedLastNum = (((comparedLastNum+1) * 2 + 1) * 2 - 1);
				}
			}
		}
		System.out.println(sb);
	}
}
