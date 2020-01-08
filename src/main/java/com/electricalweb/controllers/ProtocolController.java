package com.electricalweb.controllers;
import com.electricalweb.entities.Player;
import com.electricalweb.entities.Protocol;
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
            long idProtocol = Integer.valueOf(req.getParameter("idProtocol"));
        Protocol protocol= null;
        try {
            protocol = (Protocol) protoService.protoList.searchEntityById(idProtocol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = protoService.determineUrl();
        req.setAttribute("protocol", protocol);
        int gol = protoService.getStatisticGol(protocol);
        Map<Player, Integer> play = protoService.getStatisticGolPlayer(protocol);
        req.setAttribute("protocolStatistic", gol);
        req.setAttribute("protocolStatisticPlayer", play.keySet().stream().findFirst().get());
        req.setAttribute("protocolStatisticPlayerGoal", play.values().stream().findFirst().get());
        protoService.forwardResponse(url, req, resp);
    }

    public void setProtoService(ProtocolService protoService) {
        this.protoService = protoService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        long idProtocol = Integer.parseInt(req.getParameter("idProtocol"));
        Protocol protocol= null;
        try {
            protocol = (Protocol) protoService.protoList.searchEntityById(idProtocol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = protoService.determineUrl();
        req.setAttribute("protocol", protocol);
        int gol = protoService.getStatisticGol(protocol);
        req.setAttribute("protocolStatistic", gol);
        protoService.forwardResponse(url, req, resp);
    }
}
