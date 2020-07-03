package test

import LinkedList
import Node
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Test {
    val linkedList = LinkedList()
    var expectString = linkedList.toString(linkedList.first)
    var actual: Node? = Node(data = 0)
    var actualString = ""

    @Test
    @Order(1)
    fun `Create deep copy`() {
        linkedList.add(1)
        linkedList.add(2)
        linkedList.add(3)
        linkedList.add(4)
        expectString = linkedList.toString(linkedList.first)
        actual = linkedList.deepCopy(
                prev = null,
                node = linkedList.first
        )
        actualString = linkedList.toString(actual)
        printAssert(actualString, actualString.isNotEmpty())
        assertThat(actualString).isNotEmpty()
    }

    @Nested
    inner class testNodes {
        @Test
        @Order(2)
        fun `Test 'next' and 'prev'`() {
            println(actualString)
            println("node.next.prev == node")
            var currentNode = actual
            var nodeNextPrev = currentNode?.next?.prev
            while (currentNode?.next != null) {
                printAssert(nodeNextPrev == currentNode, true)
                assertThat(nodeNextPrev == currentNode).isEqualTo(true)
                currentNode = currentNode.next
                nodeNextPrev = currentNode?.next?.prev
            }
        }

        @Test
        @Order(3)
        fun `Test after 'first' node is cleared`() {
            linkedList.clear()
            val expectClearString = linkedList.toString(Node(null, 0, null))
            val actualClearString = linkedList.toString(linkedList.first)
            printAssert(actualClearString, expectClearString)
            assertThat(actualClearString).isEqualTo(expectClearString)
            printAssert(actualString, actualString.isNotEmpty())
            assertThat(actualString).isNotEmpty()
        }
    }

}