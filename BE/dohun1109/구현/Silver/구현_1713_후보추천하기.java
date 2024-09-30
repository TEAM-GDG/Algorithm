package 구현.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 구현_1713_후보추천하기 {
    static ArrayList<Student> frame = new ArrayList<>();  // 액자에 있는 학생 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());    // 사진 틀의 개수
        int studentsNum = Integer.parseInt(br.readLine());    // 전체 추천 횟수
        st = new StringTokenizer(br.readLine());    // 추천 순서
        int[] recommendName = new int[studentsNum];

        for (int i = 0; i < studentsNum; i++) {
            recommendName[i] =  Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < studentsNum; i++) {
            int name = recommendName[i];
            
            // 중복된 학생이 있는지 확인하고 중복된 경우 추천 수만 증가시킴
            if (incrementRecommendationIfExists(name)) {
                continue;  // 중복된 경우는 추천 수만 증가하고 넘어감
            }

            // 프레임이 꽉 차지 않은 경우
            if (frame.size() < N) {
                frame.add(new Student(name, i));  // 새로운 학생 추가
            } else {
                // 프레임이 꽉 찼을 경우 추천 수가 가장 적고 오래된 학생을 제거
                frame.sort(Comparator.comparing(Student::getRecommendNum)
                        .thenComparing(Student::getTime));
                frame.remove(0); // 가장 오래된 학생 제거
                frame.add(new Student(name, i));  // 새로운 학생 추가
            }
        }

        // 학생의 이름을 기준으로 정렬하여 출력
        frame.sort(Comparator.comparingInt(s -> s.name));
        StringBuilder sb = new StringBuilder();
        for (Student student : frame) {
            sb.append(student.name).append(" ");
        }
        System.out.println(sb);
    }

    // 중복된 이름이 있으면 추천 수를 증가시키고, true 반환 (중복 처리)
    public static boolean incrementRecommendationIfExists(int name) {
        for (Student student : frame) {
            if (student.name == name) {
                student.addRecommend();  // 추천 수 증가
                return true;  // 중복된 학생 처리 완료
            }
        }
        return false;  // 중복되지 않음
    }

    static class Student {
        int recommendNum = 0;
        int name;
        int time = 0;  // 입력 순서를 저장해 오래된 학생을 찾기 위함

        public Student(int name, int time) {
            this.name = name;
            this.recommendNum = 1;  // 처음 들어오면 추천 수 1로 시작
            this.time = time;  // 입력된 순서를 기반으로 시간 설정
        }

        public int getRecommendNum() {
            return recommendNum;
        }

        public int getTime() {
            return time;
        }

        public void addRecommend() {
            this.recommendNum++;  // 추천 수 증가
        }
    }
}