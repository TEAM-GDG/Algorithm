import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Student> photos = new ArrayList<>();
        Student[] students = new Student[101];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (students[num] != null) { // 사진틀 존재
                students[num].cnt++;
            } else {

                Collections.sort(photos);

                if (photos.size() == N) {
                    Student del = photos.remove(N - 1);
                    students[del.cnt] = null;
                }
            }

        }

        photos.sort((o1, o2) -> o1.num - o2.num);
        for (Student s : photos) {
            System.out.print(s.num + " ");
        }

        br.close();
    }

    public static class Student implements Comparable<Student>{
        public int cnt;
        public int timeStamp;
        public int num;

        public Student(int num, int timeStamp, int nickname) {
            this.cnt = num;
            this.timeStamp = timeStamp;
            this.num = nickname;
        }

        @Override
        public int compareTo(Student s2) {
            int resultCnt = s2.cnt - cnt;
            if (resultCnt == 0) {
                return s2.timeStamp - timeStamp;
            }

            return resultCnt;
        }
    }
}

