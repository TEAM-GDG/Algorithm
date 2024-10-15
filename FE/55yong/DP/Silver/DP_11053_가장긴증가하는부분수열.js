const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const N = input[0];
const arr = input[1].split(" ").map(Number);

const dp = new Array(N).fill(0);

for (let i = 0; i < N; i++) {
  let max = 0;
  for (let j = 0; j < i; j++) {
    if (arr[i] > arr[j] && dp[j] > max) {
      max = dp[j];
    }
  }
  dp[i] = max + 1;
}
console.log(Math.max(...dp));
