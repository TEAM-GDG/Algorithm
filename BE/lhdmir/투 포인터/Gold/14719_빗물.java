import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

       st = new StringTokenizer(br.readLine());

       int[] arr = new int[w];

       for (int i = 0; i < w; i++) {
           arr[i] = Integer.parseInt(st.nextToken());
       }

       // 빗물 총량
       int volume = 0;
       int left = 0;
       int right = w - 1;
       // 초기 leftMax 값을 arr[left]값, 제일 왼쪽 높이로 설정
       int leftMax = arr[left];
       // 초기 rightMax 값을 arr[right]값, 제일 오른쪽 높이로 설정
       int rightMax = arr[right];

       // left포인터가 right포인터 위치로 이동할때까지 반복
       while (left < right) {
           // 반복문 시작할때마다 최대높이와 현재높이를 비교해서
           // leftMax, rightMax 값을 업데이트
           leftMax = Math.max(leftMax, arr[left]);
           rightMax = Math.max(rightMax, arr[right]);

           // 더 높은쪽을 향해 포인터 이동
           if(leftMax <= rightMax) {
               // leftMax = 3
               // arr[left] = 0 일떄
               // volume = 3
               volume += leftMax  - arr[left++];
           }
           else {
               // right도 left와 같이 계산
               volume += rightMax - arr[right--];
           }
       }

        System.out.println(volume);
    }
}