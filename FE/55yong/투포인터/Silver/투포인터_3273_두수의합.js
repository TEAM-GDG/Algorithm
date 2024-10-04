const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const n = Number(input[0]);
const a = input[1]
  .split(" ")
  .map(Number)
  .sort((a, b) => a - b);
const x = Number(input[2]);

let p1 = 0;
let p2 = n - 1;
let cnt = 0;

while (p1 !== p2) {
  const sum = a[p1] + a[p2];

  if (sum == x) {
    cnt++;
  }

  if (sum > x) {
    p2--;
  } else {
    p1++;
  }
}

console.log(cnt);
