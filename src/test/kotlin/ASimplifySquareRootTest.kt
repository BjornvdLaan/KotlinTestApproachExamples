import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith


class ASimplifySquareRootTest : FunSpec({
    context("Simplification of square roots") {
        test(
            "Simplified square root should be equal to original square root"
        ) {
            val originalSquareRoot = SquareRoot(1, 5)
            val simplifiedSquareRoot = simplifySquareRoot(originalSquareRoot)

            (simplifiedSquareRoot.coefficient sqrt simplifiedSquareRoot.radicand)
                .shouldBe(originalSquareRoot.coefficient sqrt originalSquareRoot.radicand)
        }

        test(
            "Simplified square root should be equal to original square root with coefficient"
        ) {
            val originalSquareRoot = SquareRoot(3, 5)
            val simplifiedSquareRoot = simplifySquareRoot(originalSquareRoot)

            (simplifiedSquareRoot.coefficient sqrt simplifiedSquareRoot.radicand)
                .shouldBe(originalSquareRoot.coefficient sqrt originalSquareRoot.radicand)
        }

        test("Randicand cannot be negative") {
            val negativeSquareRoot = SquareRoot(1, -4)
            val exception = shouldThrow<IllegalArgumentException> {
                simplifySquareRoot(negativeSquareRoot)
            }
            exception.message should startWith("Randicand cannot be negative")
        }
    }
})
