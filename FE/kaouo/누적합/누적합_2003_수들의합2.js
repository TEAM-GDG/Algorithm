/* 초기 세팅 */
// 파일 읽기 기능 사용
const fs = require("fs");

// 실행 환경에 따라 입력 경로 다르게 설정
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";

// 입력을 읽고, 문자열로 변환한 뒤, 공백을 제거하고, 각 문자를 배열로 나눔
const input = fs.readFileSync(filepath).toString().trim().split("\n");

/* 문제 풀이 */
// 수열의 길이(N), 목표 합(M)
let [N, M] = input[0].split(" ").map(Number);

// 수열 A의 각 값을 배열로 변환하여 저장
let A = input[1].split(" ").map(Number);

// 결과를 저장할 변수
let result = 0;

// 다중 포인터를 사용하여 수열에서 연속된 부분합 계산
let left = 0;
let right = 0;

// 초기 상태에서 첫 번째 요소를 부분합으로 설정
let sum = A[left];

// 두 포인터가 수열의 범위를 넘지 않는 동안 반복
while (left < N && right < N) {
  // 현재 부분합이 M과 같다면
  if (sum === M) {
    result++;
    sum += A[++right];
  }
  // 현재 부분합이 M보다 작다면
  else if (sum < M) sum += A[++right];
  // 현재 부분합이 M보다 크다면
  else if (sum > M) sum -= A[left++];
}

// 결과 출력
console.log(result);
