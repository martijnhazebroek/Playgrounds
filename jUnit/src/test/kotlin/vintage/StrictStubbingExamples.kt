package vintage

import misc.RuleTests
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.rules.ExpectedException
import org.mockito.Mock
import org.mockito.exceptions.misusing.PotentialStubbingProblem
import org.mockito.exceptions.misusing.UnnecessaryStubbingException
import org.mockito.junit.MockitoJUnit
import org.mockito.quality.Strictness
import org.mockito.Mockito.`when` as at


@Category(RuleTests::class)
class StrictStubbingExamples {

    @Rule
    @JvmField
    var mockito = MockitoJUnit.rule()

    @Rule
    @JvmField
    val thrown = ExpectedException.none()

    @Mock
    private lateinit var systemUnderTest: Stubbed

    @Test
    fun succeeds() {
        mockito.strictness(Strictness.STRICT_STUBS)

        at(systemUnderTest.doThis()).thenReturn(1)
        at(systemUnderTest.shouldDoThis()).thenReturn(true)
        if (systemUnderTest.shouldDoThis()) {
            systemUnderTest.doThis()
        }
    }

    @Test
    fun failsNotCalledStrict() {
        mockito.strictness(Strictness.STRICT_STUBS)
        thrown.expect(UnnecessaryStubbingException::class.java)

        at(systemUnderTest.doThis()).thenReturn(1) // not called, therefore will throw exception
        at(systemUnderTest.shouldDoThis()).thenReturn(false)
        if (systemUnderTest.shouldDoThis()) {
            systemUnderTest.doThis()
        }
    }

    @Test
    fun failsDifferentArgStrict() {
        mockito.strictness(Strictness.STRICT_STUBS)
        thrown.expect(PotentialStubbingProblem::class.java)

        at(systemUnderTest.doThis(1)).thenReturn(1) // not called, therefore will throw exception
        at(systemUnderTest.shouldDoThis()).thenReturn(true)
        if (systemUnderTest.shouldDoThis()) {
            systemUnderTest.doThis(2)
        }
    }

    @Test
    fun warnsNotCalledSilent() {
        mockito.silent()

        at(systemUnderTest.doThis()).thenReturn(1) // not called, therefore will throw exception
        at(systemUnderTest.shouldDoThis()).thenReturn(false)
        if (systemUnderTest.shouldDoThis()) {
            systemUnderTest.doThis()
        }
    }

    interface Stubbed {
        fun doThis(arg: Int = 1): Int
        fun shouldDoThis(): Boolean
    }

}