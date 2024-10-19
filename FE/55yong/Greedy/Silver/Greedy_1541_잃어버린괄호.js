const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs").readFileSync(filepath).toString().trim();

const fomula = input.split("-");

let answer = [];

fomula.forEach((item) => {
  if (item.includes("+")) {
    let sum = 0;
    item = item.split("+").map(Number);
    item.forEach((i) => (sum += i));
    answer.push(sum);
  } else {
    answer.push(Number(item));
  }
});

console.log(answer.reduce((prev, cur) => prev - cur));

/**
 * 연산자는 +와 -만 존재하므로 0를 기준으로 끊어주면 값이 최소가 됨
 * -를 기준으로 나눠 배열을 만들고, 해당 배열을 순회하며
 * +를 가지고 있는 요소는 다시 +를 기준으로 나누어 합한 값을 answer에 push함
 * +가 없는 요소는 그대로 answer 배열에 push
 * 순서대로 push되었으므로, answer에 있는 값을 모두 순서대로 빼주는 작업 수행
 */
