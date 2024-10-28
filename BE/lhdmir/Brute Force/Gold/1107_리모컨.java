import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int m = Integer.parseInt(br.readLine());
        boolean[] brokenButtons = new boolean[10];

        // m이 0개 이상일때만 입력을 받고 고장난 버튼을 표기
        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int brokenButton = Integer.parseInt(st.nextToken());
                brokenButtons[brokenButton] = true;
            }
        }

        // 1. +, - 버튼으로만 이동하는 경우
        int minPressCount = Math.abs(n - 100);

        int pressCount, totalPress;
        // 2. 숫자 버튼으로 이동 가능한 가장 가까운 채널 찾기
        // 숫자 버튼이 일부 고장난 경우에도 n에 근접한 수를 만들기위해
        // 문제에서 주어진 n <= 500_000 의 두배인 1_000_000까지 검사
        // 숫자 버튼이 일부 고장났다면 고장나지 않은 숫자로 n보다 큰 수를 만드는게 n에 제일 가까운수를 만들수도 있기때문
        // ex) n = 500_000, 고장난 버튼[0,1,2,3,4,5,6,7,] 일 경우
        // 8, 9를 사용해서 500_000에 가장 가깝게 만들수있는 수는 888_888 이다.
        // 위 경우가 최악의 경우
        for (int i = 0; i <= 888_888; i++) {
            // 숫자 버튼으로 i번 채널을 만들 수 있다면 pressCount 에는 0초과의 수가 저장
            // 아닐경우에는 0이 저장
            pressCount = canPressNumber(i, brokenButtons);
            // pressCount 가 0이상이라면 숫자 버튼으로 i번 채널을 만들수있으므로
            if (pressCount > 0) {  // 숫자 버튼으로 눌러서 만들 수 있는 경우
                // pressCount: 채널을 바꾸기위해서 버튼을 누른 횟수(5455 이라면 4번 누름)
                // Math.abs(n - i): n=5457, i=5455 라면 2번의 +버튼을 추가적으로 눌러야함
                totalPress = pressCount + Math.abs(n - i);
                // 현재 기록된 최솟값과 비교해 최솟값을 업데이트
                minPressCount = Math.min(minPressCount, totalPress);
            }
        }

        System.out.println(minPressCount);
    }

    // 숫자 버튼으로 특정 채널을 만들 수 있는지 확인하고, 가능하면 자릿수를 반환
    public static int canPressNumber(int number, boolean[] brokenButtons) {
        if (number == 0) {
            // 0이 고장났다면 0리턴, 아니라면 1리턴
            return brokenButtons[0] ? 0 : 1;
        }

        // 자릿수
        int digits = 0;
        // number = 5457 일때
        while (number > 0) {
            // 1번째 반복: 5457 % 10 = 7번 버튼
            // 2번째 반복: 545 % 10 = 5번 버튼
            // 3번째 반복: 54 % 10 = 4번 버튼
            // 4번째 반복: 5 % 10 = 5번 버튼
            if (brokenButtons[number % 10]) {
                // number 에 하나라도 고장난 버튼이 포함되어있다면 0을 리턴
                return 0;
            }
            number /= 10;
            digits++;
        }
        // number 에 고장난 버튼이 없다면 number 의 자릿수를 계산한 digits 를 리턴
        // number = 5457 일때, digits = 4
        return digits;
    }
}
