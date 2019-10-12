
fun main() {

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