package BruteForce.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BF_1107_리모컨 {
    static int[] Btn = new int[10];
    static ArrayList<Integer> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //현재 보고있는 채널 100번
        int N = Integer.parseInt(br.readLine());    //이동하려고 하는 채널 번호
        int M = Integer.parseInt(br.readLine());    //고장난 버튼의 개수
        StringBuilder sb = new StringBuilder();
        int MIN_COUNT = Math.abs(N - 100);  //발생할 수 있는 최대 경우(직접  +,-)

        if(M>0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                Btn[Integer.parseInt(st.nextToken())] = -1;
            }
        }

        DFS(sb, 1);
        sb.setLength(0);
        if (!arr.isEmpty()){
            int MIN_SIGN = 0;
            int index =0;
            for (Integer num : arr) {
                int distance = Math.abs(N-num);
                if (distance<Math.abs(N-arr.get(MIN_SIGN))){
                    MIN_SIGN = index ;
                }else if (distance == Math.abs(N-arr.get(MIN_SIGN))){
                    if(arr.get(MIN_SIGN) > num) MIN_SIGN = index;
                }
                index++;
            }
            MIN_COUNT = Math.min(MIN_COUNT, (Math.abs(N-arr.get(MIN_SIGN))+arr.get(MIN_SIGN).toString().length()));
            sb.append(MIN_COUNT);
        }else {
            sb.append(MIN_COUNT);
        }
        System.out.println(sb);
    }

    
    static void DFS(StringBuilder sb, int currentDepth) {
        if (currentDepth == 6) { // 현재 깊이가 목표 깊이와 같다면
            for (int i = 0; i < 10; i++) { // 0부터 9까지 반복
                if (Btn[i] != -1) { // 버튼이 고장나지 않았다면
                    sb.append(i); // StringBuilder에 숫자 추가
                    try {
                        arr.add(Integer.parseInt(sb.toString())); // 숫자로 변환하여 리스트에 추가
                    } catch (NumberFormatException e) {
                        // 예외 처리 (필요시)
                    }
                    sb.setLength(sb.length() - 1); // StringBuilder 길이 조정 (마지막 문자 제거)
                }
            }
            return; // 메서드 종료
        }
        for (int i = 0; i < 10; i++) { // 0부터 9까지 반복
            if (Btn[i] != -1) { // 버튼이 고장나지 않았다면
                sb.append(i); // StringBuilder에 숫자 추가
                DFS(sb, currentDepth + 1); // 재귀 호출로 다음 깊이 탐색
                try {
                    arr.add(Integer.parseInt(sb.toString())); // 숫자로 변환하여 리스트에 추가
                } catch (NumberFormatException e) {
                    // 예외 처리 (필요시)
                }
                sb.setLength(sb.length() - 1); // StringBuilder 길이 조정 (마지막 문자 제거)
            }
        }
    }

}
