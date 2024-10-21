import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int n;
        int[] prices;

        long maxProfit; 
        int maxPrice;

        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            prices = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            // 최대 수익
            maxProfit = 0;
            // 주식의 최고가
            maxPrice = 0;

            for (int i = n - 1; i >= 0; i--) {
                if (prices[i] > maxPrice) {
                    maxPrice = prices[i];
                } else {
                    maxProfit += maxPrice - prices[i];
                }
            }
            System.out.println(maxProfit);
        }
    }
}
