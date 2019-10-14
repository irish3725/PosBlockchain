/**
 * Single transaction that can occur on a block.
 *
 * @param from: signature of the giving client
 * @param to: signature of the receiving client
 * @param amount: amount of currency being transferred
 */
class Transaction (val from: String, val to: String, val amount: Long, val note: String){

    /**
     * Prints transaction in format "From: <giving signature>; To <receiving signature>; Amount: <amount>"
     */
    fun printTransaction() {
        println("From: $from; To: $to; Amount: ${String.format("%,d", amount)}\n\t\tNote: $note")
    }
}