package me.surge.amalia

import me.surge.amalia.handler.*

class Bus {

    private val handlers = mutableMapOf<Any, MutableList<MethodHandler>>()

    fun subscribe(obj: Any) {
        if (handlers.containsKey(obj)) {
            handlers[obj]!!.forEach {
                it.enabled = true
            }

            return
        }

        obj.javaClass.methods.forEach { method ->
            method.isAccessible = true

            if (method.isAnnotationPresent(Listener::class.java)) {
                val parameters = method.parameters

                if (parameters.size != 1) {
                    throw IllegalStateException("Listener method requires exactly one parameter")
                }

                val handler = MethodHandler(obj, parameters[0].type.kotlin, method)

                if (!handlers.containsKey(obj)) {
                    handlers[obj] = mutableListOf(handler)
                } else {
                    handlers[obj]!!.add(handler)
                }
            }
        }
    }

    fun unsubscribe(obj: Any) {
        if (handlers.containsKey(obj)) {
            handlers[obj]!!.forEach {
                it.enabled = false
            }
        }
    }

    fun post(event: Any): Boolean {
        var result = false

        handlers.forEach { (_, list) ->
            list.forEach { handler ->
                if (handler.isOfType(event)) {
                    handler.execute(event)
                    result = true
                }
            }
        }

        return result
    }

}