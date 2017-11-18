package com.practize;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class LDAPContext {

    private static DirContext directoryContext = null;

    public static DirContext getContext() {
        if (directoryContext == null) {
            synchronized (LDAPContext.class) {
                if (directoryContext == null) {
                    try {
                        directoryContext = init();
                    } catch (NamingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return directoryContext;
    }

    private static DirContext init() throws NamingException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:1389" + "/" + "ou=person,dc=realsuite,dc=com");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=Directory Manager");
        env.put(Context.SECURITY_CREDENTIALS, "secret");

        return new InitialDirContext(env);
    }

    private LDAPContext() {
    }
}
