const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const N = require("fs").readFileSync(filepath).toString().trim();

const dp = new Array(1000001).fill(0);

dp[0] = 1;
dp[1] = 1;

for (let i = 2; i <= N; i++) {
  dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
}

console.log(dp[N]);
