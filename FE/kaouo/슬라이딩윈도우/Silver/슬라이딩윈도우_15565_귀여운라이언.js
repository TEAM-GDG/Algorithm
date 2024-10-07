/* 초기 세팅 */
const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

/* 문제 풀이 */
// 인형의 개수 N, 라이언 인형의 최소 개수 K
// N개의 인형 정보를 배열로 가져오기
const [[N, K], dolls] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map(Number));

// 라이언 인형 개수 저장하는 변수
let count = 0;

// K개 이상의 라이언 인형이 있는
// 가장 작은 구간의 길이를 저장하는 변수
let minCount = Infinity;

// 구간을 탐색하기 위한 변수
let i = 0;
let j = 0;

// 끝 포인터 j가 배열의 끝까지 탐색
while (j < N) {
  // 인형이 라이언(1)이면 카운트 증가
  if (dolls[j] === 1) {
    count++;
  }

  // 라이언 인형이 K개가 되었을 때, 구간의 최소 길이를 계산
  while (count === K) {
    // 현재 구간에서 가장 작은 길이를 기록
    minCount = Math.min(minCount, j - i + 1);

    // 시작 포인터가 가리키는 인형이 라이언(1)이면 카운트 감소
    if (dolls[i] === 1) {
      count--;
    }
    i++;
  }

  // 끝 포인터를 오른쪽으로 한 칸 이동하여 구간을 확장
  j++;
}

// 결과 출력
console.log(minCount === Infinity ? -1 : minCount);
