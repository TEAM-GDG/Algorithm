const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const [N, X] = input[0].split(" ").map((item) => Number(item));
const rate = input[1].split(" ").map((item) => Number(item));
let cur = 0;
let max = 0;
let cnt = 0;

for (let i = 0; i < N - X + 1; i++) {
  // 초기 설정
  if (i == 0) {
    for (let j = 0; j < X; j++) {
      cur += rate[j];
    }
    max = cur;
    cnt = 1;
  } else {
    // 그 다음부터 현재 값 - 이전 값 + 다음 값
    cur = cur - rate[i - 1] + rate[i + X - 1];

    // 만약 현재값 == 최대값인 경우 카운트 1 증가
    if (cur == max) {
      cnt += 1;
    } else if (cur > max) {
      // 아닌 경우 최대값은 현재값이 되고 카운트는 1로 초기화
      max = cur;
      cnt = 1;
    }
  }
}

console.log(max === 0 ? "SAD" : [max, cnt].join("\n"));
