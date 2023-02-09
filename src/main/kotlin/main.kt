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
    getPayslip()
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

//Generates the payslip template and makes calculations with the values given
fun getPayslip(){

    val monthlySalary = roundTwoDecimals(grossSalary/12)

    val monthlyPrsi     = roundTwoDecimals(monthlySalary * (prsiPercentage / 100))

    val monthlyPaye     = roundTwoDecimals(monthlySalary * (payePercentage / 100))

    val grossPay        = roundTwoDecimals(monthlySalary + (annualBonus/12))

    val totalDeductions = roundTwoDecimals((monthlyPrsi + monthlyPrsi + monthlyCycleDeduction))

    val printOut = """
        >_______________________________________________________________________
        >                             MONTHLY PAYSLIP
        >_______________________________________________________________________
        >                                                                       
        >     EMPLOYEE NAME: ${getFullName()}, ID: $employeeId
        >_______________________________________________________________________
        > 
        >     PAYMENT DETAILS (Total Payment: $grossPay)
        >_______________________________________________________________________
        >        Salary: $monthlySalary
        >        Bonus: ${roundTwoDecimals(annualBonus) / 12}
        >_______________________________________________________________________
        >                                                                       
        >     DEDUCTION DETAILS (Total Deductions: $totalDeductions)
        >_______________________________________________________________________
        >                                         
        >        PRSI: $monthlyPrsi
        >        PAYE: $monthlyPaye
        >        Cycle To Work: $monthlyCycleDeduction
        >_______________________________________________________________________
        >
        >     NET PAY: ${roundTwoDecimals(grossPay - totalDeductions)}                            
        >_______________________________________________________________________
        """.trimMargin(">")

    println(printOut)
}