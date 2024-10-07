const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/투포인터,슬라이딩윈도우/Gold/15961_회전 초밥/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

const [N, d, k, c] = input[0].split(" ").map(Number);  
const data = input.slice(1).map(Number);

// 초밥 종류 카운팅 배열
let count = Array(d + 1).fill(0);
let variety = 0;
let maxVariety = 0;

// 초기 슬라이딩 윈도우 설정 (처음 k개의 초밥 선택)
for (let i = 0; i < k; i++) {
    if (count[data[i]] === 0) variety++;  // 새로운 초밥 종류면 증가
    count[data[i]]++;
}

// 쿠폰 초밥 포함 여부 확인
if (count[c] === 0) {
    maxVariety = variety + 1;  // 쿠폰 초밥을 추가로 먹을 수 있으면 +1
} else {
    maxVariety = variety;
}

// 슬라이딩 윈도우 이동
for (let i = 1; i < N; i++) {
    // 왼쪽 초밥 제거
    const leftSushi = data[i - 1];
    count[leftSushi]--;
    if (count[leftSushi] === 0) {
        variety--;
    }

    // 오른쪽 초밥 추가
    const rightSushi = data[(i + k - 1) % N];
    if (count[rightSushi] === 0) {
        variety++;
    }
    count[rightSushi]++;

    // 쿠폰 초밥 포함 여부 확인 및 최대 종류 갱신
    if (count[c] === 0) {
        maxVariety = Math.max(maxVariety, variety + 1);
    } else if (count[c] > 0) {
        maxVariety = Math.max(maxVariety, variety);
    }
}

console.log(maxVariety);
