import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Jungol_2247_������ {

	public static int studentNum;
	public static PriorityQueue<Student> pq;
	public static int maxExistTime = Integer.MIN_VALUE;
	public static int maxEmptyTime = Integer.MIN_VALUE;
	public static int startValue, endValue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pq = new PriorityQueue<>();
		StringTokenizer st;
		int startTime, endTime;

		studentNum = Integer.parseInt(br.readLine());

		for (int i = 0; i < studentNum; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			startTime = Integer.parseInt(st.nextToken());
			endTime = Integer.parseInt(st.nextToken());

			pq.add(new Student(startTime, endTime));
		}
		Student tempStudent = pq.remove();
		startValue = tempStudent.sTime;
		endValue = tempStudent.eTime;
		
		// ť�� �� �������� ����
		while (!pq.isEmpty()) {
			tempStudent = pq.remove();
			// ��ģ�ٸ�
			if (endValue >= tempStudent.sTime) {
				// ����
				if(endValue < tempStudent.eTime) {
					endValue = tempStudent.eTime;					
				}
			}
			// �Ȱ�ģ�ٸ�
			else {
				// �л� ���� �ִ� �ð� ��
				if (maxExistTime < endValue - startValue) {
					maxExistTime = endValue - startValue;
				}

				// �л� ���� �ִ� �ð� ��
				if (maxEmptyTime < tempStudent.sTime - endValue) {
					maxEmptyTime = tempStudent.sTime - endValue;
				}

				// �ٽ� �ʱ�ȭ
				startValue = tempStudent.sTime;
				endValue = tempStudent.eTime;
			}
		}
		// ������ ����Ǿ��ִ� ���
		if(maxExistTime < endValue - startValue) {
			maxExistTime = endValue - startValue;
		}

		// ����� ���� �ð��� X�ΰ��
		if (maxExistTime == Integer.MIN_VALUE) {
			maxEmptyTime = 0;
		}

		System.out.print(maxExistTime + " " + maxEmptyTime);
	}

	public static class Student implements Comparable<Student> {
		int sTime;
		int eTime;

		public Student(int sTime, int eTime) {
			this.sTime = sTime;
			this.eTime = eTime;
		}

		@Override
		public int compareTo(Student o) {
			if (this.sTime < o.sTime) {
				return -1;
			}
			else {
				return 1;
			}
		}
	}
}
