import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7272_안경이없어 {
	public static void main(String[] args) throws Exception {
		int inputCase;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		String str1, str2;
		int num1, num2;
		boolean sameCheck;
		inputCase = Integer.parseInt(br.readLine());

		for (int a = 1; a <= inputCase; a++) {
			sameCheck = true;

			st = new StringTokenizer(br.readLine());
			str1 = st.nextToken();
			str2 = st.nextToken();

			if (str1.length() != str2.length()) {
				sb.append("#" + a + " " + "DIFF" + "\n");
				continue;
			}

			for (int i = 0; i < str1.length(); i++) {
				num1 = convertToInt(str1.charAt(i));
				num2 = convertToInt(str2.charAt(i));

				if (num1 != num2) {
					sameCheck = false;
					sb.append("#" + a + " " + "DIFF" + "\n");
					break;
				}
			}

			if (sameCheck) {
				sb.append("#" + a + " " + "SAME" + "\n");
			} 
		}
		System.out.println(sb);
	}

	public static int convertToInt(char alphabet) {
		int result = 0;

		if (alphabet == 'A' || alphabet == 'D' || alphabet == 'O' || alphabet == 'P' || alphabet == 'Q'
				|| alphabet == 'R') {
			result = 1;
		} else if (alphabet == 'B') {
			result = 2;
		} else {
			result = 0;
		}
		return result;
	}
}
