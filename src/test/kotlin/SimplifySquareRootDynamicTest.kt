import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith


class SimplifySquareRootDynamicTest : FunSpec({
    context("Simplication of square roots") {
        val exampleCoefficients = listOf(2, 3, 6, 10, 11, 14)
        val exampleRandicands = listOf(1, 2, 3, 6, 10, 11, 14)
        val examples = (exampleCoefficients zip exampleRandicands).map { SquareRoot(it.first, it.second) }

        examples.forEach { originalSquareRoot ->
            run {
                test(
                    "Simplified square root should be equal to ${originalSquareRoot.coefficient} sqrt(${originalSquareRoot.radicand})"
                ) {
                    val simplifiedSquareRoot = simplifySquareRoot(originalSquareRoot)

                    (simplifiedSquareRoot.coefficient sqrt simplifiedSquareRoot.radicand)
                        .shouldBe(originalSquareRoot.coefficient sqrt originalSquareRoot.radicand)
                }

                test(
                    "Simplified square root of ${originalSquareRoot.coefficient} sqrt(${originalSquareRoot.radicand}) cannot be simplified further"
                ) {
                    val simplifiedSquareRoot = simplifySquareRoot(originalSquareRoot)
                    val twiceSimplifiedSquareRoot =
                        simplifySquareRoot(simplifiedSquareRoot)

                    simplifiedSquareRoot.coefficient shouldBe twiceSimplifiedSquareRoot.coefficient
                    simplifiedSquareRoot.radicand shouldBe twiceSimplifiedSquareRoot.radicand
                }

                test("Randicand cannot be negative") {
                    run {
                        val negativeSquareRoot = originalSquareRoot
                            .copy(radicand = -1 * originalSquareRoot.radicand)
                        val exception = shouldThrow<IllegalArgumentException> {
                            simplifySquareRoot(negativeSquareRoot)
                        }
                        exception.message should startWith("Randicand cannot be negative")
                    }
                }
            }
        }
    }
})
