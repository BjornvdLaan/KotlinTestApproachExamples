import java.math.BigInteger

/**
 * Square root with its coefficient.
 *
 * Definitions: a square root is of form 'coefficient * sqrt(radicand)'.
 * In other words, the coefficient is outside the square root
 * and the radicand in inside the square root.
 *
 * @property coefficient number in front of the radical ('root') sign.
 * @property radicand number inside the radical ('root') sign.
 */
data class SquareRootBI(val coefficient: BigInteger, val radicand: BigInteger)

/**
 * Simplifies the square root and makes randicand as small as possible.
 *
 *
 * @param squareRoot the square root to simplify.
 * @return the simplified square root.
 */
fun simplifySquareRoot(squareRoot: SquareRootBI): SquareRootBI =
    if (squareRoot.radicand < BigInteger.ZERO) throw IllegalArgumentException("Randicand cannot be negative")
    else if (squareRoot.radicand == BigInteger.ZERO) SquareRootBI(BigInteger.ZERO, BigInteger.ZERO)
    else (squareRoot.radicand.intValueExact()..2)
        .fold(squareRoot) { simplifiedSquareRoot, i ->
            if (simplifiedSquareRoot.radicand.toInt() % (i * i) == 0) {
                SquareRootBI(
                    simplifiedSquareRoot.coefficient.times(i.toBigInteger()),
                    simplifiedSquareRoot.radicand.div(i.toBigInteger().pow(2))
                )
            } else {
                simplifiedSquareRoot
            }
        }
