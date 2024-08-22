package income;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Income_Manager extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;

    // Database instance
    private DataBase db = new DataBase();
    private DefaultTableModel T_model;
    private JTable table_2_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Income_Manager frame = new Income_Manager();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Income_Manager() {
        setTitle("Income_Statement");
        ImageIcon icon = new ImageIcon("logoxo.jpg");
        this.setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 255));
        panel.setBounds(10, 10, 676, 102);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_2_1 = new JLabel("Total: ");
        lblNewLabel_2_1.setForeground(Color.WHITE);
        lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_2_1.setBounds(272, 22, 60, 22);
        panel.add(lblNewLabel_2_1);

        JLabel lblNewLabel_2_1_1 = new JLabel("0");
        lblNewLabel_2_1_1.setForeground(Color.WHITE);
        lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_2_1_1.setBounds(334, 22, 154, 22);
        panel.add(lblNewLabel_2_1_1);

        JLabel lblNewLabel_2_1_2 = new JLabel("T_Income:");
        lblNewLabel_2_1_2.setForeground(Color.WHITE);
        lblNewLabel_2_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_2_1_2.setBounds(380, 54, 87, 22);
        panel.add(lblNewLabel_2_1_2);

        JLabel lblNewLabel_2_1_1_1 = new JLabel("0");
        lblNewLabel_2_1_1_1.setForeground(Color.WHITE);
        lblNewLabel_2_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_2_1_1_1.setBounds(477, 56, 173, 22);
        panel.add(lblNewLabel_2_1_1_1);

        JLabel lblNewLabel_2_1_2_1 = new JLabel("T_out:");
        lblNewLabel_2_1_2_1.setForeground(Color.WHITE);
        lblNewLabel_2_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_2_1_2_1.setBounds(508, 22, 60, 22);
        panel.add(lblNewLabel_2_1_2_1);

        JLabel lblNewLabel_2_1_1_1_1 = new JLabel("0");
        lblNewLabel_2_1_1_1_1.setForeground(Color.WHITE);
        lblNewLabel_2_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_2_1_1_1_1.setBounds(564, 22, 102, 22);
        panel.add(lblNewLabel_2_1_1_1_1);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0, 128, 255));
        panel_1.setBounds(10, 119, 279, 344);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("Income");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(29, 42, 116, 22);
        panel_1.add(lblNewLabel_2);

        textField = new JTextField();
        textField.setBounds(29, 68, 240, 34);
        panel_1.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Withdrawal");
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(29, 112, 116, 26);
        panel_1.add(lblNewLabel_3);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(29, 137, 240, 34);
        panel_1.add(textField_1);

        JButton btnNewButton = new JButton("Save");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int income = Integer.parseInt(textField.getText());
                int withdrawal = Integer.parseInt(textField_1.getText());

                try {
                    db.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Income",
                            "root", "Ab12el34te56sf78@");
                    String sql = "INSERT INTO income (income, withdrawal) VALUES (?, ?)";

                    // Determine the number of rows in the table
                    Statement state_3 = db.con.createStatement();
                    ResultSet countResult = state_3.executeQuery("SELECT COUNT(*) FROM income");
                    if (countResult.next()) {
                        db.rowlength = countResult.getInt(1);
                    }

                    PreparedStatement pstmt = db.con.prepareStatement(sql);
                    pstmt.setInt(1, income);
                    pstmt.setInt(2, withdrawal);
                    int rowsAffected = pstmt.executeUpdate();
                    System.out.println("rowsAffected: " + rowsAffected);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnNewButton.setForeground(new Color(0, 128, 255));
        btnNewButton.setBounds(29, 226, 96, 33);
        panel_1.add(btnNewButton);

        JButton btnNewButton_3 = new JButton("Clear");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                textField_1.setText("");
            }
        });
        btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnNewButton_3.setForeground(new Color(0, 128, 255));
        btnNewButton_3.setBounds(173, 225, 96, 34);
        panel_1.add(btnNewButton_3);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
                    db.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Income",
                            "root", "Ab12el34te56sf78@");
                    
                    int response = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure, You want to clear your statement",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                        );
                    
                    if(response == JOptionPane.YES_OPTION) {
                    	  // truncate table income
                        Statement state_3 = db.con.createStatement();
                        state_3.executeUpdate("Truncate table income");
                    } 
                    
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
        	}
        });
        btnDelete.setForeground(new Color(255, 0, 0));
        btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnDelete.setBounds(173, 300, 96, 34);
        panel_1.add(btnDelete);
        
        JLabel lblNewLabel_2_2 = new JLabel("only on month end =>");
        lblNewLabel_2_2.setForeground(Color.WHITE);
        lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel_2_2.setBounds(39, 307, 135, 22);
        panel_1.add(lblNewLabel_2_2);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(0, 128, 255));
        panel_2.setBounds(299, 117, 387, 346);
        contentPane.add(panel_2);
        panel_2.setLayout(new BorderLayout(0, 0));

        // Initialize table and model
        String[] column = { "ID", "Income", "Withdrawal", "Date" };
        T_model = new DefaultTableModel(new Object[0][4], column);
        table_2_1 = new JTable(T_model) {
            Class[] columnTypes = new Class[] {
                Integer.class, Integer.class, Integer.class, String.class
            };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        };

        table_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        table_2_1.setBackground(new Color(228, 228, 228));

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table_2_1);
        panel_2.add(scrollPane, BorderLayout.CENTER);
        
        
        // list button start
        JButton btnNewButton_4 = new JButton("List");
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Fetch data from the database and update the table
                    db.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Income",
                            "root", "Ab12el34te56sf78@");
                    Statement stmt = db.con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM income");

                    // Clear the table model before adding new data
                    T_model.setRowCount(0);

                    while (rs.next()) {
                        Object[] row = {
                            rs.getInt("id"), 
                            rs.getInt("income"), 
                            rs.getInt("withdrawal"), 
                            rs.getDate("date")
                        };
                        T_model.addRow(row);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton_4.setForeground(new Color(0, 128, 255));
        btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnNewButton_4.setBounds(28, 56, 98, 25);
        panel.add(btnNewButton_4);
        // list button end

        JButton btnNewButton_4_1 = new JButton("Refresh");
        btnNewButton_4_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	DataBase db = new DataBase();
            	String netIncome = String.valueOf(db.netIncome);
            	String totalIncome = String.valueOf(db.totalIncome);
            	String totalWithdrawal = String.valueOf(db.totalWithdrawal);
            	
            	lblNewLabel_2_1_1.setText(netIncome);
            	lblNewLabel_2_1_1_1.setText(totalIncome);
            	lblNewLabel_2_1_1_1_1.setText(totalWithdrawal);
            	
                
            }
        });
        btnNewButton_4_1.setForeground(new Color(0, 128, 255));
        btnNewButton_4_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnNewButton_4_1.setBounds(136, 56, 109, 25);
        panel.add(btnNewButton_4_1);
    }
}
