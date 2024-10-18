import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 그리디_11497_통나무건너뛰기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());        //T: 테스트 케이스의 수
        int[] resultArr = new int[T];       //결과값들을 담을 배열

        for(int i = 0; i < T; i++) {        //테스트 케이스 수 만큼 반복
            int N = Integer.parseInt(br.readLine());        //N: 통나무의 개수
            int[] heightArr = new int[N];       //통나무들의 높이를 담을 배열
            StringTokenizer st = new StringTokenizer(br.readLine());        //높이들을 입력받음

            for(int j = 0; j < N; j++) {
                heightArr[j] = Integer.parseInt(st.nextToken());        //배열에 높이 저장
            }

            Arrays.sort(heightArr);     //높이들이 담긴 배열 정렬
            int maxNum = heightArr[N-1];        //최대값 따로 뺌
            int[] calArr = new int[N];      //최소 난이도를 만들 배열 생성
            calArr[N/2] = maxNum;       //배열의 가운데에 최대값 대입

            int left = 0;       //왼쪽 포인터
            int right = N-1;    //오른쪽 포인터
            int index = 0;      //배열의 인덱스

            while(index < N-1){     //인덱스가 N-1보다 작을동안 반복
                if(index % 2 == 0){     //인덱스가 짝수이면
                    calArr[left] = heightArr[index];    //배열의 왼쪽에 높이가 담긴 배열 index번째 값을 넣음
                    left++;     //왼쪽 포인터 +1
                    index++;    //인덱스 +1
                } else {        //인덱스가 홀수이면
                    calArr[right] = heightArr[index];   //배열의 오른쪽에 높이가 담긴 배열 index번째 값을 넣음
                    right--;    //오른쪽 포인터 -1
                    index++;    //인덱스 +1
                }
            }

            int result = 0;     //만들어진 배열에서 가장 높은 배열을 뽑아내기 위하 변수
            for(int j = 0; j < N-1; j++) {      //배열을 돌면서
                result = Math.max(result, Math.abs(calArr[j] - calArr[j+1]));       //j번째값 - j+1번째값의 절대값을 result와 비교해서 더 큰 값을 저장
            }
            resultArr[i] = result;      //최종적으로 result에 담긴 값이 최소난이도
        }

        for (int i = 0; i < T; i++) {
            System.out.println(resultArr[i]);       //결과 출력
        }
    }
}
