import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        int zeroCount = 0;
        int oneCount = 0;

        if(str.charAt(0) == '0') {
            zeroCount++;
        }
        else {
            oneCount++;
        }

        for(int i = 1; i < str.length(); i++) {
            if(str.charAt(i) != str.charAt(i - 1)) {
               if(str.charAt(i) == '0') {
                   zeroCount++;
               }
               else{
                   oneCount++;
               }
            }
        }

        System.out.println(Math.min(zeroCount, oneCount));
    }
}
