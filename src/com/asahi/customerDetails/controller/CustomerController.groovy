package com.asahi.customerDetails.controller

import com.asahi.customerDetails.entity.Customer
import com.asahi.customerDetails.service.CustomerService
import com.asahi.customerDetails.service.CustomerServiceImpl

class CustomerController {
    CustomerService customerService

    CustomerController(){
        customerService = new CustomerServiceImpl()
    }

    def createCustomer(Customer customer){
        customerService.createCustomer(customer)
    }

    def deleteCustomer(int id){
        customerService.deleteCustomer(id)
    }

    def readAllCustomers(){
        customerService.readAllCustomers()
    }

    def searchCustomer(int id){
        customerService.searchCustomer(id)
    }

    def idCheckerForUpdation(int idToUpdate){
        customerService.idCheckerForUpdation(idToUpdate)
    }

    def emailUpdate(int idToUpdate,String emailToUpdate){
        customerService.emailUpdate(idToUpdate,emailToUpdate)
    }

    def mobNumberUpdate(int idToUpdate, long mobNumberToUpdate){
        customerService.mobNumberUpdate(idToUpdate,mobNumberToUpdate)
    }

}
