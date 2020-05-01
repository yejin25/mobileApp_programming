package hitesh.asimplegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.annotation.Native;

public class SettingFragment extends PreferenceFragment {
    private SharedPreferences pref;
    private SwitchPreference switchPreference;
    private Preference btnLogout, btnRevoke;

    public SettingFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);

        switchPreference = (SwitchPreference)findPreference("bgm");
        btnLogout = (Preference)findPreference("resetBD");
        btnRevoke = (Preference)findPreference("revokeBD");

        btnLogout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                FirebaseAuth.getInstance().signOut();
                LoginData.mGoogleSignInClient.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

                return true;
            }
        });

        btnRevoke.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                LoginData.firebaseAuth.getCurrentUser().delete();
                FirebaseAuth.getInstance().signOut();
                LoginData.mGoogleSignInClient.revokeAccess();
                LoginData.mGoogleSignInClient.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

                return true;
            }
        });

        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());

//        boolean isBGM = pref.getBoolean("bgm", false);
//        Toast.makeText(getActivity(),"배경음악"+isBGM, Toast.LENGTH_SHORT).show();

    }


    public void onResume(){
        super.onResume();

    }



}
