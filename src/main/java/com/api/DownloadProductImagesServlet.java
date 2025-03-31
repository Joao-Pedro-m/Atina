package com.api;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.database.DataBaseConnection;

@WebServlet("/images")
public class DownloadProductImagesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));

        try (Connection conn = DataBaseConnection.getConnection()) {
            String sql = "SELECT image FROM product_images WHERE product_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            response.setContentType("text/html");
            response.getWriter().println("<html><body>");

            int imageIndex = 1;
            while (rs.next()) {
                byte[] imageData = rs.getBytes("image");
                String base64Image = java.util.Base64.getEncoder().encodeToString(imageData);
                response.getWriter().println("<h3>Imagem " + imageIndex + "</h3>");
                response.getWriter().println("<img src='data:image/jpeg;base64," + base64Image + "' width='300' /><br>");
                imageIndex++;
            }

            response.getWriter().println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().println("Erro ao buscar imagens: " + e.getMessage());
        }
    }
}

