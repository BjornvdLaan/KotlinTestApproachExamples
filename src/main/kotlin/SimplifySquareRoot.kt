
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
 * Simplifies the square root and makes radicand as small as possible.
 *
 * @param squareRoot the square root to simplify.
 * @return the simplified square root.
 */
fun simplifySquareRoot(squareRoot: SquareRoot): SquareRoot =
    if (squareRoot.radicand < 0) throw IllegalArgumentException("Randicand cannot be negative")
    //    else if (squareRoot.radicand * squareRoot.radicand < squareRoot.radicand)
//        throw IllegalArgumentException("Radicand ^ 2 is bigger than max value of Integer")
    else (2..squareRoot.radicand)
        .fold(squareRoot) { simplifiedSquareRoot, i ->
            if (simplifiedSquareRoot.radicand % (i * i) == 0) {
                SquareRoot(
                    simplifiedSquareRoot.coefficient * i,
                    simplifiedSquareRoot.radicand / (i * i)
                )
            } else {
                simplifiedSquareRoot
            }
        }
