package dataStructureKT.stack

interface IStack<T> {
    fun isEmpty(): Boolean
    fun push(t: T): Boolean
    fun pop(): T
    fun top(): T
}