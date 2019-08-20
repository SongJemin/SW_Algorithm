import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_1213 {
	public static void main(String[] args) throws Exception {
		
		String checkString, str, inputCase;
		StringBuilder sb;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		for (int a = 1; a <= 10; a++) {
			
			inputCase = br.readLine();
			checkString = br.readLine();
			str = br.readLine();
			int checkSize = 0;
			int count = 0;
			
			// 총 문자열 반복
			for (int i = 0; i < str.length(); i++) {
				// 첫 글자가 같다면
				if(checkString.charAt(0) == str.charAt(i)){
					checkSize += 1;
					// 뒤에도 같은지 다 비교
					for (int j = 1; j < checkString.length(); j++) {
						if(i+j < str.length()){
							if(checkString.charAt(j) == str.charAt(i+j)){
								checkSize += 1;

								// 다 같다면 count + 1
								if(checkSize == checkString.length()){
									count += 1;
									checkSize = 0;
								}
							} else{
								// 틀렸으므로 검사 확인한 크기 초기화
								checkSize = 0;
							}
						}
					}
				}
			}
			sb.append("#" + a + " " + count + "\n");
		}
		System.out.println(sb);
	}
}