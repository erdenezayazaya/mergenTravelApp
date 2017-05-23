package dao;

import java.util.List;

import entity.Comment;

public interface CommentDAO {
	public void addComment(Comment comment);
	public void deleteComment(int idComments);
	public void updateComment(Comment comment);
	public List<Comment> getAllComment();
	public List<Comment> getCommentByUserId(int idComments);
}
