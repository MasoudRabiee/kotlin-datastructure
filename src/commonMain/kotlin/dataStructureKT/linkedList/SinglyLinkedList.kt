package dataStructureKT.linkedList

class SinglyLinkedList<T> : ILinkedList<T>, Iterable<T> {

    data class Node<T>(var element: T, var next: Node<T>?)

    var head: Node<T>? = null
        private set
    var tail: Node<T>? = null
        private set
    var size: Int = 0
        private set

    override fun isEmpty(): Boolean = size == 0

    override fun first(): T {
        if (isEmpty()) {
            throw NoSuchElementException("SinglyLinkedList is Empty. ( size = 0 )")
        }
        return head!!.element
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
        return moveTo(index).element
    }

    fun set(value: T, index: Int) {
        if (isEmpty()) throw NoSuchElementException("SinglyLinkedList is Empty. ( size = 0 )")
        if (!checkIndex(index)) throw IndexOutOfBoundsException("Index $index out of bounds for length $size")
        moveTo(index).element = value
    }

    override fun addFirst(element: T) {
        head = Node(element, head)
        if (isEmpty()) {
            tail = head
        }
        size++
    }

    override fun addLast(element: T) {
        val newest = Node(element, null)
        if (isEmpty()) {
            head = newest
        } else {
            tail!!.next = newest
        }
        tail = newest
        size++
    }

    override fun removeFirst(): Boolean {
        if (isEmpty()) return false
        head = head!!.next
        size--
        if (size == 0) {
            tail = null
        }
        return true
    }

    override fun removeLast(): Boolean {
        if (isEmpty()) return false
        var start = head
        while ((start?.next)?.next != null)
            start = start.next
        size--
        if (isEmpty()) clear()
        else {
            tail = start
            tail?.next = null
        }
        return true
    }

    override fun clear() {
        head = null
        tail = null
        size = 0
    }

    private fun checkIndex(index: Int): Boolean {
        return !(index < 0 || index > size - 1)
    }

    private fun moveTo(index: Int): Node<T> {
        var result = head
        for (i in 1..index) {
            result = result!!.next
        }
        return result!!
    }

    override fun iterator() = object : Iterator<T> {
        var initValue = head
        override fun hasNext() = initValue != null
        override fun next(): T {
            val currentNode = initValue?.element
            initValue = initValue?.next
            return currentNode!!
        }
    }

}

fun <T> singleLinkListOf(vararg elements: T): SinglyLinkedList<T> {
    return SinglyLinkedList<T>().apply {
        elements.forEach {
            addLast(it)
        }
    }
}