/* 초기 세팅 */
const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input/txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

/* 문제 풀이 */
// 각 칸의 블록 높이를 배열로 변환하여 저장
const height = input[1].split(" ").map(Number);

// 최종적으로 고일 빗물의 양을 저장할 변수
let answer = 0;

// 각 칸에 대해 빗물이 고일 수 있는 양을 계산하기 위한 반복문
for (let i = 1; i < height.length; i++) {
  let left = -1; // 현재 칸의 왼쪽에서 가장 높은 블록의 높이
  let right = -1; // 현재 칸의 오른쪽에서 가장 높은 블록의 높이
  let min = 0; // 왼쪽과 오른쪽 블록 중 더 낮은 블록의 높이
  let result = 0; // 현재 칸에 고일 수 있는 빗물의 양

  // 현재 칸을 기준으로 왼쪽에서 가장 높은 블록의 높이 찾기
  for (let j = i; j >= 0; j--) {
    // 왼쪽에서 가장 높은 블록을 계속 갱신
    left = Math.max(left, height[j]);
  }

  // 현재 칸을 기준으로 오른쪽에서 가장 높은 블록의 높이 찾기
  for (let j = i; j < height.length; j++) {
    // 오른쪽에서 가장 높은 블록을 계속 갱신
    right = Math.max(right, height[j]);
  }

  // 현재 칸에서 왼쪽과 오른쪽 중 더 낮은 높이를 구함
  min = Math.min(left, right);

  // 빗물이 고일 수 있는 양
  // 더 낮은 블록의 높이 - 현재 칸의 블록 높이
  result += min - height[i];

  // 현재 칸에서 고일 수 있는 빗물의 양을 총 빗물의 양에 더해줌
  answer += result;
}

// 최종적으로 고일 수 있는 빗물의 양 출력
console.log(answer);
