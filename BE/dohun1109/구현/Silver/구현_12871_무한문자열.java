package 구현.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 구현_12871_무한문자열 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        StringBuilder ss = new StringBuilder(s);
        StringBuilder tt = new StringBuilder(t);

        int sLen = s.length();  //s의 길이
        int tLen = t.length();  //t의 길이

        //유클리드 호제법 이용 lcm = 최소공배수 gcd = 최대공약수 구하는 메소드
        int lcm = (sLen * tLen) / gcd(Math.max(sLen, tLen), Math.min(sLen, tLen)); // (두수의 곱)/(최대 공약수) = 최소공배수



        for (int i = 0; i < lcm / sLen - 1; i++) {  // lcm / sLen 최소공배수가 되기위한 값을 구함, -1은 최초 한번은 존재하기 때문
            ss.append(s);
        }
        for (int i = 0; i < lcm / tLen - 1; i++) {
            tt.append(t);
        }

        int result = (ss.toString().equals(tt.toString())) ?1:0;    //두 문자열의 길이를 최소공배수로 같게 만든 후 equals로 비교

        System.out.println(result); //결과 출력

    }
    // 유클리드 호제법 최대공약수 구하기
    private static int gcd(int max, int min) {  //ex) 12, 8
        //반복문 방식 구현
        while (min != 0) {
            int remainder = max%min;    //(1)12%8 = 4 (2) 8%4 = 2 (3) 4%2 = 0
            max = min;      //(1)max = 8   (2) 4  (3) 2
            min = remainder;// (1)min = 4   (2) 2 (3) 0
        }
        return max;

        /** 재귀 방식으로 구현
         * if( min == 0)return max;
         * return gcd(min, max % min);
         */
    }
}
