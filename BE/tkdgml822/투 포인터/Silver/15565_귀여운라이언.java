package two_week.Silver;

import java.io.*;
import java.util.*;

public class 귀여운라이언_15565 {
    public static void main(String[] args) throws IOException {
        /* BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        br.close();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 1;
        int max = 0;
        List<Integer> list = new ArrayList<>();

        while (start < end) {

            if (arr[start] == 1) {
                if (end == N) {
                    break;
                }
                int cnt = 1;
                end = start + 1;

                for (int i = 1; i < N; i++) {

                    if (arr[end++] == 1) {
                        cnt++;
                    }

                    if (cnt == K) {
                        max = i + 1;
                        break;
                    }
                }
                list.add(max);

            }
            start++;

        }

        if (list.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(Collections.min(list));
        }
    }
    */
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();	//라이언 인형 위치 저장 리스트
        st = new StringTokenizer(br.readLine()," ");
        //인형 정보 저장
        for(int i=0;i<N;i++){
            int n = Integer.parseInt(st.nextToken());
            if(n == 1){		//라이언 인형일 때
                list.add(i);
            }
        }
        //라이언 인형의 개수가 K보다 작을 때
        if(list.size()<K){
            bw.write("-1");
        }else{		//라이언 이형의 개수가 K 이상일 때
            int result = Integer.MAX_VALUE;
            //첫 번째 라이언부터 탐색 진행
            for(int i=0;i<=list.size()-K;i++){
                int start = list.get(i);
                int end = list.get(i+K-1);
                result = Math.min(result, end-start+1);
            }
            //최소 크기 BufferedWriter 저장
            bw.write(String.valueOf(result));
        }
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
}
