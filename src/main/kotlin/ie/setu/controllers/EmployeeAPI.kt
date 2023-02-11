package ie.setu.controllers

import ie.setu.ansiReset
import ie.setu.ansiYellow
import ie.setu.models.Employee

var lastId = 0

internal fun getId(): Int {
    return lastId++
}

class EmployeeAPI {

    private val employees = ArrayList<Employee>()

    fun findAll(): List<Employee> {
        return employees
    }

    fun findOne(id: Int): Employee? {
        return employees.find { p -> p.employeeId == id }
    }

    fun create(employee: Employee) {
        employee.employeeId = getId()
        employees.add(employee)
    }

    fun remove(id: Int){
        val removeId = employees.find { r -> r.employeeId == id }
        employees.remove(removeId)
        println(ansiYellow + "Employee removed" + ansiReset)
    }

    fun sortName(){
        val sortedNames = employees.sortedWith(compareBy { it.firstName })
        for (obj in sortedNames){
            println(obj.firstName)
        }
    }

    fun searchLastName(lName: String){
        employees
            .filter { it.lastName.contains(lName) }
            .forEach { println(it) }
    }
}