import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int f(String l, String s) {
        char sc[] = s.toCharArray();
        int i = 0;
        // l과 s에서 한문자씩 뽑아서 매칭
        for (char lc : l.toCharArray()) {
            // i가 s의 길이만큼되면 0으로 초기화
            if (i == s.length()) {
                i = 0;
            }
            // 뽑아서 안맞으면 0
            if (sc[i] != lc) {
                return 0;
            }
            i++;
        }
        // 무사히 통과하면 1
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        if (a.length() > b.length()) {
            // 입력받은 문자열을 2배로 만들면 한세트로는 검증이 불가능한 부분을 검증가능
            System.out.println(f(a.repeat(2), b.repeat(2)));
        } else {
            System.out.println(f(b.repeat(2), a.repeat(2)));
        }

    }
}

