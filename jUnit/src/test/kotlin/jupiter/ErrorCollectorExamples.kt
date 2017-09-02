package jupiter

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Rule
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport
import org.junit.rules.ErrorCollector


@Tag("backwards-compatibility")
@EnableRuleMigrationSupport
class ErrorCollectorExamples {

    @Rule
    @JvmField
    val errorCollector = ErrorCollector()

    @Test
    fun fails() {
        val testNumeric = 1
        errorCollector.checkThat(testNumeric, equalTo(2))
        errorCollector.checkThat(testNumeric.isEven(), equalTo(true))
    }

    @Test
    fun succeeds() {
        val testNumeric = 2
        errorCollector.checkThat(testNumeric, equalTo(testNumeric))
        errorCollector.checkThat(testNumeric.isEven(), equalTo(true))
    }

    @Test
    fun addError() {
        (1..4).filter { it.isEven() }.forEach { errorCollector.addError(Exception("%2 != 0")) }
    }

    private fun Int.isEven(): Boolean = this % 2 == 0
}