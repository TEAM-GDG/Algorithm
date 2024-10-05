import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 빗물이 고일려면 기본적으로 자신의 높이보다 높은 블럭에 둘러져야 한다.
 * - 1열과 마지막 열에는 물이 고일 수 없다.
 * - 조건
 * 1. 열 기준 좌특으로 자신보다 높은 블럭이 존재할 때
 * 2. 열 기준 우축으로 자신보다 높은 블럭이 존재할 때
 *
 * 조건에 만족할 때 빗물 고이는 양 계산
 * 왼쪽 오른쪽 중 더 작은 높이 만큼 빗물이 고인다.
 */
public class 14719_빗물 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        int[] height = new int[W];
        int answer = 0;
        //블럭 높이 저장
        for(int i=0;i<W;i++) {
            height[i] = Integer.parseInt(st .nextToken());
        }

        // 2번째 열부터 시작 -> 첫번째와 마지막은 물이 고일 수 없기 때문이다.
        for (int i = 1; i < W - 1; i++) {
            int left = 0, right = 0;

            // 좌측 블럭 높이 구하기
            for (int j = 0; j < i; j++) {
                left = Math.max(left, height[j]);
            }

            // 우측 블럭 높이 구하기
            for (int j = i + 1; j < W; j++) {
                right = Math.max(right, height[j]);
            }

            // 현재 열 블럭이 좌측, 우측 블럭보다 작을 때
            if (height[i] < left && height[i] < right) {
                // 더 작은 벽면 - 현재 열
                answer += Math.min(left, right) - height[i];
            }
        }

        System.out.println(answer);
    }
}
