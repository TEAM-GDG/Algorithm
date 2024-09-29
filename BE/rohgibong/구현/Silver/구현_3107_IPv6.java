import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 구현_3107_IPv6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] strArr = str.split(":");       //문자열 입력 받은거 : 기준으로 나눔
        if(strArr.length == 8){     //나눠서 나온 요소들이 8개이면 ::이게 없는거
            for(int i = 0; i < 8; i++){     //요소들이 무조건 8개인경우이므로, 8번반복
                if(strArr[i].length() != 4){    //각 요소의 길이가 4개가 아니면 앞에 0이 생략된경우
                    strArr[i] = "0".repeat(4-strArr[i].length()) + strArr[i];   //생략된 갯수만큼 0을 붙임
                }
            }
            for(int i = 0; i < 8; i++){     //8번 반복 돌면서
                System.out.print(strArr[i]);    //네자리 수 찍고
                if(i != 7){     //마지막만 아니면
                    System.out.print(":");      //끝에 :도 찍음
                }
            }
        } else {        //8개가 아니면 무조건 :: 이게 있는 상황
            String resultString = "";       //출력할 문자열 선언
            String[] strArr2 = str.split("::");     //:: 기준으로 나눔
            if(strArr2[0].equals("")){      //::이게 젤 앞인 경우
                String[] strArr3 = strArr2[1].split(":");       //::뒤로 오는 문자열들을 : 기준으로 나눔
                resultString = "0000:".repeat(8-strArr3.length);        //앞에 부족한만큼 0000:을 붙여서 출력할 문자열에 넣음
                for(int i = 0; i < strArr3.length; i++){        //::뒤에 온 문자열들 길이만큼 반복
                    if(strArr3[i].length() != 4){       //각 요소의 길이가 4개가 아니면 앞에 0이 생략된경우
                        strArr3[i] = "0".repeat(4-strArr3[i].length()) + strArr3[i];        //생략된 갯수만큼 0을 붙임
                    }
                    if(i != strArr3.length-1){      //마지막만 아니면
                        strArr3[i] += ":";      //끝에 :를 찍음
                    }
                    resultString += strArr3[i];     //출력할 문자열 뒤에 붙임
                }
            } else if(strArr2.length == 1){        //::이게 젤 뒤일 경우
                String[] strArr3 = strArr2[0].split(":");       //::앞에 오는 문자열들을 : 기준으로 나눔
                for(int i = 0; i < strArr3.length; i++){        //::앞에 온 문자열들 길이만큼 반복
                    if(strArr3[i].length() != 4){       //각 요소의 길이가 4개가 아니면 앞에 0이 생략된경우
                        strArr3[i] = "0".repeat(4-strArr3[i].length()) + strArr3[i];        //생략된 갯수만큼 0을 붙임
                    }
                    if(i != strArr3.length-1){      //마지막만 아니면
                        strArr3[i] += ":";      //끝에 :를 찍음
                    }
                    resultString += strArr3[i];     //출력할 문자열 뒤에 붙임
                }
                resultString += ":0000".repeat(8-strArr3.length);   //출력할 문자열 맨 뒤에 :0000을 부족한만큼 붙임
            } else {    //::이게 중간일 경우
                String[] middle1 = strArr2[0].split(":");       //앞에 오는 문자열들을 : 기준으로 나눔
                String[] middle2 = strArr2[1].split(":");       //뒤에 오는 문자열들을 : 기준으로 나눔
                for(int i = 0; i < middle1.length; i++){             //::앞에 온 문자열들 길이만큼 반복   
                    if(middle1[i].length() != 4) {       //각 요소의 길이가 4개가 아니면 앞에 0이 생략된경우
                        middle1[i] = "0".repeat(4 - middle1[i].length()) + middle1[i];        //생략된 갯수만큼 0을 붙임
                    }
                    middle1[i] += ":";      //끝에 : 찍음
                    resultString += middle1[i];     //출력할 문자열에 붙이기
                }
                resultString += "0000:".repeat(8 - (middle1.length + middle2.length));   //출력할 문자열에 0000:을 부족한 만큼 붙임
                for(int i = 0; i < middle2.length; i++){             //::뒤에 온 문자열들 길이만큼 반복   
                    if(middle2[i].length() != 4){            //각 요소의 길이가 4개가 아니면 앞에 0이 생략된경우  
                        middle2[i] = "0".repeat(4 - middle2[i].length()) + middle2[i];        //생략된 갯수만큼 0을 붙임
                    }
                    if(i != middle2.length-1){      //마지막만 아니면
                        middle2[i] += ":";      //끝에 :를 찍음
                    }
                    resultString += middle2[i];     //출력할 문자열 뒤에 붙임
                }
            }
            System.out.println(resultString);   //출력
        }

    }
}
