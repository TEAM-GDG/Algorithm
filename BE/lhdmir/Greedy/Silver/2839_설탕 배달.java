import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static int calc(int n) {
        int pack5 = n / 5;
        int pack3;
        int remainder;

        while (pack5 >= 0) {
            remainder = n - (pack5 * 5);

            if (remainder % 3 == 0) {
                pack3 = remainder / 3;
                return pack5 + pack3;
            }
            pack5--;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(calc(n));
    }
}
