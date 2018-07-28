package com.hrssc.service;

public interface AuthorizationService{
    boolean checkProject(int projectId, int userId);
    boolean checkResource(int resourceId, int userId);
    boolean checkResource(int resourceId);
    boolean checkRoleRelease(int jobId, int userId);
    boolean checkRoleReject(int jobId, int userId);
}
