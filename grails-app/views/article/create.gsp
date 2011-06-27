<%@ page import="se.webinventions.plugins.artisteer.Post" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'post.label', default: 'Post')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${postInstance}">
        <div class="errors">
            <g:renderErrors bean="${postInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="content"><g:message code="post.content.label" default="Content"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: postInstance, field: 'content', 'errors')}">
                        <g:textField name="content" value="${postInstance?.content}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="page"><g:message code="post.page.label" default="Page"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: postInstance, field: 'page', 'errors')}">
                        <g:select name="page.id" from="${se.webinventions.plugins.artisteer.Page.list()}" optionKey="id"
                                  value="${postInstance?.page?.id}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="published"><g:message code="post.published.label" default="Published"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: postInstance, field: 'published', 'errors')}">
                        <g:checkBox name="published" value="${postInstance?.published}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="visibleToRoles"><g:message code="post.visibleToRoles.label"
                                                               default="Visible To Roles"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: postInstance, field: 'visibleToRoles', 'errors')}">
                        <g:textField name="visibleToRoles" value="${postInstance?.visibleToRoles}"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:submitButton name="create" class="save"
                                                 value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
