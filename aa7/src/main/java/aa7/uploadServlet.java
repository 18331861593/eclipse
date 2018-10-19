package aa7;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name="upload")
public class uploadServlet  extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> parms = new HashMap<String, Object>();
		// 下面的代码开始使用Commons-UploadFile组件处理上传的文件数据
		DiskFileItemFactory factory = new DiskFileItemFactory();// 建立FileItemFactory对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 分析请求，并得到上传文件的FileItem对象
		List<FileItem> items;
		try {
			items = upload.parseRequest(req);
			for (FileItem item : items) {
				// 处理普通的表单域
				if (item.isFormField()) {
					parms.put(item.getFieldName(), item.getString("UTF-8"));
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		System.out.println(parms);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}
