package com.koch.kotlin.kochkotlin.android.repositories

import com.koch.kotlin.kochkotlin.domain.model.PROFILE_ID
import com.koch.kotlin.kochkotlin.domain.model.User
import com.koch.kotlin.kochkotlin.domain.repositories.UserRepository
import io.objectbox.Box

class UserRepositoryAndroid ( private val userBox: Box<User> ) : UserRepository {

    override var profile: User?
        get() = userBox.get(PROFILE_ID)

        set(profile){
            profile?.let {
                userBox.put(profile)
            } ?: userBox.remove(PROFILE_ID)
        }
}
