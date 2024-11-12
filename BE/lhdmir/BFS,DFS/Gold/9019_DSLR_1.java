import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 숫자가 변한 경로를 저장할 배열
    static int[] logNumber = new int[10_000];
    // 숫자가 변할때 사용한 명령어를 저장할 배열
    static char[] logCommand = new char[10_000];
    // 해당 숫자를 탐색했는지 검사할 배열
    static boolean[] visited = new boolean[10_000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 탐색한 배열들을 초기화
            resetVisited();
            // dslr 계산
            dslrCalc(a, b);

            // 결과물을 저장할 StringBuilder 객체
            StringBuilder result = new StringBuilder();
            // b부터 시작해 a까지의 기록을 역순으로 추적
            int current = b;
            while (current != a) {
                // result 에 logCommand[current]를 추가
                // 마지막으로 사용했던 커맨드부터 처음에 사용했던 커맨드까지 result에 저장한다.
                result.append(logCommand[current]);
                // logNumber[x]에는 x번째 값에 도달하기 전의 위치가 기록되어있다
                // 현재 위치를 logNumber로 설정함으로써 현재 위치로 오기위해서 도달했던 위치로 이동한다.
                // 즉, b부터 시작해 a까지 탐색해가며 그 경로를 result에 추가하는것
                current = logNumber[current];
            }

            // 역순으로 추적했기때문에 반대로 뒤집어준다.
            System.out.println(result.reverse());
        }
    }

    static void dslrCalc(int a, int b) {
        // 다음에 방문할 위치를 저장할 큐 생성
        Queue<Integer> q = new LinkedList<>();
        // 큐에 현재 위치 삽입
        q.add(a);
        // 현재 위치 방문 표시
        visited[a] = true;

        char[] command = {'D', 'S', 'L', 'R'};
        while (!q.isEmpty()) {
            int current = q.poll();

            // 현재 탐색할 숫자가 b(목표값)과 같으면 종료
            if (current == b) return;

            // 현재 수에서 모든 D,S,L,R 명령어를 실행
            for (char c : command) {
                int next = 0;

                switch (c) {
                    case 'D':
                        next = commandD(current);
                        break;
                    case 'S':
                        next = commandS(current);
                        break;
                    case 'L':
                        next = commandL(current);
                        break;
                    case 'R':
                        next = commandR(current);
                        break;
                }

                // 다음에 탐색할 수를 아직 탐색하지 않았다면
                if (!visited[next]) {
                    // 다음 수를 큐에 삽입
                    q.offer(next);
                    // 다음 탐색할 수를 방문처리
                    visited[next] = true;
                    // 이동한 기록을 저장할 배열의 next칸에 현재 값을 저장함으로써
                    // b부터 역순으로 추적하면 최단 경로가 나온다.
                    logNumber[next] = current;
                    // 또한, 명령어의 기록이 필요하기 때문에 logCommand 배열의 next칸에 명령어를 저장한다.
                    logCommand[next] = c;
                }
            }
        }
    }

    static int commandD(int n) {
        return (n * 2) % 10_000;
    }

    static int commandS(int n) {
        return (n == 0) ? 9999 : n - 1;
    }

    static int commandL(int n) {
        return (n % 1000) * 10 + n / 1000;
    }

    static int commandR(int n) {
        return (n % 10) * 1000 + n / 10;
    }

    static void resetVisited() {
        Arrays.fill(visited, false);
        Arrays.fill(logNumber, -1);
        Arrays.fill(logCommand, '\0');
    }
}
