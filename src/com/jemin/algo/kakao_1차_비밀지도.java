package exam2018;

import java.util.Arrays;

public class kakao_1차_비밀지도 {

	public static void main(String[] args) {

		int n = 6;
		int[] arr1 = {46, 33, 33, 22, 31, 50};
		int[] arr2 = {27, 56, 19, 14, 14, 10};
		
		String[] result = solution(n, arr1, arr2);
		
		System.out.println(Arrays.toString(result));
	}
	
	public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        int[][] map1 = new int[n][n];
        int[][] map2 = new int[n][n];
        int num, count, restnum;
        
        for (int i = 0; i < arr1.length; i++) {
        	num = arr1[i];
        	count = n;
        	
        	while(num > 0) {
				int restNum = (num % 2);
				map1[i][--count] = restNum;
				num /= 2;
			}
        	
        	num = arr2[i];
        	count = n;
        	while(num > 0) {
				int restNum = (num % 2);
				map2[i][--count] = restNum;
				num /= 2;
			}
		}
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if((map1[i][j] == 0) && (map2[i][j]) == 0) {
					sb.append(" ");
				}
				else {
					sb.append("#");
				}
			}
			answer[i] = sb.toString();
			sb.setLength(0);
		}
        
        return answer;
    }
}
