package com.api;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product/imageform")
public class ProductFormServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Upload Image</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Upload Images</h2>");
	out.println("<form action='image' method='post' enctype='multipart/form-data'>");
	out.println("<label for='product_id'>Product ID:</label>");
	out.println("<input type='number' id='product_id' name='product_id' required>");
	out.println("<br><br>");
	out.println("<label for='file'>Choose Images:</label>");
	out.println("<input type='file' id='file' name='file' accept='image/*' multiple required>");
	out.println("<br><br>");
	out.println("<button type='submit'>Upload</button>");
	out.println("</form>");
	out.println("<h2>View Uploaded Image</h2>");
        out.println("<form action='image' method='get'>");
        out.println("<label for='view_product_id'>Product ID:</label>");
        out.println("<input type='number' id='view_product_id' name='product_id' required>");
        out.println("<br><br>");
        out.println("<button type='submit'>View Image</button>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }
}

