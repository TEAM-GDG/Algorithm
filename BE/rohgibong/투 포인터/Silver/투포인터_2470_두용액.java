import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 투포인터_2470_두용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());        //N: 전체 용액의 수
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[N];     //N 크기의 정수형 배열 선언
        for(int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());      //두번째 줄에서 받은 값들을 넣어줌
        }
        Arrays.sort(numbers);

        int left = 0;       //왼쪽 포인터 0부터 시작
        int right = N-1;    //오른쪽 포인터 N-1부터 시작
        int minNum = 2000000000;    //최소 절대값 비교할 수 (-100만부터 100만까지니까 제일 크게 나올 수 있는 절대값은 199만9999임. 그래서 200만으로 잡아둠 -> 초기엔 무조건 맨 처음 값들이 들어감)
        int leftNum = 0;    //왼쪽 값 넣을 변수
        int rightNum = 0;   //오른쪽 값 넣을 변수

        while(left < right) {   //왼쪽 포인터가 오른쪽 포인터보다 작은동안 반복
            int checkNum = Math.abs(numbers[left] + numbers[right]);    //왼쪽 포인터의 값과 오른쪽 포인터의 값을 합하고 절대값을 구해서 checkNum에 넣음
            if(minNum > checkNum) {     //minNum이 checkNum보다 클 경우
                minNum = checkNum;      //checkNum(새로 구한 값)을 minNum(절대값이 젤 작은 수)에 넣음
                leftNum = numbers[left];    //이때 왼쪽 포인터의 값을 leftNum에 저장
                rightNum = numbers[right];  //오른쪽 포인터의 값도 rightNum에 저장
            }
            if(numbers[left] + numbers[right] < 0){     //왼쪽 포인터의 값과 오른쪽 포인터의 값의 합이 음수일 경우
                left++;     //왼쪽 포인터 이동
            } else if(numbers[left] + numbers[right] > 0){      //왼쪽 포인터의 값과 오른쪽 포인터의 값의 합이 0보다 클 경우
                right--;    //오른쪽 포인터 이동
            } else {        //왼쪽 포인터의 값과 오른쪽 포인터의 값의 합이 0일 경우
                break;      //반복문 종료 (더 이상 안돌아도 됨)
            }
        }

        System.out.println(leftNum + " " + rightNum);       //왼쪽 값과 오른쪽 값 출력

    }
}
