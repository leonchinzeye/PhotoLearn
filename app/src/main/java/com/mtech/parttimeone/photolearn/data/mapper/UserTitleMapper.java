package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.data.entity.UserTitleEntity;
import com.mtech.parttimeone.photolearn.data.entity.UserTypeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 25/3/2018.
 */

public class UserTitleMapper extends FirebaseMapper<UserTitleEntity, UserTitleEntity> {
    @Override
    public UserTitleEntity map(UserTitleEntity UserTitleEntity) {
        List<UserTypeEntity> trainerlist = new ArrayList();
        List<UserTypeEntity> participantlist = new ArrayList();
        UserTitleEntity usertitle = new UserTitleEntity(trainerlist, participantlist);
        return usertitle;
    }
}
