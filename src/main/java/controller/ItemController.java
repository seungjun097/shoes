package controller;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import domain.item.Item;
import service.ItemService;

@WebServlet("/item")
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ItemController() {
        super();
    }
 
    protected void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String cmd = req.getParameter("cmd");
    	ItemService itemService = new ItemService();
    	//여자신발 리스트
    	if(cmd.equals("womanlist")) {
    		int page = Integer.parseInt(req.getParameter("page"));
    		List<Item> items = itemService.womanList(page);
    		req.setAttribute("items", items);
    		req.setAttribute("page", page);
    		int total = itemService.pagenum();
    		int lastpage = (total-1)/5;
    		req.setAttribute("lastpage", lastpage);
    		req.getRequestDispatcher("/item/womanlist.jsp")
    		.forward(req, res);
    	}else if(cmd.equals("manlist")) {
    		int page = Integer.parseInt(req.getParameter("page"));
    		List<Item> items = itemService.manList(page);
    		req.setAttribute("items", items);
    		req.setAttribute("page", page);
    		int total = itemService.pagenum();
    		int lastpage = (total-1)/5;
    		req.setAttribute("lastpage", lastpage);
    		req.getRequestDispatcher("/item/manlist.jsp")
    		.forward(req, res);
    	}else if(cmd.equals("itemdetail")) {
    		int id = Integer.parseInt(req.getParameter("id"));
    		Item item = itemService.itemDetail(id);
    		req.setAttribute("item", item);
    		req.getRequestDispatcher("/item/itemdetail.jsp").forward(req, res);
    	}else if (cmd.equals("saveitem")) {
    		
    		String saveDirectory = req.getSession().getServletContext().getRealPath("/file/images");
    		int maxPostSize = 1024*1000;
    		MultipartRequest mr = 
    				new MultipartRequest(req, saveDirectory, maxPostSize, "utf-8");
    		
    		String item_detail_img = mr.getFilesystemName("item_detail_img");
    		String item_cart_img = mr.getFilesystemName("item_cart_img");
    		String item_list_img = mr.getFilesystemName("item_list_img");
    		
    		String detail_exe = item_detail_img.substring(item_detail_img.lastIndexOf("."));
    		String cart_exe = item_cart_img.substring(item_cart_img.lastIndexOf("."));
    		String list_exe = item_list_img.substring(item_list_img.lastIndexOf("."));
    		String now = new SimpleDateFormat("yyyyMMdd_Hmss").format(new Date());
    		
    		String new_item_detail_img = "detail"+now+detail_exe;
    		String new_item_cart_img = "cart"+now+detail_exe;
    		String new_item_list_img = "list"+now+detail_exe;
    		
    		File oldFile1 = new File(saveDirectory+File.separator+item_detail_img);
    		File newFile1 = new File(saveDirectory+File.separator+new_item_detail_img);
    		oldFile1.renameTo(newFile1);
    		
    		File oldFile2 = new File(saveDirectory+File.separator+item_cart_img);
    		File newFile2 = new File(saveDirectory+File.separator+new_item_cart_img);
    		oldFile2.renameTo(newFile2);
    		
    		File oldFile3 = new File(saveDirectory+File.separator+item_list_img);
    		File newFile3 = new File(saveDirectory+File.separator+new_item_list_img);
    		oldFile3.renameTo(newFile3);
    		
    		Item item = new Item();
    		
    		item.setItem_gender(mr.getParameter("item_gender"));
			item.setItem_cate(mr.getParameter("item_cate"));
			item.setItem_color(mr.getParameter("item_color"));
			item.setItem_content(mr.getParameter("item_content"));
			item.setItem_detail_img(new_item_detail_img);
			item.setItem_cart_img(new_item_cart_img);
			item.setItem_list_img(new_item_list_img);
			item.setItem_name(mr.getParameter("item_name"));
			item.setItem_price(Integer.parseInt(mr.getParameter("item_price")));
			item.setItem_size(Integer.parseInt(mr.getParameter("item_size")));
			item.setItem_stock(Integer.parseInt(mr.getParameter("item_stock")));
    		
			int result = itemService.itemSave(item);
			
			if(result == 1) {
				System.out.println("저장완료");
				res.sendRedirect("/shoes/item/itemsave.jsp");
			}
    	}
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
}

