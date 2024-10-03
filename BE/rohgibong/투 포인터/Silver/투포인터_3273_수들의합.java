import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 투포인터_3273_수들의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());      //수열에 수들 배열에 저장
        }

        int x = Integer.parseInt(br.readLine());    //x: 합해서 나와야 할 수

        Arrays.sort(arr);       //배열 정렬

        int left = 0;       //왼쪽 포인터
        int right = n - 1;  //오른쪽 포인터
        int result = 0;     //결과값 (조건을 만족하는 쌍의 개수)

        while (left < right) {      //왼쪽 포인터가 오른쪽 포인터보다 작을동안 반복
            if(arr[left] + arr[right] == x) {   //왼쪽 포인터의 수와 오른쪽 포인터의 수 합이 x인경우
                result++;   //결과값 +1
                left++;     //왼쪽 포인터 이동
                right--;    //오른쪽 포인터 이동
            } else if(arr[left] + arr[right] < x) {     //왼쪽 포인터의 수와 오른쪽 포인터의 수 합이 x보다 작은경우
                left++;     //왼쪽 포인터 이동
            } else {        //왼쪽 포인터의 수와 오른쪽 포인터의 수 합이 x보다 큰 경우
                right--;    //오른쪽 포인터 이동
            }
        }
        System.out.println(result);     //결과값 출력
    }
}
