import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.negativeInt
import io.kotest.property.arbitrary.nonNegativeInt
import io.kotest.property.checkAll

class SimplifySquareRootPropertyBasedTest : FunSpec({
    context("Simplication of square roots") {
        val squareRootArb = arbitrary {
            SquareRootBI(
                coefficient = Arb.int().bind().toBigInteger(),
                radicand = Arb.nonNegativeInt().bind().toBigInteger()
            )
        }

        val negativeSquareRootArb = arbitrary {
            SquareRootBI(
                coefficient = Arb.int().bind().toBigInteger(),
                radicand = Arb.negativeInt().bind().toBigInteger()
            )
        }

        context("Simplified square root should be equal to original square root") {
            squareRootArb.checkAll { originalSquareRoot ->
                run {
                    val simplifiedSquareRoot = simplifySquareRoot(originalSquareRoot)

                    (simplifiedSquareRoot.coefficient.pow(2).multiply(simplifiedSquareRoot.radicand))
                        .shouldBe((originalSquareRoot.coefficient.pow(2).multiply(originalSquareRoot.radicand)))
                }
            }
        }

        context("Simplified square root cannot be simplified further") {
            squareRootArb.checkAll { originalSquareRoot ->
                val simplifiedSquareRoot = simplifySquareRoot(originalSquareRoot)
                val twiceSimplifiedSquareRoot =
                    simplifySquareRoot(simplifiedSquareRoot)

                simplifiedSquareRoot.coefficient shouldBe twiceSimplifiedSquareRoot.coefficient
                simplifiedSquareRoot.radicand shouldBe twiceSimplifiedSquareRoot.radicand

            }
        }

        context("Randicand cannot be negative") {
            negativeSquareRootArb.checkAll { squareRoot ->
                run {
                    val exception = shouldThrow<IllegalArgumentException> {
                        simplifySquareRoot(squareRoot)
                    }
                    exception.message should startWith("Randicand cannot be negative")
                }
            }
        }
    }
})
