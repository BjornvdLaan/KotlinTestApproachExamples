import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext

infix fun Int.sqrt(other: Int): Double = this * kotlin.math.sqrt(other.toDouble())

infix fun BigInteger.sqrt(other: BigInteger): BigDecimal =
    this.toBigDecimal()
        .multiply(
            other.toBigDecimal()
                .sqrt(MathContext(15))
        )