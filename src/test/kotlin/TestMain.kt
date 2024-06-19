import me.surge.amalia.Bus
import me.surge.amalia.handler.Listener

object TestMain {

    val bus = Bus()

    @JvmStatic fun main(args: Array<String>) {
        val dummy = Dummy()
        val dummy2 = SecondDummy()

        bus.subscribe(dummy)
        bus.subscribe(dummy2)

        bus.post("hello, world!")
        bus.post(3)
        bus.post(Event(true))
    }

    class Dummy {

        @Listener fun stringListener(message: String) {
            println("STR: $message")
        }

        @Listener fun intListener(number: Int) {
            println("INT: $number")
        }

        @Listener fun eventListener(event: Event) {
            println("EVT: ${event.flag}")
        }

    }

    class SecondDummy {

        @Listener fun stringListener(message: String) {
            println("STR 2: $message")
        }

    }

    data class Event(val flag: Boolean)

}
