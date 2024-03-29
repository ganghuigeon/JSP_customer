package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.Customer;

public class CustomerDAO {
	final static String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final static String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";

	// 데이터베이스 연결 메소드
	public Connection open() {
		Connection conn = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "test", "test1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn; // 데이터베이스 연결 객체 리턴
	}

	// 고객 리스트 가져오는 메소드
	public ArrayList<Customer> getList() throws Exception {
		Connection conn = open(); // DB커넥션 열기
		ArrayList<Customer> customerList = new ArrayList<>(); // Customer 객체를 저장할 ArrayList

		String sql = "SELECT CUSTOMER_ID, NAME, ADDRESS, PHONE, GENDER, AGE, IMG, POINT, GRADE FROM customer"; // 쿼리문
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery(); // 쿼리문 실행 -> 데이타베이스 결과 저장

		// Exception에서 사용하는 리소스 자동 닫기(try-with-resource)
		try (conn; pstmt; rs) {
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomer_id(rs.getInt("customer_id"));
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setPhone(rs.getString("phone"));
				customer.setGender(rs.getString("gender"));
				customer.setAge(rs.getInt("age"));
				customer.setImg(rs.getString("img"));
				customer.setPoint(rs.getInt("point"));
				customer.setGrade(rs.getString("grade"));

				customerList.add(customer);
			}
			return customerList;
		}
	}

	// 고객 내용 가져오는 메소드
	public Customer getView(int customer_id) throws Exception {
		Connection conn = open();
		Customer customer = new Customer();

		String sql = "SELECT CUSTOMER_ID, NAME, ADDRESS, PHONE, GENDER, AGE, IMG, POINT, GRADE FROM customer WHERE customer_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, customer_id); // 물음표에 들어갈 값을 반드시 먼저 지정
		ResultSet rs = pstmt.executeQuery();

		try (conn; pstmt; rs) {
			while (rs.next()) {
				customer.setCustomer_id(rs.getInt("customer_id"));
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setPhone(rs.getString("phone"));
				customer.setGender(rs.getString("gender"));
				customer.setAge(rs.getInt("age"));
				customer.setImg(rs.getString("img"));
				customer.setPoint(rs.getInt("point"));
				customer.setGrade(rs.getString("grade"));
			}
		}
		return customer;
	}

	// 고객 등록 메소드
	public void insertCustomer(Customer customer) throws Exception {
		Connection conn = open();
		String sql = "INSERT INTO customer (CUSTOMER_ID, NAME, ADDRESS, PHONE, GENDER, AGE, IMG, POINT, GRADE) VALUES (customer_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		try (conn; pstmt) {
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getAddress());
			pstmt.setString(3, customer.getPhone());
			pstmt.setString(4, customer.getGender());
			pstmt.setInt(5, customer.getAge());
			pstmt.setString(6, customer.getImg());
			pstmt.setInt(7, customer.getPoint());
			pstmt.setString(8, customer.getGrade());
			pstmt.executeUpdate();

		}
	}

	// 고객 수정 메소드
	public void updateCustomer(Customer customer) throws Exception {
		Connection conn = open();
		String sql = "UPDATE customer SET NAME=?, ADDRESS=?, PHONE=?, GENDER=?, AGE=?, IMG=?, POINT=?, GRADE=? WHERE CUSTOMER_ID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		try (conn; pstmt) {
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getAddress());
			pstmt.setString(3, customer.getPhone());
			pstmt.setString(4, customer.getGender());
			pstmt.setInt(5, customer.getAge());
			pstmt.setString(6, customer.getImg());
			pstmt.setInt(7, customer.getPoint());
			pstmt.setString(8, customer.getGrade());
			pstmt.setInt(9, customer.getCustomer_id());

			if (pstmt.executeUpdate() != 1) {
				throw new Exception("수정된 고객 정보가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace(); // 예외 상황을 콘솔에 출력
			// 사용자에게 오류 메시지를 전달할 수 있는 코드 작성
		}
	}

	// 고객 삭제 메소드
	public void deleteCustomer(int customer_id) throws Exception {
		Connection conn = open();
		String sql = "DELETE FROM customer WHERE CUSTOMER_ID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		try (conn; pstmt) {
			pstmt.setInt(1, customer_id);

			// 삭제된 고객 정보가 없을 때
			if (pstmt.executeUpdate() != 1) {
				throw new Exception("삭제된 고객 정보가 없습니다.");
			}
		}
	}
}
