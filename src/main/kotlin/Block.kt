import java.security.MessageDigest
import kotlin.math.max

/**
 * Block is a single component in the blockchain. Block structure:
 * previous hash: hash of previous block
 * sequence: number of block in blockchain
 * transactions: array list of transactions. format:
 *      {{<from>, <to>, <amount>}...}
 * stakeHolders: array list of stakeholders. format:
 *      {{<stakeholder signature>, <amount staked>}
 *
 * @param prevHash: hash of the previous block.
 * @param prevSeq: sequence number of the previous block. if genesis block,
 * use -1.
 */
class Block(var prevHash: ByteArray, val prevSeq: Int) {
    var seq: Int = prevSeq + 1
    var transactions: ArrayList<Transaction> = arrayListOf<Transaction>()
    var signatures: ArrayList<Signature> = arrayListOf<Signature>()
    var transactionCount = 0

    /**
     * Init handles the genesis block
     */
    init {
        if(prevSeq == -1) {
            // generate initial hash
            this.addTransaction(Transaction("genesis", "Dwight", 100000000000, "Genesis block. " +
                    "Dwight is the Schrutebuck administrator."))
        }
    }

    /**
     * Prints everything on the block in the format:
     *      Sequence: <this block's sequence number>
     *      Previous Hash: <hash of previous block>
     *      Transactions:
     *          <print of first transaction>
     *          ...
     *          <print of last transaction>
     *      Signatures:
     *          <print of first signature>
     *          ...
     *          <print of last signature>
     */
    fun printBlock() {
        println("Sequence: $seq\n" +
                "Previous Hash: ${getHashHex(prevHash)}\n" +
                "Transaction Count: $transactionCount\n" +
                "Transactions:")
        for (t: Transaction in transactions) {
            print("\t")
            t.printTransaction()
        }
        println("Signatures:")
        for (s: Signature in signatures) {
            print("\t")
            s.printSingature()
        }
        println("Current Hash:  ${getHashHex(getHash())}\n------------------------------")
    }

    /**
     * Returns a sha256 hash of this block as ByteArray!
     */
    fun getHash(): ByteArray {
        // get byte array of this block
        val byte = this.toString().toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(byte)

        return digest
    }

    /**
     * Prints hash as string version of hex for prettier printing
     */
    fun getHashHex(hash: ByteArray): String {

        val hex: StringBuilder = StringBuilder()

        // iterate over each byte and append string rep to our hash string
        for (b: Byte in hash) {
            hex.append(String.format("%2X", b).replace(" ", "0"))
        }
        return hex.toString()
    }

    /**
     * Adds transaction to block
     */
    fun addTransaction(transaction: Transaction) {

        val insertIndex = findInsert(transaction)

        transactions.add(insertIndex, transaction)
        transactionCount++
    }

    /**
     * returns index to insert new transaction
     */
    fun findInsert(newTransaction: Transaction): Int {

        // if the list is empty return first position
        if (transactions.size == 0) return 0

        var start = 0
        var end = transactions.size - 1
        //var checkIndex = (start + end) / 2
        var checkIndex: Int

        while (end - start > 1) {

            checkIndex = (start + end) / 2

            // if newTransaction comes before checked transaction
            if (newTransaction.compareTo(transactions[checkIndex]) < 0) {

                // set end to checkIndex
                end = checkIndex
            // if newTransaction comes after checked transaction
            } else if (newTransaction.compareTo(transactions[checkIndex]) > 0) {

                // set start to checkIndex
                start = checkIndex
                // if the transactions are exactly the same, return the index of the matching transaction
            } else {return checkIndex}
        }

        // if less than or equal to start, return start position
        if (newTransaction.compareTo(transactions[start]) <= 0) {
            return start

        // if less than or equal to end, return end position
        } else if (newTransaction.compareTo(transactions[end]) <= 0) {
            return end

        // otherwise return position to the left of end
        } else { return end + 1 }
    }
}