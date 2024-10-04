import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int x = Integer.parseInt(br.readLine());

        int left = 0, right = n - 1;
        int sum;
        int count = 0;
        while (left < right) {
            sum = arr[left] + arr[right];

            if (sum > x) {
                right--;
            } else if (sum < x) {
                left++;
            } else {
                count++;
                left++;
                right--;
            }
        }

        System.out.println(count);
    }
}