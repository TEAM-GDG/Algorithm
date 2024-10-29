package F_six_week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 마인크래프트_18111 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // 최소, 최대
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (min > arr[i][j]) min = arr[i][j];
                if (max < arr[i][j]) max = arr[i][j];
            }
        }

        // time은 최소시간을 저장 할 변수, 풀이에 적었듯이
        int time = Integer.MAX_VALUE;
        int high = 0;
        //만들 층 i
        for (int i = min; i <= max; i++) {
            int count = 0;
            int block = B; // 인벤토리에 있는 블록
            //좌표 j와 k
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    //현재 좌표의 층이 만들 층보다 높으면 제거하는데, 블록은 제거한 만큼 추가, 시간은 두배로
                    if (i < arr[j][k]) {
                        count += ((arr[j][k] - i) * 2);
                        block += (arr[j][k] - i);
                    } else { //낮을 경우 블록은 제거, 시간은 1배
                        count += (i - arr[j][k]);
                        block -= (i - arr[j][k]);
                    }
                }
            }
            //블록의 개수가 음수가 되면 반복문 종료
            if (block < 0) break;

            if (time >= count) {
                time = count;
                high = i;
            }
        }
        System.out.println(time + " " + high);
    }

}