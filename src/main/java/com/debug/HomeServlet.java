import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("API Base: http://localhost:8080/api/");
        out.println();
        out.println("/user");
        out.println(" ├── POST   -> Criar usuário (body: { \"name\": \"exemplo\", \"password\": \"senha123\" })");
        out.println(" ├── GET    -> Listar todos os usuários");
        out.println(" ├── GET /{id} -> Obter um usuário específico");
        out.println(" ├── PUT /{id} -> Atualizar usuário (body: { \"name\": \"novo_nome\" })");
        out.println(" ├── DELETE /{id} -> Remover usuário");
        out.println();
        out.println("/product");
        out.println(" ├── POST   -> Criar produto (body: { \"user_id\": 1, \"name\": \"Produto X\", \"description\": \"Detalhes\", \"price\": 100.0, \"state\": \"disponível\" })");
        out.println(" ├── GET    -> Listar todos os produtos");
        out.println(" ├── GET /{id} -> Obter um produto específico");
        out.println(" ├── PUT /{id} -> Atualizar produto (body: { \"name\": \"novo_nome\", \"state\": \"reservado\" })");
        out.println(" ├── DELETE /{id} -> Remover produto");
        out.println();
        out.println("/product_images");
        out.println(" ├── POST   -> Adicionar imagem a um produto (body: { \"product_id\": 1, \"name\": \"imagem1.jpg\", \"image\": \"base64_encoded_data\" })");
        out.println(" ├── GET /{product_id} -> Listar imagens de um produto");
        out.println(" ├── DELETE /{id} -> Remover imagem específica");
    }
}

