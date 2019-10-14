class Chain {
    // an arraylist of blocks sounds fine for now
    var blocks: ArrayList<Block> = arrayListOf<Block>()
    var commitIndex: Int = -1
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
    }

    /**
     * After block has sufficient signatures,
     * ask for signatures so block can be committed,
     * create new block for new transactions to be
     * written to.
     *
     * TODO: beginCommit()
     */
    fun beginCommit() {

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
    fun commitBlock(){

    }

    /**
     * Validate each block by counting currency and
     * comparing hashes to previous hashes.
     *
     * TODO: validateChain()
     */
    fun validateChain(){

    }

    /**
     * Add transaction to list of transactions.
     *
     * TODO: addTransaction()
     */
    fun addTransaction(){

    }

    /**
     * Sign block.
     *
     * TODO: signBlock()
     */
    fun signBlock(){

    }

    /**
     * View all blocks in chain starting at index and on till end.
     *
     * @param blockIndex: index of the first block to be printed.
     * TODO: viewChain
     */
    fun viewChain(blockIndex: Int) {

        if (blockIndex >= blocks.size) {
            println("Last index of this chain is ${blocks.size -1}")
            return
        }

        for (i in blockIndex..blocks.size) {
            blocks[i].printBlock()
        }
    }
}
