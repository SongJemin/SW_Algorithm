package exam2020;

import java.util.*;

public class kakao_3_°ýÈ£º¯È¯ {
	
	static String entireValue;
	
	public static void main(String[] args) {
		String str1 = "(()())()";
		String str2 = ")(";
		String str3 = "()))((()";

		String result = solution(str3);
		System.out.println(result);
	}
	
    public static String solution(String p) {
        String answer = "";
        String inputStr = p;
		entireValue = p;
		
        answer =  balance(inputStr, 0);
        return answer;
    }
    
    public static String balance(String str, int startIndex) {
		
        String u, v;
    	String result = "";
    	int count = 0;
    	boolean perfectFlag = true;
    	Stack<Character> stack = new Stack<>();
    	
    	if(str.length() == 0) {
    		return "";
    	}
    	else {
    		for (int i = startIndex; i < entireValue.length(); i++) {
    			if(entireValue.charAt(i) == '(') {
    				count++;
    				stack.push('(');
    			}
    			else {
    				count--;
    				if(stack.isEmpty()) {
    					perfectFlag = false;
    				}
    				else stack.pop();
    			}
    			if(count == 0) {
    				
    				u = entireValue.substring(startIndex, i+1);
    				v = entireValue.substring(i+1, entireValue.length());
    				
    				if(perfectFlag && stack.size() == 0) {
    					result = u + balance(v, i+1);
    					return result;
    				}
    				else {
    					result = '(' + balance(v, i+1) + ')' + reverseU(u.substring(1, u.length()-1));
    					return result;
    				}
    			}
			}
    	}
    	return result;
    }
    
    public static String reverseU(String str) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '(') sb.append(')');
			else sb.append('(');
		}
    	return sb.toString();
    }
}
