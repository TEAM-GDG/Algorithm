package 투포인터_슬라이딩윈도우.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 투포인터_1806_부분합 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] sumA = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            sumA[i] = sumA[i - 1] + Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 1, min = Integer.MAX_VALUE;

        // 두 포인터로 최소 길이 탐색
        while (right <= N) {
            int sum = sumA[right] - sumA[left];

            if (sum >= S) {
                // 합이 S 이상이면 left를 증가시켜 구간을 줄여본다
                min = Math.min(min, right - left);
                left++;
            } else {
                // 합이 S보다 작으면 right를 늘려 구간을 확장
                right++;
            }
        }

        // min 값이 갱신되지 않은 경우는 불가능한 경우
        if (min == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(min);
        }
    }
}