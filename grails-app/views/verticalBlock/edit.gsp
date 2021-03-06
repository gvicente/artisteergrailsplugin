<%@ page import="se.webinventions.plugins.artisteer.VerticalBlock" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'verticalBlock.label', default: 'VerticalBlock')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
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
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${verticalBlockInstance}">
        <div class="errors">
            <g:renderErrors bean="${verticalBlockInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${verticalBlockInstance?.id}"/>
        <g:hiddenField name="version" value="${verticalBlockInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="content"><g:message code="verticalBlock.content.label" default="Content"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: verticalBlockInstance, field: 'content', 'errors')}">
                        <g:textField name="content" value="${verticalBlockInstance?.content}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="column"><g:message code="verticalBlock.column.label" default="Column"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: verticalBlockInstance, field: 'column', 'errors')}">
                        <g:textField name="column" value="${fieldValue(bean: verticalBlockInstance, field: 'column')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="published"><g:message code="verticalBlock.published.label"
                                                          default="Published"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: verticalBlockInstance, field: 'published', 'errors')}">
                        <g:checkBox name="published" value="${verticalBlockInstance?.published}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="type"><g:message code="verticalBlock.type.label" default="Type"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: verticalBlockInstance, field: 'type', 'errors')}">
                        <g:select name="type" from="${se.webinventions.plugins.artisteer.BlockType?.values()}"
                                  keys="${se.webinventions.plugins.artisteer.BlockType?.values()*.name()}"
                                  value="${verticalBlockInstance?.type?.name()}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="visibleToRoles"><g:message code="verticalBlock.visibleToRoles.label"
                                                               default="Visible To Roles"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: verticalBlockInstance, field: 'visibleToRoles', 'errors')}">
                        <g:textField name="visibleToRoles" value="${verticalBlockInstance?.visibleToRoles}"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:actionSubmit class="save" action="update"
                                                 value="${message(code: 'default.button.update.label', default: 'Update')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete"
                                                 value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                                 onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
