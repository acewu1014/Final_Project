import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

public class GoogleQuery

{

	public String searchKeyword;

	public String url;

	public String content;
	public WordCounter counter;
	public ArrayList<WebPage> list = new ArrayList<WebPage>();

	public GoogleQuery(String searchKeyword)

	{

		this.searchKeyword = searchKeyword;

		this.url = "http://www.google.com.tw/search?q=" + searchKeyword + "&oe=utf8&num=10";

	}

	private String fetchContent() throws IOException

	{
		String retVal = "";

		URL u = new URL(url);

		URLConnection conn = u.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in, "utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while ((line = bufReader.readLine()) != null) {
			retVal += line;

		}
		return retVal;
	}

	public void sort(int n) {
		quickSort(n + 1, list.size() - 1);
	}

	private void quickSort(int leftbound, int rightbound) {
		// implement quickSort algorithm

		if (rightbound <= leftbound)
			return;

		// middle pivot
		int pivotIndex = (leftbound + rightbound) / 2;
		double pivot = list.get(pivotIndex).score;

		swap(pivotIndex, rightbound);
		int swapIndex = leftbound;
		for (int i = leftbound; i < rightbound; i++) {
			if (list.get(i).score >= pivot) {
				swap(i, swapIndex);
				swapIndex++;
			}
		}
		swap(swapIndex, rightbound);

		quickSort(leftbound, swapIndex - 1);
		quickSort(swapIndex + 1, rightbound);

	}

	private void swap(int aIndex, int bIndex) {
		WebPage temp = list.get(aIndex);
		list.set(aIndex, list.get(bIndex));
		list.set(bIndex, temp);
	}

	public ArrayList<WebPage> query(ArrayList<Keyword> lst) throws IOException

	{

		if (content == null)

		{

			content = fetchContent();

		}

		// HashMap<String, Double> retVal = new HashMap<String, Double>();

		Document doc = Jsoup.parse(content);
		System.out.println(doc.text());
		Elements lis = doc.select("div");
		lis = lis.select(".ZINbbc");
		System.out.println(lis.size());
		list = new ArrayList<WebPage>();

		int n = 0;

		for (Element li : lis) {

			try

			{
				String a = "http://www.google.com.tw" + li.select("a").get(0).attr("href");
				if (!a.contains("/search?") && !a.contains("maps")&&!a.contains("youtube")) {

					WebPage web = new WebPage(a, li.select(".BNeawe").get(0).text());
					list.add(web);
					counter = new WordCounter(a);
					for (int i = 0; i < lst.size(); i++) {
						lst.get(i).setCount(web.counter.countKeyword(lst.get(i).getName()));
					}
					web.setScore(lst);
				}

			} catch (Exception e) {
//    e.printStackTrace();
			}
			try {

				String title2 = li.select(".MUxGbd").get(0).text();
				String citeUrl = "http://www.google.com.tw" + li.select("a").get(0).attr("href");

				if (!citeUrl.contains("/search") && !title2.contains("上一頁")) {
					WebPage w = new WebPage(citeUrl, title2);
					list.add(n, w);
					n++;
					counter = new WordCounter(citeUrl);
					for (int i = 0; i < lst.size(); i++) {
						lst.get(i).setCount(w.counter.countKeyword(lst.get(i).name));
					}
					w.setScore(lst);

					// System.out.println(title2);
					// System.out.println(citeUrl);
				}

			} catch (Exception e) {

			}

		}
		sort(n);

		return list;

	}

}