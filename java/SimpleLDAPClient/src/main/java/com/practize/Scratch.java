package com.practize;


import org.opensaml.ws.soap.soap11.Envelope;
import org.opensaml.xml.Configuration;
import org.opensaml.xml.io.Unmarshaller;
import org.opensaml.xml.parse.BasicParserPool;
import org.w3c.dom.Document;

import java.io.StringReader;


public class Scratch {

    private static BasicParserPool pool;
    public static void main(String [] args) {
//        String respone = "<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Header><paos:Request xmlns:paos=\"urn:liberty:paos:2003-08\" S:actor=\"http://schemas.xmlsoap.org/soap/actor/next\" S:mustUnderstand=\"1\" responseConsumerURL=\"https://realsuite-uat.altisource.com/Shibboleth.sso/SAML2/ECP\" service=\"urn:oasis:names:tc:SAML:2.0:profiles:SSO:ecp\"/><ecp:Request xmlns:ecp=\"urn:oasis:names:tc:SAML:2.0:profiles:SSO:ecp\" IsPassive=\"0\" S:actor=\"http://schemas.xmlsoap.org/soap/actor/next\" S:mustUnderstand=\"1\"><saml:Issuer xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\">https://realsuite-uat.altisource.com/shibboleth</saml:Issuer></ecp:Request><ecp:RelayState xmlns:ecp=\"urn:oasis:names:tc:SAML:2.0:profiles:SSO:ecp\" S:actor=\"http://schemas.xmlsoap.org/soap/actor/next\" S:mustUnderstand=\"1\">https://realsuite-uat.altisource.com/iamsvc/tenants</ecp:RelayState></S:Header><S:Body><samlp:AuthnRequest xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\" AssertionConsumerServiceURL=\"https://realsuite-uat.altisource.com/Shibboleth.sso/SAML2/ECP\" ID=\"_207ba09bd461096f043f1342a7eda877\" IssueInstant=\"2017-11-03T14:47:03Z\" ProtocolBinding=\"urn:oasis:names:tc:SAML:2.0:bindings:PAOS\" Version=\"2.0\"><saml:Issuer xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\">https://realsuite-uat.altisource.com/shibboleth</saml:Issuer><samlp:NameIDPolicy AllowCreate=\"1\"/></samlp:AuthnRequest></S:Body></S:Envelope>";
        String response = "<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "    <S:Header>\n" +
                "        <paos:Request xmlns:paos=\"urn:liberty:paos:2003-08\" S:actor=\"http://schemas.xmlsoap.org/soap/actor/next\" S:mustUnderstand=\"1\" responseConsumerURL=\"https://realsuite-iqa.vz.altidev.net/Shibboleth.sso/SAML2/ECP\" service=\"urn:oasis:names:tc:SAML:2.0:profiles:SSO:ecp\"/>\n" +
                "        <ecp:Request xmlns:ecp=\"urn:oasis:names:tc:SAML:2.0:profiles:SSO:ecp\" IsPassive=\"0\" S:actor=\"http://schemas.xmlsoap.org/soap/actor/next\" S:mustUnderstand=\"1\">\n" +
                "            <saml:Issuer xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\">https://realsuite-iqa.vz.altidev.net/shibboleth</saml:Issuer>\n" +
                "        </ecp:Request>\n" +
                "        <ecp:RelayState xmlns:ecp=\"urn:oasis:names:tc:SAML:2.0:profiles:SSO:ecp\" S:actor=\"http://schemas.xmlsoap.org/soap/actor/next\" S:mustUnderstand=\"1\">https://realsuite-iqa.vz.altidev.net/iamsvc/tenants</ecp:RelayState>\n" +
                "    </S:Header>\n" +
                "    <S:Body>\n" +
                "        <samlp:AuthnRequest xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\" AssertionConsumerServiceURL=\"https://realsuite-iqa.vz.altidev.net/Shibboleth.sso/SAML2/ECP\" ID=\"_9b129c342d32f58f6e58d1c3347591c0\" IssueInstant=\"2017-11-03T14:27:16Z\" ProtocolBinding=\"urn:oasis:names:tc:SAML:2.0:bindings:PAOS\" Version=\"2.0\">\n" +
                "            <saml:Issuer xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\">https://realsuite-iqa.vz.altidev.net/shibboleth</saml:Issuer>\n" +
                "            <samlp:NameIDPolicy AllowCreate=\"1\"/>\n" +
                "        </samlp:AuthnRequest>\n" +
                "    </S:Body>\n" +
                "</S:Envelope>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "    <S:Header>\n" +
                "        <paos:Request xmlns:paos=\"urn:liberty:paos:2003-08\" S:actor=\"http://schemas.xmlsoap.org/soap/actor/next\" S:mustUnderstand=\"1\" responseConsumerURL=\"https://realsuite-uat.altisource.com/Shibboleth.sso/SAML2/ECP\" service=\"urn:oasis:names:tc:SAML:2.0:profiles:SSO:ecp\"/>\n" +
                "        <ecp:Request xmlns:ecp=\"urn:oasis:names:tc:SAML:2.0:profiles:SSO:ecp\" IsPassive=\"0\" S:actor=\"http://schemas.xmlsoap.org/soap/actor/next\" S:mustUnderstand=\"1\">\n" +
                "            <saml:Issuer xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\">https://realsuite-uat.altisource.com/shibboleth</saml:Issuer>\n" +
                "        </ecp:Request>\n" +
                "        <ecp:RelayState xmlns:ecp=\"urn:oasis:names:tc:SAML:2.0:profiles:SSO:ecp\" S:actor=\"http://schemas.xmlsoap.org/soap/actor/next\" S:mustUnderstand=\"1\">https://realsuite-uat.altisource.com/iamsvc/tenants</ecp:RelayState>\n" +
                "    </S:Header>\n" +
                "    <S:Body>\n" +
                "        <samlp:AuthnRequest xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\" AssertionConsumerServiceURL=\"https://realsuite-uat.altisource.com/Shibboleth.sso/SAML2/ECP\" ID=\"_ad61ebd2670dd712b7b4a19ac4ff8712\" IssueInstant=\"2017-11-03T14:28:10Z\" ProtocolBinding=\"urn:oasis:names:tc:SAML:2.0:bindings:PAOS\" Version=\"2.0\">\n" +
                "            <saml:Issuer xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\">https://realsuite-uat.altisource.com/shibboleth</saml:Issuer>\n" +
                "            <samlp:NameIDPolicy AllowCreate=\"1\"/>\n" +
                "        </samlp:AuthnRequest>\n" +
                "    </S:Body>\n" +
                "</S:Envelope>";
        pool = new BasicParserPool();
        pool.setNamespaceAware(true);
        Envelope envlope = null;
        try {
            Document soapDoc = pool.parse(new StringReader(response));
            Unmarshaller unmarshall = Configuration.getUnmarshallerFactory()
                    .getUnmarshaller(soapDoc.getDocumentElement());
            envlope = (Envelope) unmarshall.unmarshall(soapDoc.getDocumentElement());
            System.out.println(envlope);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
