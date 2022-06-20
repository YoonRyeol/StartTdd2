import java.time.LocalDate
import java.time.YearMonth

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        payData.payAmount!!
        payData.billingDate!!

        var addendMonths = 0L
        if (payData.payAmount == 100_000 ) addendMonths = 12L else payData.payAmount/10_000L

        if (payData.firstBillingDate != null) {
            return calculateExpiryDate(payData, addendMonths)
        }
        return payData.billingDate.plusMonths(addendMonths)
    }

    private fun calculateExpiryDate(payData: PayData, addendMonths: Long): LocalDate {
        payData.payAmount!!
        payData.billingDate!!
        payData.firstBillingDate!!

        val candidateExp = payData.billingDate.plusMonths(addendMonths)
        val dayOfFirstBilling = payData.firstBillingDate.dayOfMonth
        if (dayOfFirstBilling != candidateExp.dayOfMonth) {
            return decideCandidateDay(candidateExp, dayOfFirstBilling)
        }
        return candidateExp
    }

    private fun decideCandidateDay(candidateExp: LocalDate, dayOfFirstBilling: Int): LocalDate{
        val dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth()
        if (dayLenOfCandiMon < dayOfFirstBilling) {
            return candidateExp.withDayOfMonth(dayLenOfCandiMon)
        }
        return candidateExp.withDayOfMonth(dayOfFirstBilling)
    }
}
