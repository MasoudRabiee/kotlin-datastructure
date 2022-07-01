package dataStructureKT.stack

import dataStructureKT.linkedList.SinglyLinkedList

class LinkedStack<T> : IStack<T>, Iterable<T> {

    private val singlyList = SinglyLinkedList<T>()

    override fun isEmpty(): Boolean = singlyList.isEmpty()

    override fun pop(): T = singlyList.removeFirst()

    override fun top(): T = singlyList.first()

    override fun push(item: T) = singlyList.addFirst(item)

    fun size(): Int = singlyList.size

    override fun toString(): String = singlyList.toString()

    override fun iterator(): Iterator<T> = singlyList.iterator()

}

fun <T> linkedStackOf(vararg items: T): LinkedStack<T> {
    return LinkedStack<T>().apply {
        items.forEach {
            push(it)
        }
    }
}
