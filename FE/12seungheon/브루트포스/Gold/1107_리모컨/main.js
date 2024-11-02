const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/브루트포스/Gold/1107_리모컨/test.txt")
  .toString()
  .trim()
  .split("\n")
  .map((e) => e.split(" ").map(Number));

const N = input[0][0];

const list = input[2];

const solution = () => {
  let canMove = true;
  let minRes = Number.MAX_SAFE_INTEGER;

  if (list !== undefined) {
    for (let n = 0; n <= 1000000; n++) {
      const stn = n.toString();
      for (let i = 0; i < list.length; i++) {
        for (let j = 0; j < stn.length; j++) {
          if (stn.charAt(j) === list[i].toString()) {
            canMove = false;
            break;
          }
        }
        if (!canMove) break;
      }
      if (canMove) minRes = Math.min(minRes, stn.length + Math.abs(N - n));
      canMove = true;
    }
    minRes = Math.min(minRes, Math.abs(N - 100));
  } else {
    minRes = Math.min(N.toString().length, Math.abs(N - 100));
  }
  console.log(minRes);
};

solution();

