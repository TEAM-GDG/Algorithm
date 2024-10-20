import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());

            // 파일 크기 저장할 배열, 누적 합 배열을 만드는데 사용
            int[] files = new int[K];
            // 파일 크기들의 누적 합
            int[] prefixSum = new int[K];

            StringTokenizer st = new StringTokenizer(br.readLine());
            files[0] = Integer.parseInt(st.nextToken());
            prefixSum[0] = files[0];
            for (int i = 1; i < K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
                // 누적합 생성
                prefixSum[i] = prefixSum[i - 1] + files[i];
            }

            // 구간별 최소 비용을 계산해서 저장하는 배열
            int[][] dp = new int[K][K];

            // 구간 길이를 2에서 K까지 확장해가며 최소 비용 계산
            for (int length = 2; length <= K; length++) {
                // 현재 계산할 구간의 시작점
                for (int i = 0; i <= K - length; i++) {
                    // 계산할 구간의 끝점
                    int j = i + length - 1;
                    dp[i][j] = Integer.MAX_VALUE;

                    // i에서 j까지 합치는 최소 비용 계산
                    // i에서 j까지의 구간을 계산할때 구간을 어떻게 나눌지 결정하는 변수
                    // C1, C2, C3가 있을때
                    // (C1+C2)+C3 로 나눌지 C1+(C2+C3)로 나눌지 결정함
                    for (int k = i; k < j; k++) {
                        // (C1+C2)+C3 를 계산할 경우
                        // dp[i][k] = C1+C2의 부분 구간의 최소 비용
                        // dp[k+1][j] = C3의 부분 구간의 최소 비용(1개만 있으므로 더할게 없어서 0)
                        // prefixSum[j] - (i > 0 ? prefixSum[i - 1] : 0)
                        // 두 구간을 합치는데 필요한 비용
                        // C1 = 40, C2 = 30, C3 = 30, i = 0, j = 2일때
                        // prefixSum[j] = 100
                        // (i > 0 ? prefixSum[i - 1] : 0)
                        // i가 0보다 크다면 prefix[i - 1]부터 prefixSum[j]까지의 구간의 합을 계산
                        // 예시처럼 i = 0일때는 처음부터 j까지의 구간의 합을 계산해야하기 때문에 prefixSum[j]에서 0을 빼준다.
                        // C1+C2 와 (C1+C2)+C3 의 두 구간을 합치는데 필요한 비용을 계산
                        int cost = dp[i][k] + dp[k + 1][j] + prefixSum[j] - (i > 0 ? prefixSum[i - 1] : 0);
                        // 각 경우의 수별로 계산한 비용중 최소 비용을 각 칸에 저장
                        dp[i][j] = Math.min(dp[i][j], cost);
                    }
                }
            }

            // 위 과정을 거치고나면 자동적으로 dp[0][K - 1]의 위치에 최소 비용이 저장됨
            System.out.println(dp[0][K - 1]);
        }

        br.close();
    }
}
