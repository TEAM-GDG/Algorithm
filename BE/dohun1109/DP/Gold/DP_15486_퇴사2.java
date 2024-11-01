package DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_15486_퇴사2 {
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N + 2][2];
        int[] dp = new int[N + 2];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int ti = Integer.parseInt(st.nextToken());
            int pi = Integer.parseInt(st.nextToken());
            arr[i][0] = ti; // 기간
            arr[i][1] = pi; // 금액
        }

        int max = -1;
        for (int i = 1; i <= N + 1; i++) {
            max = Math.max(max, dp[i]); // 0
            System.out.println("max = " + max);

            int next = i + arr[i][0];   //끝나는 기간 4
            System.out.println("next = " + next);

            if (next < N + 2) {dp[next] = Math.max(dp[next], max + arr[i][1]);
            System.out.println("dp["+ next+ "]= " + dp[next]);}
        }
        System.out.println(dp[N + 1]);
    }
}