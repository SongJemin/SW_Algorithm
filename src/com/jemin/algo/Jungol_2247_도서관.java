import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Jungol_2247_도서관 {

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
		
		// 큐가 다 돌때까지 진행
		while (!pq.isEmpty()) {
			tempStudent = pq.remove();
			// 겹친다면
			if (endValue >= tempStudent.sTime) {
				// 연장
				if(endValue < tempStudent.eTime) {
					endValue = tempStudent.eTime;					
				}
			}
			// 안겹친다면
			else {
				// 학생 존재 최대 시간 비교
				if (maxExistTime < endValue - startValue) {
					maxExistTime = endValue - startValue;
				}

				// 학생 없는 최대 시간 비교
				if (maxEmptyTime < tempStudent.sTime - endValue) {
					maxEmptyTime = tempStudent.sTime - endValue;
				}

				// 다시 초기화
				startValue = tempStudent.sTime;
				endValue = tempStudent.eTime;
			}
		}
		// 끝까지 연결되어있는 경우
		if(maxExistTime < endValue - startValue) {
			maxExistTime = endValue - startValue;
		}

		// 사람이 없는 시간이 X인경우
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
