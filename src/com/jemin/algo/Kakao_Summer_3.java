package kakao_summer_2020;

import java.util.*;

public class Kakao_Summer_3 {
	
	static Set<String> set = new HashSet<>();
	static Map<String, Integer> map = new HashMap<>();
	static Queue<String> que = new LinkedList<>();
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
		int[] result = solution(gems);
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] solution(String[] gems) {
		int[] answer = new int[2];
		int start, end, total, add;
		
		for (int i = 0; i < gems.length; i++) {
			set.add(gems[i]);
		}
		total = set.size();		
		start = 0;
		add = 0;
		
		for (int i = 0; i < gems.length; i++) {
			
			que.add(gems[i]);
			end = i;
			
			if(!map.containsKey(gems[i])) map.put(gems[i], 1);
			else map.put(gems[i], map.get(gems[i]) + 1);
			
			while(true) {
				if(map.get(que.peek()) > 1) {
					map.put(que.peek(), map.get(que.peek()) - 1);
					que.poll();
					add++;
				}
				else break;
			}
			
			// 다 모음(total == HashMap.size()) && 최소 길이
			if((total == map.size()) && (min > (end-start))) {
				start = add;
				min = (end - start);
			}
		}
		answer[0] = start + 1;
		answer[1] = start + min + 1;
		
		return answer;
	}
}
