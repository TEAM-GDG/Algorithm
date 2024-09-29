import java.util.*;

class Student {
    // 학생 id
    int id;
    // 추천수
    int recommendations;
    // 추천순서
    int time;

    public Student(int id, int time) {
        this.id = id;
        this.recommendations = 1;
        this.time = time;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 사진틀
        int n = sc.nextInt();
        // 총 추천 횟수
        int totalRecommendations = sc.nextInt();

        // 추천된 학생들을 저장할 배열
        int[] recommendations = new int[totalRecommendations];

        // 추천된 학생들을 배열에 저장
        for (int i = 0; i < totalRecommendations; i++) {
            recommendations[i] = sc.nextInt();
        }

        // 사진틀에 올라간 학생들
        List<Student> frames = new ArrayList<>();
        // 학생 번호로 접근할 수 있는 Map
        // 사진틀에 게시된 학생들을 관리하기 위해 사용
        Map<Integer, Student> studentMap = new HashMap<>();

        // 시간 추척 변수
        int time = 0;

        for (int studentId : recommendations) {
            time++;

            // 이미 게시된 학생이면 추천 횟수만 증가
            if (studentMap.containsKey(studentId)) {
                // Map에서 key:studentId로 학생 데이터 반환
                Student student = studentMap.get(studentId);
                // 해당 학생의 추천수 증가
                student.recommendations++;
            } else { // 게시되지 않은 학생인 경우
                // 사진들이 꽉 찾을 경우
                if (frames.size() == n) {
                    // 사진틀에 있는 학생들 중 가장 추천 횟수가 적거나,
                    // 추천 횟수가 같으면 가장 오래된 학생을 삭제
//                    frames.sort((s1, s2) -> {
//                        // 추천 횟수가 같으면 오래된 순으로 정렬
//                        if (s1.recommendations == s2.recommendations) {
//                            return Integer.compare(s1.time, s2.time);
//                        }
//                        // 추천 횟수가 적은 순으로 정렬
//                        return Integer.compare(s1.recommendations, s2.recommendations);
//                    });
                    // 위 코드를 thenComparingInt를 사용하면 아래와 같이 변경가능
                    frames.sort(Comparator.comparingInt((Student s) -> s.recommendations)
                            .thenComparingInt((Student s) -> s.time));

                    // 정렬 후, 가장 앞에 있는 학생(추천 횟수가 가장 적거나, 오래된 학생)을 삭제
                    // 제거된 학생을 toRemove에 저장
                    Student toRemove = frames.remove(0);
                    // Map에서도 제거된 학생의 id를 사용해 삭제
                    studentMap.remove(toRemove.id);
                }

                // 새로운 학생 추가
                Student newStudent = new Student(studentId, time);
                // 새로운 학생을 사진틀에 게시
                frames.add(newStudent);
                // 학생 번호로 접근할 수 있는 Map에 새로운 학생을 추가
                studentMap.put(studentId, newStudent);
            }
        }

        // 학생 번호를 오름차순으로 정렬

        // 아래 코드를 인텔리제이에서 아래 93번줄 코드로 replace 해줌.
//        frames.sort(new Comparator<>() {
//            @Override
//            public int compare(Student s1, Student s2) {
//                return Integer.compare(s1.id, s2.id);
//            }
//        });
        // 아래 코드를 또 replace해서 96번줄로 바꿔줌
//        frames.sort((s1, s2) -> Integer.compare(s1.id, s2.id));

        frames.sort(Comparator.comparingInt(s -> s.id));

        for (Student student : frames) {
            System.out.print(student.id + " ");
        }
    }
}
