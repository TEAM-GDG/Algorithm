const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/BFS,DFS/Sliver/15270_친구 팰린드롬/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

const [n, m] = input[0].split(" ").map(Number); // 사람 수와 친구 관계 수
const friend = []; // 친구 관계를 저장할 배열
const visited = Array(n + 1).fill(false); // 방문 여부 확인 배열

for (let i = 1; i <= m; i++) {
  const [a, b] = input[i].split(" ").map(Number);
  friend.push([a, b]); // 각 친구 관계를 배열에 추가
}

let answer = 0;

function dfs(depth, count) {
  // 모든 친구 관계를 확인했을 때 최대 짝의 수 갱신
  if (depth >= m) {
    answer = Math.max(answer, count);
    return;
  }

  const [a, b] = friend[depth];

  // 현재 친구 관계 중 한 명이라도 이미 다른 짝이 있는 경우
  if (visited[a] || visited[b]) {
    dfs(depth + 1, count); // 현재 짝을 고려하지 않고 다음 관계로 이동
  } else {
    // 두 사람이 짝이 될 경우
    visited[a] = true;
    visited[b] = true;

    dfs(depth + 1, count + 1); // 짝을 이룬 경우 처리

    // 두 사람이 짝이 되지 않을 경우를 위해 방문 여부 초기화
    visited[a] = false;
    visited[b] = false;

    dfs(depth + 1, count); // 짝을 이루지 않은 경우 처리
  }
}

// 깊이 우선 탐색 시작
dfs(0, 0);

// 각 짝은 두 명이므로 결과에 2를 곱함
answer *= 2;

// 짝이 되지 않은 사람이 남을 수 있으므로 이를 고려
if (answer < n) {
  answer++;
}

console.log(answer);
