import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class ExpiryDateCalculatorTest {

    @Test
    fun 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
            LocalDate.of(2019,3,1),
            10000,
            LocalDate.of(2019,4,1))
        assertExpiryDate(
            LocalDate.of(2019,5,5),
            10000,
            LocalDate.of(2019,6,5))

    }

    private fun assertExpiryDate(
        billingDate: LocalDate,
        payAmount: Int,
        expectedExpiryDate: LocalDate
    ){
        val cal = ExpiryDateCalculator()
        val realExpiryDate = cal.calculateExpiryDate(billingDate, payAmount)
        assertEquals(expectedExpiryDate, realExpiryDate)
    }
}
