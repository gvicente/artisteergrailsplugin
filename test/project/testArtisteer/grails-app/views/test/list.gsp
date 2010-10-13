
<%@ page import="se.webinventions.test.artisteer.Test" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'test.label', default: 'Test')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'test.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="myNewValue" title="${message(code: 'test.myNewValue.label', default: 'My New Value')}" />
                        
                            <g:sortableColumn property="testString" title="${message(code: 'test.testString.label', default: 'Test String')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${testInstanceList}" status="i" var="testInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${testInstance.id}">${fieldValue(bean: testInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: testInstance, field: "myNewValue")}</td>
                        
                            <td>${fieldValue(bean: testInstance, field: "testString")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${testInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
