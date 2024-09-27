const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filepath).toString().trim();

// input 값을 ':'을 기준으로 구분해서 그룹을 나눔
const group = input.split(":");

// ""인 값의 인덱스를 찾아 indexStart에 할당
const indexStart = group.findIndex((item) => item === "");
// 만약 indexStart+1의 값이 ""인 경우 indexEnd는 indexStart + 2, 그렇지 않은 경우 indexStart + 1
const indexEnd = group[indexStart + 1] === "" ? indexStart + 2 : indexStart + 1;
// 축약된 IPv6 주소를 찾아내는 과정
const groupCount = 8 - (indexStart + (group.length - indexEnd));

// 비어있는 배열을 0000으로 채우는 과정
const colon =
  indexStart === -1
    ? group
    : [
        ...group.slice(0, indexStart),
        ...new Array(groupCount).fill("0000"),
        ...group.slice(indexEnd),
      ];

// 완성되지 않은 배열(값의 길이가 4가 아닌 배열)을 채우는 과정
const zero = colon.map((item) => "0".repeat(4 - item.length) + item);

// 출력
console.log(zero.join(":"));
