## 코틀린 기초

### 2.1 기본 요소: 함수와 변수
* 코틀린에서 타입 선언을 생략해도 된다는 사실을 보고, 코틀린이 어떻게 변경 가능한 데이터 보다 변경할 수 없는 불변 데이터 사용을 장려하는 지 배운다.

#### 2.1.1 Hello, World!
* 함수를 최상위 수준에 정의할 수 있다. (자바와 달리) 꼭 클래스 안에 함수를 넣어야 할 필요가 없다.
* System.out.println 대신에 println 이라고 쓴다. 
  코틀린 표준 라이브러리는 여러 가지 표준 자바 라이브러리 함수를 간결하게 사용할 수 있게 감싼 wrapper 를 제공한다. println 도 그런 함수 중 하나다.

#### 2.1.2 함수
* 블록이 본문인 함수
```kotlin
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}
```

> 문(statement)과 식(expression)의 구분
> > 자바에서는 모든 제어 구조가 문인 반면 코틀린에서는 루프를 제외한 대부분의 제어 구조가 식이다.
> > 코틀린에서 if 는 식이지 문이 아니다. 제어 구조를 다른 식으로 엮어낼 수 있으면 여러 일반적인 패턴을 아주 간결하게 표현할 수 있다.

* 식이 본문인 함수
```kotlin
fun max(a: Int, b: Int): Int = if (a > b) a else b
```

* 반환 타입 생략 
```kotlin
fun max(a: Int, b: Int) = if (a > b) a else b
```

* 이렇게 컴파일러가 타입을 분석해 프로그래머 대신 프로그램 구성 요소의 타입을 정해주는 기능을 **타입 추론**이라 부른다.

#### 2.1.3 변수

* 초기화 식이 없다면 변수에 저장될 값에 대해 아무 정보가 없기 때문에 컴파일러가 타입을 추론할 수 없다.
```kotlin
val answer: Int
answer = 42
```

* 변경 가능한 변수와 변경 불가능한 변수
> val (값을 뜻하는 value 에서 따옴)
> > 변경 불가능한 immutable 참조를 저장하는 변수다. val 로 선언된 변수는 일단 초기화하고 나면 재대입이 불가능하다. 자바로 말하자면 final 변수에 해당한다.

 
> var (변수를 뜻하는 variable 에서 따옴)
> > 변경 가능한 mutable 참조다. 이런 변수의 값은 바뀔 수있다. 자바의 일반 변수에 해당한다.

* 기본적으로는 모든 변수를 val 키워드를 사용해 불변 변수로 선언하고, 나중에 꼭 필요할 때만 var 로 변경하라
* 변경 불가능한 참조와 변경 불가능한 객체를 부수 효과가 없는 함수와 조합해 사용하면 코드가 함수형 코드에 가까워진다.


#### 2.1.4 더 쉽게 문자열 형식 지정: 문자열 템플릿

```kotlin
fun main(args: Array<String>) {
    val name = if (args.size > 0) args[0] else "Kotlin"
    println("Hello, $name!")
}
```
* 이 예제는 **문자열 템플릿 기능**을 보여준다. 이 코드는 name 이라는 변수를 선언하고 그 다음 줄에 있는 문자열 리터럴 안에서 그 변수를 사용했다.
여러 스크립트 언어와 비슷하게 코틀린에서도 변수를 문자열 안에 사용할 수 있다. 문자열 리터럴의 필요한 곳에 변수를 넣되 변수 앞에 $를 추가해야 한다.
  
* 문자열 템플릿 안에서 변수 이름만을 사용하는 경우라도 ${name} 처럼 중괄호로 변수명을 감싸는 습관을 들이면 더 좋다.

### 2.2 클래스와 프로퍼티

```kotlin
  class Person(val name: String)
```

* 이런 유형의 클래스(코드가 없이 데이터만 저장하는 클래스)를 값 객체 (value object) 라 부르며, 
  다양한 언어가 값 객체를 간결하게 기술할 수 있는 구문을 제공한다.
* 코틀린의 기본 가시성 변경자 (Visibility modifier) 는 public 이다.

