import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        int[] cases = new int[11];

        // 1일때 경우의 수
        cases[0] = 1;
        // 2일때 경우의 수
        cases[1] = 2;
        // 3일때 경우의 수
        cases[2] = 4;

        // 4부터 11까지 경우의 수부터 계산
        for (int i = 3; i < 11; i++) {
            // 현재 수의 경우의 수를 구할려면 n-1, n-2, n-3의 경우의 수를 합산하면 된다.
            cases[i] = cases[i - 3] + cases[i - 2] + cases[i - 1];
        }

        // 이미 계산한 값들을 불러와서 출력하는 방식이기
        // 해당 방법은 Bottom-Up 방식
        for (int i = 0; i < t; i++) {
            int x = Integer.parseInt(br.readLine());
            System.out.println(cases[x - 1]);
        }
    }
}
