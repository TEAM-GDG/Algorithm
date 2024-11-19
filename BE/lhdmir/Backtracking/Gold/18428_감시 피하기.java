import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    // 선생님의 위치
    static List<Pair> teacherPositions = new ArrayList<>();
    // 빈 공간의 위치
    static List<Pair> emptyPositions = new ArrayList<>();
    static int n;
    static char[][] hallway;
    // 모든 학생들이 감시로 부터 피할수있는지
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        hallway = new char[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                hallway[i][j] = st.nextToken().charAt(0);
                if (hallway[i][j] == 'T') teacherPositions.add(new Pair(i, j));
                if (hallway[i][j] == 'X') emptyPositions.add(new Pair(i, j));
            }
        }

        installObject(0, 0);
        System.out.println(flag ? "YES" : "NO");
    }

    static void installObject(int count, int start) {
        // 모든 학생이 감시를 피할수있는 경우의 수가 존재한다면 더이상 탐색을 안해도됨
        if (flag) return;
        // 장애물을 3개 설치한후 감시 시뮬레이션
        if (count == 3) {
            // 모든 학생이 감시를 피할수있는 경우의 수라면 flag 업데이트
            if (surveillance()) {
                flag = true;
            }
            return;
        }

        // 모든 빈공간의 위치를 순회하면서
        for (int i = start; i < emptyPositions.size(); i++) {
            Pair p = emptyPositions.get(i);
            // 해당 위치에 장애물을 설치
            hallway[p.x][p.y] = 'O';
            // 장애물 수와 시작위치를 증가시켜서 다시 장애물 설치
            installObject(count + 1, i + 1);
            hallway[p.x][p.y] = 'X';
        }
    }

    static boolean surveillance() {
        // 현재 선생님의 위치에서 상하좌우 에 학생이 있는지 검사
        for (Pair teacher : teacherPositions) {
            // 검사에서 한번이라도 걸린다면 false 리턴
            // 상
            if (!canHide(teacher.x, teacher.y, -1, 0)) return false;
            // 하
            if (!canHide(teacher.x, teacher.y, 1, 0)) return false;
            // 좌
            if (!canHide(teacher.x, teacher.y, 0, -1)) return false;
            // 우
            if (!canHide(teacher.x, teacher.y, 0, 1)) return false;
        }
        // 모든 방향에서 감시에 걸리지 않았으므로 true 리턴
        return true;
    }

    static boolean canHide(int x, int y, int dx, int dy) {
        while (x >= 0 && x < n && y >= 0 && y < n) {
            // 현재 검사하는곳에 장애물이 있다면 중단
            if (hallway[x][y] == 'O') break;
            // 학생이 있다면 감시에 걸렸으므로 false 리턴
            if (hallway[x][y] == 'S') return false;
            x += dx;
            y += dy;
        }
        // 감시에 걸리지 않았으므로 true 리턴
        return true;
    }
}
