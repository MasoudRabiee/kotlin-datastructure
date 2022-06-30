package dataStructureKT.linkedList

class DoublyLinkedList<T> : ILinkedList<T>, Iterable<T> {

    data class Node<T>(var element: T, var prev: Node<T>?, var next: Node<T>?)

    private var header: Node<T?> = Node(null, null, null)
    private var trailer: Node<T?> = Node(null, header, null)
    var size: Int = 0
        private set

    init {
        header.next = trailer
    }

    override fun isEmpty(): Boolean = size == 0

    override fun first(): T {
        if (isEmpty()) {
            throw NoSuchElementException("SinglyLinkedList is Empty. ( size = 0 )")
        }
        return header.next!!.element!!
    }

    override fun last(): T {
        if (isEmpty()) {
            throw NoSuchElementException("SinglyLinkedList is Empty. ( size = 0 )")
        }
        return trailer.prev!!.element!!
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

    override fun addFirst(element: T) = addBetween(element, header, header.next)

    override fun addLast(element: T) = addBetween(element, trailer.prev, trailer)

    override fun removeFirst(): T {
        if (isEmpty()) throw NoSuchElementException("SinglyLinkedList is Empty. ( size = 0 )")
        return remove(header.next!!)
    }

    override fun removeLast(): T {
        if (isEmpty()) throw NoSuchElementException("SinglyLinkedList is Empty. ( size = 0 )")
        return remove(trailer.prev!!)
    }

    override fun clear() {
        header.next = null
        size = 0
    }

    override fun toString(): String {
        val display = StringBuilder("[")
        if (!isEmpty()) {
            var print = header.next
            display.append(print!!.element)
            for (i in 1 until size) {
                print = print!!.next
                display.append(", ${print!!.element}")
            }
        }
        display.append("]")
        return display.toString()
    }

    private fun addBetween(t: T, predecessor: Node<T?>?, successor: Node<T?>?) {
        val newest = Node(t, predecessor, successor)
        predecessor?.next = newest
        successor?.prev = newest
        size++
    }

    private fun remove(node: Node<T?>): T {
        val predecessor = node.prev
        val successor = node.next
        predecessor?.next = successor
        successor?.prev = predecessor
        size--
        return node.element!!
    }

    private fun checkIndex(index: Int): Boolean {
        return !(index < 0 || index > size - 1)
    }

    private fun moveTo(index: Int): Node<T?> {
        var result = header.next!!
        for (i in 1..index) {
            result = result.next!!
        }
        return result
    }

    override fun iterator() = object : Iterator<T> {
        var initNode = header.next as? Node<T>?
        override fun hasNext(): Boolean = initNode != trailer
        override fun next(): T {
            val currentNode = initNode!!
            initNode = initNode!!.next
            return currentNode.element
        }
    }
}

fun <T> doublyLinkedListOf(vararg elements: T): DoublyLinkedList<T> {
    return DoublyLinkedList<T>().apply {
        elements.forEach {
            addLast(it)
        }
    }
}