const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs")
  .readFileSync(filepath)
  .toString()
  .trim()
  .split("\n");

const [N, d, k, c] = input.shift().split(" ").map(Number);
const arr = input.map(Number);
const check = new Array(d + 1).fill(0);

let res = 1;
check[c]++;

// 첫 번째 K개의 초밥 선택
for (let i = 0; i < k; i++) {
  // 해당 초밥이 처음 선택된 경우 종류 수를 증가
  if (check[arr[i]] == 0) res++;
  // 초밥 개수 증가
  check[arr[i]]++;
}

// 현재 선택한 초밥 종류 수를 저장하는 변수
let cnt = res;

// 슬라이딩 윈도우를 통해 모든 경우의 수 탐색
for (let i = 1; i < N; i++) {
  // 윈도우에서 빠지는 초밥 구하기
  let pop = arr[i - 1];
  // 해당 초밥의 개수 감소
  check[pop]--;
  // 만약 초밥의 개수가 0일 경우 종류 수 감소
  if (check[pop] == 0) cnt--;

  // 윈도우에 새로 추가되는 초밥 구하기
  let add = arr[(i + k - 1) % N];
  // 해당 초밥이 처음 선택된 경우 종류 수 증가
  if (check[add] == 0) cnt++;
  // 초밥 개수 증가
  check[add]++;

  // 최대 종류 수 갱신
  res = Math.max(res, cnt);
}

console.log(res);
