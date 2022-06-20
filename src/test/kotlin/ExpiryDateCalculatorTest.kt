import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class ExpiryDateCalculatorTest {

    @Test
    fun 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
            PayData.Builder()
                .billingDate(LocalDate.of(2019,3,1))
                .payAmount(10_000)
                .build(),
            LocalDate.of(2019,4,1))

        assertExpiryDate(
            PayData.Builder()
                .billingDate(LocalDate.of(2019,5,5))
                .payAmount(10_000)
                .build(),
            LocalDate.of(2019,6,5))
    }

    @Test
    fun 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
            PayData.Builder()
                .billingDate(LocalDate.of(2019,1,31))
                .payAmount(10_000)
                .build(),
            LocalDate.of(2019,2,28)
        )
        assertExpiryDate(
            PayData.Builder()
                .billingDate(LocalDate.of(2019,5,31))
                .payAmount(10_000)
                .build(),
            LocalDate.of(2019,6,30)
        )
        assertExpiryDate(
            PayData.Builder()
                .billingDate(LocalDate.of(2020,1,31))
                .payAmount(10_000)
                .build(),
            LocalDate.of(2020,2,29)
        )
    }
    @Test
    fun 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        val payData = PayData.Builder()
            .firstBillingDate(LocalDate.of(2019,1,31))
            .billingDate(LocalDate.of(2019,2,28))
            .payAmount(10_000)
            .build()
        assertExpiryDate(payData,LocalDate.of(2019,3,31))

        val payData2 = PayData.Builder()
            .firstBillingDate(LocalDate.of(2019,1,30))
            .billingDate(LocalDate.of(2019,2,28))
            .payAmount(10_000)
            .build()
        assertExpiryDate(payData2, LocalDate.of(2019,3,30))

        val payData3 = PayData.Builder()
            .firstBillingDate(LocalDate.of(2019,5,31))
            .billingDate(LocalDate.of(2019,6,30))
            .payAmount(10_000)
            .build()
        assertExpiryDate(payData3, LocalDate.of(2019,7,31))
    }


    private fun assertExpiryDate(
        payData: PayData,
        expectedExpiryDate: LocalDate
    ){
        val cal = ExpiryDateCalculator()
        val realExpiryDate = cal.calculateExpiryDate(payData)
        assertEquals(expectedExpiryDate, realExpiryDate)
    }
}
