const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

let [N, K] = input.shift().split(" ");
const A = input.reverse().map(Number);
let cnt = 0;
let i = 0;

while (i < N) {
  if (K - A[i] >= 0) {
    K = K - A[i];
    cnt++;
  } else {
    i++;
  }
}

console.log(cnt);
