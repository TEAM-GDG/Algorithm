package three_week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이항계수2_11051 {
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][K + 1];

        System.out.println(getC(N, K));
    }

    public static int getC(int N, int K) {
        if (N == K || K == 0) {
            return 1;
        }

        if (dp[N][K] != 0) {
            return dp[N][K];
        }

        dp[N][K] = (getC(N - 1, K - 1) + getC(N - 1, K)) % 10007;

        return dp[N][K];
    }

}
