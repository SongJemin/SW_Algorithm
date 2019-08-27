package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_4408 {
	
	static int studentNum;
	static List<Move> moveArr;
	static int time;
	static boolean[] checked;
	
	public static void main(String[] args) throws Exception {
		int inputCase;
		int start, dest;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		inputCase = Integer.parseInt(br.readLine().trim());
		for (int a = 1; a <= inputCase; a++) {
			time = 0;
			studentNum = Integer.parseInt(br.readLine().trim());
			moveArr = new ArrayList<>();
			
			checked = new boolean[studentNum];
			
			for (int i = 0; i < studentNum; i++) {
				st = new StringTokenizer(br.readLine());
				start = Integer.parseInt(st.nextToken());
				dest = Integer.parseInt(st.nextToken());
				if(start <= dest) {
					moveArr.add(new Move(start, dest));
				}
				else {
					moveArr.add(new Move(dest, start));
				}
			}
			
			Collections.sort(moveArr);
			
			int target = 0;
			int count = 0;
			
			while(true) {
				time += 1;
				// 가장 빠른 출발 방 구하기
				for (int i = 0; i < studentNum; i++) {
					if(!checked[i]) {
						target = moveArr.get(i).destRoom;	// target 지정
						count++;
						checked[i] = true;
						break;
					}
				}
				// 뒤에 학생들 출발 시간 조사
				for (int i = 1; i < studentNum; i++) {
					if(checked[i]) {
						continue;
					}
					// 이전 사람 도착 방이 홀수이고 바로 다음 사람 출발방이 바로 다음 숫자 (짝수)일경우 통행 겹치므로 제외 
					if((target % 2) == 1 && (target+1 == moveArr.get(i).startRoom)) {
						continue;
					}
					// 도착 방 < 다음 사람 출발 방
					if(target < moveArr.get(i).startRoom) {
						count++;
						checked[i] = true;
						target = moveArr.get(i).destRoom;	// 다음 target 넘기기
						continue;
					}
				}
				// 모두 조사 끝
				if(count == studentNum) {
					break;
				}

			}
			sb.append("#" + a + " " + time + "\n");
		}
		System.out.println(sb);
		
	}
	
	public static class Move implements Comparable<Move> {
		int startRoom;
		int destRoom;
		
		public Move(int startRoom, int destRoom) {
			this.startRoom = startRoom;
			this.destRoom = destRoom;
		}

		@Override
		public int compareTo(Move o) {
			if(this.startRoom > o.startRoom) {
				return 1;
			}
			else if(this.startRoom == o.startRoom) {
				return 0;
			}
			else {
				return -1;
			}
		}
	}
}
