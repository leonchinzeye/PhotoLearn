package com.mtech.parttimeone.photolearn.data.repository;

import com.mtech.parttimeone.photolearn.data.entity.UserTitleEntity;
import com.mtech.parttimeone.photolearn.data.mapper.UserTitleMapper;

/**
 * Created by karen on 25/3/2018.
 */

public class UserTitleRepository extends FirebaseDatabaseRepository<UserTitleEntity> {
    public UserTitleRepository() { super (new UserTitleMapper()); }

    @Override
    public String getRootNode() {
        return "user_titles";
    }
}
