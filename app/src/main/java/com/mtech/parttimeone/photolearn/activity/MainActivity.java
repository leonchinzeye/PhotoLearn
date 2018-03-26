package com.mtech.parttimeone.photolearn.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.ViewModel.AccountViewModel;
import com.mtech.parttimeone.photolearn.ViewModel.LearningSessionViewModel;
import com.mtech.parttimeone.photolearn.application.GlobalPhotoLearn;
import com.mtech.parttimeone.photolearn.data.entity.AccountEntity;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private CallbackManager mCallbackManager;

    private static final String TAG = "PhotoLearn";

    private static final int RESULT_CODE_SIGN_IN = 1981;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate call!");

        findViewById(R.id.sign_in_button).setOnClickListener(this);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);

        mAuth = FirebaseAuth.getInstance();

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.button_facebook_login);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart call!");
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

        if(currentUser == null){
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.button_facebook_login).setVisibility(View.VISIBLE);
        }else{
            Log.d(TAG, "onStart uesr id:"+currentUser.getUid()+"::display name:"+currentUser.getDisplayName());
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.button_facebook_login).setVisibility(View.GONE);
            updateUI(currentUser);
        }
    }

    public void onClick(View view){
        int i = view.getId();

        if (i == R.id.sign_in_button) {

            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent,RESULT_CODE_SIGN_IN);

        }
    }
    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_CODE_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuthWithGoogle(account);

            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
                updateUI(null);
            }
        }
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();

                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                                findViewById(R.id.button_facebook_login).setVisibility(View.GONE);

                                Toast.makeText(MainActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                Toast.makeText(MainActivity.this, "Facebook Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                });
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }

                    }
                });
    }

    private void updateUI(FirebaseUser user) {

        if (user != null) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.button_facebook_login).setVisibility(View.GONE);

            AccountEntity UserAccount = new AccountEntity(user.getUid(),user.getDisplayName(),user.getEmail(),"");
            AccountViewModel model = ViewModelProviders.of(this).get(AccountViewModel.class);
            LearningSessionViewModel lModel = ViewModelProviders.of(this).get(LearningSessionViewModel.class);

            model.signIn(user);

//            model.setAccount(UserAccount);

            Log.d(TAG, "signInWithCredential:success:Display Name:"+user.getDisplayName()+":Email:"
                    +user.getEmail()+":Uid:"+user.getUid());

            GlobalPhotoLearn globalPhotoLearn = (GlobalPhotoLearn)getApplicationContext();
            globalPhotoLearn.setmAuth(mAuth);
            globalPhotoLearn.setmGoogleSignInClient(mGoogleSignInClient);

            Intent learningSessionIntent = new Intent(this,BottomBarActivity.class);
            startActivity(learningSessionIntent);
        } else {

            Toast.makeText(MainActivity.this, "No user!",
                    Toast.LENGTH_SHORT).show();
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.button_facebook_login).setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onStop() {
        super.onStop();

    }

}
