const fs = require('fs')
const input = fs.readFileSync(process.platform === "linux" ? "/dev/stdin":"FE/12seungheon/구현/Silver/3107_IPv6/test.txt")
  .toString().trim()

  // 0이 생략된 문자열에 0을 채워주는 함수
  const padStartAct = (arr) => {
    return arr.map((el)=> {
      return el.length < 4 ? el.padStart(4,"0") : el
    })
  }
  
  // '::'이 존재하는 경우
  const includesDubbleSemi = (data) => {
    const arr = data.split('::')
    
    // 이 경우에는 ::으로 문자열을 쪼갠 뒤 각각 0이 생략된 부분을 채워준다
    const [unit1, unit2] = arr.map(el => padStartAct(el.split(':')))
    
    // 문자열의 총 길이는 정해져 있으므로, 규칙2로 생략된 문자열의 길이를 구할 수 있다
    const zeroUnit = Array.from({
      length : 8 - (unit1.length + unit2.length
        )}, ()=> "0000")
    
    // 생략된 부분을 채워 반환
    const result = [...unit1, ...zeroUnit, ...unit2].join(':')
    return result
  }
  
  // '::'이 존재하지 않는 경우

  const notIncludesDubbleSemi = (data) => {
    const arr = data.split(':')
    // 이 경우에는 0이 생략된 부분만 채워준 뒤 반환
    return padStartAct(arr).join(':')
  }

  
  function solution(data) {
    console.log(
      data.includes('::') ? 
      includesDubbleSemi(data) : 
      notIncludesDubbleSemi(data)
      )
  }
  
  solution(input)