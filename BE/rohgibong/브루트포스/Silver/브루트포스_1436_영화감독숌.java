import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 브루트포스_1436_영화감독숌 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 666;
        int count = 0;

        while(true){
            if(String.valueOf(result).contains("666")){
                count++;
                if(count == N) break;
            }
            result++;
        }
        System.out.println(result);
    }
}
