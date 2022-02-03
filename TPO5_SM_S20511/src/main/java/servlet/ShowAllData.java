package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ShowAllData extends HttpServlet {
    private static final String url = "jdbc:mysql://localhost:3306/Books?serverTimezone=Europe/Kiev&useSSL=false";
    private static final String username = "root";
    private static final String password = "mihach3333";
    private static final String request = "SELECT * FROM Book";


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

            ResultSet resultSet = statement.executeQuery(request);

            writer.println(docType + "<html><head><title>" + title + "</title></head><body bgcolor=\"#FFDEAD\">");
            writer.println("<h1 align=\"center\">BOOKS DATA</h1>");
            writer.println("<br/>");
            writer.println("<table align=\"center\" " +
                    "bgcolor=\"#FFFAF0\" border=\"1\" cellpadding=\"2\" cellspacing=\"0\" bordercolor=\"black\">");
            writer.println("<tr><th>Id</th><th>Book Name</th><th>Author</th><th>Price</th></tr>");

            while (resultSet.next()) {
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
            }

            resultSet.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        writer.println("</table></body></html>");
    }
}
