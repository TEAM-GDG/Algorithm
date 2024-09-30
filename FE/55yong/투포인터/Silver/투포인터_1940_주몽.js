const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const N = Number(input[0]); // 재료의 개수
const M = Number(input[1]); // 갑옷을 만드는데 필요한 수
const S = input[2].split(" ").sort((a, b) => a - b); // 재료 번호

let answer = 0;
let p1 = 0;
let p2 = N - 1;

while (p1 !== p2) {
  const result = Number(S[p1]) + Number(S[p2]);

  if (result === M) {
    answer++;
  }

  if (result > M) {
    p2--;
  } else {
    p1++;
  }
}
console.log(answer);
