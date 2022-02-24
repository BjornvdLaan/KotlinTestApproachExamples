import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.negativeInt
import io.kotest.property.arbitrary.nonNegativeInt
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.checkAll

/**
 * NOTE: Part of these tests fail for exemplary purposes.
 */
class DSimplifySquareRootPropertyBasedTest : FunSpec({
    context("Simplification of square roots") {
        val squareRootArb = arbitrary {
            SquareRoot(
                coefficient = Arb.positiveInt().bind(),
                radicand = Arb.positiveInt().bind()
            )
        }

        context("Simplified square root should be equal to original square root (using division)") {
            squareRootArb.checkAll { originalSquareRoot ->
                run {
                    val simplifiedSquareRoot = simplifySquareRoot(originalSquareRoot)

                    (simplifiedSquareRoot.coefficient sqrt simplifiedSquareRoot.radicand)
                        .shouldBe(originalSquareRoot.coefficient sqrt originalSquareRoot.radicand)
                }
            }
        }

        context("Simplified square root should be equal to original square root (without division)") {
            squareRootArb.checkAll { originalSquareRoot ->
                run {
                    val simplifiedSquareRoot = simplifySquareRoot(originalSquareRoot)

                    ((simplifiedSquareRoot.coefficient * simplifiedSquareRoot.coefficient) * simplifiedSquareRoot.radicand)
                        .shouldBe((originalSquareRoot.coefficient * originalSquareRoot.coefficient) * originalSquareRoot.radicand)
                }
            }
        }

        context("Simplified square root should be equal to original square root (simple arb, without division)") {
            Arb.positiveInt().checkAll { radicand ->
                run {
                    val originalSquareRoot = SquareRoot(1, radicand)
                    val simplifiedSquareRoot = simplifySquareRoot(originalSquareRoot)

                    ((simplifiedSquareRoot.coefficient * simplifiedSquareRoot.coefficient) * simplifiedSquareRoot.radicand)
                        .shouldBe((originalSquareRoot.coefficient * originalSquareRoot.coefficient) * originalSquareRoot.radicand)
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

        val negativeSquareRootArb = arbitrary {
            SquareRoot(
                coefficient = Arb.nonNegativeInt().bind(),
                radicand = Arb.negativeInt().bind()
            )
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
