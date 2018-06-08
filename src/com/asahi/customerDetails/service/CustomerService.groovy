package com.asahi.customerDetails.service

import com.asahi.customerDetails.dao.CustomerDao
import com.asahi.customerDetails.dao.CustomerDaoImpl
import com.asahi.customerDetails.entity.Customer

class CustomerServiceImpl implements CustomerService {

    CustomerDao customerDao

    CustomerServiceImpl() {
        customerDao = new CustomerDaoImpl()
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


