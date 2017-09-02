package jupiter

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.RepetitionInfo
import org.junit.jupiter.api.BeforeEach


class RepeatedTestsExamples {

    private var runNumber = 1

    @BeforeEach
    fun beforeEach(testInfo: TestInfo, repetitionInfo: RepetitionInfo) {
        val currentRepetition = repetitionInfo.currentRepetition
        val totalRepetitions = repetitionInfo.totalRepetitions
        val methodName = testInfo.testMethod.get().name
        println("About to execute $currentRepetition of $totalRepetitions of $methodName")
    }

    @RepeatedTest(value = 3)
    fun nTimes() {
        println("Run number: ${runNumber} on class instance ${hashCode()}") // prints three times 1
    }

    @RepeatedTest(value = 3, name = "text in runner")
    fun nTimesWithName(testInfo: TestInfo) {
        println(testInfo.displayName)
    }

    @RepeatedTest(value = 3, name = "repetition number {currentRepetition} of {totalRepetitions}")
    fun nTimesWithNameAndRepetition(testInfo: TestInfo) {
        println("${testInfo.testMethod.get().name}: ${testInfo.displayName}")
    }
}