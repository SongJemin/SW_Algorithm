import java.util.*;

public class kakao_2019_4 {
	
	public static void main(String[] args) throws Exception {
	
		int[] food_times = {3, 1, 2};
		int k = 5;
		
		int result = solution(food_times, k);
		System.out.println(result);
	}

	public static int solution(int[] food_times, long k) {
		int answer = -1;
		long kNum = k;
		ArrayList<Dish> dishArr = new ArrayList<>();
		
		for (int i = 0; i < food_times.length; i++) {
			dishArr.add(new Dish(i, food_times[i]));
		}
		Collections.sort(dishArr);
		int sIndex = 0;
		int dishSize = food_times.length;
		
		Comparator<Dish> compareIndex = new Comparator<Dish>() {
			@Override
			public int compare(Dish o1, Dish o2) {
				if(o1.index < o2.index) return -1;
				else return 1;
			}
		};
		
		int prevTime = 0;
		ArrayList<Dish> dishs = new ArrayList<>();
		
		while(sIndex < dishSize) {
			int sameValue = 1;
			
			// ���� ���� �ּ� time ��
			int frontTime = dishArr.get(sIndex).time;
			// �ּ� time ���� ���� index�� �� �����ϴ� �� Ȯ��
			for (int i = sIndex+1; i < dishArr.size(); i++) {
				if(dishArr.get(i).time == frontTime) {
					sameValue += 1;
				}
				else break;
			}
			
			// ���� k�� (���� ���� �ּ� time�� - ���� �ܰ� time �ּҰ�) * (���� ���� ����)) ��
			if(kNum < ((long)(frontTime - prevTime) * (long)(dishSize - sIndex))){
				for (int i = sIndex; i < dishArr.size(); i++) {
					dishs.add(dishArr.get(i));
				}
				// index �������� Dish ����
				Collections.sort(dishs, compareIndex);
				long value = kNum % dishs.size();
				answer = dishs.get((int) value).index + 1;
				break;
			}
			
			// k�� ����
			kNum -= (long)((long)(frontTime - prevTime) * (long)(dishSize - sIndex));
			prevTime = frontTime;
			sIndex += sameValue;
		}
		
		return answer;
	}
	
	public static class Dish implements Comparable<Dish>{
		int index;
		int time;
		
		public Dish(int index, int time) {
			super();
			this.index = index;
			this.time = time;
		}

		@Override
		public int compareTo(Dish o) {
			if(this.time < o.time) {
				return -1;
			}
			else return 1;
		}
	}
}
