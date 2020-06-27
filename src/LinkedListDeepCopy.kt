/*
  Return a deep copy of a double LinkedList.
   • Each node also contains an additional random pointer, potentially to any node or null.
*/

fun main() {
    val linkedList = LinkedList()
    val node1 = Node(1)
    val node2 = Node(2)
    val node3 = Node(3)
    node1.next = node2
    node1.random = node3
    node2.previous = node1
    node2.next = node3
    node3.previous = node2
    node3.random = node1

    val deepCopy = linkedList.deepCopy(node1)

    // LinkedList data changed.
    node1.data = 101
    node2.data = 202
    node3.data = 303
    val shallowCopy = linkedList.shallowCopy(node1)

    println("Deep copy made")
    linkedList.print(deepCopy)

    println()
    println("Shallow copy made")
    linkedList.print(shallowCopy)
}

data class Node<T>(
        var data: T?,
        var previous: Node<T>? = null,
        var next: Node<T>? = null,
        var random: Node<T>? = null
)

class LinkedList {
    fun <T> deepCopy(node: Node<T>?): Node<T>? {
        if (node != null) {
            return Node(
                    data = node.data,
                    previous = newCopy(node.previous),
                    next = deepCopy(node.next),
                    random = newCopy(node.random)
            )
        } else return null
    }

    fun <T> newCopy(node: Node<T>?): Node<T>? {
        if (node != null) {
            return Node(
                    data = node.data,
                    previous = node.previous,
                    next = node.next,
                    random = node.random
            )
        } else return null
    }

    fun <T> shallowCopy(node: Node<T>?) = node!!.copy()

    fun <T> print(node: Node<T>?){
        if (node != null){
            println("Node data:${node.data} previous:${node.previous?.data} next:${node.next?.data} random:${node.random?.data}")
            print(node.next)
        }
    }
}