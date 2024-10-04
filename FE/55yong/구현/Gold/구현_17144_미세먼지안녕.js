const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

// 0으로 채워진 2차원 배열을 리턴하는 함수
const newMap = (r, c) => {
  return Array.from({ length: r }, (_) => Array.from({ length: c }, (_) => 0));
};

// 확산 기능
const spread = (oldmap, r, c) => {
  // 이차원 배열을 deep copy
  const newmap = oldmap.map((row) => [...row]);
  for (let i = 0; i < r; i++) {
    for (let j = 0; j < c; j++) {
      // 5보다 작으면 확산 X, 이동만 수행
      if (oldmap[i][j] > 4) {
        const value = Math.floor(oldmap[i][j] / 5);
        // 상
        if (i > 0 && oldmap[i - 1][j] !== -1) {
          newmap[i][j] -= value;
          newmap[i - 1][j] += value;
        }
        // 하
        if (i < r - 1 && oldmap[i + 1][j] !== -1) {
          newmap[i][j] -= value;
          newmap[i + 1][j] += value;
        }
        // 좌
        if (j > 0 && oldmap[i][j - 1] !== -1) {
          newmap[i][j] -= value;
          newmap[i][j - 1] += value;
        }
        // 우
        if (j < c - 1 && oldmap[i][j + 1] !== -1) {
          newmap[i][j] -= value;
          newmap[i][j + 1] += value;
        }
      }
    }
  }
  return newmap;
};

// 1칸 순환 기능
const circulate = (oldmap, r, c, k) => {
  const newmap = newMap(r, c);

  for (let i = 0; i < r; i++) {
    for (let j = 0; j < c; j++) {
      // 우로 이동
      if ((i === k || i === k + 1) && j >= 2) {
        newmap[i][j] = oldmap[i][j - 1];
      }
      // 좌로 이동
      else if ((i === 0 || i === r - 1) && j < c - 1) {
        newmap[i][j] = oldmap[i][j + 1];
      }
      // 위로 이동
      else if ((i < k && j === c - 1) || (i > k + 1 && i < r - 1 && j === 0)) {
        newmap[i][j] = oldmap[i + 1][j];
      }
      // 아래로 이동
      else if ((i > 0 && i < k && j === 0) || (i > k + 1 && j === c - 1)) {
        newmap[i][j] = oldmap[i - 1][j];
      } else {
        newmap[i][j] = oldmap[i][j];
      }
    }
  }
  // 공기청정기 배출구 0으로 만들기
  newmap[k][1] = 0;
  newmap[k + 1][1] = 0;
  // 공기 청정기 위치 고정
  newmap[k][0] = -1;
  newmap[k + 1][0] = -1;
  return newmap;
};

// 입력 처리
const [R, C, T] = input.shift().split(" ").map(Number);
let board = input.map((row) => row.split(" ").map(Number));

let kRow = 0;
for (let i = 2; i < R - 2; i++) {
  if (board[i][0] === -1) {
    kRow = i;
    break;
  }
}

for (let i = 0; i < T; i++) {
  // 확산하고 이동
  board = spread(board, R, C);
  board = circulate(board, R, C, kRow);
}

let answer = 2;
board.forEach((row) => (answer += row.reduce((acc, cur) => acc + cur, 0)));
console.log(answer);
