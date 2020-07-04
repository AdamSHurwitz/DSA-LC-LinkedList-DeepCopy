class Node<T>(
        var prev: Node<T>? = null,
        var next: Node<T>? = null,
        var rand: Node<T>? = null,
        var data: T
)

class LinkedList<T>(
        var first: Node<T>? = null,
        var last: Node<T>? = null,
        val randMap: HashMap<Node<T>?, Node<T>?> = hashMapOf()
) {
    // Add Node to the end of LinkedList
    fun add(data: T): Node<T> {
        val temp = last
        val newNode = Node(prev = temp, data = data)
        last = newNode
        if (temp == null)
            first = newNode
        else
            temp.next = newNode
        return newNode
    }

    fun deepCopyWithoutRandoms(prev: Node<T>?, node: Node<T>?): Node<T>? {
        return if (node == null)
            null
        else {
            val newNode = Node(data = node.data)
            if (node.rand != null) {
                newNode.rand = node.rand
                randMap.put(node.rand, null)
            }
            newNode.prev = prev
            newNode.next = deepCopyWithoutRandoms(newNode, node.next)
            if (randMap.containsKey(node))
                randMap.put(node, newNode)
            return newNode
        }
    }

    fun updateRandoms(node: Node<T>?): Node<T>? {
        if (node != null) {
            if (node.rand != null)
                node.rand = randMap.get(node.rand!!)
            updateRandoms(node.next)
            return node
        } else return null
    }

    fun clear() {
        var node = first
        while (node != null) {
            node.prev = null
            node.next = null
            node.rand = null
            node.data = 0 as T
            node = node.next
        }
    }

    fun toString(first: Node<T>?): String {
        var output = ""
        var node = first
        while (node != null) {
            output += String.format("(prev:%s next:%s data:%s random:%s)\n", node.prev, node.next, node.data, node.rand)
            node = node.next
        }
        return output
    }
}