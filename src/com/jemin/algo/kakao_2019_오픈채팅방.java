import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class kakao_2019_1 {

	public static void main(String[] args) throws Exception {
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		String[] result = solution(record);

		System.out.println(Arrays.toString(result));
	}

	public static String[] solution(String[] record) {
		String[] answer;
		ArrayList<String> content = new ArrayList<>();
		ArrayList<String> idArr = new ArrayList<>();
		String[] strArr = record;

		StringTokenizer st;
		String oper, id, nickName;

		HashMap<String, String> map = new HashMap<>();

		for (int i = 0; i < record.length; i++) {
			st = new StringTokenizer(strArr[i], " ");
			oper = st.nextToken();
			id = st.nextToken();
			
			// Leave
			if(oper.equals("Leave")) {
				idArr.add(id);
				content.add("´ÔÀÌ ³ª°¬½À´Ï´Ù.");
			}
			else {
				nickName = st.nextToken();
				// Enter or Change
				map.put(id, nickName);

				if(oper.equals("Enter")) {
					idArr.add(id);
					content.add("´ÔÀÌ µé¾î¿Ô½À´Ï´Ù.");
				}
			}
		}
		
		answer = new String[idArr.size()];
		
		for (int i = 0; i < idArr.size(); i++) {
			answer[i] = map.get(idArr.get(i)) + content.get(i);
		}
		return answer;
	}
}
