package kakao_summer_2020;

public class Kakao_Summer_1 {

	public static void main(String[] args) throws Exception {
	
		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		String hand = "right";
		
		String result = solution(numbers, hand);
		System.out.println(result);
	}
	
	public static String solution(int[] arr, String hand) {
	      
	
		int[][] map = new int[11][9];
		map[1][2] = 1;
		map[1][5] = 2;
		map[1][8] = 3;
		map[1][0] = 4;
		
		map[4][2] = 2;
		map[4][5] = 1;
		map[4][8] = 2;
		map[4][0] = 3;
		
		map[7][2] = 3;
		map[7][5] = 2;
		map[7][8] = 1;
		map[7][0] = 2;
		
		map[10][2] = 4;
		map[10][5] = 3;
		map[10][8] = 2;
		map[10][0] = 1;
		
		map[2][2] = 0;
		map[2][5] = 1;
		map[2][8] = 2;
		map[2][0] = 3;
		
		map[5][2] = 1;
		map[5][5] = 0;
		map[5][8] = 1;
		map[5][0] = 2;
		
		map[8][2] = 2;
		map[8][5] = 1;
		map[8][8] = 0;
		map[8][0] = 1;
		
		map[0][2] = 3;
		map[0][5] = 2;
		map[0][8] = 1;
		map[0][0] = 0;
		
		int num;
		int lNum = 10;
		int rNum = 10;
		StringBuilder sb = new StringBuilder();
		int lSub, rSub;
		
		for (int i = 0; i < arr.length; i++) {
			num = arr[i];
			// LEFT
			if(num == 1 || num == 4 || num == 7) {
				sb.append("L");
				lNum = num;
			}
			// RIGHT
			else if(num == 3 || num == 6 || num == 9) {
				sb.append("R");
				rNum = (num-2);
			}
			// CENTER
			else {
				lSub = map[lNum][num];
				rSub = map[rNum][num];
				if(lSub < rSub) {
					sb.append("L");
					lNum = num;
				}
				else if(lSub > rSub) {
					sb.append("R");
					rNum = num;
				}
				else {
					if(hand.equals("left")) {
						sb.append("L");
						lNum = num;
					}
					else {
						sb.append("R");
						rNum = num;
					}
				}
			}
		}
		return sb.toString();
	}
}
