import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 구현_1940_주몽 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());        //n: 재료의 개수
        int m = Integer.parseInt(br.readLine());        //m: 갑옷을 만드는데 필요한 수
        String numbers = br.readLine();         //숫자들이 사이에 공백을 두고 문자열로 입력받아짐
        String[] numArr = numbers.split(" ");   //공백을 기준으로 나눠서 배열에 담음
        int[] integerNumArr = new int[n];       //정수형 배열 생성
        for(int i = 0; i < n; i++){
            integerNumArr[i] = Integer.parseInt(numArr[i]);     //정수형 배열에 숫자들을 담음
        }
        int result = 0;         //결과를 반환할 변수

        for(int i = 0; i < n-1; i++){       //마지막 전 정수까지 반복
            if(integerNumArr[i] == 0) continue;     //배열에 담긴 i번째 수가 0이면 반복 건너뜀
            for(int j=i+1; j<n; j++){
                if(integerNumArr[j] == 0) continue;     //배열에 담긴 j번째 수가 0이면 반복 건너뜀
                if(integerNumArr[i] + integerNumArr[j] == m ){      //i번째수와 j번째수가 m이면
                    integerNumArr[i] = 0;       //i번째와
                    integerNumArr[j] = 0;       //j번째 수를 0으로 바꿈
                    result++;       //결과값 +1
                    break;      //반복 종료
                }
            }
        }

        System.out.println(result);     //결과 출력
    }
}
