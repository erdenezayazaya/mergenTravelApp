package entity;

public class Comment {
	private int idcomments;
	private User user;
	private Post post;
	private String comment;
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int idcomments, User user, Post post, String comment) {
		super();
		this.idcomments = idcomments;
		this.user = user;
		this.post = post;
		this.comment = comment;
	}

	public int getIdcomments() {
		return idcomments;
	}

	public void setIdcomments(int idcomments) {
		this.idcomments = idcomments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}	
}
