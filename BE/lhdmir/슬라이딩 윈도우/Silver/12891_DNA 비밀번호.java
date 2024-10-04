import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    // 부분 문자열에 A,C,G,T가 포함되어야할 최소 갯수 저장
    // 3번째줄의 입력이 2 0 1 1 일때
    // minCount[1] = 1
    static int[] minCount = new int[4];
    // 현재 윈도우 안에있는 DNA 문자열에서 A,C,G,T가 몇번 나왔는지 저장
    // ex) dna = CCTGGATTG, p = 4
    // 초기 윈도우 = CCTG
    // A는 없으므로 currentCount[0] = 0
    // C는 2개 있으므로 currentCount[1] = 2
    // G는 1개 있으므로 currentCount[2] = 1
    // T는 1개 있으므로 currentCount[3] = 1
    static int[] currentCount = new int[4];
    // 현재 윈도우 안에서 minCount의 횟수를 충족한 문자의 갯수
    static int validCharCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        String dna = br.readLine();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            minCount[i] = Integer.parseInt(st.nextToken());
            // 최소로 충족해야할 문자의 갯수가 0이라면 validCharCount 증가
            if (minCount[i] == 0) {
                validCharCount++;
            }
        }

        // 초기 윈도우 세팅
        for (int i = 0; i < p; i++) {
            addChar(dna.charAt(i));
        }

        // 결과값을 저장할 변수
        // 모든 조건을 충족할 경우(validCharCount = 4 일때)
        // result = 1로 설정
        // 아니면 0부터 시작
        int result = (validCharCount == 4) ? 1 : 0;

        for (int i = p; i < s; i++) {
            // 새로운 문자 추가
            addChar(dna.charAt(i));
            // 기존의 문자 삭제
            removeChar(dna.charAt(i - p));

            // 조건을 만족하면 결과값 증가
            if (validCharCount == 4) {
                result++;
            }
        }

        // 결과 출력
        System.out.println(result);
    }

    // 현재 슬라이딩 윈도우에 매개변수 c의 등장횟수 증가 후 최소 등장 조건을 만족했는지 확인
    private static void addChar(char c) {
        int index = getIndex(c);
        currentCount[index]++;
        if (currentCount[index] == minCount[index]) {
            validCharCount++;
        }
    }

    // 현재 슬라이딩 윈도우에 매개변수 c의 최소 등장 조건을 만족중이라면
    // validCharCount를 감소 후 현재 슬라이딩 윈도우에서 매개변수 c의 등장횟수를 감소.
    private static void removeChar(char c) {
        int index = getIndex(c);
        if (currentCount[index] == minCount[index]) {
            validCharCount--;
        }
        currentCount[index]--;
    }

    // 문자에 대응되는 배열 인덱스를 반환 (A=0, C=1, G=2, T=3)
    private static int getIndex(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
            default:
                return -1;
        }
    }
}
