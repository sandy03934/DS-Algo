package com.practize;

import com.unboundid.ldap.sdk.AddRequest;
import com.unboundid.ldap.sdk.BindRequest;
import com.unboundid.ldap.sdk.DN;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPResult;
import com.unboundid.ldap.sdk.RDN;
import com.unboundid.ldap.sdk.SimpleBindRequest;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.ArrayList;
import java.util.List;

public class LDAPClientApp {

    static void onBoardOrganisationUnit(String ou, String org) {

        DN ouDN = null;
        DN orgDN = null;

        if (org != null && org.length() > 0) {
            orgDN = new DN(new RDN("o", org), getDomainDN());
        }

        if (ou != null && ou.length() > 0) {
            if (orgDN != null) {
                ouDN = new DN(new RDN("ou", ou), orgDN);
            } else {
                ouDN = new DN(new RDN("ou", ou), getDomainDN());
            }
        }

        if (ouDN != null) {
            AddRequest addRequest = new AddRequest(ouDN,
                    new com.unboundid.ldap.sdk.Attribute("objectClass", "top", "organizationalUnit"),
                    new com.unboundid.ldap.sdk.Attribute("ou", ou));
            LDAPResult addResult;
            try
            {
                addResult = LDAPConnectionManager.getLDAPConnectionPool().add(addRequest);
                System.out.println("OrganizationUnit Successfully added :: " + addResult);
            } catch (LDAPException le) {
                addResult = le.toLDAPResult();
                System.out.println("OrganizationUnit Failed to Add :: " + addResult);
            }
        }
    }

    static void onBoardDomain() {
        String domainName = "realsuite";
        DN parentDN = new DN(new RDN("dc", "realsuite"), new RDN("dc", "com"));
//        DN orgUnitDN = new DN(new RDN("ou", domainName), parentDN);

        AddRequest addRequest = new AddRequest(parentDN,
                new com.unboundid.ldap.sdk.Attribute("objectClass", "top", "domain"),
                new com.unboundid.ldap.sdk.Attribute("dc", domainName));

        LDAPResult addResult;
        try
        {
            addResult = LDAPConnectionManager.getLDAPConnectionPool().add(addRequest);


            // If we get here, then the add operation succeeded.
        }
        catch (LDAPException le)
        {
            addResult = le.toLDAPResult();

            // If we get here, then the add operation failed.
        }
    }

    static void onBoardOrganisation(String organisation) {

        DN orgDN = null;

        if (organisation != null && organisation.length() > 0) {
            orgDN = new DN(new RDN("o", organisation), getDomainDN());
        }
        if (orgDN != null) {
            AddRequest addRequest = new AddRequest(orgDN,
                    new com.unboundid.ldap.sdk.Attribute("objectClass", "top", "organization"),
                    new com.unboundid.ldap.sdk.Attribute("o", organisation));

            LDAPResult addResult;
            try {
                addResult = LDAPConnectionManager.getLDAPConnectionPool().add(addRequest);
                System.out.println("Organization Successfully added :: " + addResult);
            } catch (LDAPException le) {
                addResult = le.toLDAPResult();
                System.out.println("Organization Failed to Add :: " + addResult);
            }
        }
    }


    static void onBoardUser(User user, String organizationUnit, String organisation) {

        DN ouDN = null;
        DN orgDN = null;

        if (organisation != null && organisation.length() > 0) {
            orgDN = new DN(new RDN("o", organisation), getDomainDN());
        }

        if (organizationUnit != null && organizationUnit.length() > 0) {
            if (orgDN != null) {
                ouDN = new DN(new RDN("ou", organizationUnit), orgDN);
            } else {
                ouDN = new DN(new RDN("ou", organizationUnit), getDomainDN());
            }
        }
        DN userDN = null;
        if (user != null) {
            if (ouDN != null) {
                userDN = new DN(new RDN("uid", user.getName()), ouDN);
            } else if (orgDN != null) {
                userDN = new DN(new RDN("uid", user.getName()), orgDN);
            } else {
                userDN = new DN(new RDN("uid", user.getName()), getDomainDN());
            }
        }


        if (userDN != null) {
            final List<com.unboundid.ldap.sdk.Attribute> attrList = new ArrayList<>(4);
            attrList.add(new com.unboundid.ldap.sdk.Attribute("givenName", user.getName()));
            attrList.add(new com.unboundid.ldap.sdk.Attribute("sn", user.getName()));
            attrList.add(new com.unboundid.ldap.sdk.Attribute("cn", user.getName()));
            attrList.add(new com.unboundid.ldap.sdk.Attribute("userPassword", new String[]{user.getPassword()}));
            attrList.add(new com.unboundid.ldap.sdk.Attribute("objectClass", "top", "person", "organizationalPerson", "inetOrgPerson"));
            attrList.add(new com.unboundid.ldap.sdk.Attribute("uid", user.getName()));


            AddRequest addRequest = new AddRequest(userDN, attrList);
            LDAPResult addResult;
            try
            {
                addResult = LDAPConnectionManager.getLDAPConnectionPool().add(addRequest);
                System.out.println("User Successfully added :: " + addResult);
            }
            catch (LDAPException le)
            {
                addResult = le.toLDAPResult();
                System.out.println("User Failed to Add :: " + addResult);
            }
        }
    }

