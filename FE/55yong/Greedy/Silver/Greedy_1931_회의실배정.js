const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const T = input.shift();
const L = input.map((item) => item.split(" ").map(Number));

L.sort((a, b) => a[1] - b[1] || a[0] - b[0]);

let cnt = 0;
let lastEnd = 0;

for (const [start, end] of L) {
  if (start >= lastEnd) {
    lastEnd = end;
    cnt++;
  }
}

console.log(cnt);
