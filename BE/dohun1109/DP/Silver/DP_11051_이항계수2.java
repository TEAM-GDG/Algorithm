package DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP_11051_이항계수2 {
    static int[][] dp;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        dp = new int[1001][1001];
        Arrays.fill(dp[0],1);

        for (int i = 1; i < k+1; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= n - k + 1; j++) {
                dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 100_07;
            }
        }


        System.out.println(dp[k][n-k]);

    }

}
