import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 그리디_1541_잃어버린괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();     //수식 입력받음
        String[] arr = str.split("-");      //- 기준으로 나눔 (마이너스 뒤 수들은 전부 빼게끔 괄호를 치는 형식)

        int result = 0;     //결과값 출력할 변수
        for(int i = 0; i < arr[0].split("\\+").length; i++){        //- 기준으로 앞의 수들을 다 더함
            result += Integer.parseInt(arr[0].split("\\+")[i]);
        }

        if(arr.length != 1){        //수식에서 -가 하나라도 있는 경우
            for(int i = 1; i < arr.length; i++){        //- 기준으로 나눈 수들을 다 더할거
                int minusNum = 0;       //빼줄 값을 저장하는 변수
                for(int j = 0; j < arr[i].split("\\+").length; j++){        //- 기준으로 나눈 값들 중 i번째 수들을 다 더함
                    minusNum += Integer.parseInt(arr[i].split("\\+")[j]);
                }
                result -= minusNum;     //결과값에서 다 더한값을 뺌
            }
        }

        System.out.println(result);     //출력
    }
}
