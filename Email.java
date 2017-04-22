import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "EmailServlet", urlPatterns = {"/email"})
public class EmailServlet extends HttpServlet {

    static PrintWriter pw=null;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            pw=resp.getWriter();
            String name=req.getParameter("name");
            String email=req.getParameter("email");
            String phone=req.getParameter("phone");
            String details=req.getParameter("details");
           // pw.println(name+" "+email+" "+phone+" "+details);
            sendEmail(email);
        }
        catch(Exception e)
        {
            pw.println(e);
        }
    }
     public static void sendEmail(String email)
    {
       try{
        String user="amanaggarawal71";//Enter user name from which you want to send email   
       String password="";//Enter the password of that email id
       String sender="amanaggarawal71@gmail.com";//Enter the email id from which you want to send email
       String host="smtp.gmail.com";
       String port="465";
       Properties props=new Properties();
       props.put("mail.smtp.user",user);
      props.put("mail.smtp.password",password);
      props.put("mail.smtp.host",host);
      props.put("mail.smtp.port",port);
      props.put("mail.smtps.auth",true);
      Session ses=Session.getDefaultInstance(props);
      MimeMessage mime=new MimeMessage(ses);
      InternetAddress from=new InternetAddress(sender);//for getting ip address of server
      InternetAddress to=new InternetAddress(email);
      mime.setSender(from);
      mime.setRecipient(Message.RecipientType.TO, to);
       mime.setSubject("Thank you");//Enter Subject
       InetAddress address=InetAddress.getLocalHost();
       String ip=address.getHostAddress();
       mime.setContent("Dear Sir/Mam,<br><br>Greetings from Portal!!!<br><br> This is the mail from vayu travel.We thank you for using our services.","text/html");//Enter the content you want to send
       Transport trans=ses.getTransport("smtps");
       trans.connect(host,user,password);
       trans.sendMessage(mime,mime.getAllRecipients());}
       catch(Exception e)
       {pw.println(e);}
    }
}
