package DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_2491_수열 {
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        dp = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dp[i] = Integer.parseInt(st.nextToken());
        }


        if( N == 1){
            System.out.println(1);
        }else{
            int MAX = Integer.MIN_VALUE;
            int down_count = 1;
            int up_count = 1;
            for (int i = 1; i < N; i++) {
                if (dp[i] - dp[i-1] >=0){
                    up_count++;
                }else {
                    up_count = 1;
                }
                MAX = Math.max(MAX, up_count);
            }
            for (int j = 1; j < N; j++) {
                if (dp[j] - dp[j-1] <=0){
                    down_count++;
                }else {
                    down_count = 1;
                }
                MAX = Math.max(MAX, down_count);
            }


            System.out.println(MAX<3?2:MAX);
        }




    }
}
