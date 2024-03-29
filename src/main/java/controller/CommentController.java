/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Comment;
import model.CommentSessionBean;
import entity.DebateUser;
import entity.Rating;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author D062575
 */
@WebServlet(name = "CommentController", urlPatterns = {"/servlets/CommentController"})
public class CommentController extends HttpServlet {

    public static final String URL_PATTERN = "/servlets/CommentController";
    public static final String CONTEXT_NAME = "comment";
    //commands
    private static final String COMMAND = "command";
    private static final String COMMAND_CREATE = "create";
    private static final String COMMAND_READ = "read";
    private static final String COMMAND_RATE = "rate";
    private static final String COMMAND_GET_RATERS = "getRaters";

    private static final String ATTR_DEBID = "debateId";
    private static final String ATTR_COMMENTID = "comId";
    private static final String ATTR_COMMTEXT = "commentText";
    private static final String ATTR_PARENTCOMMENTID = "commentParentId";
    private static final String ATTR_RATING = "rating";

    private static final String ATTR_USER = "user";
    private static final String ATTR_DEBATE = "debate";

    @EJB
    private CommentSessionBean commentBean;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try {

            String name = (String) request.getParameter(COMMAND);
            Long parentCommentId = null;

            if (name != null) {

                switch (name) {
                    case COMMAND_CREATE:
                        
                        Long debateId = Long.parseLong(request.getParameter(ATTR_DEBID));
                        String commentText = request.getParameter(ATTR_COMMTEXT);
                        String parentId = request.getParameter(ATTR_PARENTCOMMENTID);
                       
                        
                        Comment c = commentBean.createComment((DebateUser) request.getSession().getAttribute(ATTR_USER), 
                                                    debateId,
                                                    commentText, 
                                                    parentId == null? null : Long.parseLong(parentId));
                        
                        List<Comment> comments = new ArrayList();
                        comments.add(c);
                        request.setAttribute("comments", comments);
                        getServletContext().getRequestDispatcher(FrontController.PAGES_PREFIX + "/Comments.jsp").forward(request, response);
                        
                    case COMMAND_GET_RATERS:
                        
                        Long commentId = Long.parseLong(request.getParameter(ATTR_COMMENTID));
                        request.setAttribute("raters", commentBean.getRatings(commentId));
                        getServletContext().getRequestDispatcher(FrontController.PAGES_PREFIX + "/Raters.jsp").forward(request, response);
                        
                        break;
                    case COMMAND_RATE:
                        
                        Rating.RatingValue value;
                        
                        switch(request.getParameter(ATTR_RATING)){
                        
                            case "positive":
                                value = Rating.RatingValue.POSITIVE; break;
                            case "negative":
                                value = Rating.RatingValue.NEGATIVE; break;
                            default:
                                throw new IllegalArgumentException();
                        }

                        long newRating = commentBean.rateComment(Long.parseLong(request.getParameter(ATTR_COMMENTID)),
                                                (DebateUser) request.getSession().getAttribute(ATTR_USER),
                                                value);
                        
                    default:
                        break;

                }
            }
//            response.sendRedirect(FrontController.FRONT_PATH);
        }
        catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);

        }
    }



}
