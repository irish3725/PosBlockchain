import kotlin.random.Random

fun main() {

    // generate chain
    val chain = Chain()
    val customers = arrayOf("Jim", "Stanley", "Karen", "Pam", "Angela", "Ryan", "Michael",
        "Creed", "Meredith", "Kevin", "Oscar", "Kelley", "Erin")

    val stakeholder = Stakeholder(customers[0])
    // start generating transactions to place on block
    //val transactionsToGenerate = Random.nextInt(17, 20)
//    val transactionsToGenerate = 10000
//    for (i in 0 until transactionsToGenerate) {
//        chain.addTransaction(generateTransaction(chain, customers))
//    }

    // print chain with new transaction
//    chain.printChain(0, chain.lastIndex)

    // print balances
//    chain.getBalances().printBalances()

}

fun generateTransaction(chain: Chain, customers: Array<String>): Transaction {

    // get list of balances on current block
    val balances  = chain.getBalances()
    // get list of customers with balances
    val customersWithBalance = balances.getCustomers()
    // get customer that will be giving
    val fromIndex = Random.nextInt(0, customersWithBalance.size)
    val from = customersWithBalance[fromIndex]
    // get amount customer will be giving
    val amount = Random.nextLong(minOf(balances.getBalance(from), 5000))

    // get customer that will be receiving
    var to = from
//    while (to == from || to == "Dwight") { // I don't want to give any back to the admin
    while (to == from) { // I don't want to give any back to the admin
        to = customers[Random.nextInt(0, customers.size)]
    }

    val transaction = Transaction(from, to, amount, "$from gave $to ${String.format("%,d", amount)} Shrutebucks.")
    return transaction

}
