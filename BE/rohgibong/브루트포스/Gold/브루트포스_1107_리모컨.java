import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 브루트포스_1107_리모컨 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] numArr = new boolean[10];     //0~9 버튼 상태

        if (M > 0) {    //고장난 버튼이 있으면
            StringTokenizer st = new StringTokenizer(br.readLine());    //값 받기
            for (int i = 0; i < M; i++) {
                int brokenButton = Integer.parseInt(st.nextToken());    //고장난 버튼 값
                numArr[brokenButton] = true;        //해당하는 버튼에 true(1)
            }
        }

        int result = Math.abs(100 - N);     //초기 결과값은 100에서 N까지 + 또는 -를 누르는 횟수

        for (int i = 0; i <= 999999; i++) {     //가능한 모든 채널을 탐색
            int length = checkNum(i, numArr);   // 해당 채널 번호를 누를 수 있는지 확인하고 숫자 길이 반환
            if (length > 0) {   // 해당 채널 번호로 이동 가능할 경우
                int pressCount = Math.abs(N - i) + length;  // 목표 채널까지의 거리(버튼 누름 횟수) 계산
                result = Math.min(result, pressCount);  //버튼 누르는 횟수 비교해서 result에 넣음
            }
        }

        System.out.println(result);
    }

    public static int checkNum(int N, boolean[] numArr) {   // 주어진 숫자를 누를 수 있는지 확인
        if (N == 0) {       //숫자가 0이면
            return numArr[0] ? 0 : 1;   // 0 버튼이 고장났으면 0 반환, 고장나지 않았으면 1 반환(길이 1)
        }
        int length = 0;
        while (N > 0) {     //숫자가 0이 될때까지 반복
            if (numArr[N % 10]) {   //현재 자리의 수가 고장난 버튼이면
                return 0;   //0 반환
            }
            length++;   //누를 수 있는 경우 길이 증가
            N /= 10;    //다음 자리로 이동
        }
        return length;  //모든 자리의 수를 누를 수 있으면 길이 반환
    }
}