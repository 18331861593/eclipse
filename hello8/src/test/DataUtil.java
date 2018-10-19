package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.Abdlf;

public class DataUtil {
	 private static String driver = "org.postgresql.Driver";//驱动
	 private static String url ="jdbc:postgresql://123.57.215.107:5432/ABDLIFE"; //JDBC连接URL  
	 private static String user = "abd"; //用户名  
	 private static String password = "Abd54321"; //密码  

	public static String doEncry(String data, String secretkey){
		String result = "";
		try {
			DesUtils des = new DesUtils(secretkey);//自定义密钥
			if(data!=null&&!"".equals(data)&&secretkey!=null&&!"".equals(secretkey)){
				result = des.encrypt(data);
			}
		} catch (Exception e) {
			System.out.println("加密数据出错"+e.getMessage());
			return "";
		}
		return result;
	}
	
	public static String deEncry(String data, String secretkey){
		String result = "";
		try {
			DesUtils des = new DesUtils(secretkey);//自定义密钥
			if(data!=null&&!"".equals(data)&&secretkey!=null&&!"".equals(secretkey)){
				result = des.decrypt(data);
			}
		} catch (Exception e) {
			System.out.println("解密数据出错"+e.getMessage());
			return "";
		}
		return result;
	}
	
	public static String getRandomStr(){
		String[] arr_ = {"G","T","q","j","7","D","t","f","n","9","M","c","m","B","g","6","W","O","v","U","p","1","3","h","u","V","e","L","R","A","l","2","0","F","Z","C","H","r","P","8","s","d","Y","z","x","Q","w","k","J","N","a","X","b","o","E","4","K","I","S","y","i","5"};
		String result = "";
		String key = "";
		String value = "";
		for (int i = 0; i < 10; i++) {
			int k = (int) Math.floor(arr_.length * Math.random());
			if("".equals(value)){
				value+=k;
			}else{
				value+="_"+k;
			}
			key += arr_[k];
		}
		result = key+"-"+value;
		return result;
	}
	
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Abdlf> list = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			String sql="select * from \"ABD_USER\"";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<Abdlf>();
			while (rs.next()) {
				Abdlf ad=new Abdlf();
				ad.setAddress_id(rs.getInt("user_id"));
				ad.setName(doEncry(rs.getString("user_name"),"abd20170406"));
				ad.setPhone(doEncry(rs.getString("user_phone"),"abd20170406"));
				ad.setTel(doEncry(rs.getString("user_tel"),"abd20170406"));
				ad.setComment(doEncry(rs.getString("user_comment"),"abd20170406"));
				//ad.setDepart(doEncry(rs.getString("unit_name"),"abd20170406"));
				list.add(ad);
			}
			StringBuffer str=new StringBuffer("");
			for(int a=0;a<list.size();a++){
				
				//if(list.get(a).getAddress_id()>=87){
					str.append("update \"ABD_USER\" set user_name='"+list.get(a).getName()+"',");
					str.append(" user_phone='"+list.get(a).getPhone()+"',");
					str.append(" user_tel='"+list.get(a).getTel()+"',");
					str.append(" user_comment='"+list.get(a).getComment()+"'");
					//str.append(" address_department='"+list.get(a).getDepart()+"'");
					str.append(" where user_id="+list.get(a).getAddress_id()+";");
				//}
			}
			System.out.println(str.toString());
			pstmt=con.prepareStatement(str.toString());
			pstmt.executeUpdate();
			pstmt.close();
			rs.close();
			con.close();
			System.out.println(list.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
