package Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Greedy_1541_잃어버린_괄호 {

    /**
     * 문제 조건
     * 1. '0'~'9', '+', '-' 이루어진 입력
     * 2. 처음과 마지막은 숫자 이다.
     * 3. 연속해서 2개 이상의 연산자가 나올 수 없다.
     * 4. 5자리보다 많이 연속되는 숫자는 없다.
     * 5. 0으로 시작할 수 있다.
     * 6. 길이는 50 보다 작다
     */

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Integer> list = new ArrayList<>();
        String[] split1 = br.readLine().split("-");

        for (String S : split1) {
            int sum = 0;
            String[] split2 = S.split("\\+");
            for (String sp : split2) {
                sum += Integer.parseInt(sp);
            }
            list.add(sum);
        }
        

        long answer = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            answer -= list.get(i);
        }
        System.out.println(answer);
    }

}
