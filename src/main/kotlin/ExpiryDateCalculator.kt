import java.time.LocalDate

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        val addendMonths = payData.payAmount!!/10_000L
        val candidateExp = payData.billingDate!!.plusMonths(addendMonths)
        if(payData.firstBillingDate != null && payData.firstBillingDate!!.dayOfMonth != candidateExp.dayOfMonth)
            return candidateExp.withDayOfMonth(payData.firstBillingDate.dayOfMonth)
        return payData.billingDate!!.plusMonths(addendMonths)
    }
}
