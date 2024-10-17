package Greedy.Silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Greedy_1439_뒤집기 {

    public static void main(String[] args) throws IOException {

        // 0001100
        // 1110011
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] S = br.readLine().toCharArray();


        char before = S[0];
        int count = 0;
        for (int i = 1; i < S.length; i++) {
            if (before != S[i]){
                before = S[i];
                count++;
            }
        }
        System.out.println((count/2) + (count%2));
    }
}
