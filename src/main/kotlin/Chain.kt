import kotlin.math.min

class Chain {
    // an arraylist of blocks sounds fine for now
    var blocks: ArrayList<Block> = arrayListOf<Block>()
    var commitIndex: Int = -1
    var lastIndex: Int = -1

    /**
     * Generate genesis block.
     */
    init {
        // initialize with genesis block. pass -1 as prevSeq
        blocks.add(Block(ByteArray(32), -1))
        // genesis block is committed by default
        commitIndex = 0
        // create second empty block to hold new transactions
        blocks.add(Block(blocks[0].getHash(), blocks[0].seq))
        lastIndex = 1
    }

    // --- Begin public Functions ---
    /**
     * Ask for signatures so block can be committed,
     * create new block for new transactions to be
     * written to.
     *
     * TODO: beginCommit()
     */
    fun beginCommit() {

        // only allow beginCommit() when there is exactly one uncommitted block
        if (lastIndex != commitIndex + 1) {
            println("Can only begin commit when exactly one block is uncommitted.")
            return
        }

        // for now, just add a new block and commit since we don't have a way to sign
        appendBlock()
        commitBlock()
    }

    /**
     * When transactions are all entered, and block has been
     * signed by enough stakeholders:
     *  validate chain,
     *  commit block,
     *  tell everyone of commit
     *
     *  TODO: commitBLock()
     */
    fun commitBlock() {

        // keep track of broken block
        val valid = validateChain(commitIndex + 1)

        // validate chain up to the block after commit. set new commitIndex if no validation errors
        if (valid) {
            // commit next block
            commitIndex++
            // write lastHash to next block to be committed
            blocks[commitIndex + 1].prevHash = blocks[commitIndex].getHash()
        } else {
            println("Commit failed: validation failed.")
        }


    }

    /**
     * Validate each block by counting currency and
     * comparing hashes to previous hashes until index
     * and then making sure number of Schrutebucks total
     * matches the number at genesis
     *
     * TODO: validateChain()
     */
    fun validateChain(blockIndex: Int): Boolean {

        // only validate up to the lower of blockIndex or block after commit
        val validateEnd = min(blockIndex, commitIndex + 1)

        // start at second block, and look backwards to check prevHash on each
        for (i in 1..validateEnd) {
            // if this block's prevHash does not match the previous block's hash, return index of broken block
            if (!blocks[i].prevHash.contentEquals(blocks[i - 1].getHash())) {
                return false
            }
        }

        val balances = getBalances()

        // check balances match
        if (balances.isEmpty() || !balances.reconcile()) {
            return false
        }

        // return true if no errors were found
        return true
    }

    /**
     * Sign block.
     *
     * TODO: signBlock()
     */
    fun signBlock() {

    }

    /**
     * View all blocks in chain starting at index and on till end.
     *
     * @param startIndex: index of the first block to be printed.
     * @param endIndex: index of the last block to be printed.
     */
    fun printChain(startIndex: Int, endIndex: Int) {

        // don't allow incorrect indices
        if (startIndex >= blocks.size || startIndex < 0 ||
            endIndex >= blocks.size || endIndex < 0 ||
            startIndex > endIndex
        ) {
            println("Chain indices out of bounds. Last index of this chain is ${blocks.size - 1}")
            return
        }

        println("\nChain beginning at block: $startIndex and ending at block: $endIndex")
        println("--------------------------------------------------")

        // print each block from start to end
        for (i in startIndex..endIndex) {
            blocks[i].printBlock()
            if (i == commitIndex) {
                println("^^^^^^^^^^^^^^^Commit^^^^^^^^^^^^^^^")
            }
        }

        println("--------------------------------------------------")
    }

    /**
     * return block for editing. This will be used only for debugging
     */
    fun getBlock(blockIndex: Int): Block {
        return blocks[blockIndex]
    }

    /**
     * Add transaction to last block in chain
     */
    fun addTransaction(transaction: Transaction) {
        blocks[lastIndex].addTransaction(transaction)

        // if we have committed the last black and we reach at least 15 transactions, begin committing this block
        if (commitIndex == lastIndex - 1 && blocks[lastIndex].transactions.size >= 15) {
            beginCommit()
        }
    }

    /**
     * Add transaction to last block in chain
     */
    fun addTransaction(from: String, to: String, amount: Long, note: String) {
        blocks[lastIndex].addTransaction(Transaction(from, to, amount, note))

        // if we have committed the last black and we reach at least 15 transactions, begin committing this block
        if (commitIndex == lastIndex - 1 && blocks[lastIndex].transactions.size >= 15) {
            beginCommit()
        }
    }

    /**
     * returns balances object that has updated balances for every customer that exists on block
     */
    fun getBalances(): Balances {

        // create empty Balances object
        val balances = Balances()

        // iterate over each block
        for (b: Block in blocks) {

            // change balance based on new transaction
            for (t: Transaction in b.transactions) {
                balances.changeBalance(t)
            }
        }

        // after iterating over all blocks reconcile.
        if(!balances.reconcile()) return Balances()

        // if balances reconcile, return balances
        return balances
    }
    // --- End Public Functions ---

    // --- Begin Private Functions ---
    /**
     * Adds a new empty block to the end of the chain
     */
    private fun appendBlock() {

        // find the last block, append new block to end.
        // Can't set prevHash until commit of prevBlock. Use blank ByteArray until commit
        blocks.add(Block(ByteArray(32), blocks.last().seq))
        // increment end index
        lastIndex++
    }

    /**
     * Removes final block. This should only be done by the chain
     */
    private fun removeLast() {
        blocks.remove(blocks.last())
    }
    // --- End Private Functions ---
}
