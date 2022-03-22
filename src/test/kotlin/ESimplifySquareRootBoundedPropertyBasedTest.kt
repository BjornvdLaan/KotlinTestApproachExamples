import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.checkAll

/**
 * NOTE: Part of these tests FAIL for exemplary purposes.
 */
class ESimplifySquareRootBoundedPropertyBasedTest : FunSpec({
    context("Simplification of square roots") {

        val squareRootArb = arbitrary {
            SquareRoot(
                coefficient = Arb.positiveInt(10000).bind(),
                radicand = Arb.positiveInt(10000).bind()
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

                    val originalDesimplifiedRadicand =
                        (originalSquareRoot.coefficient * originalSquareRoot.coefficient) * originalSquareRoot.radicand
                    val simplifiedDesimplifiedRadicand =
                        (simplifiedSquareRoot.coefficient * simplifiedSquareRoot.coefficient) * simplifiedSquareRoot.radicand

                    simplifiedDesimplifiedRadicand.shouldBe(originalDesimplifiedRadicand)
                }
            }
        }
    }
})