#### 2.2.1 프로퍼티
```kotlin
class Person(
  val name: String,
  var isMarried: Boolean
)
```
> val name
> > 읽기 전용 프로퍼티로, 코틀린은 (비공개) 필드와 필드를 읽는 단순한 (공개) 게터를 만들어 낸다.

> var isMarried
> > 쓸 수 있는 프로퍼티로, 코틀린은 (비공개) 필드, (공개) 게터, (공개) 세터를 만들어 낸다.

* 기본적으로 코틀린에서 프로퍼티를 선언하는 방식은 프로퍼티와 관련있는 접근자를 선언하는 것이다.

#### 2.2.2 커스텀 접근자
```kotlin
class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get () {
            return height == width
        }
}
```

* 파라미터가 없는 함수와 커스텀 게터를 정의하는 방식은 구현이나 성능상 차이는 없다. 차이나는 부분은 가독성 뿐이다.

#### 2.2.3 코틀린 소스코드 구조: 디렉터리와 패키지
* 코틀린에서는 여러 클래스를 한 파일에 넣을 수도 있고, 파일의 이름도 마음대로 정할 수 있다.
* 하지만 대부분의 경우 자바와 같이 패키지별로 디렉터리를 구성하는 편이 낫다. 특히 자바와 코틀린을 함께 사용하는 프로젝트에서는 자바의 방식을 따르는게 중요하다.


### 2.3 선택 표현과 처리: enum 과 when

#### 2.3.1 enum 클래스 정의
```kotlin
enum class Color (
  val r: Int, val g: Int, val b: Int
        ) {
    RED(255, 0, 0), ORANGE(255, 165, 0), YELLOW(255, 255, 0);
  
  fun rgb() = (r * 256 + g) * 256 +b
}
```

* enum 에서도 일반적인 클래스와 마찬가지로 생성자와 프로퍼티를 선언한다.
* enum 클래스 안에서 메소드를 정의하는 경우 반드시 enum 상수 목록과 정의 사이에 세미콜론을 넣어야 한다.

#### 2.3.2 when 으로 enum 클래스 다루기
* switch 에 해당하는 코틀린 구성요소는 when 이다.
```kotlin
fun getMnemonic(color: Color) = when (color) {
    Color.RED -> "Richard"
    Color.ORANGE -> "Of"
}

```
* 자바와 달리 각 분기의 끝에 break 를 넣지 않아도 된다.

```kotlin
fun getWarmth(color: Color) = when(color) {
    Color.RED, Color.ORANGE -> "warm"
}
```

* 한 분기 안에서 여러 값을 매치 패턴으로 사용할 수도 있다.

#### 2.3.3 when 과 임의의 객체를 함께 사용
```kotlin
fun mix(c1: Color, c2: Color) = when (setOf(c1, c2)) {
  setOf(RED, YELLOW) -> ORANGE
  setOf(YELLOW, BLUE) -> GREEN
  else -> throw Exception("Dirty color")
}
```

* when 의 분기 조건에 여러 다른 객체와 식을 넣을 수 있기 때문에 많은 경우 코드를 더 간결하고 아름답게 작성할 수 있다.

#### 2.3.4 인자 없는 when 사용
```kotlin
fun mixOptimized(c1: Color, c2: Color) = when {
  (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
  (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) -> GREEN
  else -> throw Exception("Dirty color")
}
```
* Set 인스턴스를 생성하지 않기 때문에 가비지 객체가 늘어나는 것을 방지한다.
* 다만, 가독성이 떨어진다.

#### 2.3.5 스마트 캐스트: 타입 검사와 타입 캐스트를 조합
* 코틀린에서는 프로그래머 대신 컴파일러가 캐스팅을 해준다. 
  어떤 변수가 변수가 원하는 타입인지 일단 is 로 검사하고 나면 굳이 변수를 원하는 타입으로 캐스팅하지 않아도 된다. 이를 **스마트 캐스트** 라고 부른다.
  
