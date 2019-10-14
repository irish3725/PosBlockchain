import kotlin.random.Random

fun main() {

    // generate chain
    val chain = Chain()
    val customers = arrayOf("Dwight", "Jim", "Stanley", "Karen", "Pam", "Angela", "Ryan", "Michael",
        "Creed", "Meredith", "Kevin", "Oscar", "Kelley", "Erin")

    // simulate blocks being added (between 3 and 10)
    val blocksToGenerate = Random.nextInt(3, 10)
    var transactionsToGenerate: Int

    for (i in 0 until blocksToGenerate) {

        // simulate transactions being added (between 3 and 15)
        transactionsToGenerate = Random.nextInt(3, 15)
        for (j in 0 until transactionsToGenerate) {
            chain.addTransaction(generateTransaction(chain, customers))
            chain.addTransaction(generateTransaction(chain, customers))
            chain.addTransaction(generateTransaction(chain, customers))
            chain.addTransaction(generateTransaction(chain, customers))
            chain.addTransaction(generateTransaction(chain, customers))
        }

        // add new block
        chain.beginCommit()
        // commit new block
        chain.commitBlock()
    }

    // print chain with new transaction
    chain.printChain(0, chain.lastIndex)

    // print balances
    chain.getBalances().printBalances()

}

fun generateTransaction(chain: Chain, customers: Array<String>): Transaction {

    val balances  = chain.getBalances()
    val customersWithBalance = balances.getCustomers()
    val fromIndex = Random.nextInt(customersWithBalance.size)
    val from = customersWithBalance[fromIndex]
    val amount = Random.nextLong(minOf(balances.getBalance(from), 5000))
    var to = from

    while (to == from) {
        to = customers[Random.nextInt(customers.size)]
    }

    return Transaction(from, to, amount, "$from gave $to ${String.format("%,d", amount)} Shrutebucks.")

}
