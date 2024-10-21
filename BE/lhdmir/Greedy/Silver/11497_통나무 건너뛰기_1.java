import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());


        int n;
        int[] logs;
        int[] arrangedLogs;
        int left;
        int right;
        int maxDifficulty = 0;
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            logs = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                logs[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(logs);

            // logs의 요소를 교차로 배치해서
            // arrangedLogs에 저장
            arrangedLogs = new int[n];
            left = 0;
            right = n - 1;

            for (int i = 0; i < n; i++) {
                // 짝수번째 요소들은 왼쪽부터 채워넣음
                if (i % 2 == 0) {
                    arrangedLogs[left++] = logs[i];
                }
                // 홀수번째 요소들은 오른쪽부터 채워넣음
                else {
                    arrangedLogs[right--] = logs[i];
                }
                // 위 과정을 거치면 [2, 4, 5, 7, 9]는
                // [2, 5, 9, 7, 4]가 저장됨
            }

            // 각 요소들을 순환하면서 i와 i-1 차이의 최대값을 계산
            maxDifficulty = 0;
            for (int i = 0; i < n; i++) {
                maxDifficulty = Math.max(maxDifficulty, Math.abs(arrangedLogs[i] - arrangedLogs[(i + 1) % n]));
            }

            System.out.println(maxDifficulty);
        }
    }
}
