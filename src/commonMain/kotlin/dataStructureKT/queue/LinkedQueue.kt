package dataStructureKT.queue

import dataStructureKT.linkedList.SinglyLinkedList

class LinkedQueue<T> : IQueue<T>, Iterable<T> {

    private val singlyLinked = SinglyLinkedList<T>()

    override fun isEmpty(): Boolean = singlyLinked.isEmpty()

    override fun dequeue(): T = singlyLinked.removeFirst()

    override fun first(): T = singlyLinked.first()

    override fun enqueue(item: T) = singlyLinked.addLast(item)

    fun size(): Int = singlyLinked.size

    override fun toString(): String = singlyLinked.toString()

    override fun iterator(): Iterator<T> = singlyLinked.iterator()

}

fun <T> linkedQueueOf(vararg items: T): LinkedQueue<T> {
    return LinkedQueue<T>().apply {
        items.forEach {
            enqueue(it)
        }
    }
}
