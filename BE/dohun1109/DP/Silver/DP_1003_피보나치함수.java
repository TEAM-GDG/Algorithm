package DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DP_1003_피보나치함수 {
    static int[] dp;
    static int[] answer;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            dp = new int[41];
            answer = new int[2];
            dp[0] = 0;
            dp[1] = 1;
            answer[0] = 1;
            answer[1] = 1;

            int num = Integer.parseInt(br.readLine());
            if (num == 0){
                sb.append(1).append(" ").append(0).append('\n');
                continue;
            } else if( num == 1){
                sb.append(0).append(" ").append(1).append('\n');
                continue;
            }
            fibo(num);

            for (int j = 0; j< num-2; j++){
                answer[0]+=dp[j];
                answer[1]+=dp[j+1];
            }
            sb.append(answer[0]).append(" ").append(answer[1]).append('\n');

        }
        System.out.print(sb.toString());


    }

    static int fibo(int x){
        if ( x == 1) return 1;
        if (x == 0) return 0;
        if (dp[x] != 0) {
            return dp[x];
        }
        dp[x] = fibo(x - 1) + fibo(x - 2);
        return dp[x];
    }
}
