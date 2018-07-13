package com.alonelaval.acegi.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.alonelaval.acegi.user.User;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;


/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.view.PdfView.java
 * @createDate 2009-6-17 下午02:00:07	
 */
public class PdfView extends AbstractPdfView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		 String fileName="huawei.pdf";
		 response.setHeader("Content-disposition", "attachment; filename=student.pdf");// 设定输出文件头   

		  //  response.addHeader("'Content-Disposition", "attachment; filename=" + fileName);
			List<User> usersList = (List<User>)model.get("users");
			
			for (User user : usersList) {
				document.add(new Paragraph(user.getUsername()));
				document.add(new Paragraph(user.getPassword()));
				//document.add(new Paragraph((String)user.isEnabled()));
			}
	}


}
