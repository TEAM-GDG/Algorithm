import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int title = 666;
        String str;
        while (n > 0) {
            str = Integer.toString(title);
            if (str.contains("666")) {
                n--;
            }
            title++;
        }

        System.out.println(title - 1);
    }
}
