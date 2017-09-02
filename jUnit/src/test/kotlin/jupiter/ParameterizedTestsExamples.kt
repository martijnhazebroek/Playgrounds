package jupiter

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.concurrent.TimeUnit
import java.util.stream.IntStream
import java.util.stream.Stream


class ParameterizedTestsExamples {

    @ParameterizedTest
    @ValueSource(strings = ["Hello", "World"])
    fun stringSource(value: String) {
        println(value)
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit::class, names = ["DAYS", "HOURS"])
    fun enumSourceInclude(value: TimeUnit) {
        println(value)
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit::class, mode = EnumSource.Mode.EXCLUDE, names = ["DAYS", "HOURS"])
    fun enumSourceExclude(value: TimeUnit) {
        println(value)
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit::class, mode = EnumSource.Mode.MATCH_ALL, names = ["^.+SECONDS$"])
    fun enumSourceMatches(value: TimeUnit) {
        println(value)
    }

    @ParameterizedTest
    @MethodSource(value = "sourceSingle")
    fun methodSourceSingle(value: Int) {
        println(value)
    }

    @ParameterizedTest
    @MethodSource(value = "sourcePlural")
    fun methodSourcePlural(first: String, second: Int) {
        println("$first $second")
    }

    companion object {

        @JvmStatic
        fun sourceSingle() = IntStream.of(1, 2, 3)

        @JvmStatic
        fun sourcePlural(): Stream<Arguments> =
                Stream.of(Arguments.of("foo", 1), Arguments.of("bar", 2))
    }
}