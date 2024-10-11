package three_week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 다리놓기_1010 {
    static int[][] memo;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        memo = new int[31][31];

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            System.out.println(getC(M, N));
        }

    }

    public static int getC(int n, int k) {
        if (memo[n][k] > 0) {
            return memo[n][k];
        }

        if (n == k || k == 0) {
            return 1;
        }


        memo[n][k] = getC(n - 1, k - 1) + getC(n - 1, k);

        return memo[n][k];
    }
}
