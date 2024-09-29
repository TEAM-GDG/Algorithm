import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().strip();

        // "::" 축약이 사용되었는지 확인
        if (input.contains("::")) {
            // "::"로 문자열을 나눔. 두 번째 매개변수로 -1을 전달하여 빈 문자열도 포함
            // ex) "1::1"       -> ["1", "1"]
            // ex) "::1"        -> ["", "1"]
            // ex) "1::"        -> ["1", ""]
            // ex) "1:1::1:1"   -> ["1:1", "1:1"]
            String[] sides = input.split("::", -1);


            // "::" 이전 부분 처리 (좌측 부분)
            // ex) "1::1"       -> sides[0]은 "1"    -> left는 ["1"]
            // ex) "::1"        -> sides[0]은 ""     -> left는 []
            // ex) "1::"        -> sides[0]은 "1"    -> left는 ["1"]
            // ex) "1:1::1:1"   -> sides[0]은 "1:1"  -> left는 ["1", "1"]
            // 만약 좌측 부분이 비어있으면 빈 배열을 생성 (축약이 맨 앞에서 시작한 경우)
            // 그렇지 않으면 ":"으로 구분하여 나누어 배열로 변환
            String[] left = sides[0].isEmpty() ? new String[0] : sides[0].split(":");

            // "::" 이후 부분 처리 (우측 부분)
            // ex) "1::1"       -> sides[1]은 "1"    -> right는 ["1"]
            // ex) "::1"        -> sides[1]은 "1"    -> right는 ["1"]
            // ex) "1::"        -> sides[1]은 ""     -> right는 []
            // ex) "1:1::1:1"   -> sides[1]은 "1:1"  -> right는 ["1", "1"]
            // 우측 부분이 존재하고 비어 있지 않으면 ":"으로 구분하여 나누어 배열로 변환
            // 그렇지 않으면 빈 배열을 생성 (축약이 맨 끝에서 끝난 경우)
            String[] right = !sides[1].isEmpty() ? sides[1].split(":") : new String[0];

            // 채워야할 0으로 표기된 섹션의 갯수 계산
            // ipv6는 8개의 섹션으로 이루어져 있으며 고로, 8(ipv6) - (left.length + right.length)
            int fillZeroCount = 8 - (left.length + right.length);

            List<String> fullParts = new ArrayList<>();

            // 좌측 섹션 추가
            for (String s : left) fullParts.add(s);

            // 남은 섹션을 "0000"으로 채움
            for (int i = 0; i < fillZeroCount; i++) fullParts.add("0000");

            // 우측 섹션 추가
            for (String s : right) fullParts.add(s);

            // 모든 섹션을 ":"으로 연결하여 input에 다시 저장
            input = String.join(":", fullParts);
        }

        // 복원된 IPv6 주소를 ":"으로 다시 나누어 각 섹션으로 분리
        String[] ipv6Part = input.split(":");
        StringBuilder result = new StringBuilder();

        // 각 부분을 4자리로 맞추고, 빈 자리는 '0'으로 채움
        for (String part : ipv6Part) {
            // String.format("%4s", part) 4자리로 맞추되, 앞부분을 공백으로 채움
            // replace(' ', '0') 공백을 '0'으로 대체
            // 마지막에 append(":")으로 (":") 추가
            result.append(String.format("%4s", part).replace(' ', '0')).append(":");
        }

        result.setLength(result.length() - 1);

        System.out.println(result);
    }
}
