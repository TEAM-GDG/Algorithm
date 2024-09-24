const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

let result = 0;

for (let i = 1; i <= input; i *= 10) {
  console.log(i, result);
  result += input - i + 1;
}

console.log(result);

/**
 * 입력값이 821인 경우
 * input - i + 1이니옴
 * 1   ~   9의 개수는 9 - 1 + 1 = 9개
 * 10  ~  99의 개수는 99 - 10 + 1 = 90개
 * 100 ~ 821의 개수는 821 - 100 + 1 = 722개로 계산할 수 있음
 * 그리고 자릿수를 계산해서 자릿수만큼 곱해서 덧셈하면 됨
 * (9 * 1) + (90 * 2) + (722 * 3) = 2355
 */
