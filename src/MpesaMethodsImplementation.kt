import java.text.SimpleDateFormat
import java.util.*

class MpesaMethodsImplementation : Mpesa {
    var phoneNumber:String = ""
    var amount:Int = 0
    var balance:Double = 1000.00
    private val agentNo:Int = 14141
    private val tillNo:String = 345567.toString()
    private var tillNo2:String = ""
    private val paybill:String = 220220.toString()
    private var paybill2:String = ""
    private var accountNo:String = ""
    private val paybillName = "PesaPal"
    private var pin:Int = 1234
    var pin2:Int = 0
    private val reader = Scanner(System. `in`)
    private val alphabets: List<Char> = ('A'..'Z')+('0'..'9')


    fun randomCode():String{
        return List(10){alphabets.random()}.joinToString(separator = "")
    }

    fun date_time(): String? {
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
        //val formatedDate = formatter.format(date)
        return formatter.format(date)
    }
    fun transactionCostCalculator(amount:Int):Double{
        when (amount) {
            in 1..49 -> {
                return 1.0
            }
            in 50..99 -> {
                return 2.0
            }
            in 100..499 -> {
                return 19.0
            }
            in 500..999 -> {
                return 23.0
            }
            in 1000..4999 -> {
                return 49.0
            }
            else -> {
                return 84.0
            }
        }

    }

    override fun sendMoney() {
        /**
         * I will skip the part for search SIM contacts
         */

        println("Enter phone number: ")
        phoneNumber = reader.nextLine()
        println("Enter Amount: ")
        amount = reader.nextInt()
        println("Enter M-PESA PIN: ")
        pin2 =reader.nextInt()
        if (phoneNumber.length==10 && ((amount in 1..99999)&&amount<=balance) && pin2==pin){
            balance -= amount
            println("${randomCode()} Confirmed Ksh$amount sent to $phoneNumber  on ${date_time()} " +
                    "New M-PESA balance is Ksh${balance}. Transaction cost, Ksh0.00.")
        }
        else if(phoneNumber.length<10){
            println("The MSISDN of the customer does not exist or is not in the specific number segment.")
        }
        else if((amount in 1..99999)&& (amount>balance)){
            println("Failed\n" +
                    "Not enough money in your M-PESA account to send Ksh$amount. You must be able to pay the transaction" +
                    "fee as well as the requested amount.\n"+
                    "Your M-PESA balance is KSH$balance. Use Fuliza M-PESA")
        }
        else if(pin2!=pin){
            println("You have entered the wrong PIN. Please try again.")
        }
        else{
            println("An Error occurred please try again")
        }
    }

    override fun withdrawCash() {
        /**
         * I will skip the part for "From ATM"
         * I will use a constant transaction cost of Ksh27.00
         */

        val transaction_cost = 27
        println("Enter agent no.")
        var agentNo2 = reader.nextInt()
        println("Enter amount: ")
        amount = reader.nextInt()
        println("Enter M-PESA pin:")
        pin2 = reader.nextInt()

        //970<=997
        if(agentNo==agentNo2 && ((amount in 1..99999) && balance>=amount+transaction_cost)&&pin==pin2){
            balance-=(amount+transaction_cost)
            println("${randomCode()} Confirmed on ${date_time()} withdraw Ksh$amount from $agentNo - Joe Investments." +
                    " New M-PESA balance is " +
                    "Ksh$balance. Transaction cost, Ksh$transaction_cost")
        }
        else if(agentNo!=agentNo2){
            println("Failed. The Till Number you entered does not exist.\n" +
                    " Kindly confirm the correct one before transaction")
        }
        else if((amount in 1..99999)&&balance<amount+transaction_cost){
            println("Failed." +
                    " you do not have enough money in your M-PESA account to withdraw Ksh$amount. " +
                    "You must be able to pay the transaction" +
                    " fee as well as the requested amount.\n"+
                    "Your M-PESA balance is KSH$balance.")
        }
        else if(pin2!=pin){
            println("You have entered the wrong PIN. Please try again.")
        }
        else{
            println("An Error occurred please try again")
        }
    }

