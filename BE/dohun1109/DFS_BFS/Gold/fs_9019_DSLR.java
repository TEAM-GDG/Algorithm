package DFS_BFS.Gold;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class fs_9019_DSLR {

    /** 문제
     * D(0) : n 을 2배로 단, 9999보다 큰 경우에는 n%10_000 한 결과를 저장
     * S(1) : n -1 단, 0 이면 9999가 저장
     * L(2) : n = d1 d2 d3 d4 -> d2 d3 d4 d1 으로 변경
     * R(3) : n = d1 d2 d3 d4 -> d4 d1 d2 d3 으로 변경
     *
     * e.g)
     * 3
     * 1234 3412
     * 1000 1
     * 1 16
     *
     * LL
     * L
     * DDDD
     */
    static Queue<Integer> queue;
    static int[] near,numArr;
    static char[] operator;
    static int A, B;
    static char[] conversion = {'D', 'S', 'L', 'R'};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while(T!=0){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            near = new int[10_000];
            operator = new char[10_000];
            Arrays.fill(near, -1);
            bfs();

            List<Character> path = new ArrayList<>();
            for (int at = B; at != A; at = near[at]) {
                path.add(operator[at]);
            }
            Collections.reverse(path);

            for (char pos : path) {
                sb.append(pos);
            }
            sb.append('\n');
            T--;
        }

        System.out.println(sb);


    }

    private static void bfs() {
        queue = new LinkedList<>();
        queue.offer(A);
        numArr = new int[4];
        int index;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            index = 0;
            for (int next : dslr(now)) {
                if (next >= 0 && next < 10_000 && near[next] == -1) {
                    queue.offer(next);
                    operator[next] = conversion[index];
                    near[next] = now;
                    if (next == B) return;  //위치에 도달하면 종료
                }
                index++;
            }
        }
    }

    private static int[] dslr(int now) {
        int D = now*2>9999?(now*2)%10_000:now*2;
        int S = now-1<0?9999:now-1;
        for (int i = 3; i >= 0; i--) {
            numArr[i] = now%10;
            now/=10;
        }
        int L = ((numArr[1] * 10 + numArr[2]) * 10 + numArr[3]) * 10 + numArr[0];
        int R = ((numArr[3] * 10 + numArr[0]) * 10 + numArr[1]) * 10 + numArr[2];
        return new int[]{D, S, L, R};
    }
}
