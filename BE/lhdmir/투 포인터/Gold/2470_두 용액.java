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

        int left = 0;
        int right = n - 1;
        // 최소 용액의 합
        int minSolution = Integer.MAX_VALUE;
        int s1 = 0;
        int s2 = 0;

        while (left < right) {
            // 두 용액의 합
            int sum = arr[left] + arr[right];

            // 두 용액의 합이 최소 용액의 합보다 작다면
            if (Math.abs(sum) < Math.abs(minSolution)) {
                minSolution = sum;
                s1 = arr[left];
                s2 = arr[right];
            }

            // 두 용액의 합이 0보다 작다면
            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(s1 + " " + s2);
    }
}
