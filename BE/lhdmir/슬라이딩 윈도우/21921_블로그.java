import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 최대 방문자 수
        int maxVisitor = 0;
        // 최대 방문자 수가 존재하는 기간들
        int maxPeriod = 0;

        int left = 0, right = x;
        int sum = 0;

        // 처음 x일까지의 구간까지 방문자수의 합을 계산 
        for (int i = 0; i < right; i++) {
            sum += arr[i];
        }

        while (true) {
            // 구간의 합이 최대 방문자 수보다 클 경우
            if (sum > maxVisitor) {
                // 최대 방문자 수 업데이트
                maxVisitor = sum;
                maxPeriod = 1;
            }
            // 같을경우 기간 증가 
            else if (sum == maxVisitor) {
                maxPeriod++;
            }

            // right 포인터가 배열의 끝에 도달하면 종료
            if (right == n) {
                break;
            } 
            else {
                // sum값에서 left위치의 값을 감소 후 left포인터 한칸 이동
                sum -= arr[left++];
                // sum값에서 right위치의 값을 증가 후 right포인터 한칸 이동
                sum += arr[right++];
            }
        }

        if (maxVisitor == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(maxVisitor);
            System.out.println(maxPeriod);
        }

    }
}

