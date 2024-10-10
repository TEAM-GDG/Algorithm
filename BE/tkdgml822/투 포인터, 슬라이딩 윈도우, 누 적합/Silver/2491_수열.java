package three_week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 수열_2491 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numberArr = new int[N];
        for (int i = 0; i < N; i++) {
            numberArr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 1;
        int a = 1;
        int b = 1;
        for (int i = 1; i < N; i++) {
            if (numberArr[i] >= numberArr[i - 1]) {
                a++;
            } else {
                a = 1;
            }

            if (numberArr[i] <= numberArr[i - 1]) {
                b++;
            } else {
                b = 1;
            }

            max = Math.max(max, Math.max(a , b));
        }

        System.out.println(max);
    }
}
