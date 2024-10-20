import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();

        // '-'를 기준으로 문자열을 분리
        String[] subtractedParts = expression.split("-");

        int result = 0;

        // 첫 번째 부분은 더해준다
        // -가 나오기 전은 할수있는것이 없기 때문에 모두 더하기 처리
        String[] firstPart = subtractedParts[0].split("\\+");
        for (String num : firstPart) {
            result += Integer.parseInt(num);
        }

        // 두 번째 이후 부분은 모두 빼준다
        // -가 나온 이후로는 모든 수들을 더하고 한꺼번에 빼면 되는데
        // 이렇게되면 모든 -이후의 수들은 모두 빼면되는거라 전부 빼기 처리
        for (int i = 1; i < subtractedParts.length; i++) {
            String[] addParts = subtractedParts[i].split("\\+");
            for (String num : addParts) {
                result -= Integer.parseInt(num);
            }
        }

        System.out.println(result);
    }
}

