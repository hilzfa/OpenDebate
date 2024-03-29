<%-- 
    Document   : Comments
    Created on : 16.03.2016, 15:58:25
    Author     : fabi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<c:if test="${comments.isEmpty()}">
    <span class="commentsInfo">No comments so far. Be the first contributor!</span> 
</c:if>
<c:forEach items="${comments}" var="comment">
    <div id="${comment.id}" class="comment">
        <div class="commentHeader">
            <div id="commentUser">${comment.getOwner().getUsername()}</div>
            <div id="commentDate">${comment.creationDate}</div>
        </div>
        <div class="commentBody">${comment.commentText}</div>
        <div class="commentFooter">
            <c:if test="${comment.getDebate().isOpen == true}">
                <a id="addNewChildComment" data-toggle="collapse" role="button" href="#${comment.id}_collapse" aria-expanded="true" >Answer</a>
            </c:if>
            <div id="rating${comment.id}" class="ratingTool" >
                <span>
                    <i class="glyphicon glyphicon-chevron-down downVote"></i>                    
                    <span class="counter" data-toggle="popover" title="Raters" data-content="" data-placement="left">

                        ${comment.rating}                    
                    </span>
                    <i class="glyphicon glyphicon-chevron-up upVote"></i>
                </span>
            </div>
        </div>
    </div>
    <div class="addCommentChild collapse" id="${comment.id}_collapse">
        <input id="${comment.id}_input" type="text" class="form-control" placeholder="add a comment.."/>
    </div>
    <c:forEach items="${comment.getChildren()}" var="childComment">
        <div id="${childComment.id}" class="commentChild">
            <div  class="commentHeader">
                <div id="commentUser">${childComment.getOwner().getUsername()}</div>
                <div id="commentDate">${childComment.creationDate}</div>
            </div>
            <div class="commentBody">${childComment.commentText}</div>
            <div class="commentFooter">               
                <div class="ratingTool">
                    <span>
                        <i class="glyphicon glyphicon-chevron-down downVote"></i>
                        <span class="counter" data-toggle="popover" title="Raters" data-content="" data-placement="left"> 
                            ${childComment.rating}
                        </span>
                        <i class="glyphicon glyphicon-chevron-up upVote"></i>
                    </span>
                </div>
            </div>

        </div>
    </c:forEach>



</c:forEach>
