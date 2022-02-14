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
data class SquareRoot(val coefficient: Int, val radicand: Int)

/**
 * Simplifies the square root and makes randicand as small as possible.
 *
 * @param squareRoot the square root to simplify.
 * @return the simplified square root.
 */
fun simplifySquareRoot(squareRoot: SquareRoot): SquareRoot =
    if (squareRoot.radicand < 0) throw IllegalArgumentException("Randicand cannot be negative")
    else (2..squareRoot.radicand)
        .filter { squareRoot.radicand % (it * it) == 0 }
        .fold(SquareRoot(squareRoot.coefficient, squareRoot.radicand)) { acc, i ->
            SquareRoot(acc.coefficient * i, acc.radicand / (i * i))
        }
