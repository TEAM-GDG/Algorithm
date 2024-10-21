import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] logs = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                logs[i] = Integer.parseInt(st.nextToken());
            }
            
            Arrays.sort(logs);

            int maxDifficulty = 0;

            // 기존의 방식은 제일 작은값을 배열의 제일 왼쪽 그 다음으로 작은 값을 배열의 제일 오른쪽에 배치하면서
            // 값들을 교차로 배치해 평탄하게 만들고 인접한 모든 통나무 높이를 비교해 제일 높은 값을 구했었는데
            // 아래와 같은 방법은 정렬된 상태에서 i와 i - 2 를 실행해 제일 높은값을 구한다.
            // 예를들어 [2, 4, 5, 7, 9]가 있다면 5-2=3, 7-4=3, 9-5=4 이므로 답은 4가 된다.
            // 적절히 배치한 뒤 [ 2, 5, 9, 7, 4] 인접한 모든 통나무 높이의 차를 구한다면 
            // 1.(5-2=3), 2.(9-5=4), 3.(9-7=2), 4.(7-4=3), 5.(4-2=2) 이런 경우의 수가 나온다.
            // logs[i] - logs[i-2]를 하게되면 경우의 수가 9-7, 4-2를 계산을 안하게 된다.
            // 이유는 2, 4, 5, 7, 9 에서 4-2, 9-7보다는 한칸 건너뛰어서 차이를 구하는게 더 크기때문에(ex. 9-5)
            // 9-7과 4-2 처럼 붙어있는 값들은 연산을 생략해도 문제가 없다는것이다.
            for (int i = 2; i < n; i++) {
                maxDifficulty = Math.max(maxDifficulty, logs[i] - logs[i - 2]);
            }

            System.out.println(maxDifficulty);
        }
    }
}
