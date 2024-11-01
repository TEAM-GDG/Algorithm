let input = readLine()!.split(separator: " ").map { Int($0)! }
let N = input[0]
var K = input[1]

var arr = readLine()!.map { Int(String($0))! }
var stack = [Int]()

for i in arr {
    //스택에 요소가 있으면서, K 가 0보다 커야하고, 스택의 마지막 요소가 현재의 요소보다 작다면
    while K > 0 && !stack.isEmpty && stack.last! < i {
        stack.removeLast()      //마지막 요소를 제거하여 더 큰 수를 만든다.
        K -= 1
    }
    stack.append(i)
}

//위의 반복문이 끝난 후에도 K가 남아있는 경우, 그 만큼 뒤에서부터 제거
for _ in 0..<K {
    stack.removeLast()
}

print(stack.map{String($0)}.joined())
