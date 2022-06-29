package dataStructureKT.linkedList

interface ILinkedList<T> {
    fun isEmpty(): Boolean
    fun first(): T
    fun last(): T
    fun addFirst(element: T)
    fun addLast(element: T)
    fun removeFirst(): Boolean
    fun removeLast(): Boolean
    fun clear()
}