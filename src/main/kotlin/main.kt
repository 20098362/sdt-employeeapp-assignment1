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

    printPayslip()
}

//Rounds doubles to 2 decimal places
fun roundTwoDecimals(num: Double) = round(num * 100) / 100

//Generates the payslip template and makes calculations with the values given
fun printPayslip(){

    val monthlySalary = roundTwoDecimals(grossSalary/12)

    val monthlyPrsi     = roundTwoDecimals(monthlySalary * (prsiPercentage / 100))

    val monthlyPaye     = roundTwoDecimals(monthlySalary * (payePercentage / 100))

    val grossPay        = roundTwoDecimals(monthlySalary + (annualBonus/12))

    val totalDeductions = roundTwoDecimals((monthlyPrsi + monthlyPrsi + monthlyCycleDeduction))


    val printOut = """
        >|_______________________________________________________________________|
        >|                             Monthly Payslip                           |
        >|_______________________________________________________________________|
        >|                                                                       |
        >|     Employee Name: ${firstName.uppercase()} ${lastName.uppercase()}             Gender: ${gender.uppercase()}                   |
        >|                                                                       |
        >|_______________________________________________________________________|
        >|                                                                       |
        >|     PAYMENT DETAILS                     DEDUCTION DETAILS             |
        >|_______________________________________________________________________|
        >|                                                                       |
        >|     Salary: $monthlySalary                      PRSI: $monthlyPrsi                  |
        >|     Bonus: ${roundTwoDecimals(annualBonus / 12)}                       PAYE: $monthlyPaye                 |
        >|                                         Cycle To Work: $monthlyCycleDeduction          |
        >|_______________________________________________________________________|
        >|     Gross: $grossPay                      Total Deductions: $totalDeductions      |
        >|_______________________________________________________________________|
        >|                           Net Pay: ${roundTwoDecimals(grossPay - totalDeductions)}                            |
        >|_______________________________________________________________________|
        """.trimMargin(">")

    println(printOut)
}