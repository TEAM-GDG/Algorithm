package com.rohgibong.book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구현_14719_빗물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());        //첫번째 줄 입력받음
        int H = Integer.parseInt(st.nextToken());       //H: 세로 길이
        int W = Integer.parseInt(st.nextToken());       //W: 가로 길이

        st = new StringTokenizer(br.readLine());        //두번째 줄 입력받음
        int[] arr = new int[W];     //정수형 배열 선언
        for(int i = 0; i < W; i++){
            arr[i] = Integer.parseInt(st.nextToken());      //arr이라는 배열에 하나씩 대입
        }
        int rain = 0;       //빗물 양을 출력할 변수 선언

        for(int i = 1; i < W-1; i++){       //가장 양쪽 끝 (0, W-1을 제외한 부분 반복)
            int leftMax = 0;        //왼쪽 가장 높은부분
            int rightMax = 0;       //오른쪽 가장 높은부분

            for(int j = 0; j < i; j++){
                leftMax = Math.max(arr[j], leftMax);    //leftMax에 왼쪽 가장 높은부분 넣음
            }

            for(int j = i+1; j < W; j++){
                rightMax = Math.max(arr[j], rightMax);  //rightMax에 오른쪽 가장 높은부분 넣음
            }

            if(arr[i]<leftMax && arr[i]<rightMax){      //왼쪽 가장 높은부분과 오른쪽 가장 높은부분이 배열 i번째보다 낮으면
                rain += Math.min(leftMax, rightMax) - arr[i];       //왼쪽과 오른쪽 중 낮은 부분 기준으로 i번째 높이를 뺀 값이 빗물
            }
        }

        System.out.println(rain);       //빗물 출력
    }
}
