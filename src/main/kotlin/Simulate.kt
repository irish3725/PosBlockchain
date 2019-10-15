import kotlin.random.Random

fun main() {

    // generate chain
    val chain = Chain()
    val customers = arrayOf("Jim", "Stanley", "Karen", "Pam", "Angela", "Ryan", "Michael",
        "Creed", "Meredith", "Kevin", "Oscar", "Kelley", "Erin")

    // start generating transactions to place on block
    val transactionsToGenerate = Random.nextInt(17, 20)
    for (i in 0 until transactionsToGenerate) {
        chain.addTransaction(generateTransaction(chain, customers))
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
