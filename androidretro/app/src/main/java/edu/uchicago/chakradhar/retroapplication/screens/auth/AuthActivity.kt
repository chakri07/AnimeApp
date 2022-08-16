package edu.uchicago.chakradhar.retroapplication.screens.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.amazonaws.mobile.client.*
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import edu.uchicago.chakradhar.retroapplication.Cache
import edu.uchicago.chakradhar.retroapplication.MainActivity
import edu.uchicago.chakradhar.retroapplication.R
import edu.uchicago.chakradhar.retroapplication.screens.auth.models.Token
import edu.uchicago.chakradhar.retroapplication.utils.JWTUtils


@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    private val TAG = AuthActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //if use_auth is set to false, then short circuit
        val strUseAuth = resources.getString(R.string.use_auth)

        if (strUseAuth.toBoolean()) {
            Cache.getInstance().userEmail = DEFAULT_COM
            val intent = Intent(this@AuthActivity, MainActivity::class.java)
            startActivity(intent)
        } else {
            AWSMobileClient.getInstance()
                .initialize(applicationContext, object : Callback<UserStateDetails> {
                    override fun onResult(userStateDetails: UserStateDetails) {
                        Log.d(TAG, userStateDetails.userState.toString())
                        when (userStateDetails.userState) {
                            UserState.SIGNED_IN -> {
                                val strCodedToken = userStateDetails.details["token"]
                                setEmail(strCodedToken)
                                val intent = Intent(this@AuthActivity, MainActivity::class.java)
                                startActivity(intent)
                            }
                            UserState.SIGNED_OUT -> showSignIn()
                            else -> {
                                AWSMobileClient.getInstance().signOut()
                                showSignIn()
                            }
                        }
                    }

                    override fun onError(e: Exception) {
                        Log.e(TAG, e.toString())
                    }
                })
        }
    }

    private fun setEmail(codedToken: String?) {
        var decoded = ""
        try {
            decoded = JWTUtils.decodedBody(codedToken)
            val gson = Gson()
            val token: Token = gson.fromJson(decoded, Token::class.java)
            Cache.getInstance().setUserEmail(token.getEmail())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showSignIn() {
        try {
            AWSMobileClient.getInstance().showSignIn(this,
                SignInUIOptions.builder()
                    .nextActivity(MainActivity::class.java)
                    .build(),
                object : Callback<UserStateDetails> {
                    override fun onResult(result: UserStateDetails) {
                        Log.d(TAG, "showSignIn() onResult() result: userState: " + result.userState)
                        when (result.userState) {
                            UserState.SIGNED_IN -> {
                                Log.d(TAG, "showSignIn() callback: SIGNED_IN logged in!")
                                setEmail(result.details["token"])
                            }
                            UserState.SIGNED_OUT -> Log.d(TAG,
                                "showSignIn() callback onResult: SIGNED_OUT ")
                            else -> Log.d(TAG,
                                "showSignIn() callback onResult: default; Should not be possible.")
                        }
                    }

                    override fun onError(e: Exception) {
                        Log.e(TAG, "showSignIn().onError: ", e)
                    }
                }
            )
        } catch (e: Exception) {
            Log.e("AuthActivity", e.toString())
        }
    }

    companion object {
        //used as the default email in the event authentication is disabled
        const val DEFAULT_COM = "default@default.com"
    }
}