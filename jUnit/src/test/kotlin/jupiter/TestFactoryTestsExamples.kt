package jupiter

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicContainer.dynamicContainer
import org.junit.jupiter.api.DynamicNode
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream


class TestFactoryTestsExamples {

    @TestFactory
    fun dynamicTest(): Iterable<DynamicTest> {
        return setOf(
                dynamicTest("test not null") { assertNotNull("") },
                dynamicTest("test null") { assertNull(null) }
        )
    }

    @TestFactory
    fun dynamicTestsWithContainers(): Stream<DynamicNode> {
        // Generates 9 tests.
        return Stream.of("test 1", "test 2", "test 3")
                .map { input ->
                    dynamicContainer("Container for $input",
                            Stream.of(
                                    dynamicTest("test not null") { assertNotNull(input) },
                                    dynamicContainer("Container in container",
                                            Stream.of(
                                                    dynamicTest("isNotEmpty") { assertTrue(input.isNotEmpty()) },
                                                    dynamicTest("!isEmpty") { assertFalse(input.isEmpty()) }
                                            )
                                    )
                            )
                    )
                }
    }

}