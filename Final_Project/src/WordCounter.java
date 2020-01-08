import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WordCounter {
	private String urlStr;
    private String content;
    
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() throws IOException{
    	String retVal = "";

    	  URL u = new URL(urlStr);

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
    
    public int countKeyword(String keyword) throws IOException{
		if (content == null){
		    content = fetchContent();
		}
		if(content.length() < 500) {
			Document d = Jsoup.parse(content);
			urlStr = d.select("a").get(0).attr("href");
			content = null;
			return countKeyword(keyword);
		}
//		System.out.println(content);
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		//content = content.toUpperCase();
		//keyword = keyword.toUpperCase();
	
		int index=0;
		int i=0;
		int retVal=0;
		do {
			index=content.indexOf(keyword,i);
			if(index!=-1) {
				retVal++;
			}
			i=index+keyword.length();
		}while(index!=-1);
		//System.out.println(retVal);
		return retVal;
    }

}