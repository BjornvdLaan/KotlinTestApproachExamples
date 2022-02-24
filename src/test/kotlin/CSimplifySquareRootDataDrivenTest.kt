import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith

class CSimplifySquareRootDataDrivenTest : FunSpec({
    context("Simplification of square roots") {
        val exampleCoefficients = listOf(2, 3, 6, 10, 11, 14)
        val exampleRandicands = listOf(2, 3, 6, 10, 11, 14)

        val squareRoots = exampleRandicands.map { SquareRoot(1, it) }
        val squareRootsWithCoefficients = (exampleCoefficients zip exampleRandicands)
            .map { SquareRoot(it.first, it.second) }
        val examples = squareRoots + squareRootsWithCoefficients

        context("Simplified square root should be equal to original square root") {
            withData(examples) { originalSquareRoot ->
                run {
                    val simplifiedSquareRoot = simplifySquareRoot(originalSquareRoot)

                    (simplifiedSquareRoot.coefficient sqrt simplifiedSquareRoot.radicand)
                        .shouldBe(originalSquareRoot.coefficient sqrt originalSquareRoot.radicand)
                }
            }
        }

        context("Simplified square root cannot be simplified further") {
            withData(examples) {
                val simplifiedSquareRoot = simplifySquareRoot(it)
                val twiceSimplifiedSquareRoot =
                    simplifySquareRoot(simplifiedSquareRoot)

                simplifiedSquareRoot.coefficient shouldBe twiceSimplifiedSquareRoot.coefficient
                simplifiedSquareRoot.radicand shouldBe twiceSimplifiedSquareRoot.radicand

            }
        }

        context("Randicand cannot be negative") {
            withData(
                examples.map { SquareRoot(it.coefficient, -1 * it.radicand) }
            ) { originalSquareRoot ->
                run {
                    val exception = shouldThrow<IllegalArgumentException> {
                        simplifySquareRoot(originalSquareRoot)
                    }
                    exception.message should startWith("Randicand cannot be negative")
                }
            }
        }
    }
})
