const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const n = Number(input.shift());
const [a, b] = input.shift().split(" ").map(Number);
const m = Number(input.shift());
const line = input.map((e) => e.split(" ").map(Number));

const graph = {};
let visited = Array.from({ length: n + 1 }, () => 0);

const BFS = (start, target) => {
  const queue = [];
  queue.push(start);
  visited[start] = 1;

  // 큐가 빌 때까지 반복
  while (queue.length !== 0) {
    const now = queue.shift(); // 현재 정점을 큐에서 꺼내어 BFS 배열에 추가합니다.

    if (now === target) return visited[now] - 1;

    // 현재 정점과 연결된 정점을 순서대로 큐에 추가합니다.
    for (let next of graph[now]) {
      if (!visited[next]) {
        queue.push(next);
        visited[next] = visited[now] + 1;
      }
    }
  }

  return -1;
};

const main = () => {
  line.forEach(([n1, n2]) => {
    if (!graph[n1]) graph[n1] = [];
    if (!graph[n2]) graph[n2] = [];
    graph[n1].push(n2);
    graph[n2].push(n1);
  });

  console.log(BFS(a, b));
};

main();
