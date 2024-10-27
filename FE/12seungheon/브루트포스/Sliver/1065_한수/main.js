const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/브루트포스/Sliver/1065_한수/test.txt').toString().trim().split('\n');

const max = Number(input);
let count = 0;

for(let i=1; i<=max; i++) {
  // 백, 십, 일의자리
   let hund = Math.floor(i / 100);
   let ten = Math.floor((i % 100)/10);
   let one = i % 10;

   if(i < 100){
      count++;
   } else if (i >= 100 && i < 1000) {
      if(hund - ten === ten - one) {
         count++;
      }
   }
}

console.log(count);

