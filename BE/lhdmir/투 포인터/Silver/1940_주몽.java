import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] materias = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            materias[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(materias);

        // 왼쪽 포인터
        int left = 0;
        // 오른쪽 포인터
        int right = n - 1;

        int count = 0;

        while (left < right) {
            // 두 포인터가 가르키는 값의 합이 m과 동일할 경우
            if (materias[left] + materias[right] == m) {
                count++;
                // 왼쪽, 오른쪽 포인터를 한칸씩 이동
                left++;
                right--;
            }
            // 두 포인터가 가르키는 값의 합이 m보다 작을 경우
            else if (materias[left] + materias[right] < m) {
                left++;
            }
            // 두 포인터가 가르키는 값의 합이 m보다 클 경우
            else if (materias[left] + materias[right] > m) {
                right--;
            }
        }

        System.out.println(count);
    }
}

