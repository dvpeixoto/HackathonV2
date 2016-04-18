package com.stefanini.hackathon2.managed.conversor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/image")    
public class ConversorImagem extends HttpServlet {

 private static final long serialVersionUID = 1460571643688705941L;


    private String imagePath;


    public void init() throws ServletException {


        this.imagePath = "C:/Users/dpvillanova/Desktop/fotosTemp/";


    }

    // Actions ------------------------------------------------------------------------------------

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String requestedImage = request.getParameter("imagem");


        File image = new File(imagePath, requestedImage);

        String contentType = getServletContext().getMimeType(image.getName());

        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));

        Files.copy(image.toPath(), response.getOutputStream());
    }

}