import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_1010_다리놓기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long[] resultArr = new long[T];

        for(int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            long result = 1;
            for(int j = 0; j < N; j++) {
                result *= (M - j);
                result /= (j + 1);
            }

            resultArr[i] = result;
        }

        for(int i = 0; i < T; i++) {
            System.out.println(resultArr[i]);
        }
    }
}
