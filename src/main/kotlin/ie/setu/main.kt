package ie.setu

import ie.setu.controllers.EmployeeAPI
import ie.setu.models.Employee
import mu.KotlinLogging

var employees = EmployeeAPI()

const val ansiReset = "\u001B[0m"
const val ansiRed = "\u001B[31m"
const val ansiGreen = "\u001B[32m"
const val ansiYellow = "\u001B[33m"
const val ansiBlue = "\u001B[34m"
const val ansiCyan = "\u001B[36m"


fun menu():Int{
    logger.info { "Initialising menu" }
    print(""" 
         |${ansiGreen}_______________________________________________________________________$ansiReset
         |Employee Menu
         |${ansiGreen}_______________________________________________________________________$ansiReset
         |   1. ${ansiBlue}Add Employee${ansiReset}
         |   2. ${ansiBlue}List All Employees${ansiReset}
         |   3. ${ansiBlue}Search Employees $ansiReset
         |   4. ${ansiBlue}Print Payslip for Employee${ansiReset}
         |   5. ${ansiBlue}Add sample employees${ansiReset}
         |   6. ${ansiBlue}Remove employee by ID$ansiReset
         |   7. ${ansiBlue}Sort by first name$ansiReset
         |   8. ${ansiBlue}Search by last name$ansiReset
         |   0. ${ansiYellow}Exit${ansiReset}
         |       
         |Enter Option :
         | ${ansiGreen}_______________________________________________________________________$ansiReset
         |""".trimMargin())
    return readLine()!!.toInt()
}

fun start() {
    var input: Int

    do {
        input = menu()
        when (input) {
            1 -> add()
            2 -> list()
            3 -> search()
            4 -> paySlip()
            5 -> dummyData()
            6 -> removeEmployee()
            7 -> sortByFirstName()
            8 -> searchBy()
            0 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}

fun searchBy(){
    println(ansiGreen + "Enter the the name you wish to search by (single letters accepted)")
    val searchCondition = readLine()!!.toString()
    employees.searchLastName(searchCondition)
    println(ansiYellow + "Displaying results" + ansiReset)
}

internal fun employeeSizeCheck(): Int{
    var employeeCount = 0
    employees.findAll()
        .forEach { _ -> employeeCount++ }
    return employeeCount
}

fun sortByFirstName(){
    employees.sortName()
    println(ansiYellow + "Employees sorted by first name" + ansiReset)
}

fun removeEmployee(){
    println(ansiGreen + "Enter the employee ID you wish to remove" + ansiReset)
    val deleteEmployee = readLine()!!.toInt()
    if(deleteEmployee > employeeSizeCheck())
        println(ansiRed + "Unknown employee" + ansiReset)
    else
        employees.remove(deleteEmployee)
}

fun list(){
    employees.findAll()
        .forEach{ println(it) }
    println(ansiGreen + "Finished printing employees" + ansiReset)
}

fun search() {
    println(ansiGreen + "Enter the employee ID you wish to search by" + ansiReset)
    logger.info { "Searches by employee ID" }
    val employee = getEmployeeById()
    if (employee == null)
        println("No employee found")
    else
        println(employee)
}

internal fun getEmployeeById(): Employee? {
    print("Enter the employee id to search by: ")
    val employeeID = readLine()!!.toInt()
    return employees.findOne(employeeID)
}

fun paySlip(){
    val employee = getEmployeeById()
    if (employee != null)
        println(employee.getPayslip())
}

fun dummyData() {
    println(ansiYellow + "Adding sample employees..." + ansiReset)
    employees.create(Employee("Joe", "Soap", "m", 1, 35655.43, 31.0, 7.5, 2000.0, 25.6))
    employees.create(Employee("Joan", "Murphy", "f", 2, 54255.13, 32.5, 7.0, 1500.0, 55.3))
    employees.create(Employee("Mary", "Quinn", "f", 3, 75685.41, 40.0, 8.5, 4500.0, 0.0))
    employees.create(Employee("Jason", "Sonny", "m", 4, 45732.01, 40.0, 6.5, 7000.0, 0.0))
}

fun add(){
    print("Enter first name: ")
    val firstName = readLine().toString()
    print("Enter surname: ")
    val surname = readLine().toString()
    print("Enter gender (m/f): ")
    val gender = readLine().toString()
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

    employees.create(Employee(firstName, surname, gender, 0, grossSalary, payePercentage, prsiPercentage, annualBonus, cycleToWorkMonthlyDeduction))
}

val logger = KotlinLogging.logger {}

fun main(args: Array<String>){
    logger.info { "Launching Employee App" }
    start()
}