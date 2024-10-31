package F_six_week;

import java.util.Scanner;

public class 영화감독숌_1436 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int cnt = 0;
        int number = 0;

        while (true) {
            // 1씩 증가하다가 '666'이 포함되면 cnt 증가
            if (String.valueOf(number++).contains("666")) {
                cnt++;
            }

            // N과 cnt가 같으면 break;
            if(cnt == N) {
                break;
            }
        }

        System.out.println(number - 1);
    }
}
