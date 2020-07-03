class Node(
        var prev: Node? = null,
        var data: Int,
        var next: Node? = null
)

// TODO: Generic 'Node<T>'
class LinkedList(
        var first: Node? = null,
        var last: Node? = null
) {
    // Add Node to the end of LinkedList
    fun add(data: Int) {
        val temp = last
        val newNode = Node(prev = temp, data = data)
        last = newNode
        if (temp == null)
            first = newNode
        else
            temp.next = newNode
    }

    // TODO: Random pointer
    //  Map with the old nodes as key and the new node as value
    fun deepCopy(prev: Node?, node: Node?): Node? {
        return if (node == null) null
        else return Node(data = node.data).also { newNode ->
            newNode.prev = prev
            newNode.next = deepCopy(newNode, node.next)
        }
    }

    fun clear() {
        var node = first
        while (node != null) {
            node.prev = null
            node.data = 0
            node.next = null
            node = node.next
        }
    }

    fun toString(first: Node?): String {
        var output = ""
        var node = first
        while (node != null) {
            output += String.format("(prev:%s data:%s next:%s)\n", node.prev, node.data, node.next)
            node = node.next
        }
        return output
    }
}