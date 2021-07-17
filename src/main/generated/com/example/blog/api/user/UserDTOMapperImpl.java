package com.example.blog.api.user;

import com.example.blog.domain.user.User;
import com.example.blog.service.requestDTO.RequestUserRegister;
import com.example.blog.service.responseDTO.UserData;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-18T05:12:49+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (Oracle Corporation)"
)
@Component
public class UserDTOMapperImpl implements UserDTOMapper {

    @Override
    public UserData entityToUserData(User user) {
        if ( user == null ) {
            return null;
        }

        UserData userData = new UserData();

        userData.setId( user.getId() );
        userData.setUsername( user.getUsername() );
        userData.setEmail( user.getEmail() );
        userData.setBio( user.getBio() );
        userData.setImage( user.getImage() );

        return userData;
    }

    @Override
    public User dtoToUser(RequestUserRegister requestUserRegister) {
        if ( requestUserRegister == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( requestUserRegister.getEmail() );
        user.setUsername( requestUserRegister.getUsername() );
        user.setPassword( requestUserRegister.getPassword() );

        user.setId( UUID.randomUUID().toString() );
        user.setImage( "${default.image}" );
        user.setBio( "" );

        return user;
    }
}
