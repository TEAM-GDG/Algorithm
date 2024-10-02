import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 누적합_2003_수들의합2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nAndM = br.readLine().split(" ");
        int N = Integer.parseInt(nAndM[0]);     //N: 수열의 갯수
        int M = Integer.parseInt(nAndM[1]);     //M: 수열의 합이 되야하는 수

        int[] numArr = new int[N];      //숫자를 담을 배열
        StringTokenizer st = new StringTokenizer(br.readLine());        //수들의 문자열을 받음
        for(int i = 0; i < N; i++){
            numArr[i] = Integer.parseInt(st.nextToken());       //배열에 수 저장
        }
        int result = 0;     //결과값 출력할 변수
        int sum = 0;        //합을 담을 변수
        int left = 0;       //왼쪽 포인터
        int right = 0;      //오른쪽 포인터

        while(true){        //합을 M이 되는걸 찾기위해 반복
           if(sum >= M){        //합이 M보다 크거나 같으면
               sum -= numArr[left];     //왼쪽 포인터가 가리키고 있는 값을 뺌
               left++;      //왼쪽 포인터를 한 칸 이동
           } else if(right > N - 1) {       //오른쪽 포인터가 배열 끝을 넘으면
               break;       //반복문 종료
           } else {     //합이 M보다 작으면
               sum += numArr[right];        //오른쪽 포인터가 가리키고 있는 값을 더함
               right++;     //오른족 포인터를 한 칸 이동
           }
           if(sum == M){        //합이 M과 같으면
               result++;        //결과값 증가
           }
        }

        System.out.println(result);     //결과값 출력
    }
}
