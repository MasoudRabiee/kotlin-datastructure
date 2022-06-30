package dataStructureKT.linkedList

interface ILinkedList<T> {
    fun isEmpty(): Boolean
    fun first(): T
    fun last(): T
    fun addFirst(element: T)
    fun addLast(element: T)
    fun removeFirst(): T
    fun removeLast(): T
    fun clear()
}