package F_six_week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 1065_한수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int count = 99;

        // 어차피 99까지는 등차수열이기 때문에 99 이하 일시 그냥 출력
        if (N <= 99) {
            System.out.println(N);
        }
        else {
            for (int i = 100; i <= N; i++) {
                String str = String.valueOf(i);
                // 문자열로 숫자를 나눔
                int one = Integer.parseInt(String.valueOf(str.charAt(0))); // 첫 번째 수
                int two = Integer.parseInt(String.valueOf(str.charAt(1))); // 두 번째 수
                int three = Integer.parseInt(String.valueOf(str.charAt(2))); // 세 번째 수

                int number = i / 1000;
                // 4자리수
                if (number >= 1) {
                    int four = Integer.parseInt(String.valueOf(str.charAt(3))); // 끝에 빼버림
                    // 1111같은 경우
                    if (one == two && one == three && one == four) {
                        count++;
                    }
                    // 1234일 경우
                    else if (one < two) {
                        int minusResult = two - one;
                        if (minusResult == (three - two) && minusResult == (four - three)) {
                            count++;
                        }

                    // 321일 경우
                    } else {
                        int minusResult = one - two;
                        if (minusResult == (two - three) && minusResult == (three - four)) {
                            count++;
                        }
                    }

                } else { // 3자리수
                    // 111같은 경우
                    if (one == two && one == three) {
                        count++;
                    }
                    // 123일 경우
                    else if (one < two) {
                        int minusResult = two - one;
                        if (minusResult == (three - two)) {
                            count++;
                        }
                    // 321일 경우
                    } else {
                        int minusResult = one - two;
                        if (minusResult == (two - three)) {
                            count++;
                        }
                    }

                }
            }
            System.out.println(count);
        }

    }
}
