package com.example.cookbook.data

import android.content.Context
import com.example.cookbook.domain.UserDataSource
import com.example.cookbook.domain.models.Token
import com.example.cookbook.domain.models.User
import java.util.*
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(private val context: Context) : UserDataSource {

    private val SHARED_PREFS_NAME = "UserSharedPrefs"
    private val ID_KEY = "id"
    private val EMAIL_KEY = "email"
    private val NAME_KEY = "name"
    private val TOKEN_VALUE_KEY = "token"
    private val TOKEN_EXPIRE_DATA_KEY = "expire"

    private val sharedPrefs by lazy {
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

    override fun getUser(): User? {
        val id = sharedPrefs.getInt(ID_KEY, -1)
        val email = sharedPrefs.getString(EMAIL_KEY, "")!!
        val name = sharedPrefs.getString(NAME_KEY, "")!!
        val tokenValue = sharedPrefs.getString(TOKEN_VALUE_KEY, "")!!
        val tokenExpireTime = sharedPrefs.getLong(TOKEN_EXPIRE_DATA_KEY, 0L)


        var user: User? = null
        if (isNotEmpty(id, email, name, tokenValue, tokenExpireTime)) {
            val tokenExpireDate = Date(tokenExpireTime)
            val token = Token(
                tokenValue,
                tokenExpireDate
            )
            user =
                User(id, email, name, token)
        }

        return user
    }

    override fun saveUser(user: User) {
        with(sharedPrefs.edit()) {
            putInt(ID_KEY, user.id)
            putString(EMAIL_KEY, user.email)
            putString(NAME_KEY, user.name)
            putString(TOKEN_VALUE_KEY, user.token.value)
            putLong(TOKEN_EXPIRE_DATA_KEY, user.token.expireDate.time)
            commit()
        }
    }

    override fun deleteUser() {
        with(sharedPrefs.edit()) {
            putInt(ID_KEY, -1)
            putString(EMAIL_KEY, "")
            putString(NAME_KEY, "")
            putString(TOKEN_VALUE_KEY, "")
            putLong(TOKEN_EXPIRE_DATA_KEY, 0L)
            commit()
        }
    }

    private fun isNotEmpty(
        id: Int,
        email: String,
        name: String,
        tokenValue: String,
        tokenExpireTime: Long
    ): Boolean {
        return (id != -1
                && email.isNotEmpty()
                && name.isNotEmpty()
                && tokenValue.isNotEmpty()
                && tokenExpireTime > 0)
    }

}