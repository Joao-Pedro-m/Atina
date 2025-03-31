package com.api;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.database.DataBaseConnection;

@WebServlet("/upload")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) // Limite de 10MB
public class UploadProductImageServlet extends HttpServlet {
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

