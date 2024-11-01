import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 브루트포스_1476_날짜계산 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int startE = 1;
        int startS = 1;
        int startM = 1;
        int result = 1;

        while(true){
            if(E == startE && S == startS && M == startM) break;

            startE++; startS++; startM++; result++;
            if(startE > 15) startE = 1;
            if(startS > 28) startS = 1;
            if(startM > 19) startM = 1;
        }

        System.out.println(result);
    }
}
