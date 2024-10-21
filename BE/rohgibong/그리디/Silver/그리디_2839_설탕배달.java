import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 그리디_2839_설탕배달 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());        //N: 배달해야하는 설탕

        int result = -1;        //초기 결과값 -1로 해둠
        for(int i = N/5; i>=0; i--){        //N/5부터 0까지 반복돌기
            if((N-(i*5))%3 == 0){       //N에서 i*5 뺀 값이 3으로 딱 나누어떨어지면
                result = i + ((N-(i*5))/3);     //그때 설탕 봉지의 갯수를 result에 담고
                break;      //반복문 종료
            }
        }

        System.out.println(result);     //결과값 출력
    }
}
