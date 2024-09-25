import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        // 입력받은 문자열에서 해당하는 숫자가 몇번 나왔는지 카운트할 배열
        int[] count = new int[10];

        // 숫자들을 하나씩 분리해서 해당하는 수를 카운트
        for (char c : str.toCharArray()) {
            // ex) c = '7'일때 ASCII 코드에서 '0'(48)을 빼면 7이 나온다.
            int num = c - '0';
            count[num]++;
        }

        // 6과 9는 하나로 처리할것이라서 하나로 합침.
        int sixNineCount = count[6] + count[9];
        // 66999가 들어왔을때 sixNineCount는 5가 되고 2로 나눠서 세트를 계산
        // 그럼 2세트가 만들어지고 1이 남는다 그래서 나머지 계산한 값을 더해줌.
        count[6] = sixNineCount / 2 + sixNineCount % 2;

        // 제일 많은 세트를 저장할 변수
        int maxSetCount = 0;
        // 반복문을 통해 count 배열에서 가장 큰 값을 불러온다.
        // count 배열에서 가장 큰 값이 곧 필요한 세트의 수
        for (int i = 0; i < 9; i++) { // 9와 6은 동일하게 처리했기때문에 8까지만
            if (count[i] > maxSetCount) {
                maxSetCount = count[i];
            }
        }

        System.out.println(maxSetCount);

        sc.close();
    }
}

