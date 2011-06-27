<%@ page import="se.webinventions.plugins.artisteer.Page" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'page.label', default: 'Page')}"/>
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
    <g:hasErrors bean="${pageInstance}">
        <div class="errors">
            <g:renderErrors bean="${pageInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="content"><g:message code="page.content.label" default="Content"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: pageInstance, field: 'content', 'errors')}">
                        <g:textField name="content" value="${pageInstance?.content}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="published"><g:message code="page.published.label" default="Published"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: pageInstance, field: 'published', 'errors')}">
                        <g:checkBox name="published" value="${pageInstance?.published}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="type"><g:message code="page.type.label" default="Type"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: pageInstance, field: 'type', 'errors')}">
                        <g:textField name="type" value="${fieldValue(bean: pageInstance, field: 'type')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="visibleInHorizontalMenu"><g:message code="page.visibleInHorizontalMenu.label"
                                                                        default="Visible In Horizontal Menu"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: pageInstance, field: 'visibleInHorizontalMenu', 'errors')}">
                        <g:checkBox name="visibleInHorizontalMenu" value="${pageInstance?.visibleInHorizontalMenu}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="visibleInVerticalMenu"><g:message code="page.visibleInVerticalMenu.label"
                                                                      default="Visible In Vertical Menu"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: pageInstance, field: 'visibleInVerticalMenu', 'errors')}">
                        <g:checkBox name="visibleInVerticalMenu" value="${pageInstance?.visibleInVerticalMenu}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="visibleToRoles"><g:message code="page.visibleToRoles.label"
                                                               default="Visible To Roles"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: pageInstance, field: 'visibleToRoles', 'errors')}">
                        <g:textField name="visibleToRoles" value="${pageInstance?.visibleToRoles}"/>
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
