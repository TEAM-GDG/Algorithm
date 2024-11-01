package DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP_11066_파일합치기 {
    static int T,K;
    static int[] files;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        /**
         * 1. 각 장은 각각의 다른 파일에 저장한다.
         * 2. 소설의 모든 장을 쓰고 나서는 각 장이 쓰여진 파일을 합쳐서 최종적으로 한 개의 파일을 만든다.
         * 3. 두 개의 파일을 합쳐서 하나의 임시파일을 만들고,
         * 4. 이 임시파일이나 원래의 파일을 계속 두개 씩 합쳐서 소설의 여러 장들이 연속되도록 파일을 합쳐나간다.
         * 5. 두 개의 파일을 합칠 때 필요한 비용이 = 두 파일 크기의 합
         * 6. 최종적인 한 개의 파일을 완성하는데 필요한 비용의 총 합을 구하시오.
         *
         * C1, C2, C3, C4 가 연속적인 네 개의 장을 수록하고 있는 파일이고, 파일 크기가
         * 각각 40, 30, 30, 50 이라고 하자 .
         * X1 = (C2 + C3) = 60
         * X2 = X1 + C1 = 100
         * X3 = X2 + C4 = 150
         *
         * result = X1 + X2 + X3 = 310
         *
         * Y1 = (C1 + C2) = 70
         * Y2 = (C3 + C4) = 80
         * Y3 = Y1 + Y2 = 150
         *
         * result = Y1 + Y2 + Y3 = 300
         *
         * 소설의 각 장들이 수록된 파일의 크기가 주어졌을 때, 이 파일들을 하니의 파일로 합칠 때 최소비용을 계산하시오
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while ( T >0 ) {
            K = Integer.parseInt(br.readLine());
            files = new int[K + 1];
            dp = new int[K + 1][K + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i < K; i++) {
                dp[i][i+1] = files[i]+files[i+1];
            }

            BottomUp();
            sb.append(dp[1][K]).append('\n');
            T--;
        }
        System.out.print(sb);
    }

    private static void BottomUp() {
        int[] prefixSum = new int[K + 1];
        for (int i = 1; i <= K; i++) {
            prefixSum[i] = prefixSum[i - 1] + files[i];
        }

        for (int len = 2; len <= K; len++) {
            for (int i = 1; i <= K - len + 1; i++) {
                int j = i + len -1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k+1][j] + (prefixSum[j] - prefixSum[i-1]);
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }


        
    }
}
