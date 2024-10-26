import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[][] area = new int[n][m];

        int minHeight = 256;
        int maxHeight = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
                minHeight = Math.min(minHeight, area[i][j]);
                maxHeight = Math.max(maxHeight, area[i][j]);
            }
        }

        // 땅을 고르는데 걸리는 최소 시간
        int minTime = Integer.MAX_VALUE;
        // 작업이 끝난 후 땅의 높이
        int targetHeight = 0;

        int needBlocks;
        int removeBlocks;

        for (int h = minHeight; h <= maxHeight; h++) {
            // 필요한 블럭의 갯수
            needBlocks = 0;
            // 제거할 블럭의 갯수
            removeBlocks = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    // 현재높이(area[i][j])가 목표높이(h)보다 낮다면 블럭을 메꿔야하니
                    // 목표높이에서 현재높이를 뺀 값을 needBlock에 더함
                    if (area[i][j] < h) {
                        needBlocks += h - area[i][j];
                    }
                    // 반대로, 현재 좌표(area[i][j])가 목표높이(h)보다 높다면 블럭을 삭제해야하므로
                    // 현재높이에서 목표높이를 뺀 값을 removeBlock에 더함
                    else {
                        removeBlocks += area[i][j] - h;
                    }
                }
            }

            // 현재 가지고있는 블럭(초기에 받은 블럭 + 제거된 블럭)이 메꿔야할 블럭(needBlocks)보다 많다면
            // 땅 고르기가 성공적으로 완료되었으므로 걸린 시간을 계산
            if (needBlocks <= b + removeBlocks) {
                // needBlocks(블럭을 메꾸는데 걸리는 시간 1초)
                // removeBlocks(블럭을 제거하는데 걸리는 시간 2초)
                int time = needBlocks + (2 * removeBlocks);

                // 현재 계산된시간(time)이 최단 시간(minTime)보다 작다면
                // 최단 시간과 높이를 업데이트
                // 문제에서 "답이 여러 개 있다면 그중에서 땅의 높이가 가장 높은 것을 출력하시오."
                // 이 부분은 minHeight 부터 maxHeight 까지 순회하기때문에 시간이 같다면 자동으로 제일 높은 땅의 높이가 기록됨
                if (time <= minTime) {
                    minTime = time;
                    targetHeight = h;
                }

            }
        }

        System.out.println(minTime + " " + targetHeight);
    }
}
