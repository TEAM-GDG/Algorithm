package BruteForce.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BF_1065_한수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringBuffer sb = new StringBuffer();
        int count = 99;
        int start = 100;
        if (N < 100) {
            sb.append(N);
        }else {
            while (start != N + 1) {
                char[] arr = String.valueOf(start).toCharArray();
                int standard = arr[0]-arr[1];
                boolean check = true;
                for (int i = 1; i < arr.length-1; i++) {
                    if (arr[i] - arr[i + 1] != standard) {
                        check = false;
                        break;
                    }
                }
                if (check) count++;
                start++;
            }
            sb.append(count);
        }
        System.out.println(sb);
        
    }
}
