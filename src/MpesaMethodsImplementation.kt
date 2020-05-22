import java.text.SimpleDateFormat
import java.util.*

class MpesaMethodsImplementation : Mpesa {
    var phoneNumber:String = ""
    var amount:Int = 0
    var balance:Double = 1000.00
    var pin:String = "1234"
    var pin2:String = ""
    val reader = Scanner(System. `in`)
    val alphabets: List<Char> = ('A'..'Z')+('0'..'9')


    fun randomCode():String{
        return List(10){alphabets.random()}.joinToString(separator = "")
    }

    fun date_time(): String? {
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
        //val formatedDate = formatter.format(date)
        return formatter.format(date)
    }

    override fun sendMoney() {
        println("Enter phone number: ")
        //scanning the phone number
        phoneNumber = reader.nextLine()
        println("Enter Amount: ")
        amount = reader.nextInt()
        println("Enter M-PESA PIN: ")
        pin2 =reader.nextLine()
        if (phoneNumber.length==10 && (amount in 1..99999) && pin2==pin){
            balance = balance - amount
            println("${randomCode()} Confirmed Ksh$amount sent to $phoneNumber  on ${date_time()} " +
                    "New M-PESA balance is Ksh${balance}. Transaction cost, Ksh0.00.")

        }
    }

    override fun withdrawCash() {
        TODO("Not yet implemented")
    }

    override fun buyAirtime() {
        TODO("Not yet implemented")
    }

    override fun lipaNaMpesa() {
        TODO("Not yet implemented")
    }

    override fun myAccount() {
        TODO("Not yet implemented")
    }
}
