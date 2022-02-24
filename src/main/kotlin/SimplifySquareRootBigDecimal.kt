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
    else if (squareRoot.radicand == BigInteger.ONE) SquareRootBI(squareRoot.coefficient, BigInteger.ONE)
    else {
        val decreasingSequence = sequence {
            // We can start at sqrt(radicand) because radicand is
            // never divisible without remainder by anything greater than i = sqrt(radicand)
            // This optimization is applied because tests are otherwise very very slow.
            var bi = squareRoot.radicand.sqrt()
            while (bi > BigInteger.ONE) {
                yield(bi)
                bi = bi.subtract(BigInteger.ONE)
            }
        }

        decreasingSequence
            .fold(squareRoot) { simplifiedSquareRoot, i ->
                run {
                    if (simplifiedSquareRoot.radicand.mod(i.pow(2)) == BigInteger.ZERO) {
                        SquareRootBI(
                            simplifiedSquareRoot.coefficient.times(i),
                            simplifiedSquareRoot.radicand.div(i.pow(2))
                        )
                    } else {
                        simplifiedSquareRoot
                    }
                }
            }
    }
