let str = readLine()!.map{String($0)}       //split(separator: ":") 를 쓰게 되면 연속으로 오는 경우를 처리할 수 없다.

var result = [String]()
var keep = [String]()
var colon = false
var colonIndex = 0
//input -> 25:09:1985:aa:091:4846:374:bb
for i in 0..<str.count {                    //입력된 문자열 처음부터 끝까지
    if str[i] == ":" {                      //콜론을 만났을 때와 안만나고 4자리가 아닌 경우도 처리를 해줘야 된다.
        if !keep.isEmpty {                  //주석의 input 넣어보면 현재 keep 에는 25 가 들어가있음
            while keep.count < 4 {
                keep.insert("0", at: 0)     //0번째(앞에서)부터 0 넣기
            }
            result.append(keep.joined())
        }
        
        if colon {
            colonIndex = result.count
            colon = false
        }
        colon = true
        keep.removeAll()
    } else {
        colon = false                       //콜론 안오면
        keep.append(str[i])                 //keep 에 저장하고 콜론이 오면 result에 값을 넣음
    }
}

if !keep.isEmpty {                      //콜론을 만났을 때와 안만나고 4자리가 아닌 경우도 처리를 해줘야 된다.
    while keep.count < 4 {
        keep.insert("0", at: 0)
    }
    result.append(keep.joined())
}
    
while result.count < 8 {                            //8보다 작은 경우는 콜론이 2개 이상 들어간 경우
    result.insert("0000", at: colonIndex)           //콜론이 있는 index 찾아서 0000으로 채움
}

print(result.joined(separator: ":"))

