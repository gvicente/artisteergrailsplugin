<%@ page import="se.webinventions.plugins.artisteer.VerticalBlock" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'verticalBlock.label', default: 'VerticalBlock')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="verticalBlock.id.label" default="Id"/></td>

                <td valign="top" class="value">${fieldValue(bean: verticalBlockInstance, field: "id")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="verticalBlock.content.label" default="Content"/></td>

                <td valign="top" class="value">${fieldValue(bean: verticalBlockInstance, field: "content")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="verticalBlock.column.label" default="Column"/></td>

                <td valign="top" class="value">${fieldValue(bean: verticalBlockInstance, field: "column")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="verticalBlock.published.label" default="Published"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${verticalBlockInstance?.published}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="verticalBlock.type.label" default="Type"/></td>

                <td valign="top" class="value">${verticalBlockInstance?.type?.encodeAsHTML()}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="verticalBlock.visibleToRoles.label"
                                                         default="Visible To Roles"/></td>

                <td valign="top" class="value">${fieldValue(bean: verticalBlockInstance, field: "visibleToRoles")}</td>

            </tr>

            </tbody>
        </table>
    </div>

    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${verticalBlockInstance?.id}"/>
            <span class="button"><g:actionSubmit class="edit" action="edit"
                                                 value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete"
                                                 value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                                 onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
