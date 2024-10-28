const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const T = input.shift();

for (let i = 0; i < T * 2; i += 2) {
  let answer = 0;
  let max = 0;
  let N = input[i];
  let arr = input[i + 1].split(" ").map(Number);

  for (let j = N - 1; j > -1; j--) {
    if (arr[j] > max) max = arr[j];
    else if (arr[j] < max) answer += max - arr[j];
  }
  console.log(answer);
}
