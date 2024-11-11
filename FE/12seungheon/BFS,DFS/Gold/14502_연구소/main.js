const fs = require("fs");
const filepath =
  process.platform === "linux"
    ? "/dev/stdin"
    : "/Users/12seungheon/Code/Algorithm/FE/12seungheon/BFS,DFS/Gold/14502_연구소/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

const [COL, ROW] = input.shift().split(" ").map(Number);
const graph = input.map((line) => line.split(" ").map(Number));

const dx = [0, 0, 1, -1];
const dy = [1, -1, 0, 0];

let max = 0;

// BFS를 이용해 안전지대 갯수를 계산
const countSafePlace = (copyGraph) => {
  const queue = [];
  let cnt = 0;

  for (let i = 0; i < COL; i++) {
    for (let j = 0; j < ROW; j++) {
      if (copyGraph[i][j] === 2) queue.push([i, j]);
    }
  }

  while (queue.length) {
    const [virusX, virusY] = queue.shift();

    for (let i = 0; i < 4; i++) {
      const nx = virusX + dx[i];
      const ny = virusY + dy[i];

      if (nx >= 0 && nx < COL && ny >= 0 && ny < ROW && copyGraph[nx][ny] === 0) {
        copyGraph[nx][ny] = 2;
        queue.push([nx, ny]);
      }
    }
  }

  for (let i = 0; i < COL; i++) {
    for (let j = 0; j < ROW; j++) {
      if (copyGraph[i][j] === 0) cnt++;
    }
  }

  return cnt;
};

// DFS로 벽을 세우는 모든 경우를 탐색
const solution = (cnt) => {
  if (cnt === 3) {
    const copy = graph.map((v) => [...v]);
    const num = countSafePlace(copy);
    max = Math.max(max, num);
    return;
  }

  for (let i = 0; i < COL; i++) {
    for (let j = 0; j < ROW; j++) {
      if (graph[i][j] === 0) {
        graph[i][j] = 1;
        solution(cnt + 1);
        graph[i][j] = 0;
      }
    }
  }
};

solution(0);
console.log(max);
