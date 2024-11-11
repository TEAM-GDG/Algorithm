const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

// 상, 우, 하, 좌
const ds = [
  [-1, 0],
  [0, 1],
  [1, 0],
  [0, -1],
];
const [N, M] = input.shift().split(" ").map(Number);
const [startX, startY, direction] = input.shift().split(" ").map(Number);
const graph = input.map((v) => v.split(" ").map(Number));
const visited = Array.from(Array(N), () => Array(M).fill(0));

const maxDs = ds.length;
const getPositivePosition = (num) => (num < 0 ? num + maxDs : num);

let count = 0;
const queue = [[startX, startY, direction]];
while (queue.length) {
  let [x, y, dir] = queue.shift();

  // 1. 현재 위치 청소
  if (!graph[x][y] && !visited[x][y]) {
    count++;
    visited[x][y] = 1;
  }

  // 동, 서, 남, 북 체크
  for (let i = 0; i < 4; i++) {
    // 2. 왼쪽방향 회전
    dir = getPositivePosition(--dir);
    const nx = x + ds[dir][0];
    const ny = y + ds[dir][1];

    // 2-1 왼쪽방향에 청소가 가능하면 이동
    if (!graph[nx][ny] && !visited[nx][ny]) {
      queue.push([nx, ny, dir]);
      break;
    }

    // 2-3, 2-4 네 방향 모두 청소가 이미 되어있거나 벽인 경우 진입
    if (i === 3) {
      const backDir = getPositivePosition(dir - 2);
      const bx = x + ds[backDir][0];
      const by = y + ds[backDir][1];
      // 후진이 가능한 경우 후진 (이미 청소했던 부분이여도 상관없음 벽만 아니면)
      if (!graph[bx][by]) {
        queue.push([bx, by, dir]);
        break;
      }
    }
    // 2-2 왼쪽 방향에 청소할 공간이 없다면 회전만 하고 다시 컨티뉴
  }
}
console.log(count);
