package it.polito.ezgas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.UserService;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class UserServiceImpl implements UserService {
	
	// Repository of users
	@Autowired
	private UserRepository userRepository;
	
	// Constructor for integration tests
	public UserServiceImpl(UserRepository userRepo){
		this.userRepository = userRepo;
	}
	
	@Override
	public UserDto getUserById(Integer userId) throws InvalidUserException {
		
		User user;
		
		// Check if user is not valid
		if(userId == null || userId<0)
			throw new InvalidUserException("ERROR: USER ID ISN'T VALID!");
		
		// Search for user into the repository
		user = userRepository.findByUserId(userId);
		if(user == null)
			return null;
		
		return UserConverter.toUserDto(user);
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		
		User user=UserConverter.toUser(userDto);
		user=userRepository.save(user);
		
		return UserConverter.toUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		ArrayList<UserDto> list= new ArrayList<UserDto>();
		
		// Retrieve all users from repository
		List<User> listUser = userRepository.findAll();
		if(listUser==null)
			return list;
		listUser.forEach((user)->list.add(UserConverter.toUserDto(user)));
		
		return list;
	}

	@Override
	public Boolean deleteUser(Integer userId) throws InvalidUserException {
		
		if(userId==null || userId<0)
			throw new InvalidUserException("ERROR:USER ID ISN'T VALID!");
		if(!userRepository.exists(userId))
			return false;
		userRepository.delete(userId);
		if(userRepository.exists(userId))
			return false;
		
		return true;
	}

	@Override
	public LoginDto login(IdPw credentials) throws InvalidLoginDataException {
		
		// Search user by email
		User user=userRepository.findByEmail(credentials.getUser());
		if(user==null)
			throw new InvalidLoginDataException("WRONG EMAIL");
		
		// Check if pwd is correct
		if(!user.getPassword().equals(credentials.getPw()))
			throw new InvalidLoginDataException("WRONG PASSWORD");
		
		return UserConverter.toLoginDto(user);
	}

	@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		
		Integer newreputation;
		if(userId==null || userId<0)
			throw new InvalidUserException("ERROR: USER ID ISN'T VALID!");
		
		// Search user into the repository
		User user=userRepository.findByUserId(userId);
		if(user==null)
			return null;
		
		// Increase reputation if is not already the max
		if(user.getReputation() < 5) {
			newreputation=user.getReputation()+1;
			user.setReputation(newreputation);
			userRepository.save(user);
		}
		
		return user.getReputation();
	}

	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		
		Integer newreputation;
		
		// Check if user is valid
		if(userId==null || userId<0)
			throw new InvalidUserException("ERROR: USER ID ISN'T VALID!");
		
		// Search user into the repository
		User user= userRepository.findByUserId(userId);
		if(user==null)
			return null;
		
		// Decrease reputation if is not already the min
		if(user.getReputation() > -5) {
			newreputation= user.getReputation()-1;
			user.setReputation(newreputation);
			userRepository.save(user);
		}
		
		return user.getReputation();
	}
	
}
