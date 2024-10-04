/* 초기 세팅 */
const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input/txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

/* 문제 풀이 */
// 총 일수 N, 계산할 기간 X일
let [n, x] = input[0].split(" ").map(Number);

// 방문자 수를 배열로 변환하여 저장
let visitors = input[1].split(" ").map(Number);

// 최대 방문자 수
let maxVisit = 0;

// 최대 방문자 수를 달성한 기간 수
let count = 0;

// 투 포인터를 위한 start, end, 현재 방문자 수의 합 설정
let start = 0;
let end = start + x - 1;
let sumVisit = 0;
for (let i = start; i <= end; i++) {
  sumVisit += visitors[i];
}

while (end < n) {
  // 현재 방문자 수 < 최대 방문자 수 : pass
  // 현재 방문자 수 = 최대 방문자 수 : count ++
  // 현재 방문자 수 > 최대 방문자 수 : 최대 방문자 수 갱신, count = 1

  if (maxVisit == sumVisit) count += 1;
  else if (maxVisit < sumVisit) {
    maxVisit = sumVisit;
    count = 1;
  }

  sumVisit -= visitors[start];
  start++;
  end++;
  sumVisit += visitors[end];
}

// 결과 출력
if (maxVisit == 0) console.log("SAD");
else {
  console.log(maxVisit);
  console.log(count);
}
