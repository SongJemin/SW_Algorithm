import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Check {
	public static void main(String[] args) throws Exception {
		int inputCase;
		String str;
		StringBuilder sb;
		StringBuilder result = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inputCase = Integer.parseInt(br.readLine());
		
		for (int a = 1; a <= inputCase; a++) {
			str = br.readLine();
			sb = new StringBuilder();

			int count;
			boolean flag = false;
			int index = 0;
			
			while(!flag) {
				
				// index 위치의 글자 추가
				sb.append(str.charAt(index));
				index++;	// index 증가
				
				// 글자 크기 저장
				count = 0;
				for (int i = 0; i < sb.length(); i++) {
					// 저장된 글자와 저장된 글자 뒤에 있는 글자가 서로 같은지 비교
					if(sb.charAt(i) == str.charAt(sb.length() + i)){
						// 다음 글자도 체크하기 위해 count 증가
						count++;
						// 모든 글자가 다똑같다면
						if(count == sb.length()) {
							flag = true;
						}
					}
				}
			}
			result.append("#" + a + " " + sb.length() + "\n");
		}
		System.out.println(result);
	}
}
