import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class 3107_IPv6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ipV6 = br.readLine();
        br.close();

        int subIndex, loopInt;
        int len = ipV6.length();
        StringBuilder sb = new StringBuilder();

        // "::"가 있을시
        if (ipV6.contains("::")) {
            subIndex = ipV6.indexOf("::"); // :: 가 있는 index를 찾는다.
            // 첫번째에 있는 경우 예를 들어 ::1, ::, ::2:2:
            if (subIndex == 0) {
                String str = ipV6.substring(2, len); // ::2:2: -> 2:2
                String[] split = str.split(":"); // ":" 제거
                loopInt = 8 - split.length; // 추가해야될 "0000:" 구함
                // 구한 수 만큼 반복
                sb.append("0000:".repeat(loopInt));

                // 구한 후 4자리가 아닌 그룹일시 0을 더해준다. 예) 12 -> 0012
                zeroPlus(split, sb);
                // 1:2:3:4:::5 같은 경우
            } else {
                // 자바 11은 toList()을 지원하지 않기 때문에 collect을 사용
                ArrayList<String> leftSplit = Arrays.stream(ipV6.substring(0, subIndex).split(":")).collect(Collectors.toCollection(ArrayList::new));
                ArrayList<String> rightSplit = Arrays
                        .stream(ipV6.substring(subIndex, len).split(":"))
                        .filter(item -> !item.isBlank()).collect(Collectors.toCollection(ArrayList::new));

                int leftLen = leftSplit.size();
                int rightLen = rightSplit.size();

                // 전체길이가 8이 아닌 경우
                if (leftLen + rightLen != 8) {
                    // 추가 해야되는 그룹을 구함
                    int loopNum = 8 - (leftLen + rightLen);
                    // 만약 왼쪽 그룹중 하나가 4자리 수 이하이면 0을 추가하는 메서드
                    zeroPlus(leftSplit, leftSplit.size());
                    leftSplit.forEach(item -> sb.append(item).append(":")); // StringBuilder에 넣기
                    // 부족한 길이 "0000" 추가
                    for (int h = 0; h < loopNum; h++) {
                        sb.append("0000").append(":");
                    }
                    zeroPlus(rightSplit, rightSplit.size());
                    rightSplit.forEach(item -> sb.append(item).append(":"));

                }

            }
            // "::" 가 없을시
        } else {
            String[] ip = ipV6.split(":", -1);
            zeroPlus(ip, sb);
        }

        System.out.println(sb.delete(39, 40));
    }

    // 만약 그룹중 하나가 4자리 수 이하이면 0을 추가하는 메서드
    private static void zeroPlus(ArrayList<String> ipV6, int size) {
        for (int i = 0; i < size; i++) {
            if (ipV6.get(i).length() != 4) {
                int zero = 4 - ipV6.get(i).length();
                ipV6.set(i, "0".repeat(zero) + ipV6.get(i));
            }
        }
    }

    private static void zeroPlus(String[] ip, StringBuilder sb) {
        for (String s : ip) {
            if (s.length() != 4) {
                sb.append("0".repeat(4 - s.length())).append(s).append(":");
            } else {
                sb.append(s).append(":");
            }

        }
    }
}

