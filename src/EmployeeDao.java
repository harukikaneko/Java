
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDao {
	private static String url = "jdbc:postgresql://localhost:5432/empdb";
	private static String user = "postgres";
	private static String password = "password";
	private static String jdbcDriver = "org.postgresql.Driver";

	static {
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<User> executeQueryEmployee(String sql) {
		ArrayList<User> list = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(url, user, password)) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User emp = new User();
				emp.setEmpNo(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setDeptNo(rs.getInt(3));
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<User> getEmployeeList() {
		String sql = "SELECT * FROM employee";
		return executeQueryEmployee(sql);
	}

	public static User getEmployeeByEmpNo(int empNo) {
		String sql = "SELECT * FROM employee " + " WHERE empno = " + empNo;
		ArrayList<User> list = executeQueryEmployee(sql);
		return list.get(0);
	}

	public static ArrayList<User> getEmployeeListByName(String name) {
		String sql = "SELECT * FROM employee WHERE name LIKE '%' || ? || '%'";
		ArrayList<User> list = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(url, user, password)) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User emp = new User();
				emp.setEmpNo(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setDeptNo(rs.getInt(3));
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

		// String sql = "SELECT * FROM employee WHERE name LIKE '%" + name + "%'";
		// return executeQueryEmployee(sql);
	}

	public static User loginEmployee(String login, String password) {
		User employee = null;
		String sql = "select * from employee where login = ? and password = ?";
		ArrayList<User> list = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(url, user, EmployeeDao.password)) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, login);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				employee = new User();
				employee.setEmpNo(rs.getInt(1));
				employee.setName(rs.getString(2));
				employee.setDeptNo(rs.getInt(3));
				employee.setLogin(rs.getString(4));
				employee.setPassword(rs.getString(5));
				list.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

}