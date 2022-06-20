import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class ExpiryDateCalculatorTest {

    @Test
    fun 만원_납부하면_한달_뒤가_만료일이_됨() {
        val billingDate = LocalDate.of(2019, 3, 1)
        val payAmount = 10_000

        val cal = ExpiryDateCalculator()
        val expiryDate = cal.calculateExpiryDate(billingDate, payAmount)

        assertEquals(LocalDate.of(2019,4,1), expiryDate)
    }
}
