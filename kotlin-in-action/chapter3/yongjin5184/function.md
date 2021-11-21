## 함수 정의와 호출

### 3.1 코틀린에서 컬렉션 만들기

```kotlin
val set = hashSetOf(1, 7, 53)
```

```kotlin
println(set.javaClass)
```
> class java.util.HashSet

```kotlin
print(list.javaClass)
```
> class java.util.ArrayList

```kotlin
print(map.javaClass)
```
> class java.util.HashMap

* 이는 코틀린이 자신만의 컬렉션 기능을 제공하지 않는다는 뜻이다.
* 코틀린이 자체 컬렉션을 제공하지 않는 이유는 뭘까? 표준 자바 컬렉션을 활용하면 자바 코드와 상호작용하기가 훨씬 더 쉽다.

### 3.2 함수를 호출하기 쉽게 만들기
#### 3.2.1 이름 붙인 인자
```java
joinToString(collection, /* separator */ " ", /* prefix */ " ",/* postfix */ ".");
```

* 코틀린에서는 다음과 같이 더 잘 할 수 있다.
```kotlin
joinToString(ccollection, separator = " ", prefix =" ", postfix = ".")
```

* 코틀린으로 작성한 함수를 호출할 때는 함수에 전달하는 인자 중 일부(또는 전부)의 이름을 명시할 수 있다.
* 호출 시 인자 중 어느 하나라도 이름을 명시하고 나면 혼동을 막기 위해 그 뒤에 오는 모든 인자는 이름을 꼭 명시해야한다.

#### 3.2.2 디폴트 파리미터 값
* 코틀린에서는 함수 선언에서 파라미터의 디폴트 값을 지정할 수 있으므로 이런 오버로드 중 상당수를 피할 수 있다.
```kotlin
fun <T> joinToString(
        collection: Collection<T>,
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""
): String
```

* 이제 함수를 호출할 때, 모든 인자를 쓸 수도 있고, 일부를 생략할 수도 있다.

```kotlin
joinToString(list, ", " , "", "")
```
> 1, 2, 3

```kotlin
joinToString(list)
```
> 1, 2, 3

```kotlin
joinToString(list, "; ")
```
> 1; 2; 3

* 함수의 디폴트 파리마터 값은 함수를 호출하는 쪽이 아니라 함수 선언 쪽에서 지정된다는 사실을 기억하라
* 따라서, 어떤 클래스 안에 정의된 함수의 **디폴트 값을 바꾸고 그 클래스가 포함된 파일을 재컴파일** 하면 
  **그 함수를 호출하는 코드 중에 값을 지정하지 않은 모든 인자는 자동으로 바뀐 디폴트 값을 적용**받는다. 

##### 3.2.3 정적인 유틸리티 클래스 없애기: 최상위 함수와 프로퍼티
* joinToString() 함수를 최상위 함수로 선언하기
```kotlin
package strings
fun joinToString( ... ): String{ ... }
```

```java
package strings;
public class JoinKt {
  public static String joinToString(...) { ... }
}
```

* 코틀린 컴파일러가 생성하는 클래스의 이름은 최상위 함수가 들어있던 코틀린 소스 파일의 이름과 대응한다.
* 코틀린 파일의 모든 최상위 함수는 이클래스의 정적인 메소드가 된다.

### 3.3 메소드를 다른 클래스에 추가: 확장 함수와 확장 프로퍼티
* 코틀린을 기존 자바 프로젝트에 통합하는 경우에는 코틀린으로 직접 변환할 수 없거나 
  미처 변환하지 않은 기존 자바 코드를 처리할 수 있어야 한다.
* 이런 기존 자바 API 를 재작성하지 않고도 코틀린이 제공하는 여러 편리한 기능을 사용할 수 있다면 정말 좋은 일 아닐까?
* 바로 확장 함수(extension function)가 그런 역할을 해줄 수 있다.

```kotlin
fun String.lastChar(): Char = this.get(this.length - 1)
```

* 어떤 면에서는 이는 String 클래스에 새로운 메소드를 추가하는 것과 같다. String 클래스가 여러분이 직접 작성한 코드가 아니고 
심지어 String 클래스의 소스코드를 소유한 것도 아니지만, 여러분은 여전히 원하는 메소드를 String 클래스에 추가할 수 있다.
* 하지만 확장 함수가 캡슐화를 깨지는 않는다는 사실을 기억하라.
* 클래스 안에서 정의한 메소드와 달리 확장 함수 안에서는 클래스 내부에서만 사용할 수 있는 비공개(private) 멤버나 
  보호된(protected) 멤버를 사용할 수 없다.
  
#### 3.3.1 임포트와 확장 함수
```kotlin
import strings.lastChar
val c = "Kotlin".lastChar()
```

```kotlin
import strings.lastChar as last
val c = "Kotlin".last()
```
* 코틀린 문법상 확장 함수는 반드시 짧은 이름을 써야 한다. 
  따라서 임포트할 때 이름을 바꾸는 것이 확장 함수 이름 충돌을 해결할 수 있는 유일한 방법이다.
  
#### 3.3.2 자바에서 확장 함수 호출
* 내부적으로 확장 함수는 수신 객체를 첫 번째 인자로 받는 정적 메소드다. 
  그래서 확장 함수를 호출해도 다른 어댑터(adapter) 객체나 실행 시점 부가 비용이 들지 않는다.
  
```java
char c = StringUtilKt.lastChar("Java");
```

#### 3.3.3 확장 함수로 유틸리티 함수 정의
* joinToString() 를 확장으로 정의하기
```kotlin
fun <T>Collection<T>.joinToString(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix);
    return result.toString()
}
```
```kotlin
val list = listOf(1, 2, 3)
println(list.joinToString(separator = "; ", postfix = "(", postfix =")"))
```
> (1; 2; 3)

#### 3.3.4 확장 함수는 오버라이드할 수 없다
* 코틀린의 메소드 오버라이드도 일반적인 객ㅊ체지향의 메소드 오버라이드와 마찬가지다. 하지만 확장 함수는 오버라이드할 수 없다.

* 멤버 함수 오버라이드하기

```kotlin
open class View {
    open fun click() = println("View clicked")
}

class Button : View() {
    override fun click() {
        println("Button clicked")
    }
}
```

```kotlin
val view: View = Button
view.click()
```
> Button clicked

* 확장 함수는 클래스의 일부가 아니다. 확장 함수는 클래스 밖에서 선언된다. 
  이름과 파라미터가 완전히 같은 확장 함수를 기반 클래스와 하위 클래스에 대해 정의해도 실제로는 확장 함수를 호출할 때 수신 객체로
  지정한 변수의 정적 타입에 의해 어떤 확장 함수가 호출될지 결정되지, 그 변수에 저장된 객체의 동적인 타입에 의해 확장 함수가
  결정되지 않는다.

```kotlin

 import java.awt.Buttonfun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a button")

val view:View = Button
view.showOff()
```
> I'm a view 
* 확장 함수는 정적으로 결정된다.
* 확장 함수를 첫 번째 인자가 수신 객체인 정적 자바 메소드로 컴파일한다는 사실을 기억한다면 이런 동작을 쉽게 이해할 수 있다.
* 어떤 클래스를 확장한 함수와 그 클래스의 멤버 함수의 이름과 시그니처가 같다면 확장 함수가 아니라 멤버 함수가 호출된다.
  (멤버 함수의 우선순위가 더 높다)
#### 3.3.5 확장 프로퍼티
