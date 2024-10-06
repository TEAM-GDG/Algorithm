const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";

const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const N = input[0];
const arr = input[1]
  .split(" ")
  .map(Number)
  .sort((a, b) => a - b);

let closets = [];
let left = 0;
let right = N - 1;
let min = Infinity;

while (left !== right) {
  let sum = arr[left] + arr[right];

  if (Math.abs(sum) < min) {
    min = Math.abs(sum);
    closets = [arr[left], arr[right]];
  }

  if (sum >= min) {
    right--;
  } else {
    left++;
  }
}

console.log(closets.join(" "));
