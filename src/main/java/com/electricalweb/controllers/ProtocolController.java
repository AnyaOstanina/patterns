package com.electricalweb.controllers;

import com.electricalweb.entities.Player;
import com.electricalweb.entities.Protocol;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "ProtocolController", urlPatterns = "/protocol")
public class ProtocolController extends HttpServlet {
    ProtocolService protoService = new ProtocolService();
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            long idProtocol = Integer.valueOf(req.getParameter("idProtocol"));
        Protocol protocol= null;
        try {
            protocol = protoService.getProtocol(idProtocol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = determineUrl();
            req.setAttribute("protocol", protocol);
            int gol = protoService.getStatisticGol(protocol);
            Map<Player, Integer> play = protoService.getStatisticGolPlayer(protocol);
            req.setAttribute("protocolStatistic", gol);
            req.setAttribute("protocolStatisticPlayer", play.keySet().stream().findFirst().get());
            req.setAttribute("protocolStatisticPlayerGoal", play.values().stream().findFirst().get());
            forwardResponse(url, req, resp);
    }

    public void setProtoService(ProtocolService protoService) {
        this.protoService = protoService;
    }

    private String determineUrl() {
        return "/WEB-INF/views/protocol.jsp";
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        long idProtocol = Integer.parseInt(req.getParameter("idProtocol"));
        Protocol protocol= null;
        try {
            protocol = protoService.getProtocol(idProtocol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = determineUrl();
        req.setAttribute("protocol", protocol);
        int gol = protoService.getStatisticGol(protocol);
        req.setAttribute("protocolStatistic", gol);
        forwardResponse(url, req, resp);
    }
}
