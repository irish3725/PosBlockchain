private class Balance(var name: String, var balance: Long) {}
class Balances() {

    private var balanceList = arrayListOf<Balance>()

    /**
     * Adds a new balance to list of balances
     *
     * @param name: name of new customer. This will be used as a key to get this customer's balance
     * @param balance: new customer's balance. (cannot be negative)
     * @return: true when customer has been added. Otherwise, false
     */
    fun addCustomer(name: String, balance: Long): Boolean {
        if(balance < 0) {
            println("$name cannot have a negative balance")
            return false
        }

        balanceList.add(Balance(name, balance))
        return true
    }

    /**
     * Returns balance of person
     *
     * @param name: name of customer who's balance is returned
     * @return: balance associated with name
     */
    fun getBalance(name: String): Long {

        val customerIndex = getCustomerIndex(name)

        // if customer does not exist return -1
        if (customerIndex == -1) {
            return -1
        }

        return balanceList[customerIndex].balance
    }

    /**
     * Returns index of person in balance list or -1 if not exists
     *
     * @param searchName: name of customer who's index should be returned.
     * @return: index of person with name "searchName". -1 when customer is not found.
     */
    fun getCustomerIndex(searchName: String): Int {

        // iterate over all balances
        for (i in 0 until balanceList.size) {
            // return index if match
            if (balanceList[i].name == searchName) {
                return i
            }
        }

        // return -1 if no match
        return -1
    }

    /**
     * Adds parameter "amount" to balance of customer with name "name"
     *
     * @param name: name of person whos balance should be changed.
     * @param amount: amount to be added to person's balance. use negative amount to subtract.
     * @return: true when balance has been changed. Otherwise, return false.
     */
    fun changeBalance(name: String, amount: Long): Boolean {
        val customerIndex = getCustomerIndex(name)
        val newBalance = balanceList[customerIndex].balance + amount

        if (newBalance < 0) {
            println("$name cannot have a negative balance. $name's current balance: ${getBalance(name)}")
            return false
        } else {
            balanceList[customerIndex].balance = newBalance
        }

        return true
    }

    /**
     * Set new balances of customer given a transaction.
     * If customer has no balance, add them to list.
     *
     * @param transaction: Transaction object that will change the balance of two customers
     */
    fun changeBalance(transaction: Transaction): Boolean {

        // pull out all values from transaction for readability
        val from = transaction.from
        val to = transaction.to
        val amount = transaction.amount

        if(from == "genesis") {
            addCustomer(to, amount)

        // return false if from doesn't exist or has negative balance
        } else if(getCustomerIndex(from) == -1 || getBalance(from) < amount) {
            println("$from cannot give more than he has. $from's balance: ${getBalance(from)}")
            return false

        // if customer doesn't exist yet, change from balance and create "to" customer
        } else if(getCustomerIndex(to) == -1) {
            // subtract "amount" from "from"'s balnace
            changeBalance(from, -amount)
            // add new customer with balance of "amount"
            addCustomer(to, amount)
        }

        return true
    }

    /**
     * Prints balances for each person on chain
     */
    fun printBalances(){
        println("\n----------Balances----------")
        for (b: Balance in balanceList) {
            println("${b.name}: ${String.format("%,d", b.balance)} Shrutebucks")
        }
    }
}