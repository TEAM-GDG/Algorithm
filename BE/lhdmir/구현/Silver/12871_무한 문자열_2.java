import java.util.Scanner;

// 최소공약수를 활용한 코드
public class Main {
    // 최대공약수 (GCD) 구하는 함수
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 최소공배수 (LCM) 구하는 함수
    public static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    public static int areEqualF(String s1, String s2) {
        int lenS1 = s1.length();
        int lenS2 = s2.length();

        // 최소공배수 구하기
        int lcmLength = lcm(lenS1, lenS2);

        // s1와 s2를 최소공배수 길이만큼 반복할 변수
        StringBuilder repeatedS1 = new StringBuilder();
        StringBuilder repeatedS2 = new StringBuilder();

        // s1를 반복하여 최소공배수 길이만큼 만들기
        for (int i = 0; i < lcmLength / lenS1; i++) {
            repeatedS1.append(s1);
        }

        // s2를 반복하여 최소공배수 길이만큼 만들기
        for (int i = 0; i < lcmLength / lenS2; i++) {
            repeatedS2.append(s2);
        }

        // 두 반복된 문자열이 같은지 비교
        if (repeatedS1.toString().equals(repeatedS2.toString())) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine().trim();
        String s2 = sc.nextLine().trim();

        System.out.println(areEqualF(s1, s2));
    }
}