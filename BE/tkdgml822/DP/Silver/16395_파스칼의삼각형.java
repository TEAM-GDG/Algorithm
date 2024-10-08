package three_week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 파스칼의삼각형_16395 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] triangle = new int[31][31];
        triangle[0][0] = 1;
        triangle[1][0] = 1;
        triangle[1][1] = 1;

        int l = 2;
        for (int i = 2; i < 31; i++) {
            triangle[i][0] = 1;
            for (int j = 1; j < i; j++) {
                triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
            }
            triangle[i][l++] = 1;
        }

        System.out.println(triangle[n - 1][k - 1]);

    }
}
