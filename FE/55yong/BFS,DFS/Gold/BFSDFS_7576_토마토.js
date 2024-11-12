const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
let input = require("fs").readFileSync(filepath).toString().split("\n");

const ds = [
  [-1, 0],
  [1, 0],
  [0, 1],
  [0, -1],
];
const [M, N] = input[0].split(" ").map(Number);
let queue = [];
let visit = [...Array(N)].map((e) => Array(M).fill(0));
let count = M * N;
let answer;

// 초기 상태 세팅
for (let i = 1; i < input.length; i++) {
  let box = input[i].split(" ").map(Number);

  box.forEach((tomato, pos) => {
    if (tomato === 1) {
      queue.push([i - 1, pos, 0]);
      visit[i - 1][pos] = 1;
      count--;
    } else if (tomato === -1) {
      visit[i - 1][pos] = 1;
      count--;
    }
  });
}

let idx = 0;
while (queue.length != idx) {
  const [x, y, pos] = queue[idx];
  for (let i = 0; i < 4; i++) {
    const xPos = x + ds[i][0];
    const yPos = y + ds[i][1];

    if (xPos < 0 || yPos < 0 || xPos >= N || yPos >= M) continue;
    if (!visit[xPos][yPos]) {
      visit[xPos][yPos] = 1;
      queue.push([xPos, yPos, pos + 1]);
      count--;
    }
  }

  idx++;
  answer = pos;
}

console.log(count ? -1 : answer);
