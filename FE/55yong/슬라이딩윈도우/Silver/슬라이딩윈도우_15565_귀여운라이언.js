const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const [n, k] = input[0].split(" ").map(Number);
const info = input[1].split(" ").map(Number);

let cnt = 0;
let min = Infinity;
let i = 0;
let j = 0;

while (j < n) {
  // j 인덱스의 값이 1일때까지 cnt 증가
  if (info[j] === 1) cnt++;

  // 만약 cnt가 k값이 되면 반복
  while (cnt === k) {
    // i 인덱스의 값이 1일때 까지 cnt 감소
    if (info[i] === 1) cnt--;

    // j인덱스 - i + 1인덱스 해서 나온 값이랑 min이랑 비교
    min = Math.min(min, j - i + 1);
    i++;
  }
  j++;
}

console.log(min === Infinity ? -1 : min);
