const { match } = require("assert");

const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const [N, M] = input.shift().split(" ").map(Number);
const friend = input.map((e) => e.split(" ").map(Number));
const graph = Array.from({ length: N + 1 }, () => []);
const visited = Array.from({ length: N + 1 }, () => 0);
let answer = 0;

const dfs = (index, count) => {
  // 모든 관계를 확인했을 때 최대 짝의 수 갱신
  if (index >= M) {
    answer = Math.max(answer, count);
    return;
  }

  const [a, b] = friend[index];

  // 현재 친구 관계 중 한 명이라도 짝이 있는 경우
  if (visited[a] || visited[b]) {
    dfs(index + 1, count); // 고려 X
  } else {
    visited[a] = true;
    visited[b] = true;

    dfs(index + 1, count + 1); // 짝을 이룬 경우

    visited[a] = false;
    visited[b] = false;

    dfs(index + 1, count); // 짝을 이루지 않은 경우
  }
};

const main = () => {
  for (let [from, to] of friend) {
    if (graph[from].length < 1) graph[from].push(to);
  }

  for (let i = 0; i < graph.length; i++) {
    graph[i].sort((a, b) => a - b);
  }

  dfs(0, 0);

  // 짝은 두명이므로 결과에 2 곱하기
  answer *= 2;

  // 짝이 되지 않은 사람 고려
  if (answer < N) {
    answer++;
  }

  console.log(answer);
};

main();
