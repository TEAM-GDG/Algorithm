import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP_1904_01타일 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long N = Long.parseLong(br.readLine());     //N: 2진 수열의 길이
        System.out.println(fibonacci(N));       //피보나치 수열을 통해 개수 구한 후 출력
    }

    public static long fibonacci(long n){
        if(n == 1){     //1일때
            return 1;       //1 반환
        } else if(n == 2){  //2일때
            return 2;       //2 반환
        }

        //3부터 계산
        long before2 = 1;       //2개 전 (n-2)는 1
        long before1 = 2;       //1개 전 (n-1)은 2
        long current = 0;       //현재 0으로 선언

        for(long i = 3; i <= n; i++){       //3부터 n까지 반복하며 계산
            current = (before1 + before2) % 15746;  //(n-2) + (n-1) 한 값에 15746으로 나눈 나머지를 현재값에 저장
            before2 = before1 % 15746;      //n-1을 15746으로 나눈 나머지를 n-2에 저장
            before1 = current;      //현재값을 n-1에 저장
        }
        return current;     //반복문 다 돌고 난 후의 현재 값 반환
    }
}
