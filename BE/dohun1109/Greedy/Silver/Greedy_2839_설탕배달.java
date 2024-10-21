package Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Greedy_2839_설탕배달 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        if (N == 4 || N == 7) {
            sb.append(-1);
        } else if (N % 5 == 0) {
            sb.append(N / 5);
        } else if (N % 5 == 1 || N % 5 == 3) {
            sb.append((N / 5) + 1);
        } else if (N % 5 == 2 || N % 5 == 4) {
            sb.append((N / 5) + 2);
        }

        System.out.println(sb);

    }
}
