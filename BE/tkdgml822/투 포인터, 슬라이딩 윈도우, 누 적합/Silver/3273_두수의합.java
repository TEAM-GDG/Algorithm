package two_week.Silver;

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

        int x = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int start_index = 0;
        int end_index = n - 1;
        int cnt = 0;

        System.out.println(Arrays.toString(arr));
        while (start_index < end_index) {
            int sum = arr[start_index] + arr[end_index];

            if (sum == x) {
                cnt++;
                start_index++;
                end_index--;
            }
            else if (sum < x) {
                start_index++;
            }
            else {
                end_index--;
            }
        }


        System.out.println(cnt);
    }
}
