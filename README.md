Net Cafe Management Web Application

Student Names: Juncheng Hao
Zhiyu Liao
Kooshal Aman Foolmaun
Student IDs:2234651
202311349
2232153
Course Title & Code:
420-MP6-AS C2_INFORMATION SYSTEMS IMPLEMENTATION
Instructor: Asma Aouichat
Submission Date: 02/05/2025

Table of Contents
1. Introduction
2. Project Description
3. Selected Design Patterns
4. Justification of Pattern Choice
5. System Design and UML Diagrams
5.1 Use Case Diagram
5.2 Use Case Descriptions and User Story 
5.4 Class Diagram
6. Implementation Details
6.1 Tools Used
6.2 Final Application Overview
7. Benefits of the Pattern Used
8. Challenges and Limitations
9. Conclusion
10. References
11. Appendices 






1.Introduction
Context of the Project
Today, many people still use internet cafés to access computers and the internet. These cafés need a good system to manage customers, track computer usage, handle payments, and keep everything organized. Our project helps with all of that.
Purpose of the Report
This report explains how we designed and built a Net Café Management Web Application. It covers what the system does, how we planned it, and why we chose certain design ideas and tools.
Importance of Design Patterns and UML
Design patterns are useful ways to solve common coding problems. They help make our code cleaner and easier to update. UML diagrams are drawings that show how our system works, making it easier for the team to understand and build the system together. Using these helps us build a better, more organized application.
2. Project Description
System Overview
Our project is a web application that helps internet cafés run more smoothly. It lets users register, start and stop computer sessions, get billed automatically, book seats online, and recharge their accounts. Admins can use the system to see reports, check inventory, and manage the café from one dashboard. The app is built in layers to keep everything organized and fast.
Functional Scope
The goal is to make café operations faster and easier, and give users a better experience. The system includes parts for both users and administrators, with a modern, easy-to-use design. We also used the MVC model to keep the interface and logic separate.
Key Requirements
•	Actors:
o	Users (Customers)
o	Admins (Café staff or owners)
o	The System (automated features)
•	Functionalities:
o	Register and log in
o	Start and stop using a computer
o	Automatic billing based on usage time
o	Manage café inventory
o	Book seats online
o	Recharge user accounts
o	Admin dashboard with reports
•	Non-functional:
o	Easy to scale and improve later
o	Fast and real-time updates
o	Secure login system
o	Can be used on different devices
o	Easy to maintain and understand
3. Selected Design Patterns
Factory Pattern, Composite Pattern, Facade pattern, Observer Pattern, Responsibility Pattern
4. Justification of Pattern Choice
1. Factory Pattern (User Creation)

Our system has different types of users, like Customers and Admins. If we used if/else statements everywhere to create them, the code would become messy and repeated. The Factory Pattern lets us create users in one central place. It keeps the code clean and makes it easy to add new user types later (like Technician or Guest) without changing the old code.
________________________________________
2. Composite Pattern (Product Menu Management)
Our product list includes single items (like a drink) and groups of items (like Snacks to Drinks to Soft Drinks). We needed a way to manage both in the same way. The Composite Pattern lets us treat both single products and product groups the same. This makes it easier to show the menu, search through it, and manage it.
________________________________________
3. Facade Pattern (Session Management)
Starting a computer session includes many steps—checking if the user is valid, if the computer is free, and then recording the session. The Facade Pattern gives us one simple interface (called SessionManagerFacade) that handles all these steps. This makes the code easier to read and avoids direct links between different system parts.
________________________________________
4. Observer Pattern (Booking Notification)
When someone books a computer, different parts of the system (like the admin dashboard or logs) need to know about it right away. The Observer Pattern allows those parts to “listen” for bookings without being directly connected. We can easily add or remove listeners without breaking other code.
________________________________________
5. Chain of Responsibility Pattern (Order Processing)
When a user places an order, we need to do several steps: check session, check product availability, create the order, and maybe more. The Chain of Responsibility Pattern breaks these steps into small pieces. Each part does one job and passes it to the next. This makes the system easier to manage and update (for example, we can add a discount step later without changing everything).

5. System Design and UML Diagrams
5.1 Use Case Diagram
 


5.2 Use Case Descriptions and User Story
1 User Registration & Authentication
As a customer, I want to create an account so that I can access the system.
As an admin, I want to manage user roles so that I can control access levels.
2 Computer Session Management
As a customer, I want to start a computer session so that I can use the services.
As an admin, I want to monitor all active sessions so that I can track usage.
3 Automated Billing System
As a customer, I want to view my session costs so that I know how much to pay.
As an admin, I want the system to calculate billing automatically so that I don’t have to do it manually.
4 Inventory Management
As an admin, I want to track inventory levels so that I can restock items when needed.
As a customer, I want to see available food and drinks so that I can place an order.
5 Online Seat Booking
As a customer, I want to book a seat online so that I can ensure availability.
As an admin, I want to manage seat bookings so that I can organize space efficiently.
6 Recharge Capabilities
As a customer, I want to recharge my account so that I can pay for services.
As an admin, I want to track recharges so that I can monitor financial transactions.
7 Admin Dashboard
As an admin, I want to see real-time reports so that I can analyze business performance.
As an admin, I want to manage users, sessions, and inventory from one place so that I can efficiently control the system.

Use Case Name:  
Start Computer Session	

Use Case Description: 	This use case enables a registered customer to start a computer session in the net café after logging into the system. It includes steps for authentication, seat availability check, and session initiation with tracking for billing.
Use Case Authors: 	Juncheng/Zhiyu/Aman

