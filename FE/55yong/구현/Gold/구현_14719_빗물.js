const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const [h, w] = input[0].split(" ");
const rain = input[1].split(" ").map(Number);
let water = 0;

for (let i = 0; i < rain.length; i++) {
  // 현재 블록에서 자신을 포함한 왼쪽과 오른쪽에서 가장 높은 블록들 중
  // 더 작은 블록의 높이가 현재 블록에 채워질 물의 높이가 됨
  const max = Math.max(...rain.slice(0, i + 1));
  const min = Math.max(...rain.slice(i));
  const minBlock = Math.min(max, min);
  water += minBlock - rain[i];
}

console.log(water);
