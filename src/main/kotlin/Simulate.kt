
fun main() {

    // generate chain
    val chain = Chain()

    // print initial chain
    chain.printChain(0, chain.lastIndex)

    // add some transactions to first non-genesis block
    chain.addTransaction("Dwight", "Stanly", 2000, "Stanly bought 2000 Schrutebucks from " +
            "Dwight who is the administrator.")

    // print chain with new transaction
    chain.printChain(0, chain.lastIndex)

    generateTransaction(chain)

}

fun generateTransaction(chain: Chain) {

   chain.getBalances().printBalances()

}