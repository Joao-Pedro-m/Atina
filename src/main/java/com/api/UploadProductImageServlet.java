package com.api;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
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
        // Obtendo o Product ID do formulário
        String productIdStr = request.getParameter("product_id");
        int productId = Integer.parseInt(productIdStr); // Converte para int

        // Obtendo o arquivo enviado
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        try (InputStream fileContent = filePart.getInputStream();
             Connection conn = DataBaseConnection.getConnection()) {

            // Query corrigida para incluir product_id
            String sql = "INSERT INTO product_images (product_id, name, image) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, productId);
            statement.setString(2, fileName);
            statement.setBinaryStream(3, fileContent, (int) filePart.getSize());

            int row = statement.executeUpdate();
            if (row > 0) {
                response.getWriter().println("Imagem enviada com sucesso para o produto ID: " + productId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().println("Erro ao enviar imagem.");
        }
    }
}