Actors: 	•  Customer
Location: 	Net Café Web Application (Customer Portal / Admin Dashboard)
Status: 	Using

Priority: 	High

Assumptions: 	
•  The customer has a registered and active account.
•  The system is online and operational.
•  There are available seats for session use.

Pre-conditions: 	•  The customer is authenticated and logged into the system.
•  There is sufficient balance in the customer’s account.
•  The desired computer is not currently reserved or in use.

Post-conditions: 	
•  A computer session is started and tracked in real-time.
•  The customer’s balance is adjusted according to session duration.
•  The session log is stored for billing and admin tracking.


Primary Pathway
(Happy Path)
(Main Flow)	
1. Customer logs into the system.
2. Customer navigates to "Start Session."
3. System displays available seats.
4. Customer selects a seat and confirms.
5. System verifies balance and seat availability.
6.  Session is started and the timer begins.
7.  System logs session start and status.
8. Admin dashboard is updated with active session info.

Alternative Pathway	
•  If no seats are available, the system shows a message and suggests booking.
•  If the customer’s balance is insufficient, the system prompts for recharge.


Exception Pathway
(Error Pathway)
	
•  If a technical error occurs (e.g., system crash or network error), the system logs the error and notifies the customer.
•  If session initiation fails, the system cancels the transaction and displays an appropriate error message.


5.3 Class Diagram


6. Implementation Details
1.Tools Used
Programming Language：
Backend: Java 17
Backend Framework: Spring Boot 3
Frontend: JavaScript
Frontend Framework: React.js with Styled-Components
IDE & Editors: IntelliJ IDEA
API Testing: Postman
Browser Debugging: Chrome DevTools
Database：MySQL
 
6.2 Final Application Overview
User Interface:
The system includes two main dashboards:

Customer Dashboard
•	Register/Login
•	View balance
•	Start/stop computer session
•	Buy Snacks & Drinks
•	Book the time for computer using
•	Real-time session duration and estimated charge
Admin Dashboard
•	Monitor active sessions
•	Manually start/stop sessions for customers
•	View available computers
•	Recharge user accounts
•	Manage all the users
•	Manage all the products
•	Manage all the bookings
Final Application Deployment
Run backend via Spring Boot (localhost:8080) and frontend via Vite (localhost:3000)
  For details, see appendix.

7. Benefits of the Pattern Used
1. Factory Pattern (User Creation)
•	Benefit: Simplifies object creation for different user roles (Admin, Customer) without exposing the instantiation logic.
•	Effect: Promotes consistency and flexibility when registering or logging in users, and supports future extension.
2. Facade Pattern (Session Management)
•	Benefit: Provides a unified interface (SessionManagerFacade) to manage complex interactions between UserService, ComputerService, and ComputerSessionService.
•	Effect: Reduces system complexity and simplifies session start/stop logic for both frontend and controller layers.
3. Composite Pattern (Product Menu Structure)
•	Benefit: Treats individual products and product categories uniformly using a shared interface.
•	Effect: Simplifies rendering product menus and handling nested product groups 
4. Observer Pattern (Booking Notification)
•	Benefit: Enables components (admin dashboards, notification systems) to automatically receive updates when a new booking is created.
•	Effect: Supports real-time awareness of customer actions, improving responsiveness and user experience.
5. Chain of Responsibility Pattern (Order Processing)
•	Benefit: Breaks down order handling logic into a chain of independent handler classes such as SessionValidationHandler and OrderProcessingHandler.
•	Effect: Improves modularity and scalability, making it easy to insert new without changing existing code. Each handler focuses on one responsibility and delegates the rest, promoting clean separation of concerns.

8. Challenges and Limitations
Challenges Encountered:
•	Learning how to use design patterns correctly was difficult at first.
•	Making the system work in real-time (like session tracking and billing) was a bit complex.
•	Connecting different parts of the system, like login, billing, and inventory, took a lot of time.
•	Testing the whole application and fixing bugs was challenging and required teamwork.
Limitations:
•	The system is not fully optimized for mobile devices yet.
•	The admin dashboard has basic features, but more advanced analytics could be added in the future.
•	Right now, only one language is supported; multi-language support can be added later.
•	Some features like online payment are not included due to time limits.
9. Conclusion
In this project, we successfully built a Net Café Management Web Application that helps café owners manage their business more easily. The system includes important features such as user registration, session tracking, automated billing, inventory control, seat booking, and an admin dashboard. We followed good software design practices by using the MVC pattern and several design patterns, like Factory and Singleton to make our code more organized and flexible. The project also helped us improve our teamwork, coding, and problem-solving skills. While there are still some features to add, we created a solid foundation for a complete café management system.

10. References
1.	Spring Boot Documentation
Spring Boot Reference Guide. https://docs.spring.io/spring-boot/docs/current/reference/html/
2.	React.js Documentation
React – A JavaScript library for building user interfaces. https://reactjs.org/docs/getting-started.html
3.	Styled-Components Documentation
Visual primitives for the component age. https://styled-components.com/docs
4.	Maven Project Management
Apache Maven Documentation. https://maven.apache.org/guides/index.html
5.	Postman
API platform for building and using APIs. https://www.postman.com/
6.	Jackson for JSON Handling
FasterXML Jackson Project. https://github.com/FasterXML/jackson
7.	JPA (Java Persistence API)
Java EE JPA Specification. https://jakarta.ee/specifications/persistence/
8.	Git & GitHub
Git Documentation. https://git-scm.com/doc
GitHub Docs. https://docs.github.com/en






