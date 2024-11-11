import { readFileSync } from "fs";
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/BFS,DFS/Gold/14503_로봇청소기/test.txt";
const input = readFileSync(filepath).toString().trim().split("\n");

const [N, M] = input.shift().split(" ").map(Number);
// 처음 로봇청소기의 위치 (r,c)
const [r, c, d] = input.shift().split(" ").map(Number);
const map = new Array(N)
  .fill(0)
  .map(() => input.shift().toString().trim().split(" ").map(Number));
const visited = new Array(N).fill(0).map(() => new Array(M).fill(false));
// 몇칸 청소했는지 count

// 북 동 서 남
const dx = [-1, 0, 1, 0];
const dy = [0, 1, 0, -1];

function turnLeft(d) {
  return d === 0 ? 3 : d - 1;
}

function lookAround(x, y) {
  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];
    if (map[nx][ny] === 0 && !visited[nx][ny]) return true;
  }
  return false;
}

function solution() {
  // 로봇 최초 위치 방향 r, c, d
  // 청소 할 칸 저장 위한 queue
  const queue = [[r, c, d]];
  let count = 0;

  while (queue.length) {
    // 로봇 현재 위치 방향
    let [nx, ny, nd] = queue.shift();

    // 1. 현재 칸이 청소되지 않았고 벽이 아니면 현재 칸 청소
    if (map[nx][ny] === 0 && !visited[nx][ny]) {
      visited[nx][ny] = true;
      count++;
    }
    // 현재칸의 주변 4칸 검사
    let space = lookAround(nx, ny);

    // 2. 현재칸의 주변 4칸 중 청소되지 않은 빈칸이 없는 경우
    if (!space) {
      // 후진 좌표
      let bd = nd >= 2 ? nd - 2 : nd + 2;

      let bx = nx + dx[bd];
      let by = ny + dy[bd];
      // 후진할 수 있는지 검사
      let back = map[bx][by] === 0;
      // 2.1 바라보는 방향 유지한 채로 한칸 후진할 수 있다면 한칸 후진하고 1번으로 돌아감
      if (back) {
        nx = bx;
        ny = by;
        queue.push([nx, ny, nd]);
        continue;
      }
      // 2.2 바라보는 방향 뒤가 벽이라 후진할 수 없다면 작동 멈춘다.
      else {
        return count;
      }
    }
    // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
    else {
      for (let i = 0; i < 4; i++) {
        // 3.1 반시계방향으로 90도 회전
        nd = turnLeft(nd);
        // 바라보는 방향으로 전진 한 좌표
        let tx = nx + dx[nd];
        let ty = ny + dy[nd];
        // 3.2 바라보는 방향 기준으로 앞쪽 칸이 청소되지 않은 빈칸일 경우 한칸 전진한다.
        if (!map[tx][ty] && !visited[tx][ty]) {
          nx = tx;
          ny = ty;
          queue.push([nx, ny, nd]);
          break;
          // 3.3 1번으로 돌아간다.
        }
      }
    }
  }
  return count;
}

console.log(solution());