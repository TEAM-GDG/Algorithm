import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;

        int sum = arr[0];
        int minLength = Integer.MAX_VALUE;

        // left가 n보다 커지면 중단
        while (left < n) {
            // sum 이 s보다 크거나 같다면
            if (sum >= s) {
                // 부분합의 최소길이 업데이트
                minLength = Math.min(minLength, right - left + 1);
                // sum에서 left포인터가 가리키는 값을 제거
                sum -= arr[left];
                left++;
            }
            else if (right == n - 1) {
                break;
            }
            // sum이 s보다 작다면 right포인터를 증가시켜서 다음 값을 sum에 합산
            else {
                right++;
                sum += arr[right];
            }
        }

        // minLength가 변함이 없다면(합이 존재하지 않는다면) 0출력
        if(minLength == Integer.MAX_VALUE) {
            minLength = 0;
        }
        System.out.println(minLength);

    }
}
