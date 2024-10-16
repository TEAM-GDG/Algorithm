const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const N = input[0];
const A = input[1]
  .split(" ")
  .map(Number)
  .sort((a, b) => a - b)
  .reverse();
const B = input[2]
  .split(" ")
  .map(Number)
  .sort((a, b) => a - b);
let S = 0;

for (let i = 0; i < N; i++) {
  S += A[i] * B[i];
}

console.log(S);
