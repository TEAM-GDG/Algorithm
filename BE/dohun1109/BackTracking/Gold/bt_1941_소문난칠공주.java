package BackTracking.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bt_1941_소문난칠공주 {

    static List<Student> students = new ArrayList<>();
    static boolean[][] seating = new boolean[6][6];
    static boolean[] selected;
    static Queue<Student> queue;
    // 방향 배열: 상, 우, 하, 좌 (0=북, 1=동, 2=남, 3=서)
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int isPossible = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <=5; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 1; j <= 5; j++) {
                char ch = line[j-1];
                if (ch == 'Y'){
                    students.add(new Student(i, j,'Y'));
                }else{
                    students.add(new Student(i, j,'S'));
                }
            }
        }
        selected = new boolean[students.size()];
        queue = new LinkedList<>();
        findSeatingCombination(0,7);
        System.out.println(isPossible);

    }
    static void findSeatingCombination(int start, int remaining) {
        if (remaining == 0) {
            for (int i = 0; i < selected.length; i++) {
                if (selected[i]){
                    queue.offer(students.get(i));
                    formationFamousSevenPrincesses();
                    return;
                }
            }
            return;
        }

        for (int i = start; i < students.size(); i++) {
            Student stu = students.get(i);
            selected[i] = true;
            seating[stu.x][stu.y] = true;   //특정 학생을 선택했을 때
            findSeatingCombination(i + 1, remaining - 1);
            selected[i] = false;
            seating[stu.x][stu.y] = false; //선택 안한경우
        }

    }

    static void  formationFamousSevenPrincesses(){
        int S_Count = 0; // 'S' 학생의 수
        int adjacent = 0; // 연결된 학생의 수
        boolean[][] visited = new boolean[6][6]; // 방문 체크 배열
        while (!queue.isEmpty()) {
            Student stu = queue.poll();
            visited[stu.x][stu.y] = true; // 현재 좌표 방문 처리
            adjacent++; // 연결된 학생 수 증가
            if (stu.separation == 'S') S_Count++; // 'S' 학생일 경우 카운트 증가

            for (int i = 0; i < 4; i++) { // 4방향 탐색
                int nx = stu.x + dx[i];
                int ny = stu.y + dy[i];
                // 유효한 범위 내의 좌표이며, 선택된 좌표(seating[nx][ny])이고, 방문하지 않은 경우
                if (stu.isChecked(nx,ny)&&!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(students.get((nx - 1) * 5 + (ny - 1)));
                }
            }
        }

        // 조건 확인: 'S' 학생이 4명 이상이고, 총 연결된 학생 수가 7명일 경우
        if (S_Count >= 4 && adjacent == 7) {
            isPossible++;
        }
    }

    static class Student{
        int x;
        int y;
        char separation;

        public Student(int x, int y, char separation) {
            this.x = x;
            this.y = y;
            this.separation = separation;
        }

        public boolean isChecked(int nx , int ny) {
            return 0 < nx && nx <= 5 && 0 < ny && ny <= 5 && seating[nx][ny];
        }

    }

}
