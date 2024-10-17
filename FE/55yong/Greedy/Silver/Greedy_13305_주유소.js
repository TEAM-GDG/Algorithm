const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const n = +input[0];
const distance = input[1].split(" ").map((v) => BigInt(v));
const price = input[2].split(" ").map((v) => BigInt(v));

let curPrice = price[0];
let cost = 0n;

for (let i = 0; i < n - 1; i++) {
  cost += curPrice * distance[i];
  if (curPrice > price[i + 1]) curPrice = price[i + 1];
}

console.log(String(cost));

/**
 * 현재 위치한 주유소보다 기름값이 더 저렴한 가장 가까운 주유소를 오른쪽에서 찾음
 * 더 저렴한 주유소까지만 갈 수 있는 연료를 현재 위치한 주유소에서 주유하고 거기까지 감
 * 이 과정 반복
 */
