const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

let n = Number(input[0]);
let arr = new Array(n);
let cnt_able = (cnt_disable = 0);
for (let i = 0; i < arr.length; i++) {
  arr[i] = input[i + 1].split("");
}
let check = new Array(n);
for (let i = 0; i < check.length; i++) {
  check[i] = new Array(n).fill(0);
}
function bfs(x, y) {
  let queue = [];
  queue.push([x, y]);
  check[x][y] = 1;
  let dx = [0, 0, 1, -1];
  let dy = [1, -1, 0, 0];
  while (queue.length) {
    let len = queue.length;
    for (let i = 0; i < len; i++) {
      let x = queue.shift();
      for (let i = 0; i < 4; i++) {
        let nx = x[0] + dx[i];
        let ny = x[1] + dy[i];
        if (
          nx >= 0 &&
          nx < n &&
          ny >= 0 &&
          ny < n &&
          !check[nx][ny] &&
          arr[x[0]][x[1]] === arr[nx][ny]
        ) {
          check[nx][ny] = 1;
          queue.push([nx, ny]);
        }
      }
    }
  }
}
// 적록색약 아닌 경우 bfs
for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    if (!check[i][j]) {
      bfs(i, j);
      cnt_able++;
    }
  }
}
// 적록색약인 경우 빨강,초록 색깔통합시켜주기
for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    if (arr[i][j] === "R") arr[i][j] = "G";
  }
}
// check 배열 초기화
for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    check[i][j] = 0;
  }
}
// 적록색약인 경우 bfs
for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    if (!check[i][j]) {
      bfs(i, j);
      cnt_disable++;
    }
  }
}
console.log(cnt_able, cnt_disable);
