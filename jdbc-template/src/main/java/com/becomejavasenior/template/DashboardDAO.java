package com.becomejavasenior.template;

import java.util.List;
import java.util.Map;

public interface DashboardDAO {
    Map<String, Object> getDealStat();

    Map<String, Object> getTaskStat();

    Map<String, Object> getCompanyStat();

    Map<String, Object> getContactStat();

    List<DashboardLogBox> getLog(int rowCount);
}
