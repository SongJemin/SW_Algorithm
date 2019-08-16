package hw;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
/**
*
* SWEA_5644 무선충전
* PASS
*
*/


public class Wireless {
     
    static List<Integer> aList;
    static List<Integer> bList;
    static List<BC> bcArr;
    static int inputTime, inputBCNum;
    static int[] aPointBC;
    static int[] bPointBC;
    static int sum;
     
    static int[] moveX = {0, -1 , 0, 1, 0}; // 이동X, 상, 우, 하, 좌
    static int[] moveY = {0, 0 , 1, 0, -1}; // 이동X, 상, 우, 하, 좌
     
    static int[][] arr;
     
    public static void main(String[] args) throws Exception{
         
        int inputCase;
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputCase = Integer.parseInt(br.readLine());
         
         
        for(int a=1; a<=inputCase; a++){
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            sum = 0;
            arr = new int[10][10];
            aList = new ArrayList<>();
            bList = new ArrayList<>();
             
            inputTime = Integer.parseInt(st.nextToken());
            inputBCNum = Integer.parseInt(st.nextToken());
             
            bcArr = new ArrayList<>();
            aPointBC = new int[inputBCNum];
            bPointBC = new int[inputBCNum];
             
            st = new StringTokenizer(br.readLine());
            aList.add(0);
            bList.add(0);
             
            // A의 이동 방향 입력
            for(int i=0; i<inputTime; i++){
                aList.add(Integer.parseInt(st.nextToken()));
            }
             
            // B의 이동 방향 입력
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<inputTime; i++){
                bList.add(Integer.parseInt(st.nextToken()));
            }
             
            // 충전기 BC값 입력
            for(int i=0; i<inputBCNum; i++){
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken()) - 1;
                int x = Integer.parseInt(st.nextToken()) - 1;
                int range = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());
                bcArr.add(new BC(x, y, range, power));
            }
             
            // 첫 위치 설정
            int aCurrentX = 0;
            int aCurrentY = 0;
            int bCurrentX = 9;
            int bCurrentY = 9;
             
             
            for(int i=0; i<inputTime+1; i++){
                 
                // 현재 위치 계산
                aCurrentX = aCurrentX + moveX[aList.get(i)];
                aCurrentY = aCurrentY + moveY[aList.get(i)];
                 
                bCurrentX = bCurrentX + moveX[bList.get(i)];
                bCurrentY = bCurrentY + moveY[bList.get(i)];
 
                // 모든 BC 충전기 반복
                for(int j=0; j<inputBCNum; j++){
                     
                    // A가 충전기 범위 안에 들어가는지 조사
                    if(Math.abs(aCurrentX - bcArr.get(j).x) + Math.abs(aCurrentY - bcArr.get(j).y) <= bcArr.get(j).range){
                        aPointBC[j] = bcArr.get(j).power;
                    }
                    else{
                        aPointBC[j] = 0;
                    }
                     
                    // B가 충전기 범위 안에 들어가는지 조사
                    if(Math.abs(bCurrentX - bcArr.get(j).x) + Math.abs(bCurrentY - bcArr.get(j).y) <= bcArr.get(j).range){
                        bPointBC[j] = bcArr.get(j).power;
                    }else{
                        bPointBC[j] = 0;
                    }
                }
                 
                int timeMax = 0;
                 
                for(int k=0; k<inputBCNum; k++){
                    for(int l=0; l<inputBCNum; l++){
                        // 같은 bc일 때 a,b 둘다 더해봤자 해당 충전기 파워값
                        if(k == l){
                            timeMax = Math.max(timeMax, aPointBC[k]);
                            timeMax = Math.max(timeMax, bPointBC[k]);
                        }
                        // 다른 bc일때 서로 다른 충전기 파워값 더하기
                        else{
                            timeMax = Math.max(timeMax, aPointBC[k] + bPointBC[l]);
                        }
                    }
                }
                sum += timeMax;
             
            }
            System.out.println("#" + a + " " + sum);
        }       
    }
     
    static public class BC {
         
        int x;
        int y;
        int range;
        int power;
         
        public BC(int x, int y, int range, int power) {
             
            this.x = x;
            this.y = y;
            this.range = range;
            this.power = power;
        }
    }
}
