import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 투포인터_15565_귀여운라이언 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   //N: 전체 수
        int K = Integer.parseInt(st.nextToken());   //K: 라이언인형의 갯수

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];     //전체 수 만큼 정수형 배열 생성
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());      //배열에 값들을 넣음
        }

        int left = -1;      //왼쪽 포인터 (초기값임을 구별하기 위해 -1로 함)
        int right = 0;      //오른쪽 포인터 (0부터 시작)
        int lionNum = 0;    //라이언의 수
        int minResult = N;  //최소 배열의 크기(최대값은 N)
        int result = -1;    //해당하는 배열이 있는지 없는지 구분 할 변수

        while (right < N){      //오른쪽 포인터가 N보다 작을동안 반복
            if(arr[right] == 1){        //오른쪽 포인터에 해당하는 배열 값이 1(라이언)일 경우
                if(left == -1) {        //왼쪽 포인터가 -1이면 (초기)
                    left = right;       //오른쪽 포인터의 값을 왼쪽 포인터에 넣어줌 (초기 왼쪽 포인터 지정)
                }
                lionNum++;      //라이언 +1
                if(lionNum == K){   //라이언의 수가 K값에 도달했을 때
                    minResult = Math.min(minResult, (right - left + 1));    //오른쪽 포인터에서 왼쪽 포인터 뺀 값 + 1이 배열의 크기, 가장 작은 배열의 크기를 찾아서 변수에 대입
                    result = 0;     //배열이 존재함을 알리기 위해 0 넣음
                    for(int i = left+1; i < right; i++){    //기존의 왼쪽 포인터 다음 값부터 오른쪽 포인터 전 까지 반복해서
                        if(arr[i] == 1){    //가장 가까운 라이언이 있는 곳에
                            left = i;       //왼쪽 포인터를 지정하고
                            break;          //반복 종료
                        }
                    }
                    lionNum--;      //라이언 1마리 빼고 다시 반복돌기
                }
            }
            right++;        //오른쪽 포인터 +1
        }
        System.out.println(result != -1 ? minResult : -1);      //배열이 존재하면 최소 배열의 크기를 출력, 존재하지 않으면 -1을 출력
    }
}
