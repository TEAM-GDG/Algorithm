import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 2003_수들의합2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        int index = 0;
        while (st.hasMoreElements()) {
            arr[index++] = Integer.parseInt(st.nextToken());
        }

        int start_index = 0;
        int end_index = 0;
        int count = 0;
        int sum = 0;

        while (end_index <= N) {
            if (sum == M) {
                count++;
                sum += arr[end_index++];
            } else if (sum < M) {
                sum += arr[end_index++];
            } else {
                sum -= arr[start_index++];
            }
        }

        System.out.println(count);

    }
}
