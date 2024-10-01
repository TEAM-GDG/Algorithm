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

        // left가 배열의 끝에 도달할때까지 반복
        while (left <= n - 1) {
            // right가 배열사이즈와 동일해지면 반복 중단
            if(right == n){
                break;
            }
            int sum = 0;
            // arr[left] ~ arr[right] 까지의 합 계산
            for (int i = left; i <= right; i++) {
                sum += arr[i];
            }
            // 합이 m과 같다면 포인터를 1씩 이동하고 count 증가
            if (sum == m) {
                left++;
                right++;
                count++;
            } 
            // m보다 작으면 left를 증가시켜서 sum값을 감소
            else if (sum > m) {
                left++;
            } 
            // m보다 크면 right를 증가시켜서 sum값을 증가
            else if (sum < m) {
                right++;
            }
        }
        System.out.println(count);
    }
}

