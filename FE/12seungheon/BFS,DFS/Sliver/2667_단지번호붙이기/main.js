const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/BFS,DFS/Sliver/2667_단지번호붙이기/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

const n = parseInt(input[0]);  // 첫 번째 줄은 지도의 크기
const board = input.slice(1).map(line => line.split("").map(Number));  // 지도의 각 줄을 2차원 배열로 변환

// 방문 여부를 체크할 2차원 배열
const visited = Array.from({ length: n }, () => Array(n).fill(false)); 
const complexes = []; // 각 단지 내 집의 수를 저장할 배열

// 상하좌우 탐색을 위한 이동 좌표 설정
const dx = [-1, 1, 0, 0]; // x 좌표 변화량: 상(-1), 하(1)
const dy = [0, 0, -1, 1]; // y 좌표 변화량: 좌(-1), 우(1)

// DFS 함수: (x, y) 위치에서 시작하여 단지 내 모든 집을 탐색하고, 해당 단지의 집 개수를 반환
const DFS = (x, y) => {
    visited[x][y] = true; // 현재 위치를 방문 처리
    let houseCount = 1;   // 현재 위치의 집을 포함해 집 개수를 1로 시작

    // 상하좌우 네 방향을 순회
    for (let i = 0; i < 4; i++) {
        const nx = x + dx[i];  // 새로운 x 좌표 (이동 후의 x)
        const ny = y + dy[i];  // 새로운 y 좌표 (이동 후의 y)

        // 새로운 좌표가 지도 범위 내에 있는지 확인
        if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
            const isNotVisited = !visited[nx][ny];  // 아직 방문하지 않은 위치인지 확인
            const isHouse = board[nx][ny] === 1;    // 집이 있는 위치인지 확인 (1이면 집이 있음)

            // 유효한 범위이고, 방문하지 않았으며, 집이 있는 위치일 때만 DFS로 추가 탐색
            if (isNotVisited && isHouse) {
                houseCount += DFS(nx, ny);  // 재귀적으로 DFS 호출하여 연결된 모든 집을 탐색하고, 그 개수를 누적
            }
        }
    }
    return houseCount; // 해당 단지 내 총 집 개수를 반환
};

// 모든 좌표를 순회하며 DFS로 각 단지 탐색
for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
        if (board[i][j] === 1 && !visited[i][j]) { // 집이 있고 방문하지 않은 좌표에서만 시작
            const complexSize = DFS(i, j);  // DFS를 통해 단지 내 집의 개수 계산
            complexes.push(complexSize);    // 해당 단지의 집 개수를 배열에 추가
        }
    }
}

complexes.sort((a, b) => a - b);  // 오름차순 정렬
console.log(complexes.length);    // 총 단지 수 출력
console.log(complexes.join("\n")); // 각 단지의 집 개수를 한 줄씩 출력
