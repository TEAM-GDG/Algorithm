import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        int[] count = new int[10];

        for (char c : str.toCharArray()) {
            int num = c - '0';
            count[num]++;
        }

        int sixNineCount = count[6] + count[9];
        count[6] = sixNineCount / 2 + sixNineCount % 2;

        int maxSetCount = 0;
        for (int i = 0; i < 9; i++) {
            if (count[i] > maxSetCount) {
                maxSetCount = count[i];
            }
        }

        System.out.println(maxSetCount);

        sc.close();
    }
}

