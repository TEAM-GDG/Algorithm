import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] S;
    static boolean[] startTeam;
    static int minDifference = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        S = new int[n][n];
        // start팀을 판별하기위한 배열
        startTeam = new boolean[n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divideTeams(0, 0);
        System.out.println(minDifference);
    }

    static void divideTeams(int index, int count) {
        // 차이가 0이면 더 이상 탐색할 필요 없음
        if (minDifference == 0) {
            return;
        }

        // 선수가 n/2 만큼 모였다면 팀간의 능력치 차이를 계산
        if (count == n / 2) {
            calculateDifference();
            // 다음 조합 탐색
            return;
        }

        // n만큼 반복하면서 start 팀원 구성
        for (int i = index; i < n; i++) {
            // 만약 i번째 선수가 start팀이 아니라면
            if (!startTeam[i]) {
                // start팀으로 선정
                startTeam[i] = true;
                // 재귀호출로 i번째 다음 선수와 선수의 수를 1증가
                divideTeams(i + 1, count + 1);
                // n=4 일경우 divideTeams를
                // count = 0 일때 1명의 선수 선정
                // count = 1 일때 2명의 선수 선정
                // count = 2 일때 calculateDifference() 호출후 리턴
                // count = 1으로 되돌아와서 2번쨰 선수 방출 후 다른 선수 탐색
                startTeam[i] = false;
            }
        }
    }

    static void calculateDifference() {
        int startTeamPower = 0;
        int linkTeamPower = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // i번째 사람과 j번째 사람이 startTeam이라면
                // 해당 능력치를 startTeamPower에 합산
                if (startTeam[i] && startTeam[j]) {
                    startTeamPower += S[i][j];
                }
                // i번째 사람과 j번째 사람이 startTeam이 아니라면(linkTeam이라면)
                // 해당 능력치를 linkTeamPower에 합산
                else if (!startTeam[i] && !startTeam[j]) {
                    linkTeamPower += S[i][j];
                }
            }
        }

        // 두 팀간의 능력치 차이 계산
        int difference = Math.abs(startTeamPower - linkTeamPower);
        // 최솟값 계산
        if (difference < minDifference) {
            minDifference = difference;
        }
    }
}
