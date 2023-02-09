package ie.setu

import kotlin.math.round

//Values used in the payslip
val firstName = "Joe"
val lastName = "Soap"
val gender = "m"
val employeeId = 6143
val grossSalary = 67_543.21
val payePercentage = 38.5
val prsiPercentage = 5.2
val annualBonus = 1_450.50
val monthlyCycleDeduction = 54.33

fun main(args: Array<String>){
    var input:Int
    do{
        input = menu()
        when(input){
            1 -> println("Monthly Salary: ${getMonthlySalary()}")
            2 -> println("Monthly PRSI: ${getMonthlyPRSI()}")
            3 -> println("Monthly PAYE: ${getMonthlyPAYE()}")
            4 -> println("Monthly Gross Pay: ${getGrossMonthlyPay()}")
            5 -> println("Monthly Total Deductions: ${getTotalMonthlyDeductions()}")
            6 -> println("Monthly Net Pay: ${getNetMonthlyPay()}")
            7 -> println(getPayslip())
            0 -> println("Closing app")
            else -> println("Invalid option")
        }
        println()
    } while(input != 0)
}

//Creates the menu for the employeeapp
fun menu(): Int{
    print("""
    Employee Menu for ${getFullName()}
      1. Monthly Salary
      2. Monthly PRSI
      3. Monthly PAYE
      4. Monthly Gross Pay
      5. Monthly Total Deductions
      6. Monthly Net Pay
      7. Full Payslip
      0. Exit
    Enter Option: """)
    return readLine()!!.toInt()
}

//Rounds doubles to 2 decimal places
fun roundTwoDecimals(num: Double) = round(num * 100) / 100

//Combines firstName and lastName, also adds Mr. or Ms. depending on gender
fun getFullName() = when (gender){
    "m", "M" -> "Mr. $firstName $lastName"
    "f", "F" -> "Ms. $firstName $lastName"
    else -> "$firstName $lastName"
}

//List of methods that calculate figures for payslip
fun getMonthlySalary() = roundTwoDecimals(grossSalary / 12)
fun getMonthlyPRSI() = roundTwoDecimals(getMonthlySalary() * (prsiPercentage / 100))
fun getMonthlyPAYE() = roundTwoDecimals(getMonthlySalary() * (payePercentage / 100))
fun getGrossMonthlyPay() = roundTwoDecimals(getMonthlySalary() + (annualBonus / 12))
fun getTotalMonthlyDeductions() = roundTwoDecimals((getMonthlyPRSI() + getMonthlyPAYE() + monthlyCycleDeduction))
fun getNetMonthlyPay() = roundTwoDecimals(roundTwoDecimals(getGrossMonthlyPay() - getTotalMonthlyDeductions()))

//Generates the payslip template and makes calculations with the values given
fun getPayslip(){

    val printOut = """
        >_______________________________________________________________________
        >                             MONTHLY PAYSLIP
        >_______________________________________________________________________
        >                                                                       
        >     EMPLOYEE NAME: ${getFullName()}, ID: $employeeId
        >_______________________________________________________________________
        > 
        >     PAYMENT DETAILS (Total Payment: ${getGrossMonthlyPay()})
        >_______________________________________________________________________
        >        Salary: ${getMonthlySalary()}
        >        Bonus: ${roundTwoDecimals(annualBonus) / 12}
        >_______________________________________________________________________
        >                                                                       
        >     DEDUCTION DETAILS (Total Deductions: ${getTotalMonthlyDeductions()})
        >_______________________________________________________________________
        >                                         
        >        PRSI: ${getMonthlyPRSI()}
        >        PAYE: ${getMonthlyPAYE()}
        >        Cycle To Work: $monthlyCycleDeduction
        >_______________________________________________________________________
        >
        >     NET PAY: ${roundTwoDecimals(getGrossMonthlyPay() - getTotalMonthlyDeductions())}                            
        >_______________________________________________________________________
        """.trimMargin(">")

    println(printOut)
}