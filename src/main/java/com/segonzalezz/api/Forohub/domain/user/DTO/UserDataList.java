package com.segonzalezz.api.Forohub.domain.user.DTO;

import com.segonzalezz.api.Forohub.domain.user.Profile;
import com.segonzalezz.api.Forohub.domain.user.User;

import java.util.List;

public record UserDataList(Long id, String name, String email, String password, List<Profile> profiles) {
    public UserDataList(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getProfiles());
    }
}
