package net.proselyte.securetyapp.service;

public interface SecurityService {

    String findLoggedInName();

    void autoLogin(String name, String password);
}
