const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/브루트포스/Sliver/18111_마인크래프트/test.txt').toString().trim().split('\n');

const [n, m, b] = input.shift().split(" ").map(Number); // 첫 줄의 n(세로 길이), m(가로 길이), b(인벤토리 블록 수) 저장
const arr = input.map(i => i.split(" ").map(Number)); // 나머지 줄들을 2차원 배열로 변환해 각 높이 정보 저장

let ansTime = Infinity; // 최소 작업 시간을 저장할 변수 (처음엔 무한대로 설정)
let ansHeight = -1; // 최소 작업 시간일 때의 목표 높이 저장

for (let h = 0; h <= 256; h++) { // 높이 h를 0부터 256까지 하나씩 탐색
  let inventory = 0; // 인벤토리에 추가할 블록 개수 (부족한 블록)
  let removeCnt = 0; // 제거할 블록 개수

  for (let i = 0; i < n; i++) { // 맵의 모든 위치 탐색
    for (let j = 0; j < m; j++) {
      let curh = arr[i][j] - h; // 현재 높이와 목표 높이의 차이
      if (curh < 0) inventory -= curh; // 목표 높이보다 낮으면 인벤토리에 블록 추가 (절대값 사용)
      else removeCnt += curh; // 목표 높이보다 높으면 블록 제거 개수 증가
    }
  }

  // 제거한 블록과 인벤토리 블록 합이 현재 높이에 필요한 블록 수보다 크거나 같으면 작업 가능
  if (removeCnt + b >= inventory) {
    let time = 2 * removeCnt + inventory; // 시간 계산 (제거는 2초, 추가는 1초)
    if (ansTime >= time) { // 최소 작업 시간 갱신 조건
      ansTime = time; // 최소 시간 갱신
      ansHeight = h; // 목표 높이 갱신
    }
  }
}

console.log(ansTime, ansHeight); // 최소 작업 시간과 그때의 목표 높이 출력