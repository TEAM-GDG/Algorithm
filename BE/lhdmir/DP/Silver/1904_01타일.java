import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] memo = new int[n];

        if (n <= 3) {
            System.out.println(n);
            return;
        }

        memo[0] = 1;
        memo[1] = 2;
        memo[2] = 3;

        for (int i = 3; i < n; i++) {
            memo[i] = (memo[i - 1] + memo[i - 2]) % 15746;
        }

        System.out.println(memo[n - 1]);
    }
}
