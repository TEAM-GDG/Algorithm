const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const [N, M] = input[0].split(" ").map((item) => Number(item));
const A = input[1].split(" ").map((item) => Number(item));
let cnt = 0;

for (let i = 0; i < N; i++) {
  // 누적합을 담을 변수
  let sum = A[i];
  // 인덱스 값 지정
  let idx = i;
  // sum 값이 M보다 작거나 인덱스 끝까지 도달하기 전까지 반복
  while (sum < M && idx < N - 1) {
    // 인덱스 +1 해서
    idx++;
    // A[i] + A[i + 1] + A[i + 2] ... A[i + n]이 되도록
    sum += A[idx];
  }
  // M값이랑 같아지면 cnt +1
  if (sum === M) cnt++;
}

console.log(cnt);
