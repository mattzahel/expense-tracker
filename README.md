
# :money_with_wings: Expense Tracker App  
  
The final project for the Java web application programming class. The main purpose of the app is to track user expenses.
  
![](https://user-images.githubusercontent.com/27418125/153964504-7536963d-1943-4c49-bc9c-3979c0e749ac.png)  
  
## Configuration  
  
1. Clone project  
  
    `git clone https://github.com/mattzahel/expense-tracker.git`  
  
2. Create MySQL database  
  
    > Recommended db name - `expensetracker`
  
3. Check database connection settings and change them if needed  
  
   - `src/main/java/pl/edu/wszib/expensetracker/configuration/AppConfiguration.java`  
   
	   ```  
        public Connection connection() {  
            ...  
        }
	    ```  
     
   - `src/main/resources/hibernate.cfg.xml`  
	   
	   ```
	   <hibernate-configuration>  
            ...
	   </hibernate-configuration>  
	   ```

4. Run app :blush:

    To log in to the application, you must first create an account by going to the registration view (most likely http://localhost:8080/register)
    
