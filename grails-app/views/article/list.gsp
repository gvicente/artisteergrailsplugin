<%@ page import="se.webinventions.plugins.artisteer.Post" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'post.label', default: 'Post')}"/>
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

                <g:sortableColumn property="id" title="${message(code: 'post.id.label', default: 'Id')}"/>

                <g:sortableColumn property="content"
                                  title="${message(code: 'post.content.label', default: 'Content')}"/>

                <th><g:message code="post.page.label" default="Page"/></th>

                <g:sortableColumn property="published"
                                  title="${message(code: 'post.published.label', default: 'Published')}"/>

                <g:sortableColumn property="visibleToRoles"
                                  title="${message(code: 'post.visibleToRoles.label', default: 'Visible To Roles')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${postInstanceList}" status="i" var="postInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${postInstance.id}">${fieldValue(bean: postInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: postInstance, field: "content")}</td>

                    <td>${fieldValue(bean: postInstance, field: "page")}</td>

                    <td><g:formatBoolean boolean="${postInstance.published}"/></td>

                    <td>${fieldValue(bean: postInstance, field: "visibleToRoles")}</td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${postInstanceTotal}"/>
    </div>
</div>
</body>
</html>
