package com.example.cookbook.data

import com.example.cookbook.domain.Token
import com.example.cookbook.domain.User
import com.example.cookbook.domain.UserRepository
import java.util.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDataSource: UserDataSourceImpl) :
    UserRepository {

    private var _user: User? = userDataSource.getUser()

    override fun getUser(): User {
        if (_user == null) {
            _user = userDataSource.getUser()
        }
        return _user!!
    }

    override fun saveUser(user: User) {
        _user = user
        return userDataSource.saveUser(user)
    }

    override fun deleteUser() {
        _user = null
        userDataSource.deleteUser()
    }

    override fun getToken(): Token {
        return if (_user != null) {
            _user!!.token
        } else {
            Token("", Date(0L))
        }
    }
}