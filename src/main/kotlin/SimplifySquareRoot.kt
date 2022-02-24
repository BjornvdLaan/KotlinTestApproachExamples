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
    else if (squareRoot.radicand == 0) SquareRoot(0, 0)
    else if (squareRoot.radicand == 1) SquareRoot(squareRoot.coefficient, 1)
    else {
        var simplifiedSquareRoot = squareRoot

        for (i in squareRoot.radicand downTo 2) {
//            //Uncomment this IF statement if you want to trigger shrinking when (i * i) == 0
//            //When commented, the scenario will throw ArithmeticException and that does not trigger shrinking
//            if ((i * i) == 0) {
//                simplifiedSquareRoot = SquareRoot(0, 0)
//                break
//            }

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