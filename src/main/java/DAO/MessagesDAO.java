package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MessagesDTO;

public class MessagesDAO {

	private static MessagesDAO instance;
	
	synchronized public  static MessagesDAO getInstance() throws Exception{ 
		
			if(instance==null) {
				instance = new MessagesDAO();
			}
			return instance;
		
	}
	
	
	private MessagesDAO() {} //생성자
	  //싱글톤 적용위해서 private
	
	   private Connection getConnection() throws Exception {
		      Context ctx = new InitialContext(); // tomcat 환경 찾아서 요구하는 코드, //우클릭 했을때 메뉴 상황에 따라 다르게 나오는걸 컨텍스트메뉴
		      DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle"); // java:comp/env/ 고정값,  jdbc 찾아달라고 요청
		      
		      return ds.getConnection();
		   }
	   public int  insert(MessagesDTO dto) throws Exception{ //추가
			String sql = 
					"insert into messages values(messages_seq.nextval,?,?)";  
			
			
			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){  //try with resource 적용
				pstat.setString(1, dto.getWriter());
				pstat.setString(2, dto.getMessage());
				int result = pstat.executeUpdate();

				con.commit();
				con.close();
				return result;
			}		
		}
		public List<MessagesDTO> selectAll() throws Exception {
			String sql ="select * from messages order by 1";
			
			try(Connection con= this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);
					ResultSet rs = pstat.executeQuery();){
				List<MessagesDTO> result = new ArrayList<MessagesDTO>();  //rs을 닫히면 보낼수 없기에 리스트사용
				
				while(rs.next()){
					int id = rs.getInt("id");
					String writer = rs.getString("writer");
					String message = rs.getString("message");
				
					MessagesDTO dto = new MessagesDTO(id, writer, message);
					
					result.add(dto);
					
				}
				return result;
			}
		}
}
