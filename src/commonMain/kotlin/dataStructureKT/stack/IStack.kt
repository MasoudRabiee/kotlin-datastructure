package dataStructureKT.stack

interface IStack<T> {
    fun isEmpty(): Boolean
    fun push(item: T)
    fun pop(): T
    fun top(): T
}