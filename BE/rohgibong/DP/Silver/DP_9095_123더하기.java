import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP_9095_123더하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());    //T: 테스트 케이스의 개수
        int[] resultArr = new int[T];       //결과값을 담을 배열

        for(int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());    //정수 n을 입력받아서
            resultArr[i] = calNumber(n);    //calNumber 메소드에 대입 후 결과값을 배열에 넣음
        }

        for(int i = 0; i < T; i++) {
            System.out.println(resultArr[i]);   //결과 출력
        }
    }

    public static int calNumber(int num){   //1, 2, 3의 합으로 이루어진 경우의 수를 계산하는 메소드
        if(num == 1){       //num이 1이면
            return 1;       //1 반환
        } else if(num == 2){    //num이 2이면
            return 2;       //2 반환
        } else if(num == 3){    //num이 3이면
            return 4;       //4 반환
        } else {    //1, 2, 3인 경우가 아닌 경우
            return calNumber(num-1) + calNumber(num-2) + calNumber(num-3);
            //N은 (N-1의 합) + (N-2의 합) + (N-3의 합) 이기때문
        }
    }
}
