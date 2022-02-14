import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith


class SimplifySquareRootTest : FunSpec({
    context("Simplication of square roots") {
        test(
            "Simplified square root should be equal to original square root"
        ) {
            val originalSquareRoot = SquareRoot(1, 4)
            val simplifiedSquareRoot = simplifySquareRoot(originalSquareRoot)

            (simplifiedSquareRoot.coefficient sqrt simplifiedSquareRoot.radicand)
                .shouldBe(originalSquareRoot.coefficient sqrt originalSquareRoot.radicand)
        }

        test(
            "Simplified square root should be equal to original square root with coefficient"
        ) {
            val originalSquareRoot = SquareRoot(3, 4)
            val simplifiedSquareRoot = simplifySquareRoot(originalSquareRoot)

            (simplifiedSquareRoot.coefficient sqrt simplifiedSquareRoot.radicand)
                .shouldBe(originalSquareRoot.coefficient sqrt originalSquareRoot.radicand)
        }

        test(
            "Simplified square root cannot be simplified further"
        ) {
            val originalSquareRoot = SquareRoot(1, 4)
            val simplifiedSquareRoot = simplifySquareRoot(originalSquareRoot)
            val twiceSimplifiedSquareRoot =
                simplifySquareRoot(simplifiedSquareRoot)

            simplifiedSquareRoot.coefficient shouldBe twiceSimplifiedSquareRoot.coefficient
            simplifiedSquareRoot.radicand shouldBe twiceSimplifiedSquareRoot.radicand
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
