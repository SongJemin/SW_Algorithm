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
			
			// �� ���ڿ� �ݺ�
			for (int i = 0; i < str.length(); i++) {
				// ù ���ڰ� ���ٸ�
				if(checkString.charAt(0) == str.charAt(i)){
					checkSize += 1;
					// �ڿ��� ������ �� ��
					for (int j = 1; j < checkString.length(); j++) {
						if(i+j < str.length()){
							if(checkString.charAt(j) == str.charAt(i+j)){
								checkSize += 1;

								// �� ���ٸ� count + 1
								if(checkSize == checkString.length()){
									count += 1;
									checkSize = 0;
								}
							} else{
								// Ʋ�����Ƿ� �˻� Ȯ���� ũ�� �ʱ�ȭ
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