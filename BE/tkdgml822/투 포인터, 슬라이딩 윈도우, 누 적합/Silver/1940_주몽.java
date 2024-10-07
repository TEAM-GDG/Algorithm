import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] num = new int[N];
        int i = 0;
        while(st.hasMoreElements()) {
            num[i++] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);

        int count = 0;
        int start_index = 0;
        int end_index = N - 1;

        while (start_index < end_index) {
            int sum = num[start_index] + num[end_index];

            if (sum == M) {
                start_index++;
                end_index--;
                count++;
            }
            else if (sum < M) {
                start_index++;
            }
            else {
                end_index--;
            }
        }

        br.close();
        System.out.println(count);
    }
}