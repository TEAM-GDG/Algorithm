import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 그리디_11501_주식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());        //T: 테스트 케이스 수
        long[] arr = new long[T];       //결과를 출력할 배열

        for(int i = 0; i < T; i++) {        //테스트 케이스 수 만큼 반복
            int N = Integer.parseInt(br.readLine());    //N: 날의 수
            int[] priceArr = new int[N];        //날짜별 가격을 담을 배열
            int max = 0;        //최대값 담을 변수

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                priceArr[j] = Integer.parseInt(st.nextToken());     //날짜별 가격을 입력받음
            }

            for(int j = priceArr.length - 1; j >= 0; j--){      //배열의 끝에서부터 처음까지 반복
                if(max < priceArr[j]){      //최대값보다 가격이 큰 경우
                    max = priceArr[j];      //최대값 바꿔줌
                } else {        //최대값보다 가격이 같거나 작은경우
                    arr[i] += max - priceArr[j];        //배열에 이득본값만큼 더함 (그때가 최대가 되는 상태)
                }
            }
        }

        for(int i = 0; i < T; i++) {        //전체 테스트 케이스 수 만큼 반복돌면서
            System.out.println(arr[i]);     //결과값 출력
        }
    }
}
