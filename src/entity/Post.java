package entity;

import java.util.ArrayList;
import java.util.List;

public class Post {
	private int idposts;
	private User user;
	private List<Comment> listComments;
	private int like;
	private String post;
	private String imageName;
	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(int idposts, User user, int like, String post, String imageName) {
		super();
		
		listComments = new ArrayList<>();
		this.idposts = idposts;
		this.user = user;		
		this.like = like;
		this.post = post;
		this.imageName = imageName;
	}

	public void addComment(Comment comment)
	{
		listComments.add(comment);
	}
	
	public int getIdposts() {
		return idposts;
	}
	public void setIdposts(int idposts) {
		this.idposts = idposts;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Comment> getListComments() {
		return listComments;
	}
	
	public void setListComments(List<Comment> listComments) {
		this.listComments = listComments;
	}
	
	public int getLike() {
		return like;
	}
	
	public void setLike(int like) {
		this.like = like;
	}
	
	public String getPost() {
		return post;
	}
	
	public void setPost(String post) {
		this.post = post;
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
}
