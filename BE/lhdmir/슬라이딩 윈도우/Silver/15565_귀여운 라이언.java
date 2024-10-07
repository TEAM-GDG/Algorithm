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

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int lionCount = 0;
        int minSize = n;

        while (right < n) {
            if (arr[right] == 1) {
                lionCount++;
            }
            right++;

            // lionCount 가 k개 이상일때
            while (lionCount >= k) {
                // 최소 사이즈를 비교해서 업데이트
                minSize = Math.min(minSize, right - left);

                // 기존 집합의 왼쪽 끝부분이 1이라면 lionCount 감소
                if (arr[left] == 1) {
                    lionCount--;
                }
                left++;
            }
        }


        if (minSize == n) {
            System.out.println(-1);
        } else {
            System.out.println(minSize);
        }
    }
}