    static void authenticate(User user, String organizationUnit, String organisation) {

        DN ouDN = null;
        DN orgDN = null;

        if (organisation != null && organisation.length() > 0) {
            orgDN = new DN(new RDN("o", organisation), getDomainDN());
        }

        if (organizationUnit != null && organizationUnit.length() > 0) {
            if (orgDN != null) {
                ouDN = new DN(new RDN("ou", organizationUnit), orgDN);
            } else {
                ouDN = new DN(new RDN("ou", organizationUnit), getDomainDN());
            }
        }
        DN userDN = null;
        if (user != null) {
            if (ouDN != null) {
                userDN = new DN(new RDN("uid", user.getName()), ouDN);
            } else if (orgDN != null) {
                userDN = new DN(new RDN("uid", user.getName()), orgDN);
            } else {
                userDN = new DN(new RDN("uid", user.getName()), getDomainDN());
            }
        }


        if (userDN != null) {
            BindRequest bindRequest = new SimpleBindRequest(userDN, user.getPassword());
            LDAPResult bindResult;
            try
            {
                bindResult = LDAPConnectionManager.getLDAPConnectionPool().bind(bindRequest);
                System.out.println("User Successfully authenticated :: " + bindResult);
            }
            catch (LDAPException le)
            {
                bindResult = le.toLDAPResult();
                System.out.println("User Failed to authenticate :: " + bindResult);
            }
        }
    }

    public static void main(String ... args) {

        Options options = new Options();
        options.addOption("h", false, "help");
        options.addOption("createUser", false, "add new user");
        options.addOption("authenticateUser", false, "authenticate user");
        options.addOption("createOU", false, "authenticate user");
        options.addOption("createOrganization", false, "authenticate user");
        options.addOption("u",true, "UserName");
        options.addOption("p",true, "Password");
        options.addOption("e",true, "Email");
        options.addOption("org",true, "Organization");
        options.addOption("ou",true, "Organization Unit");



        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);
            if (commandLine.hasOption("h")) {
                printUsage(options);
            } else if (commandLine.hasOption("createUser")) {
                if ((commandLine.hasOption('u') && commandLine.hasOption('p') && commandLine.hasOption("e"))) {
                    User user = new User(commandLine.getOptionValue("u"), commandLine.getOptionValue("e"), commandLine.getOptionValue("p"));

                    String ou = commandLine.getOptionValue("ou", "people");
                    String org = commandLine.getOptionValue("org");

                    onBoardUser(user, ou, org);
                } else {
                    printUsage(options);
                }
            } else if (commandLine.hasOption("createOU")) {
                if (commandLine.hasOption("ou")) {
                    String org = commandLine.getOptionValue("org");
                    onBoardOrganisationUnit(commandLine.getOptionValue("ou"), org);
                } else {
                    printUsage(options);
                }
            } else if (commandLine.hasOption("createOrganization")) {
                if (commandLine.hasOption("org")) {
                    String org = commandLine.getOptionValue("org");
                    onBoardOrganisation(org);
                } else {
                    printUsage(options);
                }
            } else if (commandLine.hasOption("authenticateUser")) {
                if ((commandLine.hasOption('u') && commandLine.hasOption('p'))) {
                    User user = new User(commandLine.getOptionValue("u"), commandLine.getOptionValue("e"), commandLine.getOptionValue("p"));

                    String ou = commandLine.getOptionValue("ou", "people");
                    String org = commandLine.getOptionValue("org");

                    authenticate(user, ou, org);
                } else {
                    printUsage(options);
                }
            } else {
                printUsage(options);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        onBoardDomain();
    }

    private static void printUsage(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "java -jar simpleldapclient.jar ", options );
    }

    private static DN getDomainDN() {
        return new DN(new RDN("dc", "realsuite"), new RDN("dc", "com"));
    }

}
