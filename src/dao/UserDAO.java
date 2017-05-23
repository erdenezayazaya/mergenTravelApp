package dao;

import java.util.List;

import entity.User;

public interface UserDAO {
	public void addUser(User user);
	public void deleteUser(int idUsers);
	public void updateUser(User user);
	public List<User> getAllUser();
	public User getUserByEmail(String email);
}
