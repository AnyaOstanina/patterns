package com.electricalweb.controllers;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Service{
    public String defaultUrl;

    public Service(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public String determineUrl() {
        return defaultUrl;
    }

    public void forwardResponse(String url, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
