<%-- 
    Document   : Debate
    Created on : 09.03.2016, 15:37:22
    Author     : fabi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html> 

<div class="container">
    <div class="row">
        <div class="col-md-12 titleHeader">
            ${requestScope.debate.getTopic()}
        </div>

    </div>
    <div class="row">
        <div class="genInfos col-md-12">
            <c:forEach items="${requestScope.debate.getTagList()}" var="tag" >
                <span class="tags">${tag}</span>
            </c:forEach> 

        </div>
    </div>

    <div class="row">
        <div class="mainInfos col-md-12">
            <div class="modal-header">
                Main Infos
            </div>
            <div class="modal-body">

                <div class="row">
                    <div class="col-md-5">
                        <i class="glyphicon glyphicon-user"></i> ${requestScope.debate.getOwner().getUsername()}</br>
                        <i class="glyphicon glyphicon-calendar"></i> ${requestScope.debate.getCreationDate()}</br>
                        <i class="glyphicon glyphicon-eye-open"></i> ${requestScope.debate.getClicks()}
                    </div>
                    <div class="col-md-offset-1 col-md-6">
                        <i class="glyphicon glyphicon-edit"> </i> ${requestScope.debate.getDescription()}
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="modal-header">
                Discussion <a data-toggle="collapse" href="#addComment"> <i class="glyphicon glyphicon-comment" ></i></a>
                <c:if test="${debate.isOpen == true}">

                    <div id="addComment" class="collapse row addComment">

                        <div class="input-group col-md-12">
                            <input id="commentInput" type="text" class="form-control" placeholder="new comment..">
                            <span class="input-group-btn">
                                <button id="commentInputBtn" class="btn btn-default" value="create" type="button"><i class="glyphicon glyphicon-send"></i></button>
                            </span>
                        </div>


                    </div>
                </c:if>
            </div>
            <div class="commentBox">
                <div id="comments" class="modal-body ">

                    <jsp:include page="Comments.jsp"></jsp:include>

                </div>
            </div>

        </div>

    </div>


</div>


