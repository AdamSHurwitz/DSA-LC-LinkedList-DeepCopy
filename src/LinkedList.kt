// Todo: Generic 'Node<T>'
class Node(
        var prev: Node? = null,
        var next: Node? = null,
        var rand: Node? = null,
        var data: Int
)

class LinkedList(
        var first: Node? = null,
        var last: Node? = null,
        val randMap: HashMap<Node?, Node?> = hashMapOf()
) {
    // Add Node to the end of LinkedList
    fun add(data: Int): Node {
        val temp = last
        val newNode = Node(prev = temp, data = data)
        last = newNode
        if (temp == null)
            first = newNode
        else
            temp.next = newNode
        return newNode
    }

    fun deepCopyWithoutRandoms(prev: Node?, node: Node?): Node? {
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

    fun updateRandoms(node: Node?): Node? {
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
            node.data = 0
            node = node.next
        }
    }

    fun toString(first: Node?): String {
        var output = ""
        var node = first
        while (node != null) {
            output += String.format("(prev:%s next:%s data:%s random:%s)\n", node.prev, node.next, node.data, node.rand)
            node = node.next
        }
        return output
    }
}