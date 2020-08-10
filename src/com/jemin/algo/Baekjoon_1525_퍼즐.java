import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_1525_퍼즐 {

	static int[][] puzzle;
	static boolean[][] isChecked;
	static int holeX, holeY;
	static StringBuilder sb;
	static Queue<String> que;
	static Map<String, Integer> map;
	
	static int[] moveX = {-1, 1, 0, 0};	// 상, 하, 좌, 우
	static int[] moveY = {0, 0, -1, 1};	// 상, 하, 좌, 우
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		map = new HashMap<>();
		StringTokenizer st;
		puzzle = new int[3][3];
		que = new LinkedList<String>();
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				String numStr = st.nextToken();
				if(numStr.equals("0")) numStr = "9";
				sb.append(numStr);
			}
		}
		
		if(sb.toString().equals("123456789")) System.out.println(0);
		else {
			map.put(sb.toString(), 0);
			que.add(sb.toString());
			
			bfs();
			
			if(map.containsKey("123456789")) System.out.println(map.get("123456789"));
			else System.out.println(-1);
		}
	}
	
	public static void bfs() {
		while(!que.isEmpty()) {
			int nineIndex, nineX, nineY;
			int targetIndex, targetX, targetY;
			
			String originStr = que.poll();
			if(originStr.equals("123456789")) break;
			
			nineIndex = originStr.indexOf("9");
			nineX = nineIndex / 3;
			nineY = nineIndex % 3;
			
			// 상,하,좌,우 조사
			for (int i = 0; i < 4; i++) {
				StringBuilder targetStr = new StringBuilder(originStr);
				targetX = nineX + moveX[i];
				targetY = nineY + moveY[i];
				
				if(targetX >= 0 && targetX < 3 && targetY >= 0 && targetY < 3) {
					targetIndex = targetX * 3 + (targetY);
					
					// 변경
					targetStr.setCharAt(targetIndex, '9');
					targetStr.setCharAt(nineIndex, originStr.charAt(targetIndex));
					
					// map에 있는지 조사
					if(!map.containsKey(targetStr.toString())) {
						que.add(targetStr.toString());
						map.put(targetStr.toString(), map.get(originStr) + 1);
					}
				}
			}
		}
	}
}
