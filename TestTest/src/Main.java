import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
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

		try {
			GoogleQuery google = new GoogleQuery("網拍");
			ArrayList<WebPage> query = google.query(lst);
			for (WebPage page : query) {
				String key = page.name;
				String url = page.url;
				// Double value = page.score;
				System.out.println(key + '\n' + url + '\n');
//       s[num][0] = key;
//       s[num][1] = value;
//       num++;
			}
//   System.out.println(google.query(lst));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}