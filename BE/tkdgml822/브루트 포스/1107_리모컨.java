package F_six_week;

import java.io.IOException;
import java.util.Scanner;

public class 리모컨_1107 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int size = sc.nextInt();

        //0-9 까지의 수 중 고장난 채널이 있는지 확인하기 위한 배열
        boolean[] broken = new boolean[10];
        for (int i = 0; i < size; i++) {
            broken[sc.nextInt()] = true;
        }
        //N-100을 RESULT의 초기값으로 주는 이유는 현재 채널이 100부터 시작하기 때문이다.
        int result = Math.abs(N - 100);

        //그냥 0부터 999999까지 전부 확인한다.
        for (int i = 0; i <= 999999; i++) {
            String num = String.valueOf(i);

            boolean isBreak = false;
            for (int j = 0; j < num.length(); j++) {
                if (broken[num.charAt(j) - '0']) {
                    // 고장난 버튼을 눌러야 하면 멈춘다.
                    isBreak = true;
                    break;
                }
            }

            // num.length() : 채널 번호 i를 입력하는 데 필요한 버튼 누름 횟수
            if (!isBreak) {
                // 버튼을 누르는 횟수 중 가장 적은 횟수를 result에 담는다.
                int min = Math.abs(N - i) + num.length();
                result = Math.min(min, result);
            }
        }
        System.out.println(result);
    }

}
