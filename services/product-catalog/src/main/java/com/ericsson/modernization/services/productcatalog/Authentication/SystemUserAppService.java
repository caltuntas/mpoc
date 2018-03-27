package com.ericsson.modernization.services.productcatalog.Authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserAppService {
	
    @Autowired
    private SystemUserRepository userRepository;
    
    public SystemUser findById(int id){
        return userRepository.findById(id).get();
    }

    public List<SystemUser> findAll(){
        return userRepository.findAll();
    }
//	List<User> users = new ArrayList<>(Arrays.asList(
//			new User("1", "Seyfullah", "123", "Seyfullah", "Tıkıç"),
//			new User("2", "Cihat", "123", "Cihat", "Altuntaş"),
//			new User("3", "Turgay", "123", "Turgay", "Yalçıntürk")
//			));
//
//	public List<User> getAllUsers() {
//		return users;
//	}
//
//	public User getUser(String id) {
//		return users.stream().filter(t -> t.getId().equals(id)).findFirst().get();
//	}
//
//	public void addUser(User topic) {
//		users.add(topic);
//	}
//
//	public void updateUser(String id, User topic) {
//		for (int i=0; i < users.size(); i++) {
//			User t = users.get(i);
//			if (t.getId().equals(id)) {
//				users.set(i,  topic);
//				return;
//			}
//		}
//	}
//
//	public void deleteUser(String id) {
//		users.removeIf(t -> t.getId().equals(id));
//	}

	public SystemUser findByUserNameAndPassword(String username, String password) {
		return userRepository.findByUserNameAndPassword(username, password);
	}
}
