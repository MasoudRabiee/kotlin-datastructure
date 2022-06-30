package dataStructureKT.linkedList

class DoublyLinkedList<T> : ILinkedList<T> {

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

    override fun addFirst(element: T) = addBetween(element, header, header.next)

    override fun addLast(element: T) = addBetween(element, trailer.prev, trailer)

    override fun removeFirst(): Boolean {
        if (isEmpty()) return false
        remove(header.next)
        return true
    }

    override fun removeLast(): Boolean {
        if (isEmpty()) return false
        remove(trailer.prev)
        return true
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

    private fun remove(node: Node<T?>?) {
        val predecessor = node?.prev
        val successor = node?.next
        predecessor?.next = successor
        successor?.prev = predecessor
        size--
    }
}