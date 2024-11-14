const fs = require("fs");
const filepath =
  process.platform === "linux"
    ? "/dev/stdin"
    : "/Users/12seungheon/Code/Algorithm/FE/12seungheon/BFS,DFS/Gold/13913_숨바꼭질4/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

const [N, K] = input[0].split(' ').map(Number);

function BFS(x, k) {
  // BFS에서 사용할 큐 초기화 (시작 위치 x를 큐에 추가)
  let queue = [x];

  // 방문 여부와 최단 시간을 기록할 배열 (크기는 최대 100001)
  let array = new Array(MAX).fill(0);

  // 경로 추적을 위한 배열 (이전 위치를 기록)
  let path = new Array(MAX).fill(0);

  // 만약 시작 위치와 목표 위치가 같다면, 바로 반환
  if (x === k) {
    return [0, x];
  } else {
    // BFS 탐색 시작
    while (queue.length) {
      // 큐에서 현재 위치 꺼내기
      let y = queue.shift();

      // 목표 위치에 도달한 경우
      if (y === k) {
        const min = array[y]; // 최소 시간
        let backtrack = String(k); // 역추적 결과를 저장할 문자열
        let start = y;

        // 역추적을 통해 경로를 구함
        while (start !== x) {
          backtrack = String(path[start]) + ' ' + backtrack;
          start = path[start];
        }

        // 최소 시간과 경로 반환
        return [min, backtrack];
      }

      // 현재 위치에서 이동할 수 있는 경우의 수 (y-1, y+1, 2*y)
      for (const move of [y - 1, y + 1, 2 * y]) {
        // 유효한 위치(move <= 100001)이고, 아직 방문하지 않았다면
        if (move <= 100001 && !array[move]) {
          queue.push(move); // 큐에 추가
          array[move] = array[y] + 1; // 걸리는 시간 기록 (현재 위치의 시간 + 1)
          path[move] = y; // 이전 위치 기록 (경로 추적용)
        }
      }
    }
  }
}

// 최대 범위 설정 (문제에서 위치의 최대값이 100000이므로 100001로 설정)
const MAX = 100001;

// BFS를 수행하여 최소 시간과 경로를 구함
const [min, track] = BFS(N, K);

// 결과 출력: 최소 시간과 경로
console.log(min);
console.log(track);
