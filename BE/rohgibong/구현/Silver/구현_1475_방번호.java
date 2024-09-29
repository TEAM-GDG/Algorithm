package Silver;

import java.util.Scanner;

public class 구현_1475_방번호 {
    public static void main(String[] args) {
        int finalRollCount = 1;     //최종 필요한 세트개수
        Scanner scanner = new Scanner(System.in);

        String roomNumber = scanner.nextLine();
        String numbers[] = roomNumber.split("");    //입력받은 번호 하나씩 배열에 저장

        for(int count = 1; count < numbers.length; count++){
            int newRollCount = 1;   //필요한 세트개수
            if(numbers[count-1].equals("6") || numbers[count-1].equals("9")){   //배열의 숫자가 6또는 9일경우
                int sixNineCount = 0;   //6또는 9의 개수
                for(int i = count; i < numbers.length; i++){    //6또는 9의 개수 세기
                    if(numbers[i].equals("6") || numbers[i].equals("9")){   //6또는 9일경우
                        sixNineCount++;    //6또는 9의 개수 증가
                    }
                    if(sixNineCount == 2){  //6또는 9의 개수가 2개일경우
                        newRollCount++;    //세트개수 증가
                        sixNineCount = 0;   //6또는 9의 개수 초기화
                    }
                }
            } else {
                for(int i = count; i < numbers.length; i++){    //배열의 숫자가 6또는 9가 아닐경우
                    if(numbers[i].equals(numbers[count-1])){    //배열의 숫자가 같을경우
                        newRollCount++;   //세트개수 증가
                    }
                }
            }
            if(newRollCount > finalRollCount){  //필요한 세트개수가 최종 세트개수보다 클경우
                finalRollCount = newRollCount;  //최종 세트개수 변경
            }
        }

        System.out.println(finalRollCount);   //최종 세트개수 출력
    }
}