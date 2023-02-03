package ie.setu.employeeapp

import java.util.*
import kotlin.math.roundToInt


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

fun printPayslip(){

    val monthlySalary = (grossSalary/12)

    val monthlyPrsi = monthlySalary * (prsiPercentage/100)

    val monthlyPaye = monthlySalary * (payePercentage / 100)

    val grossPay = (monthlySalary + (annualBonus/12))

    val totalDeductions = (monthlyPrsi + monthlyPrsi + monthlyCycleDeduction)


    val printOut = """
        >|_______________________________________________________________________|
        >|                             Monthly Payslip                           |
        >|_______________________________________________________________________|
        >|                                                                       |
        >|     Employee Name: ${firstName.uppercase()} ${lastName.uppercase()}               Gender: ${gender.uppercase()}                   |
        >|                                                                       |
        >|_______________________________________________________________________|
        >|                                                                       |
        >|     PAYMENT DETAILS                     DEDUCTION DETAILS             |
        >|_______________________________________________________________________|
        >|                                                                       |
        >|     Salary: ${"%.2f".format(monthlySalary)}                     PRSI: ${"%.2f".format(monthlyPrsi)}                  |
        >|     Bonus: ${"%.2f".format(annualBonus/12)}                       PAYE: ${"%.2f".format(monthlyPaye)}                 |
        >|                                         Cycle To Work: ${"%.2f".format(monthlyCycleDeduction)}          |
        >|_______________________________________________________________________|
        >|     Gross: ${"%.2f".format(grossPay)}                      Total Deductions: ${"%.2f".format(totalDeductions)}      |
        >|_______________________________________________________________________|
        >|                           Net Pay: ${"%.2f".format((grossPay - totalDeductions))}                            |
        >|_______________________________________________________________________|
        """.trimMargin(">")

    println(printOut)
}