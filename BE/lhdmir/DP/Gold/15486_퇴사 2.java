import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n];
        int[] p = new int[n];
        // 각 날짜별로 최대수익을 저장할 배열
        // 마지막날부터 계산하는데 이전의 계산했던 기록이 필요하기때문에
        // n + 1 사이즈로 생성
        int[] dp = new int[n + 1];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        // i일에 시작한 상담이 끝나고 다음 상담이 시작될 날짜
        int nextDay;
        // 마지막날부터 첫날까지 역순으로 계산
        for (int i = n - 1; i >= 0; i--) {
            // 현재 날짜 + 현재 날짜에 시작한 상담의 기간
            // i = 5, t[i] = 4 일 경우, 5일에 시작한 상담은 4일이 걸려 9일에 끝날 예정이다.
            nextDay = i + t[i];
            // nextDay = 9, n = 7일 경우, 8일째에는 퇴사하기때문에
            // 현재일(i)에는 상담이 불가능하다
            // nextDay = 6, n = 7일 경우에는, 6일째에 상담이 끝나기 때문에
            // 현재일(i)에는 상담이 가능하다.
            if (nextDay <= n) {
                // 현재일(i) = 현재일 + 1의 수익과 현재일의 수익 + 다음상담일의 수익을
                // 비교해서 더 큰 값을 최대 수익을 저장할 배열에 저장
                dp[i] = Math.max(dp[i + 1], p[i] + dp[nextDay]);
            } else {
                // 상담이 불가능한 경우에는 계산을 할 이유가 없으므로 이전의 기록을 그대로 가져온다.
                dp[i] = dp[i + 1];
            }
        }

        // 배열의 마지막부터 계산해 배열의 앞부분으로 올수록 점진적으로 값이 증가하기 때문에
        // dp[0]가 제일 큰 값이 된다.
        System.out.println(dp[0]);
    }
}
