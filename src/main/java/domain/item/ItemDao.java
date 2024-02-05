package domain.item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConnect;

public class ItemDao extends DBConnect {
	
	public int insertItem(Item item) {
		int result = 0;
		String query = "insert into item(item_key,item_gender,item_cate,item_color,"
				+ "item_content,item_detail_img,item_cart_img,item_list_img,item_name,"
				+ "item_price,item_size,item_stock)"
				+ " values(item_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, item.getItem_gender());
			psmt.setString(2, item.getItem_cate());
			psmt.setString(3, item.getItem_color());
			psmt.setString(4, item.getItem_content());
			psmt.setString(5, item.getItem_detail_img());
			psmt.setString(6, item.getItem_cart_img());
			psmt.setString(7, item.getItem_list_img());
			psmt.setString(8, item.getItem_name());
			psmt.setInt(9, item.getItem_price());
			psmt.setInt(10, item.getItem_size());
			psmt.setInt(11, item.getItem_stock());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	public List<Item> findWomanList(int page){
		System.out.println("find우먼리스트작동");
		List<Item> items =  new ArrayList<>();
		String query = "select * from (select Td.*,rownum as rnum from (select * from item where item_gender = '여' order by item_key) td)"
				+ " where rnum between ? and ?";
		int start = 5*page+1;
		try {
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, start);
			psmt.setInt(2, start+6);
			rs = psmt.executeQuery();
			System.out.println("rs.next ="+rs.next());
			while(rs.next()) {
				Item item = new Item();
				item = Item.builder()
						.item_key(rs.getInt("item_key"))
						.item_name(rs.getString("item_name"))
						.item_color(rs.getString("item_color"))
						.item_content(rs.getString("item_content"))
						.item_cate(rs.getString("item_cate"))
						.item_gender(rs.getString("item_gender"))
						.item_date(rs.getDate("item_date"))
						.item_price(rs.getInt("item_price"))
						.item_size(rs.getInt("item_size"))
						.item_stock(rs.getInt("item_stock"))
						.item_list_img(rs.getString("item_list_img"))
						.item_cart_img(rs.getString("item_cart_img"))
						.item_detail_img(rs.getString("item_detail_img"))
						.build(); //객체 리턴
				//리스트에 객체 추가
				System.out.println(item.getItem_key());
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(psmt!=null && conn!=null && rs!=null) {
				close();
			}
		}
		return items;
	}
	//남자 아이템 목록
	public List<Item> findManList(int page){
			List<Item> items =  new ArrayList<>();
			String query = "select * from (select Td.*,rownum as rnum from (select * from item where item_gender = '남' order by item_key) td)"
					+ " where rnum between ? and ?";
			int start = 5*page+1;
			try {
				psmt = conn.prepareStatement(query);
				psmt.setInt(1, start);
				psmt.setInt(2, start+6);
				rs = psmt.executeQuery();
				System.out.println("rs.next ="+rs.next());
				while(rs.next()) {
					Item item = new Item();
					item = Item.builder()
							.item_key(rs.getInt("item_key"))
							.item_name(rs.getString("item_name"))
							.item_color(rs.getString("item_color"))
							.item_content(rs.getString("item_content"))
							.item_cate(rs.getString("item_cate"))
							.item_gender(rs.getString("item_gender"))
							.item_date(rs.getDate("item_date"))
							.item_price(rs.getInt("item_price"))
							.item_size(rs.getInt("item_size"))
							.item_stock(rs.getInt("item_stock"))
							.item_list_img(rs.getString("item_list_img"))
							.item_cart_img(rs.getString("item_cart_img"))
							.item_detail_img(rs.getString("item_detail_img"))
							.build(); //객체 리턴
					//리스트에 객체 추가
					System.out.println(item.getItem_key());
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
			return items;
		}
	//상세보기
	public Item findById(int id) {
		Item item = null;
		String query = "select * from item where item_key=?";
		try {
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				item = Item.builder()
						.item_key(rs.getInt("item_key"))
						.item_name(rs.getString("item_name"))
						.item_color(rs.getString("item_color"))
						.item_content(rs.getString("item_content"))
						.item_cate(rs.getString("item_cate"))
						.item_gender(rs.getString("item_gender"))
						.item_date(rs.getDate("item_date"))
						.item_price(rs.getInt("item_price"))
						.item_size(rs.getInt("item_size"))
						.item_stock(rs.getInt("item_stock"))
						.item_list_img(rs.getString("item_list_img"))
						.item_cart_img(rs.getString("item_cart_img"))
						.item_detail_img(rs.getString("item_detail_img"))
						.build(); //객체 리턴			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return item;
	}
	public int count() {
		int result = 0; 
		String query = "select count(*) from item";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}
