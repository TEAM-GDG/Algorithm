var channel = Int(String(readLine()!))!
let M = Int(String(readLine()!))!
var arr: [Int] = []
if M != 0 {
    arr = readLine()!.split(separator: " ").map{ Int(String($0))! }
}
//0부터 1000000까지의 모든 채널을 다 이동해보면서 n까지 가장 조금이동해서 도달할수있는 경우를 저장하고 출력.

var minCnt = abs(channel - 100) //+ 또는 -만 눌렀을 때 나올 수 있는 최악의 값
for i in 0...1000000 {
    //수빈이가 이동하려고하는 최대채널은 500000이지만,
    //채널이 무한대만큼 있다는 힌트를보고 생각해보면 500000보다 큰 수에서 뺄수도 있겠다 싶은데
    //극단적으로 설명하면 1에서 500000가는 것보다는 999000에서 500000가는게 더 가깝다.
    //그래서 0에서 500000을 가는 경우와 1000000에서 500000을 가는 경우를 모두 고려해야함.
    let len = check(i)
    if len > 0 {
        let press = abs(channel - i)        //+또는 -버튼을 눌렀을때의 경우가된다.(i만큼의 번호로 이동했기때문)
        minCnt = min(minCnt, len + press)
        print(minCnt)
    }
}
print(minCnt)
 
//이동하려는 채널의 리모콘 클릭횟수
private func check(_ i: Int) -> Int {
    var n = i
    //목표 채널이 0 일때, 0으로 이동 가능한지 체크
    if n == 0 {
        if arr.contains(0) {
            return 0
        } else {
            return 1
        }
    }
    
    var len = 0
    //n에서 누를 번호가 고장났는지를 판단해서 고장났으면 못누르니까 0리턴
    //고장안났으면 누를거니까 len에 +1
    while n > 0 {
        if arr.contains(n % 10) { return 0 }
        n = n / 10
        len += 1
    }
    return len
}
