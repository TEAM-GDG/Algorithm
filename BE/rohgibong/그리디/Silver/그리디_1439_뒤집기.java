import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 그리디_1439_뒤집기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        char compareChar = S.charAt(0);
        int num1 = compareChar == '0' ? 1 : 0;
        int num2 = compareChar == '1' ? 1 : 0;

        for(int i = 1; i < S.length(); i++) {
            if(compareChar != S.charAt(i)) {
                if(S.charAt(i) == '0'){
                    num1++;
                } else {
                    num2++;
                }
                compareChar = S.charAt(i);
            }
        }

        System.out.println(Math.min(num1, num2));
    }
}
