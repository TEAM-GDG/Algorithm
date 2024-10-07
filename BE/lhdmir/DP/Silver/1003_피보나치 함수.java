import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // Fibonacci 수열의 0과 1이 호출되는 횟수를 기록하기 위한 2차원 배열
    // 1. 문제에서 Fibonacci 수열의 N이 40보다 작거나 같기 때문에 41로 생성
    // 2. [n][0] = 0이 호출되는 횟수를 기록
    // 3. [n][1] = 1이 호출되는 횟수를 기록
    static int[][] fibo = new int[41][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        // Fibonacci 수열의 0번째일 경우
        // 0이 호출되는 횟수 = 1
        // 1이 호출되는 횟수 = 0
        fibo[0][0] = 1;
        fibo[0][1] = 0;
        // Fibonacci 수열의 1번째일 경우
        // 0이 호출되는 횟수 = 0
        // 1이 호출되는 횟수 = 1
        fibo[1][0] = 0;
        fibo[1][1] = 1;

        // Fibonacci 수열의 2번째부터는 앞에 있는
        // 두 수의 0과 1의 호출 횟수를 더하면 해당 수의 0과 1의 호출횟수를 구할 수 있다.
        // ex1) N = 2 일 경우
        // N - 1 = 1의 0과 1의 호출 횟수, 0 = 0번 호출, 1 = 1번 호출
        // N - 2 = 0의 0과 1의 호출 횟수, 0 = 1번 호출, 1 = 0번 호출
        // 각 수의 0과 1의 호출 횟수를 더하면 0 = 1번 호출, 1 = 1번 호출
        // ex2) N = 3일 경우
        // N - 1 = 2의 0과 1의 호출 횟수, 0 = 1번 호출, 1 = 1번 호출
        // N - 2 = 1의 0과 1의 호출 횟수, 0 = 0번 호출, 1 = 1번 호출
        // 각 수의 0과 1의 호출 횟수를 더하면 0 = 1번 호출, 1 = 2번 호출
        for (int i = 2; i < 41; i++) {
            // Fibonacci수열의 i번째수가 0이 호출되는 횟수를 계산해서 저장
            fibo[i][0] = fibo[i - 1][0] + fibo[i - 2][0];
            // Fibonacci수열의 i번째수가 1이 호출되는 횟수를 계산해서 저장
            fibo[i][1] = fibo[i - 1][1] + fibo[i - 2][1];
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(fibo[n][0] + " " + fibo[n][1]);
        }
    }
}
