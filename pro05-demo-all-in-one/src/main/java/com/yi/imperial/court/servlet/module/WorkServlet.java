package com.yi.imperial.court.servlet.module;

import com.yi.imperial.court.entity.Memorials;
import com.yi.imperial.court.service.api.MemorialsService;
import com.yi.imperial.court.service.impl.MemorialsServiceImpl;
import com.yi.imperial.court.servlet.base.ModelBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WorkServlet extends ModelBaseServlet {
    private MemorialsService memorialsService = new MemorialsServiceImpl();


    protected void showMemorialsDigestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Memorials> memorialsList = memorialsService.getAllMemorialsDigest();
        request.setAttribute("memorialsList", memorialsList);
        String templateName = "memorials-list";
        processTemplate(templateName, request, response);
    }


    protected void showMemorialsDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memorialsId = request.getParameter("memorialsId");
        Memorials memorials = memorialsService.getMemorialsDetailById(memorialsId);
        Integer memorialsStatus = memorials.getMemorialsStatus();
        if (memorialsStatus == 0) {
            memorialsService.updateMemorialsStatusToRead(memorialsId);
            memorials.setMemorialsStatus(1);
        }
        request.setAttribute("memorials", memorials);
        String templateName = "memorials-detail";
        processTemplate(templateName, request, response);
    }


    protected void feedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memorialsId = request.getParameter("memorialsId");
        String feedbackContent = request.getParameter("feedbackContent");
        //执行更新
        memorialsService.updateMemorialsFeedBack(memorialsId, feedbackContent);
        response.sendRedirect(request.getContextPath() + "/work?method=showMemorialsDigestList");
    }
}
