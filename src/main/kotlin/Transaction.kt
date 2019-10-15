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

    /**
     * compares this transaction to another transaction based on
     * from, then to, then amount, then note in that order
     *
     * @return: 0 when the two transactions have all of the same contents, negative if this transaction
     * comes before the other transaction, and positive if this transaction comes later than the next transaction.
     */
    fun compareTo(otherTransaction: Transaction): Int {

        // compare from first
        if (this.from.compareTo(otherTransaction.from) < 0) {
            return -1
        } else if (this.from.compareTo(otherTransaction.from) > 0) {
            return 1
        }

        // compare to first
        if (this.to.compareTo(otherTransaction.to) < 0) {
            return -2
        } else if (this.to.compareTo(otherTransaction.to) > 0) {
            return 2
        }

        // compare from first
        if (this.amount < otherTransaction.amount) {
            return -3
        } else if (this.amount > otherTransaction.amount) {
            return 3
        }

        // compare from first
        if (this.note.compareTo(otherTransaction.note) < 0) {
            return -4
        } else if (this.note.compareTo(otherTransaction.note) > 0) {
            return 4
        }

        // if all are the same, return 0
        return 0
    }
}