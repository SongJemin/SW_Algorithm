
public class Kakao_2020_���ڿ����� {

	public static int solution(String s) {
		int answer;
		int cnt;
		int min = Integer.MAX_VALUE;
		
		if(s.length() == 1) {
			return 1;
		}
		else {
			// �� ���ڿ� ����(1�� ~ �� ������ ���� ����������)
			for (int n = 1; n <= s.length()/2; n++) {
				StringBuilder sb = new StringBuilder();
				int i = 0;
				cnt = 1;
				String before = "";
				String current;
				while(true) {
					// ���� ���� ���
					if(i + n > s.length()) {
						// ���� ��ġ ������ ������ ������ ����
						if(i != 0) {
							if(cnt > 1) {
								sb.append(cnt + before);
							}
							else {
								sb.append(before);
							}
						}
						sb.append(s.substring(i, s.length()));
						break;
					}
					// ���� ���� �ȹ��
					else {
						// ���̶� ���ؾ���
						// ���� ����� ����
						if(i==0) {
							// before �� ����
							before = s.substring(0, n);
						}
						// before ���̶� ��
						else {
							current = s.substring(i, i+n);
							// ���ٸ�
							if(current.equals(before)) {
								cnt++;
							}
							// �ٸ��ٸ�
							else {
								if(cnt > 1) {
									sb.append(cnt + before);
								}
								else {
									sb.append(before);
								}
								before = s.substring(i, i+n);
								cnt = 1;
							}
						}
					}
					// i ����
					i += n;
				}
				if(min > sb.length()) {
					min = sb.length();
				}
			}
		}
		answer = min;
		return answer;
	}

	public static void main(String[] args) {
		
		String s = "aa";
		int result = solution(s);
		
		System.out.println(result);
	}
}