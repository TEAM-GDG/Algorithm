const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const [T, ...N] = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n")
  .map(Number);

// N은 0 ~ 40이고 0또는 1의 출력 횟수를 저장하기 때문에 2차원 배열 (41, 2) 크기로 생성
const dp = Array.from(Array(41), () => new Array(2).fill(0));

// 초기값 설정 (fib(0)일 경우 0이 1번, fib(1)일 경우 1이 1번 호출되기 때문)
dp[0][0] = 1;
dp[1][1] = 1;

// fib(2) ~ fib(40)까지 0과 1이 호출된 횟수를 계산
for (let i = 2; i < 41; i++) {
  // fib(i)에서 0이 호출된 횟수를 구하는 과정, f(i - 1)과 f(i - 2)에서 0이 호출된 횟수의 합임
  dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
  // fib(i)에서 1이 호출된 횟수를 구하는 과정, f(i - 1)과 f(i - 2)에서 1이 호출된 횟수의 합임
  dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
}

// 각 테스트 케이스에 대하여 dp 배열에서 해당 값의 결과 가져오기
const result = N.map((e) => dp[e].join(" "));

console.log(result.join("\n"));
