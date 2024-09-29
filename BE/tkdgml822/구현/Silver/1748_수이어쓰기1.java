import java.io.*;

public class Main {
    public static int nineSquare(int count) {
        int sum = 1;
        for (int i = 1; i < count - 1; i++) {
            sum *= 10;
        }

        return sum * 9;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int M = Integer.parseInt(br.readLine());
        int N = M;
        int count = 0;
        int sum = 0;
        br.close();

        if (M <= 9) {
            bw.write(String.valueOf(M));
        }
        else {
            // 몇 자리수인지 구하기
            while (M > 0) {
                M /= 10;
                count++;
            }

            // 구한 자리수 구하기
            int num = 10;
            for (int i = 1; i < count - 1; i++) {
                num *= 10;
            }

            // 자기 자신 포함 + 1
            int a = N - num + 1;

            // 끝자리 수 구하기 완료
            sum = a * count;

            int copyCount = count;
            for (int i = 0; i < count - 1; i++) {
                sum += (copyCount- 1) * nineSquare(copyCount);
                copyCount--;
            }

            bw.write(String.valueOf(sum));

        }

        bw.close();

    }

}
