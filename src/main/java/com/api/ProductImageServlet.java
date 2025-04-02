package com.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.database.DataBaseConnection;

@WebServlet("/product/image")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) // Limite de 10MB
public class ProductImageServlet extends HttpServlet {
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

	

	
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        	Part filePart = request.getPart("file");
		String fileName = filePart.getSubmittedFileName();
		InputStream fileContent = filePart.getInputStream();
		int productId = Integer.parseInt(request.getParameter("product_id"));

        	try (Connection conn = DataBaseConnection.getConnection()) {
            		String sql = "INSERT INTO product_images (name, image, product_id) VALUES (?, ?, ?)";
            		PreparedStatement statement = conn.prepareStatement(sql);
            		statement.setString(1, fileName);
            		statement.setBinaryStream(2, fileContent, (int) filePart.getSize());
        	    	statement.setInt(3, productId);

            	int row = statement.executeUpdate();
            	if (row > 0) {
            	    response.getWriter().println("Imagem enviada com sucesso!");
            	}
        	} catch (Exception ex) {
        	    ex.printStackTrace();
            	response.getWriter().println("Erro ao enviar imagem.");
        	}
    	}
}

