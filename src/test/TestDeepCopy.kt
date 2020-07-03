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
class TestDeepCopy {
    val linkedList = LinkedList()
    var expectString = linkedList.toString(linkedList.first)
    var actual: Node? = Node(data = 0)
    var actualString = ""

    @Test
    @Order(1)
    fun `Create deep copy and update rands`() {
        // Setup
        val node1 = linkedList.add(data = 1)
        val node2 = linkedList.add(data = 2)
        val node3 = linkedList.add(data = 3)
        val node4 = linkedList.add(data = 4)
        node1.rand = node4
        node3.rand = node2
        expectString = linkedList.toString(linkedList.first)

        // Deep copy
        actual = linkedList.deepCopyWithoutRandoms(
                prev = null,
                node = linkedList.first
        )
        actual = linkedList.updateRandoms(actual)

        // Test
        actualString = linkedList.toString(actual)
        printAssert(actualString, actualString.isNotEmpty())
        assertThat(actualString).isNotEmpty()
    }

    @Nested
    inner class testNodes {
        @Test
        @Order(2)
        fun `Test 'next', 'prev', and 'rand'`() {
            println(actualString)
            println("node.next.prev == node")
            var currentNode = actual
            var nodeNextPrev = currentNode?.next?.prev
            while (currentNode?.next != null) {
                printAssert(nodeNextPrev, currentNode)
                assertThat(nodeNextPrev == currentNode).isEqualTo(true)
                if (currentNode.rand != null) {
                    val oldRandoms = linkedList.randMap.keys
                    printAssert(oldRandoms.contains(currentNode.rand), false)
                    assertThat(oldRandoms.contains(currentNode.rand)).isEqualTo(false)
                    val newRandoms = linkedList.randMap.values
                    printAssert(newRandoms.contains(currentNode.rand), true)
                    assertThat(newRandoms.contains(currentNode.rand)).isEqualTo(true)
                }
                currentNode = currentNode.next
                nodeNextPrev = currentNode?.next?.prev
            }
        }

        @Test
        @Order(3)
        fun `Test after 'first' node is cleared`() {
            linkedList.clear()
            val expectClearString = linkedList.toString(Node(data = 0))
            val actualClearString = linkedList.toString(linkedList.first)
            printAssert(actualClearString, expectClearString)
            assertThat(actualClearString).isEqualTo(expectClearString)
            printAssert(actualString, actualString.isNotEmpty())
            assertThat(actualString).isNotEmpty()
        }
    }
}