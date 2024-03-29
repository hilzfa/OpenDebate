<%-- 
    Document   : SearchResult
    Created on : Mar 13, 2016, 6:05:57 PM
    Author     : D062572
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<div>
    <c:if test="${requestScope.results.size() == 0}">
        <h5>No results found</h5>
    </c:if>
    <c:forEach items="${requestScope.results}" var="result">
        <div id="${result.getId()}" class="resultDebate">
          
          ${result.getTopic()}
         
          <div style="color: grey; font-size: 8pt">
              <span><i class="glyphicon glyphicon-user"></i>${result.getOwner().getUsername()}</span>
              <span><i class="glyphicon glyphicon-tags"></i>${result.getTags()}</span>
          </div>
          
        </div>       
    </c:forEach>
       
</div>
