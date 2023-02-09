package ie.setu

import kotlin.math.round

//Hardcoded sample employee object
var employee =  Employee("Joe", "Soap", "m", 6143, 67543.21, 38.5, 5.2, 1450.50, 54.33)


fun main(args: Array<String>){
    var input:Int
    addEmployee()
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

//Adds an employee to the Array
fun addEmployee(){
    print("Enter first name: ")
    val firstName = readLine().toString()
    print("Enter surname: ")
    val surname = readLine().toString()
    print("Enter gender (m/f): ")
    val gender = readLine()!!.toString()
    print("Enter employee ID: ")
    val employeeID = readLine()!!.toInt()
    print("Enter gross salary: ")
    val grossSalary = readLine()!!.toDouble()
    print("Enter PAYE %: ")
    val payePercentage = readLine()!!.toDouble()
    print("Enter PRSI %: ")
    val prsiPercentage = readLine()!!.toDouble()
    print("Enter Annual Bonus: ")
    val annualBonus= readLine()!!.toDouble()
    print("Enter Cycle to Work Deduction: ")
    val cycleToWorkMonthlyDeduction= readLine()!!.toDouble()

    employee = Employee(firstName, surname, gender, employeeID, grossSalary, payePercentage, prsiPercentage, annualBonus, cycleToWorkMonthlyDeduction)
}


//Rounds doubles to 2 decimal places
fun roundTwoDecimals(num: Double) = round(num * 100) / 100

//Combines firstName and lastName, also adds Mr. or Ms. depending on gender
fun getFullName() = when (employee.gender){
    "m", "M" -> "Mr. ${employee.firstName} ${employee.lastName}"
    "f", "F" -> "Ms. ${employee.firstName} ${employee.lastName}"
    else -> "${employee.firstName} ${employee.lastName}"
}

//List of methods that calculate figures for payslip
fun getMonthlySalary() = roundTwoDecimals(employee.grossSalary / 12)
fun getMonthlyPRSI() = roundTwoDecimals(getMonthlySalary() * (employee.prsiPercentage / 100))
fun getMonthlyPAYE() = roundTwoDecimals(getMonthlySalary() * (employee.payePercentage / 100))
fun getGrossMonthlyPay() = roundTwoDecimals(getMonthlySalary() + (employee.annualBonus / 12))
fun getTotalMonthlyDeductions() = roundTwoDecimals((getMonthlyPRSI() + getMonthlyPAYE() + employee.monthlyCycleDeduction))
fun getNetMonthlyPay() = roundTwoDecimals(roundTwoDecimals(getGrossMonthlyPay() - getTotalMonthlyDeductions()))

//Generates the payslip template and makes calculations with the values given
fun getPayslip(){

    val printOut = """
        >_______________________________________________________________________
        >                             MONTHLY PAYSLIP
        >_______________________________________________________________________
        >                                                                       
        >     EMPLOYEE NAME: ${getFullName()}, ID: ${employee.employeeId}
        >_______________________________________________________________________
        > 
        >     PAYMENT DETAILS (Total Payment: ${getGrossMonthlyPay()})
        >_______________________________________________________________________
        >        Salary: ${getMonthlySalary()}
        >        Bonus: ${roundTwoDecimals(employee.annualBonus) / 12}
        >_______________________________________________________________________
        >                                                                       
        >     DEDUCTION DETAILS (Total Deductions: ${getTotalMonthlyDeductions()})
        >_______________________________________________________________________
        >                                         
        >        PRSI: ${getMonthlyPRSI()}
        >        PAYE: ${getMonthlyPAYE()}
        >        Cycle To Work: ${employee.monthlyCycleDeduction}
        >_______________________________________________________________________
        >
        >     NET PAY: ${roundTwoDecimals(getGrossMonthlyPay() - getTotalMonthlyDeductions())}                            
        >_______________________________________________________________________
        """.trimMargin(">")

    println(printOut)
}