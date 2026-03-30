package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBBooks {
	// Connection: Java와 DB를 연결해 놓은 인터페이스
	// (Prepared)Statement와 ResultSet을 사용하려면 필수적으로 존재해야 함
	private Connection conn = null;
	// PreparedStatement: Statement에 기능을 더 추가한 것
	private PreparedStatement pstmt = null;
	// ResultSet: select문의 결과를 저장
	private ResultSet rs = null;
	Scanner sc = new Scanner(System.in);

	// Java와 DB를 연결하기 위해 필요한 데이터
	private static DBBooks dbb;
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String user = "book45";
	private final String pwd = "1234";

	// 생성자를 private으로 제한 -> 메서드(newInstance())를 통해서만 접근 가능
	private DBBooks() {
		try {
			// 데이터베이스의 드라이버를 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// SQL과 Java 연결
	// Connection 타입으로 선언: conn에 값을 넘겨주기 위해
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 연결 해제: 사용 후 연결 해제를 해주지 않으면 불필요한 자원이 낭비되며 새로운 Connection, (Prepared)Statement 생성 불가
	// 각 메서드마다 마지막에 반드시 사용
	public void disConnection() {
		try {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 객체를 만들기 위해 사용할 메서드: 생성자를 private으로 접근 제한했기 때문에
	public static DBBooks newInstance() {
		if (dbb == null)
			dbb = new DBBooks();
		return dbb;
	}

	// 전체 데이터 삽입
	public void book45Insert(Yes24VO vo) {
		try {
			// DB에 연결
			conn = getConnection();

			// SQL문 작성
			String sql = "insert into book (num, category, title, price, summary, author, pub, ratingAvg, pictureurl, isbn) values (book45_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			// 오라클로 SQL 문장 전송
			pstmt = conn.prepareStatement(sql);

			// ?에 값 저장
			pstmt.setString(1, vo.getCategory());
			pstmt.setString(2, vo.getTitle());
			pstmt.setInt(3, vo.getPrice());
			pstmt.setString(4, vo.getSummary());
			pstmt.setString(5, vo.getAuthor());
			pstmt.setString(6, vo.getPub());
			pstmt.setString(7, vo.getGrade());
			pstmt.setString(8, vo.getPictureUrl());
			pstmt.setLong(9, vo.getIsbn());

			// 전송된 값 커밋 or 업데이트
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

	// 메뉴 화면 조회
	private int menuBook() {
		System.out.println(">> 원하는 작업을 선택해 주세요. <<");
		System.out.println("1. 전체 자료 조회 2. 자료 추가 3. 자료 삭제 4. 자료 전체 수정 5. 자료 일부 수정 6. 자료 검색 0. 이전으로 돌아가기");
		System.out.print(">> ");

		int input = sc.nextInt();
		sc.nextLine();

		return input;
	}

	// 첫 화면
	public void menuBookStart() {
		// menuBook()에서 입력한 숫자를 switch문에 넘겨준다.
		while (true) {
			int choice = menuBook();

			switch (choice) {
			// 0. 프로그램 종료
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			// 1. 전체 자료 출력
			case 1:
				displayBook();
				break;
			// 2. 자료 추가
			case 2:
				addBook();
				break;
			// 3. 자료 삭제
			case 3:
				deleteBook();
				break;
			// 4. 자료 전체 수정
			case 4:
				updateBook();
				break;
			// 5. 자료 일부 수정
			case 5:
				updateSelectBook();
				break;
			// 6.자료 검색
			case 6:
				searchBook();
				break;
			// 입력 오류 시 출력값
			default:
				System.out.println("번호를 잘못 입력하셨습니다. 다시 입력해 주세요.");
				break;
			}
		}
	}

	// 데이터 조회(출력)
	private void displayBook() {
		try {
			conn = getConnection();

			String sql = "select * from book45";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt("rank") + "위");
				System.out.println("카테고리: " + rs.getString("category"));
				System.out.println("제목: " + rs.getString("title"));
				System.out.println("가격: " + rs.getString("price"));
				System.out.println("줄거리: " + rs.getString("summary"));
				System.out.println("저자: " + rs.getString("author"));
				System.out.println("출판사: " + rs.getString("pub"));
				System.out.println("평점: " + rs.getString("grade"));
				System.out.println("===========================================================================================================================================================================");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

	// 데이터 추가
	private void addBook() {
		int count = 0;
		do {
			System.out.println("책의 제목을 입력해 주세요.");
			System.out.print(">> ");
			String input = sc.nextLine();
			
			// 입력한 값과 일치하는 책 제목이 있을 시 나타나는 문구 + 재귀 호출로 추가 메서드 재호출
			count = countBook(input);
			if (count > 0) {
				System.out.println("'" + input + "'은/는 이미 등록되어 있는 책입니다. 다시 입력해 주세요.");
				this.addBook();
			}
		} while (count != 0);

		System.out.println("추가할 책의 카테고리를 입력해주세요.");
		System.out.print(">> ");
		String category = sc.nextLine();
		System.out.println("추가할 책의 제목을 입력해주세요.");
		System.out.print(">> ");
		String title = sc.nextLine();
		System.out.println("추가할 책의 가격을 입력해주세요.");
		System.out.print(">> ");
		String price = sc.nextLine();
		System.out.println("추가할 책의 줄거리를 입력해주세요.");
		System.out.print(">> ");
		String summary = sc.nextLine();
		System.out.println("추가할 책의 저자를 입력해주세요.");
		System.out.print(">> ");
		String author = sc.nextLine();
		System.out.println("추가할 책의 출판사를 입력해주세요.");
		System.out.print(">> ");
		String pub = sc.nextLine();
		System.out.println("추가할 책의 평점을 입력해주세요.");
		System.out.print(">> ");
		String grade = sc.nextLine();

		try {
			conn = getConnection();

			String sql = "insert into book values (book45_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, title);
			pstmt.setString(3, price);
			pstmt.setString(4, summary);
			pstmt.setString(5, author);
			pstmt.setString(6, pub);
			pstmt.setString(7, grade);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("데이터 추가에 성공했습니다.");
				return;
			} else {
				System.out.println("데이터 추가에 실패했습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

	// 데이터 삭제
	private void deleteBook() {
		System.out.println("삭제할 책 제목을 입력하세요.");
		System.out.print(">> ");
		String input = sc.nextLine();
		
		// 입력한 값과 일치하는 책 제목이 없을 시 나타나는 문구 + 재귀 호출로 삭제 메서드 재호출
		int count = countBook(input);
		if (count == 0) {
			System.out.println("입력하신 책의 정보가 존재하지 않습니다. 다시 입력해 주세요.");
			this.deleteBook();
		}

		try {
			conn = getConnection();

			String sql = "delete from book where title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("삭제에 성공했습니다.");
				return;
			} else {
				System.out.println("삭제에 실패했습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

	// 데이터 전체 수정
	private void updateBook() {
		System.out.println("수정할 책의 제목을 입력해주세요.");
		System.out.print(">> ");
		String input = sc.nextLine();

		// 입력한 값과 일치하는 책 제목이 없을 시 나타나는 문구 + 재귀 호출로 수정 메서드 재호출
		int count = countBook(input);
		if (count == 0) {
			System.out.println("입력하신 책의 정보가 존재하지 않습니다. 다시 입력해 주세요.");
			this.updateBook();
		}

		System.out.println("수정할 카테고리를 입력해 주세요.");
		System.out.print(">> ");
		String category = sc.nextLine();
		System.out.println("수정할 책 제목을 입력해 주세요.");
		System.out.print(">> ");
		String title = sc.nextLine();
		System.out.println("수정할 가격을 입력해 주세요.");
		System.out.print(">> ");
		String price = sc.nextLine();
		System.out.println("수정할 줄거리를 입력해 주세요.");
		System.out.print(">> ");
		String summary = sc.nextLine();
		System.out.println("수정할 저자를 입력해 주세요.");
		System.out.print(">> ");
		String author = sc.nextLine();
		System.out.println("수정할 출판사를 입력해 주세요.");
		System.out.print(">> ");
		String pub = sc.nextLine();
		System.out.println("수정할 평점을 입력해 주세요.");
		System.out.print(">> ");
		String grade = sc.nextLine();

		try {
			conn = getConnection();
			String sql = "update book set category = ?, title = ?, price = ?, summary = ?, author = ?, pub = ?, grade = ? where title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, title);
			pstmt.setString(3, price);
			pstmt.setString(4, summary);
			pstmt.setString(5, author);
			pstmt.setString(6, pub);
			pstmt.setString(7, grade);
			pstmt.setString(8, input);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("정보 수정에 성공했습니다.");
				return;
			} else {
				System.out.println("정보 수정에 실패했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

	// 데이터 일부 수정
	private void updateSelectBook() {
		System.out.println("수정할 책의 제목을 입력해주세요.");
		System.out.print(">> ");
		String input = sc.nextLine();

		int count = countBook(input);
		if (count == 0) {
			System.out.println("입력하신 책의 정보가 존재하지 않습니다. 다시 입력해 주세요.");
			this.updateSelectBook();
		}

		int num;
		String updateField = null;
		String updateTitle = null;

		do {
			System.out.println("수정할 항목을 선택하세요.");
			System.out.println("1. 카테고리 2. 제목 3. 가격 4. 줄거리 5. 저자 6. 출판사 7. 평점");
			System.out.print(">> ");
			num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				updateField = "category";
				updateTitle = "카테고리";
				break;
			case 2:
				updateField = "title";
				updateTitle = "제목";
				break;
			case 3:
				updateField = "price";
				updateTitle = "가격";
				break;
			case 4:
				updateField = "summary";
				updateTitle = "줄거리";
				break;
			case 5:
				updateField = "author";
				updateTitle = "저자";
				break;
			case 6:
				updateField = "pub";
				updateTitle = "출판사";
				break;
			case 7:
				updateField = "grade";
				updateTitle = "평점";
				break;
			default:
				System.out.println("수정할 항목을 잘못 선택하셨습니다. 다시 선택해 주세요.");
				this.updateSelectBook();
			}
		} while (num < 1 || num > 7);

		System.out.print("수정할 " + updateTitle + ": ");
		String updateData = sc.nextLine();

		try {
			conn = getConnection();
			String sql = "update book set " + updateField + " = ? where title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateData);
			pstmt.setString(2, input);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("정보 수정에 성공했습니다.");
				return;
			} else {
				System.out.println("정보 수정에 실패했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

	// 입력값과 일치하는 책 제목이 있는지 확인하는 메서드
	private int countBook(String input) {
		int count = 0;
		try {
			conn = getConnection();
			String sql = "select count(*) from book where title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (Exception e) {
			count = 0;
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return count;
	}

	// 데이터 검색
	private void searchBook() {
		String searchField = null;
		int num1;
		int num2;
		
		// 책을 검색할 방법을 2가지로 구분
		do {
			System.out.println("책을 검색할 방법을 선택해 주세요.");
			System.out.println("1.카테고리 검색 2. 키워드 검색");
			System.out.print(">> ");
			num1 = sc.nextInt();
			sc.nextLine();
			
			// 세부 카테고리 선택 시 그에 해당하는 도서 출력
			switch (num1) {
			case 1:
				System.out.println("세부 카테고리를 선택해 주세요.");
				System.out.println("1. IT 모바일 2. 가정 살림 3. 경제 경영 4. 국어 외국어 사전 5. 만화/라이트노벨 6. 사회 정치 7. 소설/시/희곡 8. 수험서 자격증");
				System.out.println("9. 어린이 10. 에세이 11. 예술 12. 유아 13. 인문 14. 자기계발 15. 자연과학 16. 종교 17. 청소년");
				System.out.print(">> ");
				num2 = sc.nextInt();
				
				switch (num2) {
				case 1:
					searchField = "IT 모바일";
					break;
				case 2:
					searchField = "가정 살림";
					break;
				case 3:
					searchField = "경제 경영";
					break;
				case 4:
					searchField = "국어 외국어 사전";
					break;
				case 5:
					searchField = "만화/라이트노벨";
					break;
				case 6:
					searchField = "사회 정치";
					break;
				case 7:
					searchField = "소설/시/희곡";
					break;
				case 8:
					searchField = "수험서 자격증";
					break;
				case 9:
					searchField = "어린이";
					break;
				case 10:
					searchField = "에세이";
					break;
				case 11:
					searchField = "예술";
					break;
				case 12:
					searchField = "유아";
					break;
				case 13:
					searchField = "인문";
					break;
				case 14:
					searchField = "자기계발";
					break;
				case 15:
					searchField = "자연과학";
					break;
				case 16:
					searchField = "종교";
					break;
				case 17:
					searchField = "청소년";
					break;
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
					this.searchBook();
			}
				
			try {
			conn = getConnection();
			String category = "select * from book where category = '" + searchField + "'";
			pstmt = conn.prepareStatement(category);
			rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println(rs.getInt("rank") + "위");
					System.out.println("카테고리: " + rs.getString("category"));
					System.out.println("제목: " + rs.getString("title"));
					System.out.println("가격: " + rs.getString("price"));
					System.out.println("줄거리: " + rs.getString("summary"));
					System.out.println("저자: " + rs.getString("author"));
					System.out.println("출판사: " + rs.getString("pub"));
					System.out.println("평점: " + rs.getString("grade"));
					System.out.println("===========================================================================================================================================================================");
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disConnection();
			}
		
			break;
			case 2:
				try {
					System.out.println("검색할 책의 키워드를 입력해 주세요.");
					System.out.print(">> ");
					String input = sc.nextLine();
					
					conn = getConnection();
					String sql = "select * from book where category like '%" + input + "%' or title like '%" + input + "%' or summary like '%"
								 + input + "%' or author like '%" + input + "%' or pub like '%" + input + "%'";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						System.out.println(rs.getInt("rank") + "위");
						System.out.println("카테고리: " + rs.getString("category"));
						System.out.println("제목: " + rs.getString("title"));
						System.out.println("가격: " + rs.getString("price"));
						System.out.println("줄거리: " + rs.getString("summary"));
						System.out.println("저자: " + rs.getString("author"));
						System.out.println("출판사: " + rs.getString("pub"));
						System.out.println("평점: " + rs.getString("grade"));
						System.out.println("===========================================================================================================================================================================");
					}
					
					// 검색한 자료의 개수를 세기 위해 count(*)가 포함된 sql문 재정의
					sql = "select count(*) from book where category like '%" + input + "%' or title like '%" + input + "%' or summary like '%"
						  + input + "%' or author like '%" + input + "%' or pub like '%" + input + "%'";
					pstmt = conn.prepareStatement(sql);
					// 검색 결과값(개수)을 rs에 저장
					rs = pstmt.executeQuery();
					int cnt = 0;

					// rs에 저장된 데이터를 불러오기 위해 rs.next() 호출
					if (rs.next()) {
						cnt = rs.getInt("count(*)");
					}

					if (cnt > 0) {
						System.out.println("검색 결과: " + cnt + "건");
						return;
					} else {
						System.out.println("검색 결과가 없습니다.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					disConnection();
				}
			
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
				this.searchBook();
			}
		} while (num1 < 1 || num1 > 2);
	}
}

/* 보완할 점
 * 원하는 항목을 잘못 선택했을 때 각 메서드마다 이전으로 돌아가는 기능을 구현했으면 더 좋았을 듯
 * 전체 수정 시 입력 값을 null로 줬을 경우 기존의 데이터가 그대로 출력되게 하는 기능...
 * 데이터 출력 기능과 DB 저장 기능을 분리해서 작성하면 더 깔끔하겠다....
 * 각 기능을 하는 메서드들을 클래스를 생성해서 분리하기
 * 카테고리 검색은 조회로 빼는 게 나을 것 같다.
 * 책 제목보다는 고유 번호로 기준을 잡는 게 좋을 것 같다. (책 같은 경우에는 ISBN번호로)
 */

