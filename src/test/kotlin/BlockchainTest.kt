
import org.junit.Test

class BlockchainTest {

    @Test fun `Genesis block is created correctly`() {
        val block = Block(ByteArray(32), -1)

        // previous hash is whatever we put in. sequence should be 0
        assert(block.prevHash.contentEquals(ByteArray(32)))
        assert(block.seq == 0)
        // one transaction with only genesis as to and from and all currency
        assert(block.transactions.size == 1)
        assert(block.transactions[0].from == "genesis")
        assert(block.transactions[0].to == "genesis")
        assert(block.transactions[0].amount == 100000000000)
        // no signers on genesis block
        assert(block.signatures.size == 0)
    }
}
