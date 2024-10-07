package two_week.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 21921_블로그 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 지난 일수
        int X = Integer.parseInt(st.nextToken()); // 알고싶은 일수

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N + 1];
        int[] copyArr = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 누적 합
        // [1 ,2, 3, 4, 5, 6, 7] -> [0, 1, 3, 6, 10 , 15, 21, 28]
        for (int i = 1; i < arr.length; i++) {
            copyArr[i] = arr[i] + copyArr[i - 1];
        }

        int max = 0;
        int cnt = 1;

        int i = X;
        while (i <= N) {
            /**
             * i에 X을 빼면 첫 부분 부터 부분합을 구함
             * 이런씩으로 하는 좋은 이유
             * - [0, 1, 3, 6, 10, 15, 21, 28]인데 만약 0이 없고
             *   만약 [1, 3, 6, 10, 15, 21, 28]이면
             *   i - X으로 하는 씩이면 따로 예외 처리를 해줘야 하는데 그럴필요가 없다.
             */
            int sum = copyArr[i] - copyArr[i-X];
            // 부분합 같다면 cnt 증가
            if(sum == max){
                cnt++;
            }
            // 더 크면 cnt 초기화 및 max에다가 부분합 대입
            else if(sum > max){
                max = sum;
                cnt = 1;
            }
            i++;
        }

        // 만약 0이면 SAD
        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max + "\n" + cnt);
        }
    }
}