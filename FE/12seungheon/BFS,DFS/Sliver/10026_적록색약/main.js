const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/BFS,DFS/Sliver/10026_적록색약/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

const n = parseInt(input[0]);
const boardNormal = input.slice(1).map(line => line.split(""));
const boardColorblind = boardNormal.map(line => line.map(color => (color === "G" ? "R" : color)));

const dx = [-1, 1, 0, 0];
const dy = [0, 0, -1, 1];

const visitedNormal = Array.from({ length: n }, () => Array(n).fill(false));
const visitedColorblind = Array.from({ length: n }, () => Array(n).fill(false));

const DFS = (x, y, board, visited, color) => {
    visited[x][y] = true;
    for (let i = 0; i < 4; i++) {
        const nx = x + dx[i];
        const ny = y + dy[i];
        if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && board[nx][ny] === color) {
            DFS(nx, ny, board, visited, color);
        }
    }
};

const countRegions = (board, visited) => {
    let regionCount = 0;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (!visited[i][j]) {
                DFS(i, j, board, visited, board[i][j]);
                regionCount++;
            }
        }
    }
    return regionCount;
};

const normalCount = countRegions(boardNormal, visitedNormal);
const colorblindCount = countRegions(boardColorblind, visitedColorblind);

console.log(normalCount, colorblindCount);
