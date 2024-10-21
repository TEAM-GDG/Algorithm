package Greedy.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Greedy_2812_크게만들기_sb {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        char[] num = br.readLine().toCharArray();

        StringBuilder sb = new StringBuilder();
        int toRemove = k;

        for (int i = 0; i < n; i++) {
            char current = num[i];
            // StringBuilder가 비어있지 않고 제거 가능한 숫자가 남아있고 현재 문자가 더 크다면 삭제
            while (sb.length() > 0 && toRemove > 0 && sb.charAt(sb.length() - 1) < current) {
                sb.deleteCharAt(sb.length() - 1);
                toRemove--;
            }
            sb.append(current);
        }

        // 남은 제거해야 할 숫자가 있으면 끝에서부터 제거
        System.out.println(sb.substring(0, n - k));
    }

}
