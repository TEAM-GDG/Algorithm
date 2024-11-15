package BackTracking.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bt_18428_감시피하기 {

    static int N; // 복도의 크기
    static char[][] corridor; // 복도 배열
    static boolean[] selected; // 빈 공간 선택 여부를 저장하는 배열
    static List<EmptySpace> emptySpaces; // 빈 공간 좌표를 저장하는 리스트
    static List<Person> teachers; // 선생님의 좌표를 저장하는 리스트
    // 방향 배열: 상, 우, 하, 좌 (0=북, 1=동, 2=남, 3=서)
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean isSafety; // 학생들이 모두 감시를 피할 수 있는지 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine())+1; // 복도 크기 입력 (+1은 배열 사용의 편의를 위해)
        corridor = new char[N][N];
        emptySpaces = new ArrayList<>();
        teachers = new ArrayList<>();

        // 복도 정보 입력
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N; j++) {
                char location = st.nextToken().charAt(0);
                corridor[i][j] = location;
                if (location == 'X'){
                    emptySpaces.add(new EmptySpace(i, j)); // 빈 공간 좌표 저장
                } else if (location == 'T') {
                    teachers.add(new Person(i, j)); // 선생님 좌표 저장
                }
            }
        }

        selected = new boolean[emptySpaces.size()]; // 빈 공간 선택 여부 초기화
        findObstacleCombination(0,3); // 장애물 3개를 배치할 조합 찾기
        System.out.println("NO"); // 학생들을 감시에서 피할 수 없는 경우
    }

    // 장애물 배치 조합을 찾는 백트래킹 함수
    static void findObstacleCombination(int start, int remaining) {
        if (remaining == 0) { // 장애물 3개를 모두 배치한 경우
            isSafety = true;
            for (Person teacher: teachers) { // 모든 선생님에 대해 감시 영역 확인
                for (int i = 0; i<4; i++) { // 네 방향으로 감시
                    if (isSafety) { // 학생이 감시당하지 않은 경우
                        surveillance(teacher.x, teacher.y, i); // 감시 실행
                    } else {
                        return; // 감시당한 학생이 있으면 중단
                    }
                }
            }
            if (isSafety) { // 학생들이 모두 감시를 피할 수 있다면
                System.out.println("YES");
                System.exit(0); // 프로그램 종료
            }
            return;
        }

        // 백트래킹: 빈 공간에 장애물을 배치하고 다음 조합 확인
        for (int i = start; i < emptySpaces.size(); i++) {
            EmptySpace obstacle = emptySpaces.get(i);
            selected[i] = true; // 현재 빈 공간 선택
            corridor[obstacle.x][obstacle.y] = 'O'; // 장애물 설치
            findObstacleCombination(i+1, remaining-1); // 다음 장애물 배치
            selected[i] = false; // 선택 취소
            corridor[obstacle.x][obstacle.y] = 'X'; // 장애물 제거
        }
    }

    // 선생님이 감시를 진행하는 함수
    static void surveillance(int x, int y, int direction) {
        int nx = x + dx[direction];
        int ny = y + dy[direction];
        // 복도 범위를 벗어나지 않으면서 학생('S')을 발견한 경우
        if (0<nx && nx<N && 0<ny && ny<N && corridor[nx][ny] == 'S') {
            isSafety = false; // 감시당했음을 표시
            return;
        }
        // 복도 범위 내의 빈 공간('X')이라면 계속 감시 진행
        if (0<nx && nx<N && 0<ny && ny<N && corridor[nx][ny] == 'X') {
            surveillance(nx, ny, direction); // 다음 칸으로 이동
        }
    }

    // 빈 공간 좌표를 저장하는 클래스
    static class EmptySpace {
        int x;
        int y;

        public EmptySpace(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 선생님 좌표를 저장하는 클래스
    static class Person {
        int x;
        int y;

        public Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}