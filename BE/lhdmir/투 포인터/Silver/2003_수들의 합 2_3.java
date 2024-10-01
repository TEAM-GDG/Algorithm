import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int count = 0;

        int sum = 0;

        // right가 배열의 끝에 도달할때까지 반복
        while (right < n) {
            // m보다 작으면 sum에서 right위치의 값을 더하고 right 포인터 이동
            if (sum < m) {
                sum += arr[right++];
            }
            // m보다 클 경우 sum이 m보다 작아질때까지 left위치의 값을 빼면서 left 포인터 이동
            while (sum >= m) {
                // 감소시키다가 sum이랑 m이 같아지면 count증가
                if (sum == m) {
                    count++;
                }
                sum -= arr[left++];
            }
        }
        System.out.println(count);
    }
}

