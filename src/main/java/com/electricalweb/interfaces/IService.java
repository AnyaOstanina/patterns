package com.electricalweb.interfaces;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IService {
    void forwardResponse(String url, HttpServletRequest request, HttpServletResponse response);
}
