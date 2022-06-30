package dataStructureKT.stack

class MutableStack<T>(vararg items: T) : IStack<T> {

    private val elements = items.toMutableList()

    override fun isEmpty(): Boolean = elements.isEmpty()
    override fun pop(): T {
        if (isEmpty()) {
            throw Exception("Stack is Empty. (size = 0)")
        } else return elements.removeAt(elements.size - 1)
    }

    override fun top(): T {
        if (isEmpty()) {
            throw Exception("Stack is Empty. (size = 0)")
        } else return elements.last()
    }

    override fun push(item: T) = elements.add(item)

    fun size(): Int = elements.size

    override fun toString() = "MutableStack(${elements.joinToString()})"
}