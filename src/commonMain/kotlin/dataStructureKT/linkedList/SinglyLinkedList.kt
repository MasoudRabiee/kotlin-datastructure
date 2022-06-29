package dataStructureKT.linkedList

class SinglyLinkedList<T> : ILinkedList<T> {

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

}