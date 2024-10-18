let N = Int(readLine()!)!

var A = [Int]()
var B = [Int]()

let inputA = readLine()!.split(separator: " ").compactMap { Int($0)! }
let inputB = readLine()!.split(separator: " ").compactMap { Int($0)! }
A.insert(contentsOf: inputA, at: 0)
B.insert(contentsOf: inputB, at: 0)
var result = 0
//S의 값을 가장 작게 만들기 위해 A의 수를 재배열하자. 단, B에 있는 수는 재배열하면 안 된다.
//배열을 직접 건들지말고 정렬한 배열을 새로 만들어서 사용하면 됩니다!
var inputAsort = inputA.sorted(by: <)
var inputBsort = inputB.sorted(by: >)

for i in 0..<N {
    result += inputAsort[i] * inputBsort[i]
}
print(result)
