<%@ page import="se.webinventions.plugins.artisteer.Page" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'page.label', default: 'Page')}"/>
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

                <g:sortableColumn property="id" title="${message(code: 'page.id.label', default: 'Id')}"/>

                <g:sortableColumn property="content"
                                  title="${message(code: 'page.content.label', default: 'Content')}"/>

                <g:sortableColumn property="published"
                                  title="${message(code: 'page.published.label', default: 'Published')}"/>

                <g:sortableColumn property="type" title="${message(code: 'page.type.label', default: 'Type')}"/>

                <g:sortableColumn property="visibleInHorizontalMenu"
                                  title="${message(code: 'page.visibleInHorizontalMenu.label', default: 'Visible In Horizontal Menu')}"/>

                <g:sortableColumn property="visibleInVerticalMenu"
                                  title="${message(code: 'page.visibleInVerticalMenu.label', default: 'Visible In Vertical Menu')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${pageInstanceList}" status="i" var="pageInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${pageInstance.id}">${fieldValue(bean: pageInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: pageInstance, field: "content").decodeHTML()}</td>

                    <td><g:formatBoolean boolean="${pageInstance.published}"/></td>

                    <td>${fieldValue(bean: pageInstance, field: "type")}</td>

                    <td><g:formatBoolean boolean="${pageInstance.visibleInHorizontalMenu}"/></td>

                    <td><g:formatBoolean boolean="${pageInstance.visibleInVerticalMenu}"/></td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${pageInstanceTotal}"/>
    </div>
</div>
</body>
</html>
