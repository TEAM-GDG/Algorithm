package E_five_week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주유소_13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] km = new long[N - 1];
        long[] gasStation = new long[N];
        long result = 0;

        for (int i = 0; i < N - 1; i++) {
            km[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            int number = Integer.parseInt(st.nextToken());
            gasStation[i] = number;
        }

        long number = gasStation[0];
        for (int i = 0; i < N - 1; i++) {

            if (number > gasStation[i]) {
                number = gasStation[i];
            }
            result += number * km[i];

        }

        System.out.println(result);
    }
}
