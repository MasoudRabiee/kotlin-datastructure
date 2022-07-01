package dataStructureKT.stack

class ArrayListStack<T> : IStack<T> {
    private var dataArray: ArrayList<T> = arrayListOf()
    var size = 0
        private set

    override fun isEmpty(): Boolean = size == 0

    override fun pop(): T {
        if (isEmpty()) {
            throw Exception("Stack is empty.")
        }
        return dataArray.removeAt(--size)
    }

    override fun top(): T {
        if (isEmpty()) {
            throw Exception("Stack is empty.")
        }
        return dataArray[size - 1]
    }

    override fun push(item: T) {
        dataArray.add(item)
        size++
    }

}

fun <T> arrayListStackOf(vararg items: T): ArrayListStack<T> {
    return ArrayListStack<T>().apply {
        items.forEach {
            push(it)
        }
    }
}