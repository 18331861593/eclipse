package aa;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.three.entity.CommAttachment;
import com.three.entity.User;

import freemarker.template.Configuration;
import freemarker.template.Template;



public class AutoGenerateProject {
	
	private static Class[] entityClasses = new Class[]{CommAttachment.class};
	
	
	private static List<String> basicTypes = new ArrayList<String>();
	static{
		basicTypes.add("String");
		basicTypes.add("Integer");
		basicTypes.add("Short");
		basicTypes.add("Long");
		basicTypes.add("Date");
		basicTypes.add("Boolean");
	}
	
	public static void main(String[] args) {
		generateDao("three");
		
	}

	private static void generateDao(String projectName)  {
		try {
			String content = FileUtils.readFileToString(new File("src/main/resources/templates/Dao.ftl"));
			for (Class clazz : entityClasses) {
				String className = clazz.getSimpleName();
				String shortName = className.substring(0,1).toLowerCase() + className.substring(1);
				String newContent = content.replace("${projectName}", projectName).replace("${entityName}", className).replace("${short_entityName}", shortName);
				FileUtils.writeStringToFile(new File("src/main/java/com/"+projectName +"/dao/"+className+"Mapper.java"), newContent, "utf-8");
			}
			System.out.println("genearete success");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	
	private static void generateService(String projectName)  {
		try {
			String content = FileUtils.readFileToString(new File("src/main/resources/templates/Service.ftl"));
			for (Class clazz : entityClasses) {
				String className = clazz.getSimpleName();
				String shortName = className.substring(0,1).toLowerCase() + className.substring(1);
				String newContent = content.replace("${projectName}", projectName).replace("${entityName}", className).replace("${short_entityName}", shortName);
				FileUtils.writeStringToFile(new File("src/main/java/com/"+projectName +"/service/"+className+"Service.java"), newContent, "utf-8");
			}
			System.out.println("genearete success");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	private static void generateController(String projectName)  {
		try {
			String content = FileUtils.readFileToString(new File("src/main/resources/templates/Controller.ftl"));
			for (Class clazz : entityClasses) {
				String className = clazz.getSimpleName();
				String shortName = className.substring(0,1).toLowerCase() + className.substring(1);
				String newContent = content.replace("${projectName}", projectName).replace("${entityName}", className).replace("${short_entityName}", shortName);
				FileUtils.writeStringToFile(new File("src/main/java/com/"+projectName +"/controller/"+className+"Controller.java"), newContent, "utf-8");
			}
			System.out.println("genearete success");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	private static void generateDetail(String projectName)  {
		try {
			String content = FileUtils.readFileToString(new File("src/main/resources/templates/list.ftl"));
			for (Class clazz : entityClasses) {
				String className = clazz.getSimpleName();
				String shortName = className.substring(0,1).toLowerCase() + className.substring(1);
				String newContent = content.replace("${projectName}", projectName).replace("${entityName}", className).replace("${short_entityName}", shortName);
				
				FileUtils.writeStringToFile(new File("src/main/webapp/WEB-INF/views/"+shortName+"/"+shortName+"Detail.jsp"), newContent);
			}
			System.out.println("genearete success");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	private static void generateForm(String projectName)  {
		try {
			
			Configuration config = new Configuration();
			config.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
			config.setDefaultEncoding("utf-8");
			Template template = config.getTemplate("form.ftl");
			
			for (Class clazz : entityClasses) {
				String className = clazz.getSimpleName();
				String shortName = className.substring(0,1).toLowerCase() + className.substring(1);
				Map<String,Object> model = new HashMap<String,Object>();
				model.put("short_entityName", shortName);
				List<Entry> fields = new ArrayList<Entry>();
				Field[] fields1 = clazz.getDeclaredFields();
				for (Field f : fields1) {
					if(!f.getName().equals("id")){
						Entry entry = new Entry();
						entry.setName(f.getName());
						//如果该属性是基础的数据类型，比如String,Integer....
						if(basicTypes.contains(f.getType().getSimpleName())){
							entry.setType(f.getType().getSimpleName());
						}else{
							entry.setType("Object");
						}
						fields.add(entry);
					}
				}
				
				model.put("fields", fields);
				File dest = new File("src/main/webapp/WEB-INF/views/" + shortName + "/edit.jsp");
				Writer writer = new OutputStreamWriter(new FileOutputStream(dest));
				template.process(model, writer);
			}
			
			System.out.println("genearete success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void generateJs()  {
		try {
			Configuration config = new Configuration();
			config.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
			config.setDefaultEncoding("utf-8");
			Template template = config.getTemplate("js.ftl");
			for (Class clazz : entityClasses) {
				String className = clazz.getSimpleName();
				String shortName = className.substring(0,1).toLowerCase() + className.substring(1);
				//准备模型数据
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("short_entityName", shortName);
				model.put("entityName", className);
				
				List<Entry> fields = new ArrayList<Entry>();
				Field[] haha = clazz.getDeclaredFields();
				for (Field f : haha) {
					if(!f.getName().equals("id")){
						Entry entry = new Entry();
						entry.setName(f.getName());
						//如果该属性是基础的数据类型，比如String,Integer....
						if(basicTypes.contains(f.getType().getSimpleName())){
							entry.setType(f.getType().getSimpleName());
						}else{
							entry.setType("Object");
						}
						fields.add(entry);
					}
				}
				model.put("fields", fields);
				
				File dest = new File("src/main/webapp/jslib/"+shortName+".js");
				Writer writer = new OutputStreamWriter(new FileOutputStream(dest));
				template.process(model, writer);
			}
			
			System.out.println("generate jsp JavaScript success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void generateMapper(String projectName)  {
		try {
			Configuration config = new Configuration();
			config.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
			config.setDefaultEncoding("utf-8");
			Template template = config.getTemplate("mapper.ftl");
			for (Class clazz : entityClasses) {
				String className = clazz.getSimpleName();
				String shortName = className.substring(0,1).toLowerCase() + className.substring(1);
				//准备模型数据
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("short_entityName", shortName);
				model.put("entityName", className);
				model.put("projectName",projectName);
				List<Entry> fields = new ArrayList<Entry>();
				Field[] haha = clazz.getDeclaredFields();
				for (Field f : haha) {
					Entry entry = new Entry();
					entry.setName(f.getName());
					//如果该属性是基础的数据类型，比如String,Integer....
					if(basicTypes.contains(f.getType().getSimpleName())){
						entry.setType(f.getType().getSimpleName());
					}
					else{
						entry.setType("Object");
					}
					fields.add(entry);
				}
				model.put("paging", getPaging(className));
				model.put("insert", getInsert(shortName, className, fields));
				model.put("delete", getDelete(className));
				model.put("update", getUpdate(className, fields));
				model.put("getById", getbyId(className));
				model.put("fields", fields);
				File dest = new File("src/main/java/com/"+projectName +"/dao/"+className+"Mapper.xml");
				Writer writer = new OutputStreamWriter(new FileOutputStream(dest));
				template.process(model, writer);
			}
			
			System.out.println("generate mapper success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static String getInsert(String shortName,String className,List<Entry> fields){
		StringBuffer paging = new StringBuffer();
		paging.append("select  " +shortName+ ".*  from (select *  from " +className+ ") " +shortName+ " limit #{arg0},#{arg1}");
		StringBuffer insert = new StringBuffer();
		insert.append("insert into "+className+"(");
		for(Entry entry : fields) {
			if(!"id".equals(entry.getName())){
				insert.append(entry.getName() + ",");
			}
		}
		insert.deleteCharAt(insert.length() -1);
		insert.append(") values (");
		for(Entry entry : fields) {
			if(!"id".equals(entry.getName())){
				insert.append("#{"+ entry.getName() + "},");
			}
		}
		insert.deleteCharAt(insert.length() -1);
		insert.append(")");
		return insert.toString();
	}
	
	public static String getDelete(String className){
		 return "delete from " + className + " where id= #{0}";
	}
	
	public static String getbyId(String className){	
		return "select * from " + className + " WHERE id = #{0}";
	}
	
	public static String getPaging(String className){
		return " select * from "+className+" limit #{0},#{1}";
	}
	
	public static String getUpdate(String className,List<Entry> fields){
		StringBuffer update = new StringBuffer();
		update.append("UPDATE " + className+" set ");
		for(Entry entry : fields) {
			if(!"id".equals(entry.getName())){
				update.append(entry.getName() + " = #{" + entry.getName()+"},");
			}
		}
		update.deleteCharAt(update.length() -1);
		update.append(" WHERE id = #{id}");
		return update.toString();
	}
	
}

