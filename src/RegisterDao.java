import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//This DAO class provides database operations for the table users in the database.
public class RegisterDao {
	
	// variables to store jdbc user name and password
private String dburl = "jdbc:mysql://localhost:3306/userdb" ;
	private String dbuname = "root" ;
	private String dbpassword= "root";
	private String dbdriver = "com.mysql.jdbc.Driver";
	
	//loading jdbc driver
	public void loadDriver (String dbDriver){
		
		try {
			Class.forName (dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//get connection method
	public Connection getConnection() {
		Connection con =null;
		try {
		 con=	DriverManager.getConnection(dburl, dbuname, dbpassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	// insert users info
	public String insert(Member member) {
		
		loadDriver(dbdriver);
		Connection con=getConnection();
		String result ="data entered successfully";
		String sql="insert into member values(?,?,?,?) ";
		try {
			PreparedStatement  ps = con.prepareStatement(sql);
			ps.setString(1,member.getUname());
			ps.setString(2,member.getPassword());
			ps.setString(3,member.getEmail());
			ps.setNString(4,member.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			result = "Data not entered.";
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}
