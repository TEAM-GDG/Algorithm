import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DNA비밀번호_정답 {
    static int s_len;
    static int p_len;
    static char[] str;
    static int[] checkArr = new int[4];
    static int[] myArr = new int[4];
    static int checkCnt = 0; // {‘A’, ‘C’, ‘G’, ‘T’} 중 최소 개수를 일치한 문자 개수 (0~4)
    static int answer = 0; // 만들 수 있는 비밀번호 개수

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        s_len = Integer.parseInt(st.nextToken());
        p_len = Integer.parseInt(st.nextToken());
        str = br.readLine().toCharArray(); // DNA 문자열

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken());
        }

        // 'GATA' 일 경우
        /**
         *  myArr <- '[G, A]'
         */
        for (int i = 0; i < p_len; i++) { // 첫 부분문자열 셋팅 (0~p_len-1) 까지
            if (str[i] == 'A') {
                myArr[0]++;
            }
            if (str[i] == 'C') {
                myArr[1]++;
            }
            if (str[i] == 'G') {
                myArr[2]++;
            }
            if (str[i] == 'T') {
                myArr[3]++;
            }
        }

        // GA가 조건이 되는지 확인
        if (checkCounting()) {
            answer++; // 만들 수 있는 비밀번호 개수 증가

        }

        int i = -1;
        /**
         * 부분문자열 만들기 => 이전 부분문자열의 첫 문자는 제외하고, 끝에서 1문자를 더 추가한다.
         * GA는 이미 검사 했으니 myArr에서 제거 그러면 A만 남는다. 그런 다음에 T를 추가한다. 이것을 반복
         * [G, A] -> for문 집입 ->  [A] -> [A, T] -> (검사) -> [T] -> [T, A] -> (검사)
         */
        for (int j = p_len; j < s_len; j++) { // 부분문자열의 끝을 나타내는 위치
            System.out.println(1);
            i = j - p_len; // 이전 부분문자열의 시작을 나타내는 위치

            // 이전 부분문자열의 시작 문자 제외
            if (str[i] == 'A') {
                myArr[0]--;
            }
            if (str[i] == 'C') {
                myArr[1]--;
            }
            if (str[i] == 'G') {
                myArr[2]--;
            }
            if (str[i] == 'T') {
                myArr[3]--;
            }

            // 이전 부분문자열의 끝에서 1문자 추가
            if (str[j] == 'A') {
                myArr[0]++;
            }
            if (str[j] == 'C') {
                myArr[1]++;
            }
            if (str[j] == 'G') {
                myArr[2]++;
            }
            if (str[j] == 'T') {
                myArr[3]++;
            }

            // {‘A’, ‘C’, ‘G’, ‘T’} 4개의 문자가 모두 최소개수를 만족했다면
            if (checkCounting()) {
                answer++; // 만들 수 있는 비밀번호 개수 증가
            }
        }

        System.out.println(answer);

    }

    public static boolean checkCounting() {
        for (int i = 0; i < 4; i++) {
            if (myArr[i] < checkArr[i]) {
                return false;
            }
        }
        return true;
    }

}