import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        br.close();

        int[] indexAr = new int[9];

        // 각 숫자의 0~9 만큼 배열의 index 크기 증가
        for (int i = 0; i < N.length(); i++) {

            // char을 int로 변환
            // Character.getNumericValue()을 써도 되긴함
            int n  = N.charAt(i) - '0';

            // 6과 9는 같은 숫자로 침
            if (n == 9) {
                indexAr[6]++;
            }
            else {
                indexAr[n]++;
            }
        }


        // 인덱스 6이 2거나 클때
        if (indexAr[6] >= 2) {
            /**
             * 짝수면 2를 나눔
             * 예를 들어 9966를 입력했을 시
             * 6의 인덱스가 4가된다.
             * 하지만 6과 9는 뒤집어서 사용이 가능하므로 총 2세트가 필요하므로 2를 나눈다.
             * 그러므로 4는 -> 2가 된다.
             */
            if (indexAr[6] % 2 == 0) {
                indexAr[6] = indexAr[6] / 2;
            }

            /**
             * 홀수면 2를 나눈 후 1를 더한다.
             * 예를 들어 99669를 입력했을 시
             * 6의 인덱스는 5가된다.
             * 그럼 총 3세트가 필요하다.
             * 그런데 2로 나눌 시 2가 나오기 때문에 1을 더해준다.
             */
            else {
                indexAr[6] = (indexAr[6] / 2) + 1;
            }
        }


        // 제일 큰 숫자를 가져온다.
        int set = indexAr[0];
        for (int i = 1; i < indexAr.length; i++) {
            if (set < indexAr[i]) {
                set = indexAr[i];
            }
        }


        // 스트림 사용
        // int set = Arrays.stream(indexAr).max().getAsInt();


        // 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(set));

        bw.close();
    }
}
