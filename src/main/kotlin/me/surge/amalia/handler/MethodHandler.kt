package me.surge.amalia.handler

import java.lang.invoke.MethodHandles
import java.lang.reflect.Method
import kotlin.reflect.KClass

class MethodHandler(private val parent: Any, private val type: KClass<*>, private val method: Method) {

    var enabled = true
    private val handle = MethodHandles.lookup().unreflect(method).bindTo(parent)!!

    fun execute(event: Any) {
        if (enabled) {
            handle.invokeWithArguments(event)
        }
    }

    fun isOfType(event: Any) = event::class == type

    override fun equals(other: Any?): Boolean {
        return this === other || other is MethodHandler && other.method == this.method
    }

    override fun hashCode(): Int {
        var result = parent.hashCode()

        result = 31 * result + type.hashCode()
        result = 31 * result + method.hashCode()
        result = 31 * result + enabled.hashCode()

        return result
    }

}