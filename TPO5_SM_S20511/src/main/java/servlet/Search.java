package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Search extends HttpServlet {
    private static final String url = "jdbc:mysql://localhost:3306/Books?serverTimezone=Europe/Kiev&useSSL=false";
    private static final String username = "root";
    private static final String password = "mihach3333";
    private static final String request = "SELECT * FROM Book WHERE Name = ";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String title = "Books";
        String docType = "<!DOCTYPE html>";
        System.out.println("Connecting...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String par = req.getParameter("book");
            ResultSet resultSet = statement.executeQuery(request  + "'" + req.getParameter("book") + "'");

            writer.println(docType + "<html><head><title>" + title + "</title></head><body bgcolor=\"#FFDEAD\">");
            writer.println("<h1 align=\"center\">BOOK</h1>");
            writer.println("<form><button formaction=\"home.html\">Go to home page</button></form>");
            writer.println("<br/>");
            if (resultSet.next()) {
                writer.println("<table align=\"center\" " +
                        "bgcolor=\"#FFFAF0\" border=\"1\" cellpadding=\"2\" cellspacing=\"0\" bordercolor=\"black\">");
                writer.println("<tr><th>Id</th><th>Book Name</th><th>Author</th><th>Price</th></tr>");

                int id = resultSet.getInt(1);
                String bookName = resultSet.getString(2);
                String author = resultSet.getString(3);
                int price = resultSet.getInt(4);

                writer.println("<tr>");
                writer.println("<td>" + id + "</td>");
                writer.println("<td>" + bookName + "</td>");
                writer.println("<td>" + author + "</td>");
                writer.println("<td>" + price + "</td>");
                writer.println("</tr>");

                writer.println("</table>");
            }
            else {
                writer.println("<h2 alignment=\"center\">There is no such book in database</h2>");
            }

            resultSet.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        writer.println("</body></html>");
    }
}
