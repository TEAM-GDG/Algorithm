package BackTracking.Silver;

import java.io.*;

public class bt_14889_스타트와링크_2 {
    static int n, total, row[] = new int[20], col[] = new int[20], ret = 987654321;  // n: 팀원 수, total: 전체 합, row[]와 col[]: 각 행, 열의 합, ret: 최소 차이를 저장할 변수 (초기값을 매우 큰 값으로 설정)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // n을 입력받고, 행렬을 읽어와 합을 계산
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int v = Integer.parseInt(str[j]);
                total += v;  // 전체 합에 더함
                row[i] += v;  // i번째 행의 합에 더함
                col[j] += v;  // j번째 열의 합에 더함
            }
        }

        // 재귀 호출을 통해 최소 차이를 구함
        match(1, 1, total - row[0] - col[0]);  // 첫 번째 행과 열을 제외하고, match 함수 호출
        bw.write(String.valueOf(ret));  // 최소 차이를 출력
        bw.close();
    }

    // 재귀적으로 조합을 선택하며 최소 차이를 계산하는 함수
    static void match(int ind, int depth, int cur) {
        if (depth == n / 2) {  // n/2개의 행과 열을 선택했으면
            ret = Math.min(ret, Math.abs(cur));  // 현재 차이값의 절대값을 최소값과 비교
            return;  // 더 이상 진행할 필요 없음
        }
        if (ind == n) return;  // 모든 행과 열을 다 선택한 경우 종료

        // ind번째 행과 열을 선택하는 경우
        match(ind + 1, depth + 1, cur - row[ind] - col[ind]);  // 현재 행과 열의 합을 빼면서 진행

        // ind번째 행과 열을 선택하지 않는 경우
        match(ind + 1, depth, cur);  // 현재 행과 열을 제외하고 진행
    }
}
