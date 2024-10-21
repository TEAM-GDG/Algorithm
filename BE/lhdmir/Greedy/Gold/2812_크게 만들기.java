import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String number = br.readLine();

        int toRemove = k;
        // 해당 문제는 앞에서 최대한 많은 수의 작은 수를 지워야한다.
        // 1231234 일 경우 앞자리가 제일 큰 수가 되게 만들어야 유리해진다.
        // 해당 숫자에서는 3이 제일 큰 숫자이므로 toRemove가 남아있는 동안은
        // 3이 앞자리가 될수있도록 3보다 작은 앞에 있는 수들은 모두 지우는게 큰 수를 만들기에 유리하다.
        // 그렇기때문에 스택을 사용해 제일 위의 값과 현재값을 비교해 제일 위의값이 현재값보다 작다면 삭제하고
        // 현재값을 스택에 넣어준다(교체)
        
        // 제일 앞자리가 아닌 중간에 위치한 수들도 마찬가지로 최대한 큰 수로 유지해주는게 좋다.
        // 위와 마찬가지의 교체로직을 적용해 중간에 들어갈 값들도 toRemove가 남아있는동안은 
        // 현재값과 스택의 최상단값을 비교해서 작은값들은 삭제하고 현재값을 넣어준다.
        
        // 만약 위 과정을 다 진행하고도 toRemove값이 남아있다면 제일 앞과 중간을 수정할 기준이 없기때문에
        // 제일 뒤에서부터 toRemove가 0이될때까지 삭제를 반복한다.
        Stack<Integer> stack = new Stack<>();


        int current;
        for (int i = 0; i < n; i++) {
            current = Integer.parseInt(String.valueOf(number.charAt(i)));

            // 스택이 비어있지 않다면
            // 지워야할 숫자가 0개이상 있다면
            // 현재 숫자가 스택의 마지막보다 크다면
            while (!stack.isEmpty() && toRemove > 0 && stack.peek() < current) {
                // 스택의 마지막숫자를 제거후 지워야할 숫자의 갯수를 감소
                stack.pop();
                toRemove--;
            }
            // 현재숫자를 스택에 추가
            stack.push(current);
        }

        // 만약 K개의 숫자를 제거하지 못했으면 뒤에서부터 남은 숫자를 제거
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n - k; i++) {
            result.append(stack.get(i));
        }

        System.out.println(result);
    }
}
