const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const [T, ...n] = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n")
  .map(Number);

// 메모이제이션을 위한 배열 초기화
const memo = [...Array(11)];

memo[1] = 1; // 1을 만들 수 있는 경우의 수(1개)
memo[2] = 2; // 2를 만들 수 있는 경우의 수(2개)
memo[3] = 4; // 3을 만들 수 있는 경우의 수(3개)

// 4 ~ 10까지의 값을 계산하여 메모 배열에 저장
for (let i = 4; i < 11; i++) {
  memo[i] = memo[i - 1] + memo[i - 2] + memo[i - 3];
}

for (let i = 0; i < T; i++) {
  console.log(memo[n[i]]);
}
