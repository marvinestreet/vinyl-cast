package tech.schober.vinylcast;

import androidx.preference.PreferenceManager;

import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.SessionManager;

import tech.schober.vinylcast.audio.NativeAudioEngine;

public class VinylCastApplication extends VinylCastApplicationBase {

    private SessionManager castSessionManager;

    @Override
    public void onCreate() {
        super.onCreate();

        // This static call will reset default values only on the first ever read. Also according
        // to StrictMode, it's slow due to disk reads on Main Thread.
        PreferenceManager.setDefaultValues(getBaseContext(), R.xml.preferences, false);

        // get Cast session manager here in Application as according to StrictMode it's slow due to
        // performing disk reads on Main Thread and only needs to be fetched once anyway.
        castSessionManager = CastContext.getSharedInstance(this).getSessionManager();

        NativeAudioEngine.create();
    }

    public SessionManager getCastSessionManager() {
        return castSessionManager;
    }
}
