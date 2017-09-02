package vintage

import misc.RuleTests
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.mockito.junit.MockitoJUnit
import org.mockito.Mockito.`when` as at
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.experimental.categories.Category


@Category(RuleTests::class)
class ExternalResourceExamples {

    @Rule
    @JvmField
    var mockito = MockitoJUnit.rule()

    @Rule
    @JvmField
    val resource: ExternalResource = object : ExternalResource() {
        override fun before() {
            isConnected = true
            println("connecting to server")
        }

        override fun after() {
            isConnected = false
            println("disconnecting from server")
        }
    }

    private var isConnected = false

    @Test
    fun externalResourceSuccess() {
        assertThat(isConnected, equalTo(true))
    }

}