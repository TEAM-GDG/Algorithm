package 구현.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 구현_3107_IPv6 {
    static ArrayList<String> arr = new ArrayList(); //전체 IPv6

    public static void main(String[] args) throws IOException {
        //25:09:1985:aa:091:4846:374:bb
        //0025:0009:1985:00aa:0091:4846:0374:00bb

        //1. 0으로 이루어저 연속된 구룹 복구
        //2. 각 앞자리 0으로 인해 일부 생략된 부분 복구

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(":", -1);

        //빈 곳이 몇개인지
        fillInBlank(inputs);
        aadSkipRecovery();
        StringBuilder sb = new StringBuilder();
        for (String s:arr) {
            sb.append(s).append(":");       //복구된 각 IP 뒤에 : 추가
        }
        sb.delete(39,40);       //마지막 : 삭제
        System.out.println(sb); //최종 출력 
    }
    private static void aadSkipRecovery(){          //4자리가 아닌 부분 앞에 필요한 만큼 0추가
        int i = 0;
        for (String s:arr){         //배열의 크기만큼 반복
            if(s.length() <4){       //해당 공간의 문자열의 길이가 4보다 작으면
                StringBuilder changeS = new StringBuilder();          //변경할 문자열
                char[] originS = s.toCharArray();       //원본 문자열 char 배열로 변경
                for (int j = 0; j<4-s.length();j++){    //0이 필요한 만큼 반복
                    changeS.append('0');        //변경할 문자열에 앞에 0추가
                }
                for (char c: originS){
                    changeS.append(c);          //변경할 문자열에 원본 문자열 추가
                }
                arr.set(i,changeS.toString());      //IPv6 부분 수정
            }
            i++;
        }
    }

    private static void fillInBlank(String[] inputs) {  //빈곳을 찾아서 0을 추가시켜줌
        int count =0;       //빈 공간의 개수 체크
        for (String s :inputs){         //받은 공간 만큼 반복
            if (s.isEmpty() && count == 0){ //빈공 존재하고 개수가 0이면
                for (int i = 0; i <= 8 - inputs.length; i++) { //필요한 공간 만큼 0추가
                    arr.add("0");
                }
                count++;
                continue;
            }else if (s.isEmpty() && count == 1){       //빈공간이 2개일때 하나 더 추가
                arr.add("0");
                continue;
            }
            arr.add(s);     //위의 경우가 아닌 존재할 때 추가
        }

    }
}
