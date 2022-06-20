import java.time.LocalDate
import java.time.YearMonth

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        val addendMonths = payData.payAmount!!/10_000L
        val candidateExp = payData.billingDate!!.plusMonths(addendMonths)
        if(payData.firstBillingDate != null && payData.firstBillingDate!!.dayOfMonth != candidateExp.dayOfMonth) {
            if(YearMonth.from(candidateExp).lengthOfMonth() < payData.firstBillingDate.dayOfMonth){
                return candidateExp.withDayOfMonth(
                    YearMonth.from(candidateExp).lengthOfMonth()
                )
            }
            return candidateExp.withDayOfMonth(payData.firstBillingDate.dayOfMonth)
        }
        return payData.billingDate!!.plusMonths(addendMonths)
    }
}
