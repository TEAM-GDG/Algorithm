import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        // 최솟값
        // 체스판의 모든 칸을 다시 칠해야하는 경우 = 64
        int minFix = 64;

        int count1;
        int count2;
        char current;

        // 두개의 중첩 반복문을 통해 체스판의 시작좌표 설정
        // 반약 10*10 사이즈라면 체스판을 만들수있는 시작점은 (0,0)부터 (2,2)까지로 총 9개가 있다
        // n - 8 을 통해서 10 - 8 = 2를 통해서 0부터 2까지 반복
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                // 시작좌표가 달라질때마다 수정횟수를 0으로 초기화

                // 좌상단 칸이 흰색일때
                count1 = 0;
                // 좌상단 칸이 검은색일때
                count2 = 0;

                // 시작좌표부터 8칸까지 각 칸을 검사
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        // 시작좌표 + 각 x,y를 더해서 보드판에서 현재 칸을 저장
                        current = board[i + x][j + y];

                        // 현재 칸이 짝수인지 홀수인지 판별
                        // 좌상단칸이 흰색일경우 W로 시작하므로, 짝수 인덱스(0,0), (0,2), (1,1)등에서 W가 되야한다.
                        // B는 홀수 인덱스(0,1),(0,3),(1,0) 이 B가 되야한다.
                        // 좌상단칸이 검은색일경우 B로 시작하므로, 짝수 인덱스(0,0), (0,2), (1,1)등에서 B가 되야한다.
                        // W는 홀수 인덱스(0,1),(0,3),(1,0) 이 W가 되야한다.
                        if ((x + y) % 2 == 0) {
                            // 짝수 인덱스가 흰색이 아닐경우
                            // 좌상단이 흰색부터 시작하면 검은색으로 수정해야하니까 count1을 증가
                            if (current != 'W') count1++;
                            // 짝수 인덱스가 검은색이 아닐경우
                            // 좌상단이 검은색부터 시작하면 흰색으로 수정해야하니까 count2을 증가
                            if (current != 'B') count2++;
                        } else {
                            // 홀수 인덱스가 검은색이 아닐경우
                            // 좌상단이 흰색부터 시작하면 홀수칸에는 흰색을 검은색으로 수정해야하니때문에 count1을 증가
                            if (current != 'B') count1++;
                            // 홀수 인덱스가 흰색이 아닐경우
                            // 좌상단이 검은색부터 시작하면 홀수칸에는 검은색을색을 흰색으로 수정해야하니때문에 count2을 증가
                            if (current != 'W') count2++;
                        }
                    }
                }

                // 이중 최솟값을 선별해서 결과로 출력
                minFix = Math.min(minFix, Math.min(count1, count2));
            }
        }

        System.out.println(minFix);
    }
}
