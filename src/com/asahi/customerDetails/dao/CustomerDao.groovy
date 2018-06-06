package com.asahi.customerDetails.dao

import com.asahi.customerDetails.entity.Customer
import groovy.sql.GroovyRowResult
import groovy.sql.Sql


class CustomerDaoClass implements CustomerDao ,DataConnection{

    def sql
    @Override
    def createCustomer(Customer customer) {

        def user

        sql = dbconnection()

        sql.execute("INSERT INTO customer (name,email,mobile_number) VALUES (${customer.name},${customer.email},${customer.mobile_number})")

        List<GroovyRowResult>  createdUser = sql.rows("select * from customer where email = ${customer.email}")

        createdUser.each {
           user = it.id
        }
        return user
    }

    @Override
    def deleteCustomer(int id) {
        sql = dbconnection()

        if(sql.executeUpdate("delete from customer where id = ${id}") == 0 ){
            return false
        }else true
    }

    @Override
    def readAllCustomers() {
        sql = dbconnection()

        List<GroovyRowResult> allCustomers = sql.rows("select * from customer")

       // return allCustomers
    }

    @Override
    def searchCustomer(int id) {
        sql = dbconnection()

        List<GroovyRowResult> searchedCustomer = sql.rows("select * from customer where id = ${id}")

    }

    @Override
    def idCheckerForUpdation(int idToUpdate) {
        sql = dbconnection()

        List<GroovyRowResult> count = sql.rows("select * from customer where id = ${idToUpdate}")

        if(count.size() == 0){
            return false
        }else true
    }

    @Override
    def emailUpdate(int idToUpdate, String emailToUpdate) {
        sql = dbconnection()

        if(sql.executeUpdate("UPDATE customer SET email = ${emailToUpdate} where id = ${idToUpdate}") == 0){
            return false
        }else true
    }

    @Override
    def mobNumberUpdate(int idToUpdate, long mobNumberToUpdate) {
        sql = dbconnection()

        if(sql.executeUpdate("UPDATE customer SET mobile_number = ${mobNumberToUpdate} where id = ${idToUpdate}") == 0){
            return false
        }else true
    }
}

trait CustomerDao{
    abstract createCustomer(Customer customer)
    abstract deleteCustomer(int id)
    abstract readAllCustomers()
    abstract searchCustomer(int id)
    abstract idCheckerForUpdation(int idToUpdate)
    abstract emailUpdate(int idToUpdate, String emailToUpdate)
    abstract mobNumberUpdate(int idToUpdate, long mobNumberToUpdate)
}

trait DataConnection{

    String username = "root"
    String password = "root"
    def dbconnection(){
        return Sql.newInstance("jdbc:mysql://localhost:3306/groovytask", username,password,"com.mysql.jdbc.Driver")
    }
}