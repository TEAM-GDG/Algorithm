const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/투포인터,슬라이딩윈도우/Sliver/10025_게으른 백곰/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

const [N, K] = input[0].split(' ').map(Number);
const ice = new Array(1000001).fill(0);

// 얼음 좌표에 있는 물고기 양 저장
for (let i = 1; i <= N; i++) {
    const [g, x] = input[i].split(' ').map(Number);
    ice[x] += g;
}

let sum = 0;
let maxFish = 0;
const range = 2 * K + 1;

// 처음 윈도우 범위 설정
for (let i = 0; i < range && i < ice.length; i++) {
    sum += ice[i];
}

maxFish = sum;

// 슬라이딩 윈도우로 최대 물고기 양 구하기
for (let i = range; i < ice.length; i++) {
    sum = sum + ice[i] - ice[i - range];
    maxFish = Math.max(maxFish, sum);
}

console.log(maxFish);
