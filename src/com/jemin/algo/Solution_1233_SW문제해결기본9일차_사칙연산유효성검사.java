import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    static char[] tree;
    static char prevData;
    static int count = 0;
    static String result;
    static boolean check;
 
    static StringBuilder sb;
     
    public static void main(String[] args) throws Exception{
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int node, c1, c2;
        char operation;
         
        for(int a = 1; a<=10; a++){
            check = false;
            sb = new StringBuilder();
            int inputNum = Integer.parseInt(br.readLine());
            tree = new char[1001];
            for(int i=0; i<inputNum; i++){
                String inputString = br.readLine();
                st = new StringTokenizer(inputString);
                node = Integer.parseInt(st.nextToken());
                operation = st.nextToken().charAt(0);
                 
                // 자식 노드 한 개 가질때
                if(st.hasMoreTokens()){
                    c1 = Integer.parseInt(st.nextToken());
                }
                // 자식 노드 두개 가질 때
                if(st.hasMoreTokens()){
                    c2 = Integer.parseInt(st.nextToken());
                }
                 
                // 노드1
                if(node == 1){
                    tree[1] = operation;
                }
                // 노드 1X
                else{
                     for (int k = 1; k < tree.length; k++) {
                         if (tree[k * 2] == 0) {
                              tree[k * 2] = operation;
                              break;
                          } 
                    
                          
                         else if (tree[k * 2 + 1] == 0) { 
                               tree[k * 2 + 1] = operation;
                                break;
                          }
                      } 
                }
                 
            }
            inorder(1);
            for (int i1 = 1; i1 < sb.length(); i1++) {
                // 현재 값 = 연산자
                if (sb.charAt(i1) == '+' || sb.charAt(i1) == '-' || sb.charAt(i1) == '/' || sb.charAt(i1) == '*') {
                    // 그 전 값 = 연산자
                    if (sb.charAt(i1 - 1) == '+' || sb.charAt(i1 - 1) == '-' || sb.charAt(i1 - 1) == '/'
                            || sb.charAt(i1 - 1) == '*') {
                        // 0 출력
                        System.out.println("#"  + a +" " + 0);
                        check = true;
                        break;
                    }
                // 현재 값 = 숫자
                } else {
                    // 그 전 값 = 연산자
                    if (sb.charAt(i1 - 1) == '+' || sb.charAt(i1 - 1) == '-' || sb.charAt(i1 - 1) == '/'
                            || sb.charAt(i1 - 1) == '*') {
                        continue;
                    }
                    // 그 전 값 = 숫자
                    else {
                        // 0 출력
                        System.out.println("#"  + a +" " + 0);
                        check = true;
                        break;
                    }
                }
            }
            // 모든 조건 만족
            if (!check) {
                System.out.println("#"  + a +" " + 1);
            }
        }
 
         
    }
     
    public static void inorder(int index) {
 
        if(index * 2 < tree.length && tree[index * 2] != 0) {
            inorder(index * 2);
         }
        sb.append(tree[index]);
        if(index * 2 + 1 < tree.length && tree[index * 2 + 1] != 0) {
         
            inorder(index * 2 + 1);
         }
     }
}