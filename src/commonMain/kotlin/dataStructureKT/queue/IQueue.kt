package dataStructureKT.queue

interface IQueue<T> {
    fun isEmpty(): Boolean
    fun enqueue(item: T)
    fun dequeue(): T
    fun first(): T
}