const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

let [h, w, inven] = input.shift().split(" ").map(Number);
let map = [];
let highist = 0;
let shortist = 256;
let overBlock = 0;
let shortBlack = 0;
let floor = 0;
let answer = [Number.MAX_VALUE, 0];

// 각 줄에서 땅의 높이를 숫자 배열로 변환하여 map에 추가
for (let i = 0; i < h; i++) {
  const v = input[i].split(" ").map(Number);

  const max = Math.max(...v);
  const min = Math.min(...v);

  if (max > highist) highist = max;
  if (min < shortist) shortist = min;

  map.push(v);
}

floor = highist;

// 최저 높이까지 반복하며 모든 가능한 평탄화 높이에 대해 작업 시간을 계산
while (floor >= shortist) {
  // 현재 층(floor)보다 높은 블록의 수를 계산
  overBlock = map.reduce((a, y) => {
    const yValue = y.reduce((b, x) => {
      if (x - floor > 0) return b + (x - floor);
      else return b;
    }, 0);
    return a + yValue;
  }, 0);

  // 현재 층(floor)보다 낮은 블록의 수를 계산
  shortBlack = map.reduce((a, y) => {
    const yValue = y.reduce((b, x) => {
      if (floor - x > 0) return b + (floor - x);
      else return b;
    }, 0);
    return a + yValue;
  }, 0);

  // 인벤토리에 블록을 더해서 부족한 블록을 충족할 수 있는지 확인
  if (inven + overBlock >= shortBlack) {
    // 작업 시간: 제거된 블록은 2초, 추가된 블록은 1초 소모
    const cost = overBlock * 2 + shortBlack;

    // 최소 시간보다 작다면 결과를 갱신
    if (answer[0] > cost) answer = [cost, floor];
  }

  // 평탄화할 높이를 낮추어 다음 높이에 대해 반복
  floor--;
}

// 결과 출력: 최소 작업 시간과 그때의 높이를 출력
console.log(answer.join(" "));
