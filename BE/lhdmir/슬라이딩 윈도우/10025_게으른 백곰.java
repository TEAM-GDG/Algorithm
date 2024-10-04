import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int minPos = Integer.MAX_VALUE;
        int maxPos = Integer.MIN_VALUE;

        // 각 양동이의 좌표와 얼음 양을 저장할 배열
        int[] positions = new int[n];
        int[] iceAmounts = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            positions[i] = x;
            iceAmounts[i] = g;

            if (x < minPos) {
                minPos = x;
            }
            if (x > maxPos) {
                maxPos = x;
            }
        }

        // 유효한 범위의 배열만 생성
        int rangeSize = maxPos - minPos + 1;
        int[] cage = new int[rangeSize];

        // 좌표를 범위 내 인덱스로 변환해 배열에 저장
        for (int i = 0; i < n; i++) {
            int index = positions[i] - minPos;
            cage[index] = iceAmounts[i];
        }

        // 앨버트가 접근 가능한 범위
        int windowSize = 2 * k + 1;
        // 앨버트가 접근 가능한 범위보다 cage가 더 적을수도 있음
        int validRange = Math.min(windowSize, cage.length);
        int sum = 0;
        int maxSum = 0;

        // 처음에 0 ~ validRange - 1 범위의 얼음 양을 계산
        for (int i = 0; i < validRange; i++) {
            sum += cage[i];
        }
        maxSum = sum;

        // 슬라이딩 윈도우로 한 칸씩 이동하며 최대 합 갱신
        for (int i = validRange; i < cage.length; i++) {
            sum += cage[i];
            sum -= cage[i - windowSize];
            maxSum = Math.max(maxSum, sum);
        }

        System.out.println(maxSum);
    }
}
