import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 그리디_1026_보물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());        //N: 배열의 길이

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr1 = new int[N];
        for(int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());     //첫번째 배열 값 입력받음
        }

        st = new StringTokenizer(br.readLine());
        int[] arr2 = new int[N];
        for(int i = 0; i < N; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());     //두번째 배열 값 입력받음
        }

        Arrays.sort(arr1);      //첫번째 배열 정렬
        Arrays.sort(arr2);      //두번째 배열 정렬

        int result = 0;     //결과값 출력할 변수
        for(int i = 0; i < N; i++) {
            result += arr1[i] * arr2[N-1-i];    //첫번째 배열 i번째와 두번째 배열 마지막-i번째 곱을 result에 합함
        }

        System.out.println(result);     //출력
    }
}
