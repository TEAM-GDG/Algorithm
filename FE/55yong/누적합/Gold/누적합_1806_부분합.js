const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const [N, S] = input[0].split(" ").map(Number);
const arr = input[1].split(" ").map(Number);
let min = Infinity;
let sum = 0;
let i = 0,
  j = 0;

while (j < N) {
  sum += arr[j];

  while (sum >= S) {
    sum -= arr[i];
    min = Math.min(min, j - i + 1);
    i++;
  }
  j++;
}

console.log(min === Infinity ? 0 : min);

/**
 * 1. arr[j]를 sum에 누적
 * 2. sum이 S 이상일 때 sum이 S 미만이 될 때 까지 i를 증가시키며
 * 3. sum이 S 이상일 때의 길이를 갱신
 */
