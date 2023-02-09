package ie.setu

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
        >_______________________________________________________________________
        >                             MONTHLY PAYSLIP
        >_______________________________________________________________________
        >                                                                       
        >     EMPLOYEE NAME: ${getFullName()}, ID: ${employeeId}
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
        >        Cycle To Work: ${monthlyCycleDeduction}
        >_______________________________________________________________________
        >
        >     NET PAY: ${roundTwoDecimals(getGrossMonthlyPay() - getTotalMonthlyDeductions())}                            
        >_______________________________________________________________________
        """.trimMargin(">")

        println(printOut)
    }
}

