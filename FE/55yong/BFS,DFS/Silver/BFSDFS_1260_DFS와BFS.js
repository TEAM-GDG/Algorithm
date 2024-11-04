const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const [N, M, V] = input.shift().split(" ").map(Number);
const line = input.map((e) => e.split(" ").map(Number));

const Dfs = [];
const Bfs = [];
const queue = [];

const graph = Array.from({ length: N + 1 }, () => []);
let visited = Array.from({ length: N + 1 }, () => 0);

const DFS = (cnt) => {
  if (Dfs.length === N) return;
  // 현재 정점을 DFS 배열에 추가하고, 방문 처리
  Dfs.push(cnt);
  visited[cnt] = 1;

  // 현재 정점과 연결된 정점을 순서대로 방문
  for (let next of graph[cnt]) {
    if (!visited[next]) {
      visited[next] = 1;
      DFS(next); // 재귀적으로 다음 정점을 탐색
    }
  }
};

const BFS = () => {
  // 시작 정점을 큐에 추가하고, 방문 처리
  queue.push(V);
  visited[V] = 1;

  // 큐가 빌 때까지 반복
  while (queue.length !== 0) {
    const now = queue.shift(); // 현재 정점을 큐에서 꺼내어 BFS 배열에 추가합니다.
    Bfs.push(now);

    // 현재 정점과 연결된 정점을 순서대로 큐에 추가합니다.
    for (let next of graph[now]) {
      if (!visited[next]) {
        queue.push(next);
        visited[next] = 1;
      }
    }
  }
};

const main = () => {
  // 그래프에 간선 추가
  for (let [from, to] of line) {
    graph[from].push(to);
    graph[to].push(from);
  }

  // 모든 정점의 인접 리스트를 오름차순으로 정렬하여 낮은 번호부터 탐색되도록 함
  for (let i = 0; i < graph.length; i++) {
    graph[i].sort((a, b) => a - b);
  }

  // DFS 수행
  DFS(V);

  // BFS 수행을 위해 방문 배열을 초기화
  visited = visited.map(() => 0);
  BFS();

  console.log(Dfs.join(" "));
  console.log(Bfs.join(" "));
};

main();
