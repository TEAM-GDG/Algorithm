import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int e = 0, s = 0, m = 0;
        int count = 0;

        while (true) {
            if (E == e && S == s && M == m) {
                break;
            }
            if (e == 15) {
                e = 0;
            }
            if (s == 28) {
                s = 0;
            }
            if (m == 19) {
                m = 0;
            }
            e++;
            s++;
            m++;
            count++;
        }

        System.out.println(count);
    }
}
