package exam2018;

import java.util.HashMap;
import java.util.Map;

public class kakao_3차_n진수게임 {
	
	static Map<Integer, String> map;
	
	public static void main(String[] args) {
		
		int n = 16;
		int t = 16;
		int m = 2;
		int p = 2;
		
		String result = solution(n, t, m, p);
		System.out.println(result);
	}
	
	public static String solution(int n, int t, int m, int p) {
        String answer = "";

        map = new HashMap<Integer, String>();
        map.put(10, "A");
        map.put(11, "B");
        map.put(12, "C");
        map.put(13, "D");
        map.put(14, "E");
        map.put(15, "F");
        
        int num = t * m;
        StringBuilder sb = new StringBuilder();
        sb.append(0);
        
        for (int i = 0; i < num; i++) {
        	String val = transform(i, n);
        	sb.append(val);
		}

        String str = sb.toString();
        int count = t;
        StringBuilder resultSb = new StringBuilder();
        int index = 0 + (p-1);
        
        while(count > 0) {
        	resultSb.append(str.charAt(index));
        	index += m;
        	count--;
        }
        
        answer = resultSb.toString();
        return answer;
    }
	
	public static String transform(int num, int target) {
		String val;
		StringBuilder sb = new StringBuilder();
		int restNum;
		
		while(num > 0) {
			restNum = num % target;
			if(restNum > 9) {
				sb.append(map.get(restNum));
			}
			else {
				sb.append(restNum);
			}
			num /= target;
		}
		val = sb.reverse().toString();
		return val;
	}
}
