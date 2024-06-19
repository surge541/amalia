# Amalia
 A lightweight event bus written in, and intended for use in, Kotlin

## Usage
<details>
<summary>Initialising a bus</summary>

```kotlin
Bus()
```

<i>with context</i>
```kotlin
fun main(args: Array<String>) {
    val bus = Bus()
}
```
</details>

<details>
<summary>Subscribing an object</summary>

```kotlin
Bus().subscribe(obj)
```

<i>with context</i>
```kotlin
class Obj {}

fun main(args: Array<String>) {
    val obj = Obj()
    val bus = Bus()
    
    bus.subscribe(obj)
}
```

</details>

<details>
<summary>Creating a listener</summary>

```kotlin
@Listener
fun methodName(event: Type) {

}
```

<i>with context</i>

```kotlin
class Obj {
    
    @Listener
    fun stringListener(message: String) {
        // when a string object is posted through the event bus,
        // the string will be printed to the console
        println(message)
    }
    
}

fun main(args: Array<String>) {
    val obj = Obj()
    val bus = Bus()
    
    bus.subscribe(obj)
}
```

</details>

<details>
<summary>Posting an event</summary>

```kotlin
Bus().post(obj)
```

<i>with context</i>

```kotlin
class Obj {
    
    @Listener
    fun stringListener(message: String) {
        // when a string object is posted through the event bus,
        // the string will be printed to the console
        println(message)
    }
    
}

fun main(args: Array<String>) {
    val obj = Obj()
    val bus = Bus()
    
    bus.subscribe(obj)

    // "hello, world!" will be printed to the console due to
    // the listener in `Obj`
    bus.post("hello, world!")
}
```

</details>

<details>

<summary>Unsubscribing an object</summary>

```kotlin
Bus().unsubscribe(obj)
```

<i>with context</i>

```kotlin
class Obj {
    
    @Listener
    fun stringListener(message: String) {
        // when a string object is posted through the event bus,
        // the string will be printed to the console
        println(message)
    }
    
}

fun main(args: Array<String>) {
    val obj = Obj()
    val bus = Bus()
    
    bus.subscribe(obj)

    // "hello, world!" will be printed to the console due to
    // the listener in `Obj`
    bus.post("hello, world!")
    
    bus.unsubscribe(obj)
    
    // nothing will be printed to the console, as we have
    // unsubscribed the only object with events to be
    // posted to
    bus.post("hello again!")
}
```

</details>