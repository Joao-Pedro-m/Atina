package com.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import org.json.JSONObject;

import org.mindrot.jbcrypt.BCrypt;

import com.database.DataBaseConnection;

import com.exceptions.Status;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/user/*")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter writer = response.getWriter();
	    
	    String pathInfo = request.getPathInfo();
	    
	    if (pathInfo == null) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        writer.print(Status.BAD_REQUEST_ERROR);
	        return;
	    }
	    
	    String[] pathParts = pathInfo.split("/");
	    
	    if (pathParts.length != 2) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        writer.print(Status.BAD_REQUEST_ERROR);
	        return;
	    }

	    String identifier = pathParts[1];
	    JSONObject user;

	    user = getUser(identifier);	    

	    if (user.length() == 0) {
	        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        writer.print(Status.NOT_FOUND_ERROR);
	    } else 
	        writer.print(user);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writer.print(Status.BAD_REQUEST_ERROR);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        if (pathParts.length != 2) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writer.print(Status.BAD_REQUEST_ERROR);
            return;
        }

        String identifier = pathParts[1];
        JSONObject resultado;

        try {
            // Tenta interpretar como um ID numérico
            int userId = Integer.parseInt(identifier);
            resultado = deleteUser(userId);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writer.print(Status.BAD_REQUEST_ERROR);
            return;
        }

        response.setStatus(resultado.getInt("status"));
        writer.print(resultado);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        JSONObject jsonResponse = new JSONObject();

        try {
            String name = request.getParameter("name");
            String password = request.getParameter("password");

            if (name == null || name.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.print(Status.BAD_REQUEST_ERROR);
                return;
            }

            String encriptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            JSONObject resultado = addUser(name, encriptedPassword);

            response.setStatus(resultado.getInt("status"));
            writer.print(resultado);

        }  catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            jsonResponse.put("message", "Erro interno ao processar a requisição.");
            writer.print(jsonResponse);
        }
    }

	private JSONObject addUser(String name, String password) {
        JSONObject jsonObject = new JSONObject();
        try (Connection connection = DataBaseConnection.getConnection()) {
            String sql = "INSERT INTO user (name, password) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, password);
                if (stmt.executeUpdate() > 0) {
                    jsonObject.put("status", HttpServletResponse.SC_CREATED);
                    jsonObject.put("message", "Usuário criado com sucesso!");
                } else {
                    jsonObject.put("status", HttpServletResponse.SC_BAD_REQUEST);
                    jsonObject.put("message", "Erro ao criar usuário.");
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            jsonObject.put("status", HttpServletResponse.SC_CONFLICT);
            jsonObject.put("message", "Erro ao criar usuário: usuário já existente.");
        } catch (Exception e) {
            jsonObject.put("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            jsonObject.put("message", "Erro interno ao inserir usuário.".concat(e.getMessage()));
        }
        return jsonObject;
    }
	
	private JSONObject deleteUser(int id) {
	    JSONObject jsonObject = new JSONObject();
	    try (Connection connection = DataBaseConnection.getConnection();
	         PreparedStatement stmt = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
	        stmt.setInt(1, id);
	        int rowsAffected = stmt.executeUpdate();

	        if (rowsAffected > 0) {
	            jsonObject.put("status", HttpServletResponse.SC_OK);
	            jsonObject.put("message", "Usuário deletado.");
	        } else {
	            jsonObject.put("status", HttpServletResponse.SC_NOT_FOUND);
	            jsonObject.put("message", "Usuário não encontrado.");
	        }
	    } catch (Exception e) {
	        jsonObject.put("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        jsonObject.put("message", "Erro ao excluir usuário: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return jsonObject;
	}

	private JSONObject getUser(String name) {
		JSONObject user = new JSONObject();
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id, name FROM user WHERE name = ?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.put("id", resultSet.getInt("id"));
                user.put("name", resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
	}
}
