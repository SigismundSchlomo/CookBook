package com.example.cookbook.data

import com.example.cookbook.domain.Token
import com.example.cookbook.domain.User
import com.example.cookbook.domain.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDataSource: UserDataSourceImpl) :
    UserRepository {

    private var _user: User? = userDataSource.getUser()

    override fun getUser(): User {
        _user = userDataSource.getUser()
        return _user!!
    }

    override fun saveUser(user: User) {
        _user = user
        userDataSource.saveUser(user)
    }

    override fun deleteUser() {
        _user = null
        userDataSource.deleteUser()
    }

    override fun getToken(): Token {
        return _user!!.token
    }
}