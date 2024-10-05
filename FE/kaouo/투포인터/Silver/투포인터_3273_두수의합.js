/* 초기 세팅 */
const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

/* 문제 풀이 */
// 입력값 처리
const N = Number(input[0]);
const arr = input[1]
  .split(" ")
  .map(Number)
  .sort((a, b) => a - b); // 오름차순 정렬
const X = Number(input[2]);

// 변수 선언
let count = 0;

// 배열 시작 인덱스
let i = 0;

// 배열 끝 인덱스
let j = N - 1;

// 끝값부터 차례대로 비교
while (i < j) {
  const sum = arr[i] + arr[j];

  // 두 수의 합이 X와 같을 때
  if (sum == X) {
    count++;
    i++; // 왼쪽 포인터를 오른쪽으로 한 칸 이동
    j--; // 오른쪽 포인터를 왼쪽으로 한 칸 이동
  }
  // 두 수의 합이 X보다 작을 때
  else if (sum < X) {
    i++;
  }
  // 두 수의 합이 X보다 클 때
  else {
    j--;
  }
}

// 쌍의 개수 출력
console.log(count);
