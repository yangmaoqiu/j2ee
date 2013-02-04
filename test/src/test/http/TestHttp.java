package test.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.transform.stream.StreamSource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestHttp {

	public void connection(URL url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.addRequestProperty(
				"Accept",
				"image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/msword, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/x-silverlight, */*");
		conn.setRequestProperty("Referer", "https://9.186.10.56:8443/index.jsp");
		conn.setRequestProperty("Accept-Language", "zh-cn");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
		conn.setRequestProperty(
				"User-Agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Foxy/1; .NET CLR 2.0.50727;MEGAUPLOAD 1.0)");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Cache-Control", "no-cache");
		HttpURLConnection.setFollowRedirects(true);
		conn.setInstanceFollowRedirects(true);
		conn.setDoOutput(true); // IO input to Server
		conn.setDoInput(true); //
		conn.setUseCaches(false); // obtain the newest info of server
		conn.setAllowUserInteraction(false);
		conn.setRequestMethod("POST");

		conn.getOutputStream().write("a=b".getBytes());
		conn.getOutputStream().flush();
		conn.connect();

		// conn.connect();
		String cookie = conn.getHeaderField("Set-Cookie");
		System.out.println(cookie);
		InputStream stream = conn.getInputStream();
		StreamSource source = new StreamSource(stream);
		Reader reader = source.getReader();
		// BufferedReader in = new BufferedReader(conn.getInputStream());
		char[] chars = new char[1024];
		while (reader.read(chars) > 0) {
			// sb.append(line);
			System.out.println(chars);
		}

		reader.close();
	}

	public static void main(String[] args) throws Exception {
		// new TestHttp().connection(new URL(
		// "http://192.168.1.18:8080/assets-web/index.html"));

		new TestHttp().httpclient("oa.bytter.com", 80);
		// new TestHttp().httpclientLocal("192.168.1.18", 8080);
	}

	public void httpclientLocal(String site, Integer port)
			throws HttpException, IOException {
		HttpClient client = new HttpClient();
		client.getHostConfiguration().setHost(site, port);

		// ģ���¼ҳ�� login.jsp->main.jsp
		PostMethod post = new PostMethod("/assets-web/mq");
		NameValuePair actionid = new NameValuePair("$actionid", "200000");
		NameValuePair name = new NameValuePair("username", "admin");
		NameValuePair pass = new NameValuePair("password", "123456");
		post.setRequestBody(new NameValuePair[] { actionid, name, pass });
		int status = client.executeMethod(post);
		System.out.println(post.getResponseBodyAsString());
		System.out.println(status);
		post.releaseConnection();

		GetMethod get = new GetMethod("/assets-web/mq");
		get.setQueryString(new NameValuePair[] {
				new NameValuePair("$actionid", "1004"),
				new NameValuePair("$dataid", "1410"),
				new NameValuePair("id", "root") });
		System.out.println(client.executeMethod(get));
		System.out.println(get.getResponseBodyAsString());
		get.releaseConnection();
	}

	public void httpclient(String site, Integer port) throws HttpException,
			IOException {
		HttpClient client = new HttpClient();
		client.getHostConfiguration().setHost(site, port);

		// ģ���¼ҳ�� login.jsp->main.jsp
		PostMethod post = new PostMethod("/c6/Jhsoft.Web.login/PassWord.aspx");
		// Password2 0
		// UserName yangmq
		// __EVENTARGUMENT
		// __EVENTTARGET LBEnter
		// __LASTFOCUS
		// __VIEWSTATE
		// /wEPDwUKLTY1OTY3NDk5Mg9kFgJmD2QWDgIBDw8WAh4EVGV4dAUM5a+G56CB55m75b2VZGQCAw8WAh4HVmlzaWJsZWhkAgQPFgIfAWhkAgUPFgIfAWhkAgYPFgIfAWhkAgcPDxYCHwAFDOeugOS9k+S4reaWh2RkAgoPFgIeCWlubmVyaHRtbAXDATxkaXYgY2xhc3M9ImNvcHlyaWdodCI+CTxzcGFuPumHkeWSjOi9r+S7tiZuYnNwOyZjb3B5OzIwMTA8YSBocmVmPSIiIG9uY2xpY2s9ImphdmFzY3JpcHQ6d2luZG93Lm9wZW4oJ2h0dHA6Ly93d3cuamgwMTAxLmNvbScsJycsJycpO3ZvaWQoMCk7cmV0dXJuIGZhbHNlOyI+Jm5ic3A7SmluaGVyIFNvZnR3YXJlPC9hPjwvc3Bhbi0tPjwvZGl2PmRk
		// hidEpass 2
		// hidIsValid 0
		// hidLoginState
		// txtLan ��������
		// txtPassword �����¼
		post.setRequestBody(new NameValuePair[] {
				new NameValuePair("Password2", "0"),
				new NameValuePair("UserName", "yangmq"),

				new NameValuePair("__EVENTARGUMENT", ""),
				new NameValuePair("__EVENTTARGET", "LBEnter"),
				new NameValuePair("__LASTFOCUS", ""),
				new NameValuePair(
						"__VIEWSTATE",
						"/wEPDwUKLTY1OTY3NDk5Mg9kFgJmD2QWDgIBDw8WAh4EVGV4dAUM5a+G56CB55m75b2VZGQCAw8WAh4HVmlzaWJsZWhkAgQPFgIfAWhkAgUPFgIfAWhkAgYPFgIfAWhkAgcPDxYCHwAFDOeugOS9k+S4reaWh2RkAgoPFgIeCWlubmVyaHRtbAXDATxkaXYgY2xhc3M9ImNvcHlyaWdodCI+CTxzcGFuPumHkeWSjOi9r+S7tiZuYnNwOyZjb3B5OzIwMTA8YSBocmVmPSIiIG9uY2xpY2s9ImphdmFzY3JpcHQ6d2luZG93Lm9wZW4oJ2h0dHA6Ly93d3cuamgwMTAxLmNvbScsJycsJycpO3ZvaWQoMCk7cmV0dXJuIGZhbHNlOyI+Jm5ic3A7SmluaGVyIFNvZnR3YXJlPC9hPjwvc3Bhbi0tPjwvZGl2PmRk"),
				new NameValuePair("hidEpass", "2"),
				new NameValuePair("hidIsValid", "0"),
				new NameValuePair("hidLoginState", ""),

				new NameValuePair("txtLan", "��������"),
				new NameValuePair("txtPassword", "�����¼") });

		int status = client.executeMethod(post);
		// System.out.println(post.getResponseBodyAsString());
		System.out.println(status);
		post.releaseConnection();

		GetMethod upwork = new GetMethod(
				"/c6/JHSoft.web.HRMAttendance/attendance_on.aspx");
		System.out.println(client.executeMethod(upwork));
		System.out.println(upwork.getResponseBodyAsString());
		upwork.releaseConnection();
		
		
		GetMethod downwork = new GetMethod(
				"/c6/JHSoft.web.HRMAttendance/attendance_off.aspx");
		System.out.println(client.executeMethod(downwork));
		System.out.println(downwork.getResponseBodyAsString());
		downwork.releaseConnection();

		/*
		 * // �鿴 cookie ��Ϣ CookieSpec cookiespec =
		 * CookiePolicy.getDefaultSpec(); Cookie[] cookies =
		 * cookiespec.match(site, port, "/" , false ,
		 * client.getState().getCookies()); if (cookies.length == 0) {
		 * System.out.println( "None" ); } else { for (Cookie cookie: cookies){
		 * System.out.println(cookie.toString()); } }
		 * 
		 * // ���������ҳ�� main2.jsp
		 * http://oa.bytter.com/c6/JHSoft.Web.WorkFlat/Index.aspx
		 * 
		 * System.out.println("--------------"); GetMethod get=new
		 * GetMethod("/c6/JHSoft.Web.WorkFlat/Index.aspx"); HttpState hs =
		 * client.getState(); hs.addCookie(cookies[0]); //
		 * get.setRequestHeader("Accept-Language","zh-cn"); //
		 * get.setRequestHeader("Accept-Encoding"," gzip, deflate"); //
		 * get.setRequestHeader
		 * ("If-Modified-Since","Thu, 29 Jul 2004 02:24:49 GMT"); //
		 * get.setRequestHeader("If-None-Match","'3014d-1d31-41085ff1'"); //
		 * get.setRequestHeader("User-Agent",
		 * " Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; InfoPath.2)"
		 * ); // get.setRequestHeader("Host","kyxk.net"); //
		 * get.setRequestHeader("Connection"," Keep-Alive"); //
		 * get.setRequestHeader("Cookie",
		 * " iscookies=0; UTMPUSERID=guest; UTMPKEY=37127828; UTMPNUM=18016; LOGINTIME=1180521529"
		 * ); // get.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		 * // client.getParams().setCookiePolicy(CookiePolicy.RFC2109); // for
		 * (Cookie cookie: cookies){ // get.setRequestHeader("Cookie",
		 * cookie.toString()); // } //
		 * System.out.println(client.executeMethod(get));
		 * System.out.println(get.getResponseBodyAsString());
		 * get.releaseConnection();
		 */
	}
}
