

<%@ page import="se.webinventions.test.artisteer.Test" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="testsidebars" />
        <g:set var="entityName" value="${message(code: 'test.label', default: 'Test')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${testInstance}">
            <div class="errors">
                <g:renderErrors bean="${testInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="myNewValue"><g:message code="test.myNewValue.label" default="My New Value" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: testInstance, field: 'myNewValue', 'errors')}">
                                    <g:textField name="myNewValue" value="${testInstance?.myNewValue}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="testString"><g:message code="test.testString.label" default="Test String" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: testInstance, field: 'testString', 'errors')}">
                                    <g:textField name="testString" value="${testInstance?.testString}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
