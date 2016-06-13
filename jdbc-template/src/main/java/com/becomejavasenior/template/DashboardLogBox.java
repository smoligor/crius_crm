package com.becomejavasenior.template;

import java.sql.Timestamp;
import java.util.Objects;

public class DashboardLogBox implements Comparable<DashboardLogBox> {
    private Timestamp dateCreate;
    private String userName;
    private String logType;
    private String logInfo;

    public DashboardLogBox(Timestamp dateCreate, String userName, String logType, String logInfo) {
        this.dateCreate = new Timestamp(dateCreate.getTime());
        this.userName = userName;
        this.logType = logType;
        this.logInfo = logInfo;
    }

    @Override
    public int compareTo(DashboardLogBox that) {
        if (that == null || dateCreate == null || that.dateCreate == null) {
            return 0;
        }
        return dateCreate.compareTo(that.dateCreate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DashboardLogBox that = (DashboardLogBox) o;
        return Objects.equals(dateCreate, that.dateCreate) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(logType, that.logType) &&
                Objects.equals(logInfo, that.logInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateCreate, userName, logType, logInfo);
    }

    public Timestamp getDateCreate() {
        return new Timestamp(dateCreate.getTime());
    }

    public String getUserName() {
        return userName;
    }

    public String getLogType() {
        return logType;
    }

    public String getLogInfo() {
        return logInfo;
    }
}
