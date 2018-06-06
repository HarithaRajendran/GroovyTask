package com.asahi.customerDetails.service

import com.asahi.customerDetails.dao.CustomerDao
import com.asahi.customerDetails.dao.CustomerDaoClass
import com.asahi.customerDetails.entity.Customer

class CustomerServiceClass implements CustomerService {

    CustomerDao customerDao

    CustomerServiceClass() {
        customerDao = new CustomerDaoClass()
    }

    @Override
    def createCustomer(Customer customer) {
        return customerDao.createCustomer(customer)
    }

    @Override
    def deleteCustomer(int id) {
        return customerDao.deleteCustomer(id)
    }

    @Override
    def readAllCustomers() {
        return customerDao.readAllCustomers()
    }

    @Override
    def searchCustomer(int id) {
        return customerDao.searchCustomer(id)
    }

    @Override
    def idCheckerForUpdation(int idToUpdate) {
        return customerDao.idCheckerForUpdation(idToUpdate)
    }

    @Override
    def emailUpdate(int idToUpdate, String emailToUpdate) {
        return customerDao.emailUpdate(idToUpdate, emailToUpdate)
    }

    @Override
    def mobNumberUpdate(int idToUpdate, long mobNumberToUpdate) {
        return customerDao.mobNumberUpdate(idToUpdate, mobNumberToUpdate)
    }
}

trait CustomerService {
    abstract createCustomer(Customer customer)
    abstract deleteCustomer(int id)
    abstract readAllCustomers()
    abstract searchCustomer(int id)
    abstract idCheckerForUpdation(int idToUpdate)
    abstract emailUpdate(int idToUpdate, String emailToUpdate)
    abstract mobNumberUpdate(int idToUpdate, long mobNumberToUpdate)
}


