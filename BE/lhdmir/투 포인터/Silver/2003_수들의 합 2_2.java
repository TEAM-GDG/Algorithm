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

        while (true) {
            // m보다 크면 sum에서 left위치의 값을 빼고 left 포인터 이동
            if (sum >= m) {
                sum -= arr[left++];
            } 
            // right가 배열의 끝에 도달하면 반복문 종료
            else if (right == n) break;
            // m보다 작으면 sum에서 right위치의 값을 더하고 right 포인터 이동
            else {
                sum += arr[right++];
            }
            // 위의 계산을 거친 sum값이 m과 같으면 count 증가
            if (sum == m) {
                count++;
            }

        }
        System.out.println(count);
    }
}