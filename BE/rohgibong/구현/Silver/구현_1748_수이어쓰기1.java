package Silver;

import java.util.Scanner;

public class 구현_1748_수이어쓰기1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String strNumber = sc.nextLine();   //숫자 입력받기
        int size = strNumber.length();      //숫자의 길이
        int number = Integer.parseInt(strNumber);   //정수로 변환
        int result = 0;    //결과값

        switch(size){   //길이에 따른 결과값 계산
            case 1:     //길이가 1일경우
                result = number;    //결과값은 입력받은 숫자
                break;  //종료
            case 2:     //길이가 2일경우
                result = 9 + (number-9)*2;  //결과값은 9 + (입력받은 숫자 - 9) * 2
                break;
            case 3:
                result = 9 + (90 * 2) + (number-99)*3;
                break;
            case 4:
                result = 9 + (90 * 2) + (900 * 3) + (number-999)*4;
                break;
            case 5:
                result = 9 + (90 * 2) + (900 * 3) + (9000 * 4) + (number-9999)*5;
                break;
            case 6:
                result = 9 + (90 * 2) + (900 * 3) + (9000 * 4) + (90000 * 5) + (number-99999)*6;
                break;
            case 7:
                result = 9 + (90 * 2) + (900 * 3) + (9000 * 4) + (90000 * 5) + (900000 * 6) + (number-999999)*7;
                break;
            case 8:
                result = 9 + (90 * 2) + (900 * 3) + (9000 * 4) + (90000 * 5) + (900000 * 6) + (9000000 * 7) + (number-9999999)*8;
                break;
            default:
                result = 9 + (90 * 2) + (900 * 3) + (9000 * 4) + (90000 * 5) + (900000 * 6) + (9000000 * 7) + (90000000 * 8) + (number-99999999)*9;
                break;
        }

        System.out.println(result);



    }
}
