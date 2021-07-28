<?xml version="1.0" encoding="UTF-8"?>
<web-app version="3.1" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd">
    <servlet>
        <servlet-name>Ponto</servlet-name>
        <servlet-class>Controladores.cPonto</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>Ponto</servlet-name>
        <url-pattern>/cartaodeponto</url-pattern>
    </servlet-mapping>
    
    <servlet>
        <servlet-name>Entrance</servlet-name>
        <servlet-class>Controller.Entrance</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>Entrance</servlet-name>
        <url-pattern>/bk</url-pattern>
    </servlet-mapping>
    
    <servlet>
        <servlet-name>Pesquisa</servlet-name>
        <servlet-class>Controller.Pesquisa</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>Pesquisa</servlet-name>
        <url-pattern>/pesquisa</url-pattern>
    </servlet-mapping>
    
    <session-config>
        <session-timeout>
            120
        </session-timeout>
    </session-config>
    <welcome-file-list>
        <welcome-file>Paginas.ConVale.java</welcome-file>
    </welcome-file-list>
</web-app>
