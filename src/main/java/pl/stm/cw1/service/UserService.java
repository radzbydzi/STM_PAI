package pl.stm.cw1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.stm.cw1.model.Task;
import pl.stm.cw1.model.User;
import pl.stm.cw1.repo.TaskRepo;
import pl.stm.cw1.repo.UserRepo;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	@Autowired
	TaskRepo taskRepo;
	// metoda do tworzenia nowego użytkownika
	public User addUser(User user) {
        return userRepo.save(user);
    }
	
	// metoda zwracająca wszystkich użytkowników
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	// metoda zwracająca użytkownika wyszukanego na podstawie klucza głównego lub adresu email
	public Optional<User> getUserByIdOrEmail(String value){
        if(value.matches("[0-9]+")){
            return userRepo.findById(Long.parseLong(value));
        } else {
            return userRepo.findByEmail(value);
        }
    }
	
	//metoda zmieniająca status użytkownika na wartość przeciwną względem aktualnej
	public boolean changeUserStatus(Long id)
	{
		Optional<User> usr = userRepo.findById(id);
		if(usr.isPresent())
		{
			usr.get().setStatus(!usr.get().isStatus());
			userRepo.save(usr.get());
			return true;
		}
		return false;
	}
	
	//metoda usuwająca użytkownika wraz z jego relacjami
	public boolean deleteUserByIdWithRelations(Long id)
	{
		Optional<User> usr = userRepo.findById(id);
		if(usr.isPresent())
		{
			for(Task t: usr.get().getTasks())
			{
				taskRepo.delete(t);
			}
			userRepo.delete(usr.get());
			return true;
		}
		return false;
	}
}
