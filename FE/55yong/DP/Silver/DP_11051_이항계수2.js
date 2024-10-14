const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const [n, k] = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split(" ")
  .map(Number);

let dp = Array.from(Array(n + 1), () => new Array(n + 1).fill(0));

dp[0][0] = 1;
dp[1][0] = 1;
dp[1][1] = 1;

for (let i = 2; i <= n; i++) {
  for (let j = 0; j <= i; j++) {
    if (j == 0 || j == i) {
      dp[i][j] = 1;
    } else dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007;
  }
}

console.log(dp[n][k]);