```kotlin
if (e is Sum) {
    return eval(e.right) + eval(e.left)
}
```
* 스마트 캐스트는 is 로 변수에 든 값의 타입을 검사한 다음에 그 값이 바뀔 수 없는 경우에만 작동한다. 
* 그 프로퍼티는 반드시 val 이어야 하며 커스텀 접근자를 사용한 것이어도 안된다.

#### 2.3.6 리팩토링: if 를 when 으로 변경
```kotlin
fun eval(e: Expr): Int = when(e) {
  is Num -> e.value
  is Sum -> eval(e.right) + eval(e.left)
  else -> throw IllegalArgumentException("Unknown expression")
}
```

#### 2.3.7 if 와 when 분기에서 블록을 사용
* if 나 when 모두 분기에서 블록을 사용할 수 있다. 그런 경우 블록의 마지막 문장이 블록 전체의 결과가 된다.
* 하지만, 이 규칙은 함수에 대해서는 성립하지 않는다. 식이 본문인 함수는 블록을 본문으로 가질 수 없고 블록이 본문인 함수는 내부에 return 문이 반드시 있어야 한다.

### 2.4 대상을 이터레이션: while 과 for 루프
* 2장에서 설명한 코틀린 특성 중 자바와 가장 비슷한 것이 이터레이션이다.
* for 는 자바의 for-each 루프에 해당하는 형태만 존재한다.
* 코틀린의 for 는 C# 과 마찬가지로 for<아이템> in <원소들> 형태를 취한다.

#### 2.4.1 while 루프
#### 2.4.2 수에 대한 이터레이션: 범위와 수열
*  앞에서 설명했지만 코틀린에는 자바의 for 루프에 해당하는 요소가 없다.
* 이런 루프의 가장 흔한 용례인 초기값, 증가 값, 최종 값을 사용한 루프를 대신하기 위해 코틀린에서는 범위(range)를 사용한다.
```kotlin
val oneToTen = 1..10
```

#### 2.4.3 맵에 대한 이터레이션
* 원소를 풀어서 letter 와 binary 라는 두 변수에 저장한다.

```kotlin
val binaryReps = TreeMap<Char,String>()
for((letter, binary) in binaryReps) {
    println("$letter = $binary")
}
```
* get 과 put 을 사용하는 대신 map[key] 나 map[key] = value 를 사용해 값을 가져오고 설정할 수 있다.

#### 2.4.4 in 으로 컬렉션이나 범위의 원소 검사
* when 에서 in 사용하기
```kotlin
fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', 'A'..'Z' -> "It's a letter!"
    else -> "I don't konw.."
}
```

* in 연산자를 사용하면 값이 범위안에 속하는지 항상 결정할 수 있다.
```kotlin
println("Kotlin" in "Java".."Scala")
```

>> true

### 2.5 코틀린의 예외 처리
* 다른 클래스와 마찬가지로 예외 인스턴스를 만들 때도 new 를 붙일 필요가 없다.
* 자바와 달리 코틀린의 throw 는 식이므로 다른 식에 포함될 수 있다.
```kotlin

 var percentage =
    if (number in 0..100) {
        number
    } else {
        throw IllegalArgumentException("...")
    }

```
#### 2.5.1 try, catch, finally
* 자바와 마찬가지로 예외를 처리하려면 try, catch, finally 절을 함께 사용한다.
* 함수를 던질 수 있는 예외를 명시할 필요가 없다.
  * 자바 코드와 가장 큰 차이는 throws 절이 코드에 없다는 점이다.
* 코틀린에서는 함수가 던지는 예외를 지정하지 않고 발생한 에외를 잡아내도 되고 잡아내지 않아도 된다.

#### 2.5.3 try 를 식으로 사용
* try 를 식으로 사용하기
* 코틀린의 try 키워드는 if 나 when 과 마찬가지로 식이다. 따라서 try 의 값을 변수에 대입할 수 있다.
* try 코드 블록의 실행이 정상적으로 끝나면 그 블록의 마지막 식의 값이 결과이다.
```kotlin

 fun readNumber(reader: BufferedReader) {
     val number = try {
         Integer.parseInt(reader.readLine())
     } catch (e: NumberFormatException) {
         null
     }
 }
```
