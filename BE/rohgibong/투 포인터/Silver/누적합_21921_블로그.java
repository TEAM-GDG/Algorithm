import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 누적합_21921_블로그 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       //N: 블로그를 시작하고 지난 일 수
        int X = Integer.parseInt(st.nextToken());       //X: X일동안 가장 많이 들어온 방문자 수

        st = new StringTokenizer(br.readLine());
        int[] visitor = new int[N];         //방문객 수를 담을 정수형 배열
        for(int i = 0; i < N; i++){
            visitor[i] = Integer.parseInt(st.nextToken());      //배열에 값 담음
        }

        int sum = 0;        //방문객 합을 담을 변수
        int days = 1;       //최대 방문객 수를 달성한 일 수 담을 변수
        int left = 0;       //왼쪽 포인터
        int right = X;      //오른쪽 포인터 (X번째)

        for(int i = 0; i < X; i++){
            sum += visitor[i];      //최초 한번 0부터 X번째까지 방문객 다 더해서
        }
        int maxVisitor = sum;       //최대 방문객수에 그 값을 담음

        while(true){
            if(right > N-1){        //오른쪽 포인터가 마지막 배열 넘기 전까지 반복
                break;
            }
            sum -= visitor[left++];     //합한 값에서 왼쪽 포인터값 빼고 왼쪽 포인터 이동
            sum += visitor[right++];    //합한 값에서 오른쪽 포인터값 빼고 오른쪽 포인터 이동

            if(sum > maxVisitor){         //방문객수 합이 최대 방문객수보다 클 경우
                days = 1;       //기간 1로 초기화
                maxVisitor = sum;       //합한 값을 최대 방문객 수 변수에 대입
            } else if (sum == maxVisitor){      //방문객수 합이 최대 방문객수랑 같으면
                days++;     //기간 +1
            }
        }

        if(sum == 0){       //합계가 0이면
            System.out.println("SAD");      //SAD 출력
        } else {        //합계가 0이 아니면
            System.out.println(maxVisitor);     //최대 방문객수 출력
            System.out.println(days);           //최대 방문객수의 기간 수 출력
        }
    }
}
