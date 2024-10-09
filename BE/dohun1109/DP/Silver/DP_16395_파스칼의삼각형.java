package DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP_16395_파스칼의삼각형 {

    static int[][] dp = new int[31][31];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Arrays.fill(dp[0],1);

        for (int i = 1; i < k; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= n - k; j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }
        System.out.println(dp[k-1][n-k]);
    }
}

