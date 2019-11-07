
public class Kakao_2020_문자열압축 {

	public static int solution(String s) {
		int answer;
		int cnt;
		int min = Integer.MAX_VALUE;
		
		if(s.length() == 1) {
			return 1;
		}
		else {
			// 비교 문자열 개수(1개 ~ 총 개수의 절반 개수까지만)
			for (int n = 1; n <= s.length()/2; n++) {
				StringBuilder sb = new StringBuilder();
				int i = 0;
				cnt = 1;
				String before = "";
				String current;
				while(true) {
					// 글자 범위 벗어남
					if(i + n > s.length()) {
						// 현재 위치 값부터 마지막 값까지 저장
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
					// 글자 범위 안벗어남
					else {
						// 앞이랑 비교해야함
						// 비교할 대상이 없음
						if(i==0) {
							// before 값 저장
							before = s.substring(0, n);
						}
						// before 값이랑 비교
						else {
							current = s.substring(i, i+n);
							// 같다면
							if(current.equals(before)) {
								cnt++;
							}
							// 다르다면
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
					// i 증가
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