package 구현.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 구현_1475_방번호 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] ch = br.readLine().toCharArray();
        int[] num = new int[10];

        
        int MIN = Integer.MIN_VALUE;

        for (int i = 0; i < ch.length; i++) {
            if (ch[i]  == '6'){
                num[9]++;
            }else {
                num[(int)ch[i]-'0']++;
            }
        }

        int nine = num[9]/2;
        if (num[9]%2==1){
            nine++;
        }
        num[9] = nine;

        for (int i =0; i<10; i++){
            MIN = Math.max(MIN,num[i]);
        }
        System.out.println(MIN);

    }
}

