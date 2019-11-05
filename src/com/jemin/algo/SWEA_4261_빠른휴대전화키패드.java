import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SWEA_4261_빠른휴대전화키패드 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int inputCase = Integer.parseInt(br.readLine());
		int result;
		String inputNum;
		int wordNum;
		Map<Character, Character> map = new HashMap<Character, Character>();
		
		map.put('a', '2');
		map.put('b', '2');
		map.put('c', '2');

		map.put('d', '3');
		map.put('e', '3');
		map.put('f', '3');
		
		map.put('g', '4');
		map.put('h', '4');
		map.put('i', '4');
		
		map.put('j', '5');
		map.put('k', '5');
		map.put('l', '5');
		
		map.put('m', '6');
		map.put('n', '6');
		map.put('o', '6');
		
		map.put('p', '7');
		map.put('q', '7');
		map.put('r', '7');
		map.put('s', '7');
		
		map.put('t', '8');
		map.put('u', '8');
		map.put('v', '8');
		
		map.put('w', '9');
		map.put('x', '9');
		map.put('y', '9');
		map.put('z', '9');

		for (int t = 1; t <= inputCase; t++) {
			result = 0;
			st = new StringTokenizer(br.readLine());
			inputNum = st.nextToken();
			wordNum = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < wordNum; i++) {
				String word = st.nextToken();
				boolean flag = true;
				for (int j = 0; j < word.length(); j++) {
					if(map.get(word.charAt(j)) == (inputNum.charAt(j))) {
						continue;
					}
					else {
						flag = false;
						break;
					}
				}
				if(flag) {
					result += 1;
				}
			}
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}
}