package dataStructureKT.linkedList

class CircularlyLinkedList<T> : ILinkedList<T> {

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

    override fun removeFirst(): Boolean {
        if (isEmpty()) {
            return false
        }
        val head = tail!!.next
        if (head == tail) {
            tail = null
        } else {
            tail!!.next = head!!.next
        }
        size--
        return true
    }

    override fun removeLast(): Boolean {
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
}