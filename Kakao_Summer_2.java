package kakao_summer_2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Kakao_Summer_2 {

	static int[] arr;
	static char[] oper = {'*', '+', '-'};
	static boolean priorityCheck;
	static long maxValue = Long.MIN_VALUE;
	
	public static void main(String[] args) {
		String expr = "100-200*300-500+20";
		long result = solution(expr);
		System.out.println(result);
	}
	
	public static long solution(String expression) {
        long answer = 0;
        String expr = expression;
        StringBuilder sb = new StringBuilder();
        arr = new int[3];
        for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
        
        perm(arr, 3, 3, 0, expr);
        answer = maxValue;
        
        return answer;
    }
	
	public static int changeChartoNum(char oper) {
		int result = -1;
		
		switch(oper) {
		case '*' : 
			result = 0;
			break;
		case '+' :
			result = 1;
			break;
		case '-':
			result = 2;
			break;
		}
		return result;
	}
	
	public static void perm(int[] arr, int n, int r, int step, String expr) {
		if(r == step) {
			Stack<Character> operStack = new Stack();
	        StringBuilder numSb = new StringBuilder();
	        List<String> exprArr = new ArrayList<>();
	        
	        // 중위 -> 후위
	        for (int i = 0; i < expr.length(); i++) {
				if(expr.charAt(i) == '*' || expr.charAt(i) == '+' || expr.charAt(i) == '-') {
					
					exprArr.add(numSb.toString());
					numSb.setLength(0);
					
					// 숫자 낮은 것이 우선 순위 더 높은걸로 취급
					// 스택이 존재하면서 현재 연산자 우선순위 <= 스택 최상단 연산자 우선순위 일때 반복
					while(!operStack.isEmpty() && (arr[changeChartoNum(expr.charAt(i))] <= arr[changeChartoNum(operStack.peek())])) {
						exprArr.add(String.valueOf(operStack.pop()));
					}
					operStack.push(expr.charAt(i));
				}
				// 숫자일때
				else {
					numSb.append(expr.charAt(i));
				}
			}
	        exprArr.add(numSb.toString());
	        
	        while(!operStack.isEmpty()) {
	        	exprArr.add(String.valueOf(operStack.pop()));
	        }
	        
	        Stack<Long> calculStack = new Stack<>();
	        for (int i = 0; i < exprArr.size(); i++) {
	        	if(exprArr.get(i).equals("*") || exprArr.get(i).equals("+") || exprArr.get(i).equals("-")) {
	        		long secondNum = calculStack.pop();
	        		long firstNum = calculStack.pop();
	        		
	        		switch(exprArr.get(i)) {
	        		case "*" :
	        			calculStack.push(firstNum * secondNum);
	        			break;
	        		case "+" :
	        			calculStack.push(firstNum + secondNum);
	        			break;
	        		case "-" :
	        			calculStack.push(firstNum - secondNum);
	        			break;
	        		}
	        	}
	        	else {
	        		calculStack.push(Long.valueOf(exprArr.get(i)));
	        	}
			}
	        
	        maxValue = Math.max(maxValue, Math.abs(calculStack.pop()));
		}
		else {
			for (int i = step; i < n; i++) {
				int temp = arr[i];
				arr[i] = arr[step];
				arr[step] = temp;
				
				perm(arr, n, r, step+1, expr);
				
				temp = arr[i];
				arr[i] = arr[step];
				arr[step] = temp;
			}
		}
	}
}
