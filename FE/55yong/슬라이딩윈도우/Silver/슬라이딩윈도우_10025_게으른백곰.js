const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map(Number));

const [N, K] = input.shift();
const coord = input.map((v) => v);

function solution(N, K, coord) {
  let MAX = 0;
  const arr = Array.from({ length: 1000001 }).fill(0);
  for (let [ice, place] of coord) {
    arr[place] = ice;
    MAX = MAX < place ? place : MAX;
  }

  let result = 0;
  for (let i = 0; i <= K; i++) result += arr[i] ?? 0;

  let answer = 0;
  for (let j = 0; j < MAX; j++) {
    let [left, right] = [j - K - 1, j + K + 1];
    result = result - (arr[left] ?? 0) + (arr[right] ?? 0);
    answer = Math.max(answer, result);
  }
  return answer;
}

console.log(solution(N, K, coord));
