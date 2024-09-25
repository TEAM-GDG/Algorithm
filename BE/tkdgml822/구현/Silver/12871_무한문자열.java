public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        String s = br.readLine();
        String t = br.readLine();

        // 만약 ab, abab가 입력 되었다면 2, 4가 입력
        int num1 = s.length();
        int num2 = t.length();
        int result = 0;

        /**
         *  최소 공배수 구하기
         *  2와 4의 공배수라고 하면 4라는 최소 공배수가 나온다.
         */
        int min = (num1 * num2) / gcd(Math.max(num1, num2), Math.min(num1, num2));

        /**
         *  최소 공배수의 길이가 되도록 s 더하기
         *  ab라고 치면 abab가 될때까지 더한다.
         */
        String tmp = s;
        while (true) {
            if (s.length() == min)
                break;

            s += tmp;
        }

        // 최소 공배수의 길이가 되도록 t 더하기
        tmp = t;
        while (true) {
            if (t.length() == min)
                break;
            t += tmp;
        }

        // 둘이 비교해서 같으면 1
        if (s.equals(t)) result = 1;
        System.out.println(result);
    }

    // 최대 공배수 구하기
    static int gcd(int max, int min) {
        while (min != 0) {
            int remainder = max % min;
            max = min;
            min = remainder;
        }

        return max;
    }
}