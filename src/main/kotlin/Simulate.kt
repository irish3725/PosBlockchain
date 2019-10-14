
fun main() {

    // generate chain
    val chain = Chain()

    // print initial chain
    chain.printChain(0, chain.lastIndex)

    // add new block. print whole chain
    chain.beginCommit()
    chain.printChain(0, chain.lastIndex)

    // validate and commit latest block
    chain.commitBlock()
    chain.printChain(0, chain.lastIndex)

    // add new block. break previous block
    chain.beginCommit()
    chain.getBlock(chain.lastIndex - 1).prevHash = ByteArray(32)
    chain.commitBlock()
    chain.printChain(0, chain.lastIndex)

}

fun oldTest() {
    // create chain as arraylist of blocks for now and add genesis block
    val chain: ArrayList<Block> = arrayListOf<Block>()
    chain.add(Block(ByteArray(32), -1))

    // define block adding variables
    var lastSeq = 0
    var prevBlock: Block
    var prevHash: ByteArray
    var prevSeq: Int

    while (lastSeq < 5) {
        // add next block
        prevBlock = chain[lastSeq]
        prevHash = prevBlock.getHash()
        prevSeq = prevBlock.seq
        chain.add(Block(prevHash, prevSeq))

        lastSeq++
    }

    // print contents of genesis block
    for (block: Block in chain) {
        block.printBlock()
    }
}