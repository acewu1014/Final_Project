import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestProject() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if (request.getParameter("keyword") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		Keyword k1 = new Keyword("新品", 50);
		Keyword k2 = new Keyword("退貨", 45);
		Keyword k3 = new Keyword("熱銷", 40);
		Keyword k4 = new Keyword("購物車", 35);
		Keyword k5 = new Keyword("優惠", 30);
		Keyword k6 = new Keyword("訂單查詢", 30);
		Keyword k7 = new Keyword("如何購買", 20);
		Keyword k8 = new Keyword("關於我們", 10);
		ArrayList<Keyword> lst = new ArrayList<Keyword>();
		lst.add(k1);
		lst.add(k2);
		lst.add(k3);
		lst.add(k4);
		lst.add(k5);
		lst.add(k6);
		lst.add(k7);
		lst.add(k8);
		Document doc = Jsoup
				.connect("http://www.google.com/search?q=" + request.getParameter("keyword") + "&oe=utf8&num=10").get();
		Elements postItems = doc.select("p.nVcaUb");
		String[][] s2 = new String[postItems.size()][2];
		for (int i = 0; i < postItems.size(); i++) {
			s2[i][0] = postItems.get(i).select("a[href]").text();
			s2[i][1] = "https://www.google.com" + postItems.get(i).select("a[href]").attr("href");
			// url : https://www.google.com/+iElement.select("a[href]").attr("href")
		}
		request.setAttribute("relative", s2);
		GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
		ArrayList<WebPage> query = google.query(lst);

		String[][] s = new String[query.size()][2];
		request.setAttribute("query", s);
		int num = 0;
		for (WebPage page : query) {
			String key = page.name;
			String url = page.url;
			System.out.println(key + '\n' + url + '\n');
			s[num][0] = key;
			s[num][1] = url;
			num++;
		}
		request.getRequestDispatcher("googleitem.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}