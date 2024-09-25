package com.rohgibong.book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();

        int sLen = s.length();
        int tLen = t.length();
        int result = 1;

        if(sLen < tLen){
            if(tLen % sLen != 0){
                t += t;
                tLen = tLen*2;
            }
            for(int i = 0; i < tLen/sLen; i++){
                String compareStr = t.substring(sLen*i, sLen*(i+1));
                if(!s.equals(compareStr)){
                    result = 0;
                    break;
                }
            }
        } else if (sLen > tLen){
            if(sLen % tLen != 0){
                s += s;
                sLen = sLen*2;
            }
            for(int i = 0; i < sLen/tLen; i++){
                String compareStr = s.substring(tLen*i, tLen*(i+1));
                if(!t.equals(compareStr)){
                    result = 0;
                    break;
                }
            }
        } else {
            result = s.equals(t) ? 1 : 0;
        }
        System.out.println(result);
    }
}
