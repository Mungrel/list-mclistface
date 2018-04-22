package kres.realtimeshoppinglist.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class PersistenceManager {

    private static final String ID_PREFS = "host_id_prefs";
    private static final String KNOWN_LISTS_SHARED_PREFERENCES_KEY = "KNOWN_LISTS";
    private static PersistenceManager instance;

    private SharedPreferences sharedPreferences;

    private PersistenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PersistenceManager.ID_PREFS, 0);
    }

    public static PersistenceManager getInstance(Context context) {
        if (instance == null) {
            instance = new PersistenceManager(context);
        }

        return instance;
    }

    public void persistKnownID(String listID) {
        Set<String> knownIDs = retrieveKnownIDs();
        knownIDs.add(listID);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(KNOWN_LISTS_SHARED_PREFERENCES_KEY, knownIDs);

        editor.apply();
    }

    public Set<String> retrieveKnownIDs() {
        return sharedPreferences.getStringSet(KNOWN_LISTS_SHARED_PREFERENCES_KEY, new HashSet<String>());
    }
}
