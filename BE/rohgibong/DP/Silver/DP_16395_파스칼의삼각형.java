import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_16395_파스칼의삼각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        System.out.println(calNumber(n, k));
    }

    public static int calNumber(int n, int k){
        if(n==k || k==1){
            return 1;
        } else {
            return calNumber(n-1, k-1) + calNumber(n-1, k);
        }
    }
}
