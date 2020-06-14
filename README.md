# Courier Management System

#### In the past 15-20 years, there has been a drastic upsurge in the digital lifestyle. People like to save their time using online portal instead of standing in long queues to get their things done. Here in our Courier Management System, we would like to give users some accesses where he can request to send mail or goods using courier. As a human tendency to always keep an eye on things, he will also have a tracking id which will enable him to track his post at any point of time. Administrators will have some advance database access like the capability to INSERT, UPDATE and DELETE the invoice, shipping details, cost, etc. Along with this, they will be able to Add, Delete and Update the delivery status. 

## Administrator
1. Screen to login to the Courier Management System
2. ADD &amp; UPDATE the Invoice of orders and Status of Delivery
3. Responsible to manage details of customers and login account
4. Screen to logout of the Courier Management System
## Customer/User
1. Screen which will enable the customer/user to register
2. At any time, customer/user can cancel or make any modification in his order
3. User can look for and get details of shipping cost before placing any order
4. User can make payment of their created order
5. Tracking ID will enable the customer/user to track his order
6. User can make modifications in his personal details
7. User will be able to logout of the system

## CLASS DESCRIPTION
1) We will have two subclasses named DeliveryAgent and Customer of a superclass User
2) There will be one interface – INVOICE which is implemented by CustomerInvoice and
CompanyInvoice
3) Between Customer and booking information there would be a logical relationship
4) Admin has rights to add and modify the cost of shipment, can update customer invoice,
reset the password for customers and can add new details on charges.
5) Customer has the right to sign up, get details, place, inquire order status, cancel the order,
track order using id, update profile, and can also see the tracking history.
6) There will be one more functionality which involves the invoice and package class for
placed order, shipment, payment etc.

## UML Diagram

![ss](https://user-images.githubusercontent.com/57380604/84603205-23d60000-ae52-11ea-8153-a472b80c8cf1.png)
