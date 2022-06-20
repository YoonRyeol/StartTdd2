import java.time.LocalDate

class PayData private constructor(val billingDate: LocalDate, val payAmount: Int) {

    data class Builder(
        var billingDate: LocalDate? = null,
        var payAmount: Int? = null
    ){
        fun billingDate(billingDate: LocalDate) = apply { this.billingDate = billingDate }
        fun payAmount(payAmount: Int) = apply { this.payAmount= payAmount }
        fun build() = billingDate?.let { payAmount?.let { it1 -> PayData(it, it1) } }!!
    }

}
