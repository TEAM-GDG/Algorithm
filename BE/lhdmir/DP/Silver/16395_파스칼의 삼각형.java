import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int binomialCoefficient(int n, int k) {
        int[][] C = new int[n + 1][k + 1];

        // nCk 계산
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                // nC0 = nCn = 1
                if (j == 0 || j == i) {
                    C[i][j] = 1;
                } else {
                    // 점화식: C(n, k) = C(n-1, k-1) + C(n-1, k)
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
            }
        }
        return C[n][k];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.println(binomialCoefficient(n - 1, k - 1));
    }
}

