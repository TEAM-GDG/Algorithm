package 투포인터_슬라이딩윈도우.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 투포인터_14719_빗물 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 구조물의 세로(H)와 가로(W) 크기 입력
        int H = Integer.parseInt(st.nextToken());   // 세로 x
        int W = Integer.parseInt(st.nextToken());   // 가로 y

        // 각 위치에서 블록의 높이를 저장하는 배열
        int[] A = new int[W];
        // 블록과 빈 공간을 맵핑하는 2차원 배열 (1: 빈 공간, 0: 블록)
        int[][] blockMap = new int[W][H];

        // 각 열의 블록 높이를 입력 받음
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 처음에 모든 공간을 빈 공간(1)으로 채움
        for (int i = 0; i < W; i++) {
            Arrays.fill(blockMap[i], 1);
        }

        // 입력 배열 A에 따라 블록이 있는 위치(0)를 표시
        for (int i = 0; i < W; i++) {
            int block = A[i];  // 해당 위치 i에서 블록의 높이
            for (int j = 0; j < block; j++) {
                blockMap[i][j] = 0;  // 블록이 있는 위치를 0으로 표시
            }
        }

        int TotalCount = 0;  // 총 빗물의 양

        // 각 높이 레벨을 순회
        for (int i = 0; i < H; i++) {
            int count = 0, s = 0, e = W - 1;  // 시작(s)과 끝(e) 포인터

            // 두 포인터를 사용한 슬라이딩 윈도우로 각 높이에서 갇힌 물 계산
            while (true) {
                // 포인터가 만나거나 교차하면 루프 종료
                if (s >= e - 1) {
                    break;
                    // 두 포인터가 모두 블록 위치에 있으면, 갇힌 물을 계산
                } else if (blockMap[s][i] == 0 && blockMap[e][i] == 0) {
                    while (s < e) {
                        s++;
                        count += blockMap[s][i];  // 갇힌 물 양 누적
                    }
                }

                // 시작 포인터가 블록이 아니면 이동
                if (blockMap[s][i] != 0) {
                    s++;
                }
                // 끝 포인터가 블록이 아니면 이동
                if (blockMap[e][i] != 0) {
                    e--;
                }
            }

            // 해당 높이에서 계산된 물의 양을 총합에 추가
            TotalCount += count;
        }

        // 총 갇힌 물의 양 출력
        System.out.println(TotalCount);
    }
}