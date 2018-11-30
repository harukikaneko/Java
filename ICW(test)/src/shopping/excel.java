package shopping;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import account.AccountBean;
import account.AccountDao;
import login.LoginUserBean;

@WebServlet("/Excel")
public class excel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインしているか検証
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String user_id = ((LoginUserBean) request.getSession().getAttribute("login_user_bean")).getId();
		String user_name = ((LoginUserBean) request.getSession().getAttribute("login_user_bean")).getName();
		AccountBean bean = AccountDao.getAccountBean1(user_id);
		String account_id = bean.getAccount_id();
		String date = request.getParameter("date");
		String name = request.getParameter("name");
		String code = request.getParameter("code");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");
		String market = request.getParameter("market");
		String status = request.getParameter("status");
		System.out.println(status);
		System.out.println(market);
		
		// 注文DTOをExcelWriterに渡し、エクセル書き込み実行

		

		request.setAttribute("user_id", user_id);

		// 画面遷移
		//request.getRequestDispatcher("HistoryInfoServlet?user_id=" + name).forward(request, response);

		String outputExcel = "";
		String outputPdf = "";

		// 既存エクセルファイルを取得
		final String inputDir = "/home/flairlink/git/ICW/ICW(test)/WebContent/excel/";

		// 変更するエクセルファイルを指定
		FileInputStream editEx = new FileInputStream(inputDir + "取引報告書.xlsx");
		Workbook wb = null;


		// 出力ファイル名

		try {
			// 既存エクセルの編集のため、WorkbookFactoryを使用
			wb = WorkbookFactory.create(editEx);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 取得するシートを指定
		Sheet sheet = wb.getSheet("Sheet1");

		/*
		 * セルに値を入力
		 */
		// 5行目取得
		Row row = sheet.getRow(7);
		// 2列目取得
		Cell cell = row.getCell(2);
		// 取得した箇所に値をセット
		cell.setCellValue(account_id);

		// 6行目取得
		row = sheet.getRow(5);
		// 2列目取得
		cell = row.getCell(1);
		// 取得した箇所に値をセット
		cell.setCellValue(user_name + " 様");

		// 6行目取得
		row = sheet.getRow(1);
		// 2列目取得
		cell = row.getCell(9);
		// 取得した箇所に値をセット
		cell.setCellValue(date);

		// 9行目取得
		row = sheet.getRow(8);
		// 2列目取得
		cell = row.getCell(2);
		// 取得した箇所に値をセット
		cell.setCellValue(date);

		// 12行目取得
		row = sheet.getRow(11);
		// 3列目取得
		cell = row.getCell(2);
		// 取得した箇所に値をセット
		cell.setCellValue(code);

		// 12行目取得
		row = sheet.getRow(11);
		// 4列目取得
		cell = row.getCell(3);
		// 取得した箇所に値をセット
		cell.setCellValue(name);

		 //12行目取得
		 row = sheet.getRow(11);
		 //5列目取得
		 cell = row.getCell(4);
		 //取得した箇所に値をセット
		 cell.setCellValue(market);

		// 12行目取得
		row = sheet.getRow(11);
		// 6列目取得
		cell = row.getCell(5);
		// 取得した箇所に値をセット
		cell.setCellValue(quantity);
		//
		
		//12行目取得
		row = sheet.getRow(11);
		//7列目取得
		cell = row.getCell(1);
		//取得した箇所に値をセット
		cell.setCellValue(status);

		// 12行目取得
		row = sheet.getRow(11);
		// 8列目取得
		cell = row.getCell(7);
		// 取得した箇所に値をセット
		int totalOrderPrice = Integer.valueOf(price);
		cell.setCellValue(totalOrderPrice);

		// //12行目取得
		// row = sheet.getRow(11);
		// //9列目取得
		// cell = row.getCell(8);
		// //取得した箇所に値をセット
		// Double fee = totalPriceCalc * 0.08;
		// //String feeValue = String.valueOf(fee);
		// cell.setCellValue(yenFormat.format(fee));

		// //12行目取得
		// row = sheet.getRow(11);
		// //10列目取得
		// cell = row.getCell(9);
		// //取得した箇所に値をセット
		// //String totalPrice = String.valueOf(fee + order.getOrderPrice());
		// cell.setCellValue(yenFormat.format(fee + totalPriceCalc));

		FileOutputStream compEx = null;

		try {
			// 変更するエクセルファイルを指定
			compEx = new FileOutputStream(inputDir + date + "取引報告書.xlsx");

			outputExcel = inputDir + date + "取引報告書.xlsx";
			// 書き込み実行
			wb.write(compEx);
			System.out.println("エクセル完了");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			compEx.close();
			wb.close();
		}
		
		XSSFWorkbook excel = new XSSFWorkbook(outputExcel);
		ByteArrayOutputStream buff = new ByteArrayOutputStream();
		excel.write(buff);
		 
		// PDF変換時に渡すInputStreamを用意
		ByteArrayInputStream in = new ByteArrayInputStream(buff.toByteArray());
		buff.close();
		 
		// PDFの出力先を開く
		FileOutputStream out = new FileOutputStream("/home/flairlink/git/ICW/ICW(test)/WebContent/pdf/ " + date + "取引報告書.pdf");
		outputPdf = "";
        outputPdf = "/home/flairlink/git/ICW/ICW(test)/WebContent/pdf/ " + date + "取引報告書.pdf";
		 
		// OpenOfficeに接続(localhostの8100番ポート)
		SocketOpenOfficeConnection con = new SocketOpenOfficeConnection(8100);
		con.connect();
		 
		try {
		    // ExcelからPDFへ変換
		    DocumentConverter converter = new OpenOfficeDocumentConverter(con);
		    converter.convert(
		            in, new DefaultDocumentFormatRegistry().getFormatByFileExtension("xlsx"),
		            out, new DefaultDocumentFormatRegistry().getFormatByFileExtension("pdf")
		            );
		            
		            response.setContentType("application/octet-stream; ");
		    		response.setHeader("Content-Disposition", "attachment; filename= " + date + "取引報告書");
		    		// attachment でなく inline とかするとブラウザウィンドウ内に表示(ブラウザの仕様にもよるかもしれない)
		    		 
		    		OutputStream os = response.getOutputStream(); 
		    		InputStream is = new FileInputStream(outputPdf);
		    		int c;
		    		while ((c = is.read()) != -1) { 
		    		     os.write(c);
		    		}
		    		
		    		os.close();
		    		is.close();
		    	
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    // OpenOfficeから切断
	    // ここで確実に切断しないとスレッドが終了しません
	    con.disconnect();
	}
	 
	out.flush();
	out.close();
	in.close();
	excel.close();
}
}