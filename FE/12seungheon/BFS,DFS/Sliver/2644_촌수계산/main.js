const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/BFS,DFS/Sliver/2644_촌수계산/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

const n = parseInt(input[0]);
const [person1, person2] = input[1].split(" ").map(Number);
const m = parseInt(input[2]);
const relations = input.slice(3).map(line => line.split(" ").map(Number));

// 그래프 초기화
const graph = Array.from({ length: n + 1 }, () => []);
for (const [u, v] of relations) {
    graph[u].push(v);
    graph[v].push(u);
}

// BFS 함수
const BFS = (graph, startNode, targetNode) => {
    const visited = Array(n + 1).fill(false); // 각 노드의 방문 여부
    const distance = Array(n + 1).fill(0); // 각 노드까지의 거리 기록

    const queue = [startNode];
    visited[startNode] = true;

    while (queue.length !== 0) {
        const node = queue.shift();

        for (const neighbor of graph[node]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                distance[neighbor] = distance[node] + 1; // 이전 노드 거리 + 1
                queue.push(neighbor);

                if (neighbor === targetNode) {
                    return distance[neighbor];
                }
            }
        }
    }

    return -1; // targetNode에 도달할 수 없는 경우
};

const result = BFS(graph, person1, person2);
console.log(result);
