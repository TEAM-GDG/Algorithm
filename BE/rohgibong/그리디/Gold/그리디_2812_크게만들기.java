import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 그리디_2812_크게만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 자리수
        int K = Integer.parseInt(st.nextToken());   // 제거할 숫자 개수

        String num = br.readLine();
        Stack<Character> stack = new Stack<>();     // 스택을 사용해 숫자를 저장

        for (int i = 0; i < N; i++) {
            char current = num.charAt(i);

            while (!stack.isEmpty() && K > 0 && stack.peek() < current) {       // 현재 숫자가 스택의 top보다 크고, 제거할 숫자가 남아 있으면
                stack.pop();
                K--;  // 제거한 숫자 감소
            }

            stack.push(current);    // 현재 숫자를 스택에 추가
        }

        while (K > 0) {        // K가 남아 있는 경우 뒤에서부터 제거
            stack.pop();
            K--;
        }

        StringBuilder result = new StringBuilder();     // 스택에 있는 숫자를 결과 문자열로 변환
        for (char c : stack) {
            result.append(c);
        }

        System.out.println(result.toString());
    }
}