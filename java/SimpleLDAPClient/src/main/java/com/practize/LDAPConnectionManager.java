package com.practize;

import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPConnectionPool;
import com.unboundid.ldap.sdk.LDAPException;

public class LDAPConnectionManager {

    private static LDAPConnectionPool ldapConnectionPool = null;

    public static LDAPConnectionPool getLDAPConnectionPool() {
        if (ldapConnectionPool == null) {
            synchronized (LDAPConnectionManager.class) {
                if (ldapConnectionPool == null) {
                    init();
                }
            }
        }

        return ldapConnectionPool;
    }

    private static void init() {
        try {
            LDAPConnection connection = new LDAPConnection("localhost", 1389);
            connection.bind("cn=Directory Manager", "secret");

            connection.getRootDSE();

            ldapConnectionPool = new LDAPConnectionPool(connection, 5);
        } catch (LDAPException e) {
            e.printStackTrace();
        }
    }

    private LDAPConnectionManager() {
    }
}
