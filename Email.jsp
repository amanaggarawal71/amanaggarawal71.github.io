<%@page import="javax.mail.Transport"%>
<%@page import="java.net.InetAddress"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="java.util.Properties"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
            String name=request.getParameter("name");
            String email=request.getParameter("email");
            String phone=request.getParameter("phone");
            String details=request.getParameter("details");
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
       mime.setContent("Dear "+name+",<br><br>Greetings from Portal!!!<br><br> This is the mail from vayu travel.We thank you for using our services.","text/html");//Enter the content you want to send
       Transport trans=ses.getTransport("smtps");
       trans.connect(host,user,password);
       trans.sendMessage(mime,mime.getAllRecipients());
       %>
