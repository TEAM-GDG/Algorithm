import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] row = new int[N];
        int[] col = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            row[i] = Integer.parseInt(st.nextToken());
            col[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][N];

        // len은 행렬을 곱하는 구간의 길이
        // len = 1일경우 2개의 행렬의 곱
        // len = 2일경우 3개의 행렬의 곱
        for (int len = 1; len < N; len++) {
            // i = 현재 곱할 행렬의 시작점
            // 예를들어, 행렬 A,B,C가 있을때 i = 0은 행렬 A부터 시작한다는 의미
            // i = 1은 행렬 B 부터 시작
            for (int i = 0; i + len < N; i++) {
                // j = 곱셈을 진행할 행렬 범위의 끝점을 나타냄
                // i에서 j까지의 행렬을 곱하는데 필요한 최소 곱셈 연산 횟수를 계산
                // j = i + len 으로 설정되어, i에서 j까지 len+1 개의 행렬을 곱함
                // 예를들어 i=0, len=2 일때, j=2가 되고 행렬A부터 C까지를 의미
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;

                // k = 행렬 곱셈을 나눌 분할점
                // i에서 j까지의 행렬을 어떻게 나눌지 결정하는 변수로,
                // i에서 k까지와 k+1에서 j까지 나누어 곱한 후 그 결과를 합산
                // 에를들어, 행렬ABC를 곱할때 (A*(B*C)) 또는 ((A*B)*C)처럼
                // 두 부분으로 나눠서 곱할때 어느 방식이 최소 연산인지를 계산
                for (int k = i; k < j; k++) {
                    // dp[i][k]
                    // i에서 k까지의 곱셈 비용(dp[i][k], ex: AB),
                    // dp[k+1][j]
                    // k+1에서 j까지의 곱셈 비용(dp[k+1][j], ex: BC)

                    // row[i] * col[k] * col[j]
                    // A(5,3), B(3,2), C(2,6) 일때
                    // i = 0, j = 2, k = 0 or 1일 경우
                    // row[i] = 5, col[j] = 6으로 고정되고 중간에 들어갈 값이 k로 인해
                    // 2가 될지 3이 될지 결정된다.
                    // (AB)C 일 경우 (5*2)2*6의 행렬이 되고
                    // A(BC) 일 경우 5*3(3*6)의 행렬이 된다.
                    // AB의 연산 횟수(5*3*2 = 30) + (AB)C의 연산 횟수((5*2)6 = 60) = 90
                    // BC의 연산 횟수(3*2*6 = 36) + A(BC)의 연산 횟수(5(3*6) = 90) = 126
                    int cost = dp[i][k] + dp[k + 1][j] + row[i] * col[k] * col[j];
                    // 이전에 계산한 결과와 비교해서 최소값을 저장
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        // 위 과정을 거치고나면 자동적으로 dp[0][N -1]의 위치에 최솟값이 저장됨
        System.out.println(dp[0][N - 1]);
    }
}
