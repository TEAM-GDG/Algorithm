package DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_1010_다리놓기 {
    static int[][] dp = new int[31][31];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int n,r;

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            int siteCount = combination(n,r);
            sb.append(siteCount).append("\n");
        }

        System.out.println(sb);

    }

    static int combination(int n, int r) {
        if (n == r || r == 0) return 1;
        //nCr = n-1Cr-1 + n-1Cr
        if (dp[n][r] != 0) return dp[n][r];
        dp[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);
        return dp[n][r];
    }
}
