import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int x = Integer.parseInt(br.readLine());

        if (x < 100) {
            System.out.println(x);
        } else if (x == 1000) {
            System.out.println(144);
        } else {
            int count = 99;
            int num1, num2, num3;

            for (int i = 100; i <= x; i++) {
                int copy = i;
                num1 = copy % 10;
                copy /= 10;
                num2 = copy % 10;
                copy /= 10;
                num3 = copy % 10;
                copy /= 10;

                if ((num3 - num2) == (num2 - num1)) {
                    count++;
                }
            }

            System.out.println(count);
        }
    }
}
