package 구현.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 수이어쓰기1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int input = Integer.parseInt(br.readLine()); //N
        int totalCount = 0; //전체 자리수
        int num = 1;    //현재 수의 자리수 개수
        int digit = 10; //현재 자리 ex)10,100,1000

        for (int i = 1; i <= input; i++) {  //N까지 반복
            if (i % digit == 0) {   //현재 수/ 자리수 나머지 가 0이되는 부분,
                //즉, 자리수가 변경되는 부분
                num += 1; //자리수 개수 증가
                digit *= 10; // 자리수 증가
            }
            totalCount += num;
        }
        System.out.println(totalCount);

        
    }
}
