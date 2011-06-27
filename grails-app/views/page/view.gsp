<%--
VIEW a page with all
--%>

<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'page.label', default: 'Page')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
      <g:if test="${pageInstance.isBlog()}">
              <g:render template="/article/view" collection="${pageInstance.posts}">

              </g:render>

      </g:if>
      <g:else>
      ${pageInstance?.content.decodeHTML()}    
      </g:else>
      
</body>

</html>