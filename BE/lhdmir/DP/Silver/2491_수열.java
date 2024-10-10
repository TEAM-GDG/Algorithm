import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 최대길이
        int maxLength = 1;
        // 오름차순길이
        int ascLength = 1;
        // 내림차순길이
        int desLength = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] >= arr[i - 1]) {
                ascLength++;
            } else {
                ascLength = 1;
            }

            if (arr[i] <= arr[i - 1]) {
                desLength++;
            } else {
                desLength = 1;
            }
            maxLength = Math.max(maxLength, Math.max(ascLength, desLength));
        }

        System.out.println(maxLength);
    }
}
