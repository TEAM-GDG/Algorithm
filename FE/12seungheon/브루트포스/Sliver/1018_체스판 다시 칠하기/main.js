const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/브루트포스/Sliver/1018_체스판 다시 칠하기/test.txt').toString().trim().split('\n');

// 첫 번째 줄에서 N과 M을 읽어온다.
const [N, M] = input[0].split(' ').map(Number);
const board = input.slice(1);

// 초기값을 무한대로 설정
let minRepaints = Infinity;

// 가능한 모든 8x8 체스판 부분을 검사
for (let startX = 0; startX <= N - 8; startX++) {
    for (let startY = 0; startY <= M - 8; startY++) {        let repaintW = 0; // 흰색으로 시작하는 경우
        let repaintB = 0; // 검은색으로 시작하는 경우

        for (let i = 0; i < 8; i++) {
            for (let j = 0; j < 8; j++) {
                const currentColor = board[startX + i][startY + j];
                
                // 체스판 색상 비교
                if ((i + j) % 2 === 0) {  
                    if (currentColor !== 'W') repaintW++; // 흰색 칸
                    if (currentColor !== 'B') repaintB++; // 검은색 칸
                } else {  
                    if (currentColor !== 'B') repaintW++; // 흰색 칸
                    if (currentColor !== 'W') repaintB++; // 검은색 칸
                }
            }
        }

        const repaints = Math.min(repaintW, repaintB);
        minRepaints = Math.min(minRepaints, repaints); // 최소값 업데이트
    }
}

console.log(minRepaints);