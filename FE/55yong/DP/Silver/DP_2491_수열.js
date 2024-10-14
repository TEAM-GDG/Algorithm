const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const N = input[0];
const arr = input[1].split(" ").map(Number);
let answer = 0;
let asc = 1;
let desc = 1;

for (let i = 0; i < N; i++) {
  if (arr[i] <= arr[i + 1]) {
    asc++;
  } else {
    asc = 1;
  }
  answer = Math.max(asc, answer);
}

for (let i = 0; i < N; i++) {
  if (arr[i] >= arr[i + 1]) {
    desc++;
  } else {
    desc = 1;
  }
  answer = Math.max(desc, answer);
}

console.log(answer);
