### CHAPTER 11 동시성

#### 스레드 이용하기
#### 코드를 병렬로 실행하려면 어떻게 해야하는가?
* Runnable 인터페이스는 public void run() 라는 메서드가 있는데, 스레드를 실행하는데 이 run 매서드를 호출하면 안된다는 점은 기억해두자.
* Thread 클래스는 병렬 실행이 가능한 스레드를 만들 수 있도록 JVM 을 호출한다는 점에서 특별하다.

#### Thread 클래스와 Executor 인터페이스의 차이점은 무엇인가?
* 스레드 풀을 이용함으로써 새로운 스레드를 사용하기 보다는 필요할 때 스레드를 가져오게 하거나 이전 코드에서 실행이 완료되었을 때 스레드를 재사용할 수 있게 하는 것이 좋다.

#### 동시성 작업하기
#### 스레드 사이의 공유 상태는 어떻게 관리하는가?
* synchronized(object) 코드를 감싸서 한 번에 스레드 하나만 코드 블록 안에서 실행할 수 있게 한다.

#### 왜 불변 객체를 사용해야 하는가
* 불변 객체를 사용했을 때의 장점은 값이 변하지 않기 때문에 스레드에서 스레드 사이로 락을 사용하지 않고 값을 전달해도 된다는 것이다.
* 이는 동기화된 코드 블록 사이에서 변하기 쉬운 값 하나를 이용할 수 있을 때까지 대기하느라 멀티스레드에 병목이 발생하는 일이 없어진다는 의미다.

#### 액터
#### Akka 란 무엇인가?
* Akka 는 동시 코드를 작성하고 작업할 때 다른 접근법을 제공해주는 프레임워크로, 액터 모델을 사용하며 함수형 프로그래밍 언어인 Erlang 에서 영감을 받았다.
* 액터는 메시지를 처리하도록 설계되었고, Akka 프레임워크는 이메시지를 분산하고 전달하는 데 한몫한다.
* 이는 스레드와 락의 동시성에 신경을 쓰기보다는 메시지의 흐름에 관심을 두면서 애플리케이션을 설계할 수 있게 해준다.
