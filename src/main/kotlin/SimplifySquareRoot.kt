
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
fun simplifySquareRootFold(squareRoot: SquareRoot): SquareRoot =
    if (squareRoot.radicand < 0) throw IllegalArgumentException("Randicand cannot be negative")
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

/**
 * Simplifies the square root and makes radicand as small as possible.
 *
 * @param squareRoot the square root to simplify.
 * @return the simplified square root.
 */
fun simplifySquareRootImp(squareRoot: SquareRoot): SquareRoot =
    if (squareRoot.radicand < 0) throw IllegalArgumentException("Randicand cannot be negative")
    else {
        var simplifiedSquareRoot = squareRoot

        for (i in 2..squareRoot.radicand) {
            if (simplifiedSquareRoot.radicand % (i * i) == 0) {
                simplifiedSquareRoot =
                    SquareRoot(
                        coefficient = simplifiedSquareRoot.coefficient * i,
                        radicand = simplifiedSquareRoot.radicand / (i * i)
                    )
            }
        }

        simplifiedSquareRoot
    }

// Makes it easy to choose one of the implementations above for testing
fun simplifySquareRoot(squareRoot: SquareRoot): SquareRoot = simplifySquareRootFold(squareRoot)