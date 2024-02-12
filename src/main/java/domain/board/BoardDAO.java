package domain.board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConnect;

public class BoardDAO extends DBConnect{
	
	public List<BoardDTO> boardlist() {
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		String query = "select * from board b join member m on b.member_key = m.member_key";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_key(rs.getString("board_key"));
				dto.setMember_key(rs.getNString("member_key"));
				dto.setBoard_content(rs.getNString("board_content"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_date(rs.getDate("board_date"));
				dto.setBoard_visitcount(rs.getString("board_visitcount"));
				dto.setMember_name(rs.getString("member_name"));
				boardlist.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return boardlist;
	}
	//제목,내용 등을 모두 인파라미터로 설정
	//BoardDTO타입의 매개변수를 받아 데이터 insert 성공한 행의 개수를 정수(int)로 반환
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		//INSERT 쿼리문 작성
		String query = "INSERT INTO board("
					 + " board_key, board_title, board_content, member_key, board_visitcount) "
					 + " VALUES (board_seq.NEXTVAL, ?, ?, ?, 0)";
		try {
			psmt = conn.prepareStatement(query);	//인파라미터이므로 동적쿼리 prepareStatemen객체생성
			psmt.setString(1, dto.getBoard_title());
			psmt.setString(2, dto.getBoard_content());
			psmt.setString(3, dto.getMember_key());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return result;		//위값을 JSP로 반환
		
	}
	//지정한 게시물을 찾아 내용을 반환합니다.
	public BoardDTO selectView(String board_key) {
		BoardDTO dto = new BoardDTO();
		//쿼리문 준비
		String query = "SELECT b.board_key, b.board_title, b.board_content,"
				+" b.board_date, b.member_key, b.board_visitcount, m.member_name"
				+" FROM member M JOIN board B ON m.member_key = b.member_key"
				+" where b.board_key ="+board_key;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			//결과처리
			if (rs.next()) {
				dto.setBoard_key(rs.getString(1));
				dto.setBoard_title(rs.getNString(2));
				dto.setBoard_content(rs.getString(3));
				dto.setBoard_date(rs.getDate(4));
				dto.setMember_key(rs.getString(5));
				dto.setBoard_visitcount(rs.getString(6));
				dto.setMember_name(rs.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;		 
	}
	//지정한 게시물의 조회수를 1증가시킨다
	public void updateVisitCount(String board_key) {
		String query = "UPDATE board SET "
					 + " board_visitcount=board_visitcount+1"
					 + " WHERE board_key=?";
		
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, board_key);
			psmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	//수정하기
	public int updateEdit(BoardDTO dto) {
		int result = 0;
		
		String query = "UPDATE board SET"
					 + " board_title=?, board_content=?"
					 + " where board_key=?";
		
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, dto.getBoard_title());
			psmt.setString(2, dto.getBoard_content());
			psmt.setString(3, dto.getBoard_key());
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}
	
	public int deletePost(String board_key) {
		int result = 0;
		
		String query = "DELETE FROM board WHERE board_key=?";
		
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, board_key);
			
			//쿼리문 실행
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}
