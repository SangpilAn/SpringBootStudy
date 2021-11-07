package com.sangpil.profile.presistance;

import com.sangpil.profile.domain.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
