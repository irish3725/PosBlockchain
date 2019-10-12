/**
 * Signature class is an object that holds a stakeholder's
 * signature and the amount they have staked.
 *
 * @param signature: public key of person staking (I don't actually know
 * what this should look like yet)
 * TODO: determine what signature should look like
 * @param stake: amount stakeholder has staked on this block
 */
class Signature (val signature: String, val stake: Long) {

    /**
     * Prints signature in format "Signature: <stakeholder signature>; Stake <amount staked>"
     */
    fun printSingature() {
        println("Signature: $signature; Stake: $stake")
    }
}