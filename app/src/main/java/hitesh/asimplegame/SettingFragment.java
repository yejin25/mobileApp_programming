package hitesh.asimplegame;

import android.app.Activity;
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
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.annotation.Native;

public class SettingFragment extends PreferenceFragment {
    private SharedPreferences pref;
    private SwitchPreference switchPreference;
    private Preference btnLogout, btnRevoke;

    private static boolean isBtnBGMStatus = false;
    private static boolean isFirstRun = true;

    public SettingFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);

        switchPreference = (SwitchPreference)findPreference("bgm");
        btnLogout = (Preference)findPreference("resetBD");
        btnRevoke = (Preference)findPreference("revokeBD");

//        switchPreference.setChecked(true);
        final Intent bgmIntent = new Intent(getActivity(), Bgm.class);
        Log.d("SettingFragment", String.valueOf(isFirstRun));

        if (isFirstRun) {
            if (switchPreference.isChecked()) {
                bgmIntent.putExtra(Bgm.MESSAGE_KEY, true);
                getActivity().startService(bgmIntent);
            } else {
                bgmIntent.putExtra(Bgm.MESSAGE_KEY, false);
                getActivity().startService(bgmIntent);
            }

            isFirstRun = false;
        }
//        switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//                if (switchPreference.isChecked()) {
//                bgmIntent.putExtra(Bgm.MESSAGE_KEY, false);
//                getActivity().startService(bgmIntent);
//           } else {
//                    bgmIntent.putExtra(Bgm.MESSAGE_KEY, true);
//                    getActivity().startService(bgmIntent);
//                }
//                return false;
//            }
//        });

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

//        switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//                if (switchPreference.isChecked()) {
//                    Log.d("SettingFragment", "----- 1 Called!");
//                    bgmIntent.putExtra(Bgm.MESSAGE_KEY, true);
//                    getActivity().startService(bgmIntent);
//                    return true;
//                } else {
//                    Log.d("SettingFragment", "----- 2 Called!");
//                    bgmIntent.putExtra(Bgm.MESSAGE_KEY, false);
//                    getActivity().startService(bgmIntent);
//                    return false;
//                }
//            }
//        });

        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    public void onResume(){
        super.onResume();
    }


    public static boolean getBgmStatus() {
        return isBtnBGMStatus;
    }
}
