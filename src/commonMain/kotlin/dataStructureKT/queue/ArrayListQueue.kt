package dataStructureKT.queue

class ArrayListQueue<T> : IQueue<T> {

    private val dataArray = arrayListOf<T>()
    var size = 0
        private set

    override fun isEmpty(): Boolean = size == 0

    override fun dequeue(): T {
        if (isEmpty()) {
            throw NoSuchElementException("Queue is empty.")
        }
        size--
        return dataArray.removeAt(0)
    }

    override fun first(): T {
        if (isEmpty()) {
            throw NoSuchElementException("Queue is empty.")
        }
        return dataArray.first()
    }

    override fun enqueue(item: T) {
        dataArray.add(item)
        size++
    }

    override fun toString(): String {
        val display = StringBuilder("(")
        if (!isEmpty()) {
            display.append(dataArray.first())
            for (i in 1 until size) {
                display.append(", ${dataArray[i]}")
            }
        }
        display.append(")")
        return display.toString()
    }

}

fun <T> arrayListQueueOf(vararg items: T): ArrayListQueue<T> {
    return ArrayListQueue<T>().apply {
        items.forEach {
            enqueue(it)
        }
    }
}
