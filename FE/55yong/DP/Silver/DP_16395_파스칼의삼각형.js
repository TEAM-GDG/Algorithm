const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const [n, k] = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split(" ")
  .map(Number);

const dp = Array.from(Array(n), () => new Array(k).fill(0));

for (let i = 0; i < n; i++) {
  dp[i][0] = 1;
}

for (let i = 1; i < n; i++) {
  for (let j = 1; j < k; j++) {
    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
  }
}

console.log(dp[n - 1][k - 1]);
