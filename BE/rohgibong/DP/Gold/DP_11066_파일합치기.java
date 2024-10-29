import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11066_파일합치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());    //T: 테스트 케이스 개수
        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[K+1];
            int[] sum = new int[K+1];
            int[][] dp = new int[K+1][K+1];

            for (int j = 1; j <= K; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
                sum[j] = sum[j-1] + arr[j];
            }

            for(int n = 1; n <= K; n++) {
                for(int start = 1; start + n <= K; start++) {
                    int end = start + n;
                    dp[start][end] = Integer.MAX_VALUE;
                    for(int mid = start; mid < end; mid++) {
                        dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid + 1][end] + sum[end] - sum[start - 1]);
                    }
                }
            }
            System.out.println(dp[1][K]);
        }

    }
}
