
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
class Block(val prevHash: String, val prevSeq: Int) {
    var seq: Int = prevSeq + 1
    var transactions: ArrayList<Transaction> = arrayListOf<Transaction>()
    var signatures: ArrayList<Signature> = arrayListOf<Signature>()

    /**
     * Init handles the genesis block
     */
    init {
        if(prevSeq == -1) {
            transactions.add(Transaction("genesis", "genesis", 100000000000))
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
                "Previous Hash: $prevHash\n" +
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
    }
}