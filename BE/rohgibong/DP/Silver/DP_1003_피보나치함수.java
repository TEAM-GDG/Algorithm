import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP_1003_피보나치함수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());    //T: 테스트 케이스의 개수
        String[] arr = new String[T];       //결과값을 담아둘 배열

        for(int i = 0; i < T; i++) {        //테스트 케이스의 수 만큼 반복
            int oneCount = 1;       //1의 갯수
            int zeroCount = 1;      //0의 갯수
            int N = Integer.parseInt(br.readLine());    //N번째 수 입력받기
            int targetZero = N-2;       //N이 0과 1이 아니라면, N-2가 0이 될때까지 반복돌거
            int tempNum = 0;    //oneCount를 잠시 담아둘 변수

            if(N == 0){     //N이 0이면
                oneCount = 0;   //1의 갯수 0개
                zeroCount = 1;  //0의 갯수 1개
            } else if(N == 1){  //N이 1이면
                oneCount = 1;   //1의 갯수 1개
                zeroCount = 0;  //0의 갯수 0개
            } else {        //N이 0 또는 1이 아니면
                while(targetZero > 0){      //N-2가 0이 될때까지 반복
                    tempNum = oneCount;     //기존 1의 갯수를 tempNum에 담음
                    oneCount = oneCount + zeroCount;    //1의 갯수 + 0의 갯수 한 값을 1의 갯수에 저장
                    zeroCount = tempNum;    //tempNum에 담아뒀던 값을 0의 갯수에 담음
                    targetZero--;   //N-2값을 -1
                }
            }
            arr[i] = zeroCount + " " + oneCount;   //결과값을 배열에 담음
        }

        for(int i = 0; i < T; i++) {        //T의 크기만큼 반복돌면서
            System.out.println(arr[i]);     //배열에 담긴 값 출력
        }
    }
}
