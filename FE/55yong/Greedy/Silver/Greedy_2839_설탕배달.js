const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
let N = require("fs").readFileSync(filepath).toString().trim();
let answer = -1;
let five = Math.floor(N / 5);

while (five >= 0) {
  let dp = N - five * 5;

  if (dp % 3 === 0) {
    answer = dp / 3 + five;
    break;
  } else {
    five -= 1;
  }
}

console.log(answer);
