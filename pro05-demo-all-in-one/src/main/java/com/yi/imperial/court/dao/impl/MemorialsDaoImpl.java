package com.yi.imperial.court.dao.impl;

import com.yi.imperial.court.dao.BaseDao;
import com.yi.imperial.court.dao.api.MemorialsDao;
import com.yi.imperial.court.entity.Memorials;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MemorialsDaoImpl extends BaseDao<Memorials> implements MemorialsDao {
    @Override
    public List<Memorials> selectAllMemorialsDigest() {
        String sql = "select memorials_id memorialsId,\n" +
                "       memorials_title memorialsTitle,\n" +
                "       concat(left(memorials_content, 10), \"...\") memorialsContentDigest,\n" +
                "       emp_name memorialsEmpName,\n" +
                "       memorials_create_time memorialsCreateTime,\n" +
                "       memorials_status memorialsStatus\n" +
                "from t_memorials m left join  t_emp e on m.memorials_emp=e.emp_id ";
        List<Memorials> beanList = getBeanList(sql, Memorials.class);
        return beanList;
    }

    @Override
    public Memorials selectMemorialsDetailById(String memorialsId) {
        String sql = "select memorials_id memorialsId,\n" +
                "       memorials_title memorialsTitle,\n" +
                "       memorials_content memorialsContent,\n" +
                "       emp_name memorialsEmpName,\n" +
                "       memorials_create_time memorialsCreateTime,\n" +
                "       memorials_status memorialsStatus,\n" +
                "       feedback_content feedbackContent,\n" +
                "       feedback_time feedbackTime\n" +
                "from t_memorials m left join  t_emp e on m.memorials_emp=e.emp_id where memorials_id=?";
        Memorials memorials = getSingleBean(sql, Memorials.class, memorialsId);
        return memorials;
    }

    @Override
    public void updateMemorialsStatusToRead(String memorialsId) {
        String sql = "update t_memorials set memorials_status=1 where memorials_id=?";
        update(sql, memorialsId);
    }

    @Override
    public void updateMemorialsFeedBack(String memorialsId, String feedbackContent) {
        String feedbackTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String sql = "update t_memorials set memorials_status=2,feedback_content=?,feedback_time=? where memorials_id=?";
        update(sql, feedbackContent, feedbackTime, memorialsId);
    }
}
