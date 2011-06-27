<%@ page import="se.webinventions.plugins.artisteer.VerticalBlock" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'verticalBlock.label', default: 'VerticalBlock')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="id" title="${message(code: 'verticalBlock.id.label', default: 'Id')}"/>

                <g:sortableColumn property="content"
                                  title="${message(code: 'verticalBlock.content.label', default: 'Content')}"/>

                <g:sortableColumn property="column"
                                  title="${message(code: 'verticalBlock.column.label', default: 'Column')}"/>

                <g:sortableColumn property="published"
                                  title="${message(code: 'verticalBlock.published.label', default: 'Published')}"/>

                <g:sortableColumn property="type"
                                  title="${message(code: 'verticalBlock.type.label', default: 'Type')}"/>

                <g:sortableColumn property="visibleToRoles"
                                  title="${message(code: 'verticalBlock.visibleToRoles.label', default: 'Visible To Roles')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${verticalBlockInstanceList}" status="i" var="verticalBlockInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${verticalBlockInstance.id}">${fieldValue(bean: verticalBlockInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: verticalBlockInstance, field: "content")}</td>

                    <td>${fieldValue(bean: verticalBlockInstance, field: "column")}</td>

                    <td><g:formatBoolean boolean="${verticalBlockInstance.published}"/></td>

                    <td>${fieldValue(bean: verticalBlockInstance, field: "type")}</td>

                    <td>${fieldValue(bean: verticalBlockInstance, field: "visibleToRoles")}</td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${verticalBlockInstanceTotal}"/>
    </div>
</div>
</body>
</html>
