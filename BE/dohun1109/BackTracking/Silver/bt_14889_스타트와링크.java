package BackTracking.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bt_14889_스타트와링크 {
    /**
     * 문제
     * 축구하기위해 모인 사람 총 N명 이고, N은 항상짝수 이다.
     * N/2 로 이루어진 스타트팀과 링크팀 으로 사람들을 나눠야 한다.
     * 1 ~ N 번 까지 배정
     * 팀의 능력치 = Sij , Sij != Sji  team += Sij + Sji
     */

    static int[][] teamBoard;  // 팀 능력치가 저장된 2D 배열
    static boolean[] visited;  // 각 사람의 팀 배정을 나타내는 방문 배열
    static int N;  // 사람 수
    static int Min = Integer.MAX_VALUE;  // 최솟값을 저장할 변수 (최초 값은 매우 큰 값으로 설정)

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()) + 1;  // 사람 수 (1부터 N까지, N은 짝수)
        teamBoard = new int[N][N];  // 팀 능력치를 저장할 배열 (1-based index)
        visited = new boolean[N];  // 각 사람의 팀 선택 여부를 나타내는 배열

        // 팀 능력치를 입력받아 teamBoard에 저장
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N; j++) {
                teamBoard[i][j] = Integer.parseInt(st.nextToken());  // 각 능력치 입력
            }
        }

        // combination 함수 호출로 팀을 나누는 조합을 구함
        combination(1, N / 2);
        System.out.println(Min);  // 최소 차이 출력
    }

    // 조합을 구하는 함수 (start부터 r개를 선택하는 조합을 구함)
    static void combination(int start, int r) {
        if (r == 0){  // r명이 선택되었을 경우
            /**
             * 방문한 팀과 방문하지 않은 팀을 각각 나누어
             * 각 팀의 점수를 구한 뒤 최솟값을 찾는다.
             */
            diff();
            return;
        }
        for (int i = start; i < N; i++) {
            if (!visited[i]){  // 아직 방문하지 않은 사람이라면
                visited[i] = true;  // 방문 처리
                combination(i + 1, r - 1);  // 다음 사람을 선택
                visited[i] = false;  // 선택을 취소하고 다음 사람을 탐색
            }
        }
    }

    // 두 팀의 점수를 계산하고 차이를 구하는 함수
    private static void diff() {
        int team_start = 0;  // 스타트 팀 점수
        int team_link = 0;  // 링크 팀 점수

        // 각 팀의 능력치 계산
        for (int i = 1; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                // i번째 사람과 j번째 사람이 스타트팀에 속하면
                if (visited[i] == true && visited[j] == true) {
                    team_start += teamBoard[i][j];  // 스타트팀 점수 추가
                    team_start += teamBoard[j][i];  // 양방향 점수
                }
                // i번째 사람과 j번째 사람이 링크팀에 속하면
                else if (visited[i] == false && visited[j] == false){
                    team_link += teamBoard[i][j];  // 링크팀 점수 추가
                    team_link += teamBoard[j][i];  // 양방향 점수
                }
            }
        }

        // 두 팀의 점수 차이 (절대값)
        int val = Math.abs(team_start - team_link);

        /*
         * 두 팀의 점수차가 0이라면 가장 낮은 최솟값이기 때문에
         * 더이상 탐색이 필요없이 0을 출력하고 종료하면 된다.
         */
        if (val == 0) {
            System.out.println(val);  // 0을 출력하고 종료
            System.exit(0);  // 프로그램 종료
        }

        // 현재 계산된 차이값과 최솟값을 비교하여 갱신
        Min = Math.min(val, Min);
    }
}
