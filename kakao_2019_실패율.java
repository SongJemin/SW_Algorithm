import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class kakao_2019_2 {

	public static void main(String[] args) throws Exception {
		
		int N = 5;
		int[] stages = {2, 1, 2, 3, 2, 4, 3, 3};
		
		int[] result = solution(N, stages);
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] solution(int N, int[] stages) {
		
		ArrayList<Stage> stageArr = new ArrayList<>();
        int[] answer = new int[N];
        int[] stagePersonNum = new int[N+2];
        int[] userPos = stages;
        
        // ������������ ���� ��ġ�� ��� total ���
        for (int i = 0; i < stages.length; i++) {
			stagePersonNum[userPos[i]] += 1;
		}
        
        // All clear
        int passNum = stagePersonNum[N+1];
        int failCount;
        double failRate;
        
        for (int i = N; i > 0; i--) {
        	// ���� ���������� ��ġ�� ��� + ����� ���
        	passNum = stagePersonNum[i] + passNum;
        	// ���� ���������� ��ġ�� �����
        	failCount = stagePersonNum[i];
        	
        	// ���������� ������ ������ ���� ���
        	if(passNum == 0) stageArr.add(new Stage(i, 0));
        	// ���������� �� ���̶� ������ ������ �ִ� ���
        	else {
        		failRate = (double) failCount / passNum;
        		stageArr.add(new Stage(i, failRate));
        	}
		}

        // �������� ���� ��ü ����
        Collections.sort(stageArr);
        
        for (int i = 0; i < stageArr.size(); i++) {
        	answer[i] = stageArr.get(i).stage;
		}
        return answer;
    }
	
	static class Stage implements Comparable<Stage> {
		
		int stage;
		double failRate;

		public Stage(int stage, double failRate) {
			super();
			this.stage = stage;
			this.failRate = failRate;
		}

		@Override
		public int compareTo(Stage o) {
			if(this.failRate > o.failRate) return -1;
			else if(this.failRate == o.failRate) {
				if(this.stage < o.stage) return -1;
				else return 1;
			}
			else return 1;
		}
	}
}
