package ie.setu.models

import ie.setu.ansiCyan
import ie.setu.ansiGreen
import ie.setu.ansiRed
import ie.setu.ansiReset
import kotlin.math.round

class Employee(var firstName: String, var lastName: String, var gender: String, var employeeId: Int,
               var grossSalary: Double, var payePercentage: Double, var prsiPercentage: Double,
               var annualBonus: Double, var monthlyCycleDeduction: Double) {

    fun roundTwoDecimals(num: Double) = round(num * 100) / 100

    //Combines firstName and lastName, also adds Mr. or Ms. depending on gender
    fun getFullName() = when (gender) {
        "m", "M" -> "Mr. ${firstName} ${lastName}"
        "f", "F" -> "Ms. ${firstName} ${lastName}"
        else -> "${firstName} ${lastName}"
    }

    //List of methods that calculate figures for payslip
    fun getMonthlySalary() = roundTwoDecimals(grossSalary / 12)
    fun getMonthlyPRSI() = roundTwoDecimals(getMonthlySalary() * (prsiPercentage / 100))
    fun getMonthlyPAYE() = roundTwoDecimals(getMonthlySalary() * (payePercentage / 100))
    fun getGrossMonthlyPay() = roundTwoDecimals(getMonthlySalary() + (annualBonus / 12))
    fun getTotalMonthlyDeductions() = roundTwoDecimals((getMonthlyPRSI() + getMonthlyPAYE() + monthlyCycleDeduction))
    fun getNetMonthlyPay() = roundTwoDecimals(roundTwoDecimals(getGrossMonthlyPay() - getTotalMonthlyDeductions()))

    //Generates the payslip template and makes calculations with the values given
    fun getPayslip() {

        val printOut = """
        >${ansiGreen}_______________________________________________________________________$ansiReset
        >                             MONTHLY PAYSLIP
        >${ansiGreen}_______________________________________________________________________$ansiReset
        >                                                                       
        >     EMPLOYEE NAME: ${ansiCyan + getFullName() + ansiReset}, ID: ${ansiCyan + employeeId + ansiReset}
        >${ansiGreen}_______________________________________________________________________$ansiReset
        > 
        >     PAYMENT DETAILS (Total Payment: ${getGrossMonthlyPay()})
        >${ansiGreen}_______________________________________________________________________$ansiReset
        >        ${ansiRed}Salary: ${ansiReset}${getMonthlySalary()}
        >        ${ansiRed}Bonus: ${ansiReset}${roundTwoDecimals(annualBonus) / 12}
        >${ansiGreen}_______________________________________________________________________$ansiReset
        >                                                                       
        >     DEDUCTION DETAILS (Total Deductions: ${getTotalMonthlyDeductions()})
        >${ansiGreen}_______________________________________________________________________$ansiReset
        >                                         
        >        ${ansiRed}PRSI: ${ansiReset}${getMonthlyPRSI()}
        >        ${ansiRed}PAYE: ${ansiReset}${getMonthlyPAYE()}
        >        ${ansiRed}Cycle To Work: ${ansiReset}${monthlyCycleDeduction}
        >${ansiGreen}_______________________________________________________________________$ansiReset
        >
        >     NET PAY: ${roundTwoDecimals(getGrossMonthlyPay() - getTotalMonthlyDeductions())}                            
        >${ansiGreen}_______________________________________________________________________$ansiReset
        """.trimMargin(">")

        println(printOut)
    }

    override fun toString(): String {
        return "Employee(firstName='$firstName', lastName='$lastName', gender='$gender', employeeId=$employeeId, grossSalary=$grossSalary, payePercentage=$payePercentage, prsiPercentage=$prsiPercentage, annualBonus=$annualBonus, monthlyCycleDeduction=$monthlyCycleDeduction)"
    }
}

