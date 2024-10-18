package D_four_week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 뒤집기_1439 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        // 1과 0으로 배열로 만든다.
        String[] arr1 = S.split("1");
        String[] arr2 = S.split("0");

        // split를 사용하면 ""이 생기기 때문에 없애준다.

        // 자바 11 사용시
        // 자바 11에는 toList가 없기 때문에 collect(Collectors.toList())를 사용
        List<String> list1 = Arrays.stream(arr1).filter(item -> !item.isBlank()).collect(Collectors.toList());
        List<String> list2 = Arrays.stream(arr2).filter(item -> !item.isBlank()).collect(Collectors.toList());

        // 자바 17 사용 시
        // List<String> list = Arrays.stream(arr2).filter(item -> !item.isBlank()).toList();

        // 작은 값 출력
        System.out.println(Math.min(list1.size(), list2.size()));
    }
}
