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

    /**
     * Adds a new empty block to the end of the chain
     */
    private fun appendBlock(){

        // find the last block, append new block to end.
        // Can't set prevHash until commit of prevBlock. Use blank ByteArray until commit
        blocks.add(Block(ByteArray(32), blocks.last().seq))
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

        // for now, just add a new block
        appendBlock()
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

        // validate chain up to the block after commit. set new commitIndex if no validation errors
        if (validateChain(commitIndex + 1) == -1) {
            commitIndex++
        }
    }

    /**
     * Validate each block by counting currency and
     * comparing hashes to previous hashes until index
     *
     * TODO: validateChain()
     */
    fun validateChain(blockIndex: Int): Int {

        // for now, just check previous hashes
        // start at second block, and look backwards to check prevHash on each
        for (i in 1..blockIndex) {
            // if this block's prevHash does not match the previous block's hash, return index of broken block
            if (!blocks[i].prevHash.contentEquals(blocks[i-1].getHash())){
               return i + 1
            }
        }

        // return -1 if no errors
        return -1
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
     * @param startIndex: index of the first block to be printed.
     * @param endIndex: index of the last block to be printed.
     */
    fun printChain(startIndex: Int, endIndex: Int) {

        // don't allow incorrect indices
        if (startIndex >= blocks.size || startIndex < 0 ||
                endIndex >= blocks.size || endIndex < 0 ||
                startIndex > endIndex) {
            println("Chain indices out of bounds. Last index of this chain is ${blocks.size -1}")
            return
        }

        // print each block from start to end
        for (i in startIndex..endIndex) {
            blocks[i].printBlock()
            if (i == commitIndex) {
                println("^^^^^^^^^^^^^^^Commit^^^^^^^^^^^^^^^")
            }
        }
    }
}
