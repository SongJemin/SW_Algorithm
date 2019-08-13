import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Sol_1240 {
	public static void main(String[] args) throws Exception {
		int inputCase, inputRow, inputCol;
		String inputString;
		int oddNumSum;
		int evenNumSum;
		int checkNum;
		int arr[];
		boolean passCheck;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		inputCase = Integer.parseInt(br.readLine());
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("0001101", 0);
		map.put("0011001", 1);
		map.put("0010011", 2);
		map.put("0111101", 3);
		map.put("0100011", 4);
		map.put("0110001", 5);
		map.put("0101111", 6);
		map.put("0111011", 7);
		map.put("0110111", 8);
		map.put("0001011", 9);
		
		for(int a = 1; a <= inputCase; a++) {

			passCheck = false;
			st = new StringTokenizer(br.readLine());
			inputRow = Integer.parseInt(st.nextToken());
			inputCol = Integer.parseInt(st.nextToken());
			arr = new int[8];
			
			for(int i = 0; i < inputRow; i++) {
				oddNumSum = 0;
				evenNumSum = 0;
				checkNum = -1;
				
				inputString = br.readLine();
				int count = 7;
				int startPosition = 0;
				int endPosition = 0;
				
				// 한 번 조사했던 암호라면 넘기기
				if(passCheck) {
					continue;
				}

				// 뒤에서부터 1나오는 순간까지 조사
				for (int j = inputString.length()-1; j >= 0; j--) {
					// 1발견
					if(inputString.charAt(j) - '0' == 1) {
						startPosition = j - 6;
						endPosition = j + 1;
						
						// 처음 조사하는 경우
						if(endPosition != 0) {
							for(int t = 0; t < 8; t++) {
								String value = inputString.substring(startPosition, endPosition);
								endPosition = startPosition;
								startPosition = endPosition - 7;

								// 해당 암호가 있는지 검사
								if(map.containsKey(value)) {
									arr[count] = map.get(value);
									
									count -= 1;		// 배열 뒤에서부터 저장하기위해서 필요한 count변수, 값 넣은 이후 1감소
									
									// 마지막 검사숫자
									if(t == 0){
										checkNum = arr[count+1];
										continue;
									}
									// 홀수 위치라면
									if(t % 2 == 1) {
										oddNumSum += arr[count+1];
									}
									
									// 짝수 위치라면
									if(t % 2 == 0) {
										evenNumSum += arr[count+1];
									}
								}
							}

							// 정상적인 암호인 경우
							if(((oddNumSum * 3) + evenNumSum + checkNum) % 10 == 0) {
								int resultSum = 0;
								for(int k = 0; k < 8; k++) {
									resultSum += arr[k];
								}
								System.out.println("#" + a + " " + resultSum);
							}
							// 비정상적인 암호인 경우
							else {
								System.out.println("#" + a + " " + 0);
							}
							// 이후 조사 안함
							passCheck = true;
							break;
						}
					}
				}
				
			}
		}
	}
}
