package com.yi.imperial.court.service.impl;

import com.yi.imperial.court.dao.api.MemorialsDao;
import com.yi.imperial.court.dao.impl.MemorialsDaoImpl;
import com.yi.imperial.court.entity.Memorials;
import com.yi.imperial.court.service.api.MemorialsService;

import java.util.List;

public class MemorialsServiceImpl implements MemorialsService {
    private MemorialsDao memorialsDao = new MemorialsDaoImpl();

    @Override
    public List<Memorials> getAllMemorialsDigest() {
        List<Memorials> memorialsList = memorialsDao.selectAllMemorialsDigest();
        return memorialsList;
    }

    @Override
    public Memorials getMemorialsDetailById(String memorialsId) {
        Memorials memorials = memorialsDao.selectMemorialsDetailById(memorialsId);
        return memorials;
    }

    @Override
    public void updateMemorialsStatusToRead(String memorialsId) {
        memorialsDao.updateMemorialsStatusToRead(memorialsId);
    }

    @Override
    public void updateMemorialsFeedBack(String memorialsId, String feedbackContent) {
        memorialsDao.updateMemorialsFeedBack(memorialsId, feedbackContent);
    }
}