    override fun buyAirtime() {
        println("Choose 1 for my phone or 2 for other phone")
        var choice = reader.nextInt()
        if(choice==1){
            println("Enter Amount")
            amount = reader.nextInt()
            println("Enter M-PESA PIN: ")
            pin2 = reader.nextInt()

            if(amount in 5..100000 && balance>=amount && pin2==pin){
                balance-=amount
                println("${randomCode()} confirmed you bought Ksh$amount of airtime on ${date_time()}. New M-PESA balance " +
                        "is Ksh${balance}. Transaction cost, Ksh0.00.")
            }
            else if(amount>balance){
                println("Failed you do not have enough money in your M-PESA account to buy airtime of Ksh$amount. " +
                        "You must be able to pay the transaction" +
                        " fee as well as the requested amount.\n"+
                        "Your M-PESA balance is KSH$balance.")
            }
            else if (pin2!=pin){
                println("You have entered the wrong PIN. Please try again.")
            }
            else{
                println("An Error occurred please try again")
            }
        }
        else if(choice==2){
            //I will not work on search SIM Contacts
            reader.nextLine()
            println("Enter phone no.")
            phoneNumber = reader.nextLine()
            println("Enter Amount")
            amount = reader.nextInt()
            println("Enter M-PESA PIN: ")
            pin2 = reader.nextInt()

            if(phoneNumber.length==10 && amount in 5..100000 && balance>=amount && pin2==pin){
                balance-=amount
                println("${randomCode()} confirmed you bought Ksh$amount of airtime on ${date_time()}. New M-PESA balance " +
                        "is Ksh${balance}. Transaction cost, Ksh0.00.")
            }
            else if(phoneNumber.length<10){
                println("The MSISDN of the customer does not exist or is not in the specific number segment.")
            }
            else if(balance<amount){
                println("Failed you do not have enough money in your M-PESA account to buy airtime of Ksh$amount. " +
                        "You must be able to pay the transaction" +
                        " fee as well as the requested amount.\n"+
                        "Your M-PESA balance is KSH$balance.")
            }
            else if (pin2!=pin){
                println("You have entered the wrong PIN. Please try again.")
            }
            else{
                println("An Error occurred please try again")
            }
        }
        else{
            //i will implement a loop so that one can repeat
            println("Invalid choice")
        }
    }

    override fun lipaNaMpesa() {
        //pay bill and buy goods and services
        println("Choose 1 for Pay Bill or 2 for Buy goods and services")
        var choice = reader.nextInt()
        if (choice==1){
            //I will skip search SIM Contact
            println("Enter business number")
            paybill2 = reader.next()
            //I will skip search SIM Contact
            println("Account no")
            accountNo = reader.next()
            println("Enter Amount")
            amount = reader.nextInt()
            println("Enter M-PESA PIN")
            pin2 = reader.nextInt()
            if(paybill2==paybill && accountNo.length==10 && amount in 1..99999 && balance>=amount+transactionCostCalculator(amount) && pin2==pin){
                balance-=(amount+transactionCostCalculator(amount))
                println("${randomCode()} Confirmed Ksh$amount sent to $paybillName for account $accountNo on${date_time()}. " +
                        "New M-PESA balance is Ksh$balance. Transaction cost, Ksh${transactionCostCalculator(amount)}")
            }
            else if (paybill2!=paybill){
                println("Incorrect paybill number")
            }
            else if (accountNo.length<10){
                println("The value of the parameter\n" +
                        "$accountNo is incorrect")
            }
            else if (amount in 1..99999 && balance<amount+transactionCostCalculator(amount)){
                println("Failed you do not have enough money in your M-PESA account to buy pay Ksh$amount.to $paybillName"+
                        "Your M-PESA balance is KSH$balance.")
            }
            else if (pin2!=pin){
                println("You have entered the wrong PIN. Please try again.")
            }
            else{
                println("An Error occurred please try again")
            }

        }
        else if (choice==2){
            println("Enter till no.")
            tillNo2 = reader.next()
            println("Enter Amount")
            amount = reader.nextInt()
            println("Enter M-PESA PIN")
            pin2 = reader.nextInt()
        }
        else{
            println("Invalid choice")
        }
    }

    override fun myAccount() {
        /**
        *check balance
        *change pin
        **/
    }
}
