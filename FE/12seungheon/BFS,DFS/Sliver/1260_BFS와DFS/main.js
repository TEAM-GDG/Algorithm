const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/BFS,DFS/Sliver/1260_BFS와DFS/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

const [n, m, start] = input[0].split(" ").map(Number);
const edges = input.slice(1).map(line => line.split(" ").map(Number));

// 그래프 초기화
const graph = Array.from({ length: n + 1 }, () => []);
for (const [u, v] of edges) {
    graph[u].push(v);
    graph[v].push(u);
}

// 각 노드의 인접 리스트를 오름차순 정렬 (문제 조건에 따라 작은 번호부터 방문하기 위해)
for (let i = 1; i <= n; i++) {
    graph[i].sort((a, b) => a - b);
}

// DFS 구현 (스택 사용)
const DFS = (graph, startNode) => {
    const visited = [];
    const stack = [startNode];

    while (stack.length > 0) {
        const node = stack.pop();
        if (!visited.includes(node)) {
            visited.push(node);
            // 스택에 추가할 때는 역순으로 추가하여 오름차순 유지
            for (let i = graph[node].length - 1; i >= 0; i--) {
                stack.push(graph[node][i]);
            }
        }
    }
    return visited;
};

// BFS 구현 (큐 사용)
const BFS = (graph, startNode) => {
    const visited = [];
    const queue = [startNode];

    while (queue.length > 0) {
        const node = queue.shift();
        if (!visited.includes(node)) {
            visited.push(node);
            // 인접 노드를 큐에 추가
            for (const neighbor of graph[node]) {
                if (!visited.includes(neighbor)) {
                    queue.push(neighbor);
                }
            }
        }
    }
    return visited;
};

// 결과 출력
const dfsResult = DFS(graph, start);
const bfsResult = BFS(graph, start);

console.log(dfsResult.join(" "));
console.log(bfsResult.join(" "));
