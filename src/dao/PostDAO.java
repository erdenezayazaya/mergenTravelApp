package dao;

import java.util.List;

import entity.Comment;
import entity.Post;

public interface PostDAO {
	public void addPost(Post post);
	public void deletePost(int idPosts);
	public void updatePost(Post post);
	public List<Post> getAllPost();
	public List<Post> getPostByUser(int idusers);
	public void likeAdd(int idPosts, int likeCount);
	public List<Comment> getCommentByPosID(int idPosts);
	public int getPostCount();
}
