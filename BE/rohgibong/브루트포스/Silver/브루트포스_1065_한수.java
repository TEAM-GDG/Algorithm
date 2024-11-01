import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 브루트포스_1065_한수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 99;        //결과값 디폴트값으로 99를 줌
        int num100 = 0;     //100의자리수   (N의 범위가 1000보다 작거나 같기때문에 1000은 어짜피 등차수열을 이루지 않아서 3자리수로 계산)
        int num10 = 0;      //10의자리수
        int num1 = 0;       //1의자리수

        if(N < 100){        //N이 100이 넘지 않는다면
            result = N;     //N을 결과값으로 출력
        } else {        //N이 100을 넘는다면
            for(int i = 100; i <= N; i++){      //100부터 N까지 반복
                int calNum = i;     //calNum이라는 변수에 i값을 대입
                num100 = calNum / 100;  //100의 자리수 계산
                calNum %= 100;      //100의 자리수를 뺀 값을 calNumd에 대입
                num10 = calNum / 10;  //10의 자리수 계산
                calNum %= 10;   //10의 자리수를 뺀 값을 calNumd에 대입
                num1 = calNum;  //나머지는 1의자리수

                if(num100 - num10 == num10 - num1) result++;        //100의자리수 - 10의자리수가 10의자리수 - 1의자리수와 같을 경우 한수
            }
        }

        System.out.println(result);
    }
}
