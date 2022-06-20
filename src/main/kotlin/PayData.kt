import java.time.LocalDate

class PayData private constructor(
    val billingDate: LocalDate?,
    val payAmount: Int?,
    val firstBillingDate: LocalDate?
) {

    data class Builder(
        var billingDate: LocalDate? = null,
        var payAmount: Int? = null,
        var firstBillingDate: LocalDate? = null
    ){
        fun billingDate(billingDate: LocalDate) = apply { this.billingDate = billingDate }
        fun payAmount(payAmount: Int) = apply { this.payAmount= payAmount }
        fun firstBillingDate(firstBillingDate: LocalDate) = apply { this.firstBillingDate = firstBillingDate }
        fun build() = PayData(billingDate, payAmount, firstBillingDate)
    }

}
