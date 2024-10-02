/* 초기 세팅 */

// 파일 읽기 기능 사용
const fs = require("fs");

// 실행 환경에 따라 입력 경로 다르게 설정
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";

// 입력을 읽고, 문자열로 변환한 뒤, 공백을 제거하고, 각 문자를 배열로 나눔
const input = fs.readFileSync(filepath).toString().trim().split("ㄴ\n");

/* 문제 풀이 */

// 재료의 개수 N
const n = Number(input[0]);

// 갑옷을 만드는 데 필요한 수 M
const m = Number(input[1]);

// 주어진 N개의 재료 고유 번호를 배열로 변환하고
// 각 재료를 숫자로 변환하여 저장함
const materials = input[2].split(" ").map(Number);

// 재료 고유 번호들을 오름차순으로 정렬
materials.sort((a, b) => a - b);

// 갑옷을 만들 수 있는 경우의 수 저장하는 변수
let count = 0;

// 다중 포인터 사용
let left = 0;
let right = n - 1;

// 두 포인터가 만나기 전까지 반복
while (left < right) {
  const sum = materials[left] + materials[right];

  // 두 재료의 합이 같다면
  if (sum === m) {
    count++;
    left++;
    right--;
  }

  // 두 재료의 합이 m보다 크다면
  else if (sum > m) {
    right--;
  }

  // 두 재료의 합이 m보다 작다면
  else {
    left++;
  }
}

// 결과 출력
console.log(count);
