import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 최종 자릿수를 저장
        int result = 0;
        // 현재 자릿수를 저장
        int digit = 1;
        // 해당 자릿수에서 시작하는 숫자
        // ex) digit = 1 한자릿수 일때 start = 1
        // ex) digit = 2 두자릿수 일때 start = 10
        int start = 1;

        while (start <= n) {
            // 해당자릿수의 마지막 수 계산
            // start = 1 일때, 1 * 10 - 1 = 9
            // start = 10 일때, 10 * 10 - 1 = 99
            int end = start * 10 - 1;
            // 단, end가 n을 넘어가면 end = n으로 설정
            if (end > n) {
                end = n;
            }
            // 해당 구간에서의 자릿수를 계산후 합산
            // ex) start = 1, end = 9, digit = 1 일때, (9 - 1 + 1) * 1 = 9
            // ex) start = 10, end = 15, digit = 2 일때, (15 - 10 + 1) * 2 = 12
            result += (end - start + 1) * digit;
            // 현재 자릿수 증가
            digit++;
            // 현재 자릿수에서 시작하는 수 증가
            start *= 10;
        }

        System.out.println(result);
    }
}