package income;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataBase {
	
	// 2D array to handle the income table
	String[][] incomeArray;
	
	// used to determine row_length for the 2D array
	int rowlength = 0;
	
	// total income and withdrawal and net_Income
	int totalIncome;
	int totalWithdrawal;
	int netIncome;
	
	// connection declaration
    Connection con;

    public DataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Income", 
                    "root", "Ab12el34te56sf78@");

            java.sql.Statement state = con.createStatement();
            ResultSet set = state.executeQuery("SELECT * FROM income");
            
            java.sql.Statement state_1 = con.createStatement();
            ResultSet set_1 = state_1.executeQuery("SELECT * FROM income");
            
            int moneyIn = 0;
            int moneyOut = 0;
            while(set.next()) {
            	moneyIn += set.getInt("income");
            	moneyOut += set.getInt("withdrawal");
            	rowlength++;
            }
            
            totalIncome = moneyIn;
            totalWithdrawal = moneyOut;
            netIncome = totalIncome - totalWithdrawal;
            
            incomeArray = new String[rowlength][4];
            
            int j = 0;
            
            while(set_1.next()) {
            	incomeArray[j][0] = set_1.getString("ID");
                incomeArray[j][1] = set_1.getString("income");
                incomeArray[j][2] = set_1.getString("withdrawal");
                incomeArray[j][3] = set_1.getString("Date");
                j++;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
    	
        new DataBase();
    }
}
