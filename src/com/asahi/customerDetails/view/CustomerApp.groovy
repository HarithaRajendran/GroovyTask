package com.asahi.customerDetails.view

import com.asahi.customerDetails.controller.CustomerController
import com.asahi.customerDetails.entity.Customer

import java.util.regex.Pattern

class CustomerApp {

    Scanner scanner
    CustomerController customerController

    CustomerApp() {
        scanner = new Scanner(System.in)
        customerController = new CustomerController()
    }

    public void start() {

        while (true) {
            int choice = choiceMenu()
            switch (choice) {
                case 0:
                    System.exit(0)
                    break
                case 1:
                    createCustomer()
                    break
                case 2:
                    updateCustomer()
                    break
                case 3:
                    readAllCustomers()
                    break
                case 4:
                    deleteCustomer()
                    break
                case 5:
                    searchCustomer()
                    break
                default:
                    println "Invalid input\n"
                    break
            }
        }

    }

    private int choiceMenu() {
        println ""
        println "--------Customer Operation--------"
        println "1. Create Customer"
        println "2. Update Customer Details"
        println "3. Read All Customers"
        println "4. Delete Customer"
        println "5. Search Customer By Id"
        println "0. Exit"
        int choice = scanner.nextInt()
        return choice
    }

    private void createCustomer() {
        String name
        String email
        long mobile_number
        println "Enter Customer Name"
        name = scanner.next()
        println "Enter Customer email(eg: example123@email.org)"
        email = scanner.next()
        boolean emailChecker = isPatternMatchingForEmail(email)
        if (emailChecker) {
            println "Enter Customer Mobile Number(Should contains exactly 10 digits)"
            mobile_number = scanner.nextLong()
            boolean mobileChecker = isPatternMatchingForMobile(mobile_number)
            if (mobileChecker) {

                Customer customer = new Customer()
                customer.name = name
                customer.email = email
                customer.mobile_number = mobile_number
                def status = customerController.createCustomer(customer)
                println "The Customer Id Created is ${status} \n Customer Created Successfully..."

            } else {
                println "Mobile number should contains exactly 10 digits..."
            }
        } else {
            println '''
                    invalid email format 
                    Try again
                    '''
        }
    }

    private void updateCustomer() {
        println "-----------Customer Updation-----------"
        println "Enter customer ID to do Updation"
        int idToUpdate = scanner.nextInt()

        def status = customerController.idCheckerForUpdation(idToUpdate)
        if(status){
            println "Select to Update Fields"
            println "1.Update email (eg: example123@email.org)"
            println "2.Update mobile_number (Should contains exactly 10 digits)"
            int fieldChoice = scanner.nextInt()
            switch(fieldChoice){
                case 1:
                    updateEmail(idToUpdate)
                    break
                case 2:
                    updateMobileNumber(idToUpdate)
                    break
                default:
                    println "Invalid selection..."
                    break
            }
        }else{
            println "ID : ${idToUpdate} is not found..."
        }

    }


    void updateEmail(idToUpdate){
        println "Enter the email id to be updated"
        String emailToUpdate = scanner.next()
        boolean emailChecker = isPatternMatchingForEmail(emailToUpdate)
        if (emailChecker) {
           def status = customerController.emailUpdate(idToUpdate,emailToUpdate)
            if(status)
            println "Email Updated Successfully..."
        } else {
            println '''
                    invalid email format 
                    Try again
                    '''
        }

    }

    void updateMobileNumber(idToUpdate){
        println "Enter the mobile number id to be updated"
        long mobNumberToUpdate = scanner.nextLong()
        boolean mobileChecker = isPatternMatchingForMobile(mobNumberToUpdate)
        if (mobileChecker) {
            def status = customerController.mobNumberUpdate(idToUpdate,mobNumberToUpdate)
            if(status)
                println "Mobile Number Updated Successfully..."
        } else {
            println "Mobile number should exactly contains 10 digits..."
        }
    }


    private void readAllCustomers() {
        def allCustomers = customerController.readAllCustomers()
        println "-----------------------------------------------------------------"
        println "Id | \tName | \t\tEmail | \t\t\t  | Mobile_Number"
        println "-----------------------------------------------------------------"

       allCustomers.each {println "${it.id}  | \t${it.name} | \t${it.email} | \t${it.mobile_number}"}
    }

    private void deleteCustomer() {
        int id
        println "Enter the Id to Delete"
        id = scanner.nextInt()
        def status = customerController.deleteCustomer(id)
        if (status) {
            println "User with id : ${id} deleted Successfully...."
        } else {
            println "id : ${id} Not found..."
        }
    }

    private void searchCustomer() {
        int id
        println "Enter the Id to Search"
        id = scanner.nextInt()
        def data = customerController.searchCustomer(id)
        if (data.size() == 0) {
            println "id : ${id} Not found..."
        } else {
            println "-----------------------------------------------------------------"
            println "Id | \tName | \t\tEmail | \t\t\t  | Mobile_Number"
            println "-----------------------------------------------------------------"

            data.each {println "${it.id}  | \t${it.name} | \t${it.email} | \t${it.mobile_number}"}
        }
    }

    // >>Using Regular Expression for email
    boolean isPatternMatchingForEmail(String email) {
        Pattern emailPattern = ~/[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})/
        return email ==~ emailPattern
    }

    // >>Using Regular Expression for mobile number (10 digits)
    boolean isPatternMatchingForMobile(long mobile_number) {
        Pattern mobilePattern = ~/[0-9]{10}$/
        return mobile_number ==~ mobilePattern
    }

}
