package com.electricalweb.controllers;
import com.electricalweb.entities.Player;
import com.electricalweb.entities.Protocol;
import com.electricalweb.services.ProtocolService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet(name = "ProtocolController", urlPatterns = "/protocol")
public class ProtocolController extends HttpServlet {
    ProtocolService protoService = new ProtocolService();
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        setAttributes(req);
        String url = protoService.determineUrl();
        protoService.forwardResponse(url, req, resp);
    }

    private void setAttributes(HttpServletRequest req) {
        Protocol protocol = protoService.getProtocolById(req);
        req.setAttribute("protocol", protocol);
        int gol = protoService.getStatisticGol(protocol,false);
        Map<Player, Integer> play = protoService.getStatisticGolPlayer(protocol);
        req.setAttribute("protocolStatistic", gol);
        req.setAttribute("protocolStatisticPlayer", play.keySet().stream().findFirst().get());
        req.setAttribute("protocolStatisticPlayerGoal", play.values().stream().findFirst().get());
    }

    public void setProtoService(ProtocolService protoService) {
        this.protoService = protoService;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Protocol protocol = protoService.getProtocolById(req);
        String url = protoService.determineUrl();
        req.setAttribute("protocol", protocol);
        int gol = protoService.getStatisticGol(protocol,false);
        req.setAttribute("protocolStatistic", gol);
        protoService.forwardResponse(url, req, resp);
    }
}
