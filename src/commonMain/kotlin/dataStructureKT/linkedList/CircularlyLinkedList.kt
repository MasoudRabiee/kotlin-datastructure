package dataStructureKT.linkedList

class CircularlyLinkedList<T> : ILinkedList<T>, Iterable<T> {

    data class Node<T>(var element: T, var next: Node<T>?)

    private var tail: Node<T>? = null
    var size: Int = 0
        private set

    override fun isEmpty(): Boolean = size == 0

    override fun first(): T {
        if (isEmpty()) {
            throw NoSuchElementException("SinglyLinkedList is Empty. ( size = 0 )")
        }
        return tail!!.next!!.element
    }

    override fun last(): T {
        if (isEmpty()) {
            throw NoSuchElementException("SinglyLinkedList is Empty. ( size = 0 )")
        }
        return tail!!.element
    }

    fun get(index: Int): T {
        if (isEmpty()) throw NoSuchElementException("SinglyLinkedList is Empty. ( size = 0 )")
        if (!checkIndex(index)) throw IndexOutOfBoundsException("Index $index out of bounds for length $size")
        return moveTo(index).element!!
    }

    fun set(value: T, index: Int) {
        if (isEmpty()) throw NoSuchElementException("SinglyLinkedList is Empty. ( size = 0 )")
        if (!checkIndex(index)) throw IndexOutOfBoundsException("Index $index out of bounds for length $size")
        moveTo(index).element = value
    }

    override fun addFirst(element: T) {
        if (isEmpty()) {
            tail = Node(element, null)
            tail!!.next = tail
        } else {
            val newest = Node(element, tail!!.next)
            tail!!.next = newest
        }
        size++
    }

    override fun addLast(element: T) {
        addFirst(element)
        tail = tail!!.next
    }

    override fun removeFirst(): T {
        if (isEmpty()) {
            throw NoSuchElementException("SinglyLinkedList is Empty. ( size = 0 )")
        }
        val head = tail!!.next
        if (head == tail) {
            tail = null
        } else {
            tail!!.next = head!!.next
        }
        size--
        return head!!.element
    }

    override fun removeLast(): T {
        for (i in 0 until size - 1) {
            rotate()
        }
        return removeFirst()
    }

    override fun clear() {
        tail = null
        size = 0
    }

    fun rotate() {
        if (tail != null) {
            tail = tail!!.next
        }
    }

    override fun toString(): String {
        val display = StringBuilder("[")
        if (!isEmpty()) {
            var print = tail!!.next
            display.append(print!!.element)
            for (i in 1 until size) {
                print = print!!.next
                display.append(", ${print!!.element}")
            }
        }
        display.append("]")
        return display.toString()
    }

    private fun checkIndex(index: Int): Boolean {
        return index >= 0
    }

    private fun moveTo(index: Int): Node<T> {
        var result = tail!!.next!!
        for (i in 1..index % size) {
            result = result.next!!
        }
        return result
    }

    override fun iterator() = object : Iterator<T> {
        var initValue = tail?.next
        var index = 0
        override fun hasNext() = index != size
        override fun next(): T {
            val currentNode = initValue
            initValue = initValue?.next
            index++
            return currentNode!!.element
        }
    }

}

fun <T> circularlyLinkedListOf(vararg elements: T): CircularlyLinkedList<T> {
    return CircularlyLinkedList<T>().apply {
        elements.forEach {
            addLast(it)
        }
    }
}