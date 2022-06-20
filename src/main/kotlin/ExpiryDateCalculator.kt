import java.time.LocalDate

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        val addendMonths = 1L
        val candidateExp = payData.billingDate!!.plusMonths(addendMonths)
        if(payData.firstBillingDate!!.dayOfMonth != candidateExp.dayOfMonth)
            return candidateExp.withDayOfMonth(payData.firstBillingDate.dayOfMonth)
        return payData.billingDate!!.plusMonths(1)
    }
}
