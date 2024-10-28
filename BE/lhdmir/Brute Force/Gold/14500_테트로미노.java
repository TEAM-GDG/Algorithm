import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int[][] paper;
    static int N, M;
    static int maxSum = 0;

    // 테트로미노의 모든 가능한 형태 (회전 및 대칭 포함)
    // [19][4][2] 사이즈의 3차원 배열을 생성
    static int[][][] tetrominos = {
            {{0, 0}, {0, 1}, {0, 2}, {0, 3}}, // I형 가로
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}}, // I형 세로
            {{0, 0}, {1, 0}, {0, 1}, {1, 1}}, // 네모형
            // L형 테트로미노 - 회전 및 대칭 포함 8가지 형태
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}}, // L형 변형 1
            {{0, 1}, {1, 1}, {2, 1}, {2, 0}}, // L형 변형 2
            {{0, 0}, {0, 1}, {1, 1}, {2, 1}}, // L형 변형 3
            {{0, 0}, {0, 1}, {1, 0}, {2, 0}}, // L형 변형 4
            {{0, 0}, {1, 0}, {1, 1}, {1, 2}}, // L형 변형 5 (대칭)
            {{0, 2}, {1, 0}, {1, 1}, {1, 2}}, // L형 변형 6 (대칭)
            {{0, 0}, {0, 1}, {0, 2}, {1, 0}}, // L형 변형 7 (대칭)
            {{0, 0}, {0, 1}, {0, 2}, {1, 2}}, // L형 변형 8 (대칭)
            // T형 테트로미노
            {{0, 0}, {0, 1}, {0, 2}, {1, 1}}, // T형 변형 1
            {{1, 0}, {0, 1}, {1, 1}, {1, 2}}, // T형 변형 2
            {{0, 1}, {1, 0}, {1, 1}, {2, 1}}, // T형 변형 3
            {{0, 0}, {1, 0}, {2, 0}, {1, 1}}, // T형 변형 4
            // Z형 테트로미노
            {{0, 0}, {1, 0}, {1, 1}, {2, 1}}, // Z형 변형 1
            {{1, 0}, {0, 1}, {1, 1}, {0, 2}}, // Z형 변형 2
            // S형 테트로미노
            {{0, 1}, {1, 0}, {1, 1}, {2, 0}}, // S형 변형 1
            {{0, 0}, {1, 1}, {0, 1}, {1, 2}}  // S형 변형 2
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 좌표에 모든 테트로미노 모양을 넣어서 최댓값을 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                calcMaxSum(i, j);
            }
        }

        System.out.println(maxSum);
    }

    static void calcMaxSum(int x, int y) {
        // 테트로미노의 각 모양이 shape에 저장
        // ex) I형 가로
        // shape = {{0, 0}, {0, 1}, {0, 2}, {0, 3}}
        for (int[][] shape : tetrominos) {
            int sum = 0;

            // 각 셀을 돌면서 범위에 벗어나지 않는지 확인
            // cell = {0, 0} 이 저장
            for (int[] cell : shape) {
                // 현재 위치(x) + 각 테트로미노의 한부분(cell[0,1])
                int nowX = x + cell[0];
                int nowY = y + cell[1];

                // 범위가 유효한지 검사
                if (nowX < 0 || nowX >= N || nowY < 0 || nowY >= M) {
                    break;
                }
                // 범위가 유효하다면 각 cell의 숫자를 sum에 합산
                else {
                    sum += paper[nowX][nowY];
                }

                // maxSum 업데이트
                maxSum = Math.max(maxSum, sum);
            }
        }
    }
}
