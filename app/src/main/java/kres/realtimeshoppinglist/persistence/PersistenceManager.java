package kres.realtimeshoppinglist.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

public class PersistenceManager {

    private static final String ID_PREFS = "shopping_list_id_prefs";
    private static final String KNOWN_LISTS_SHARED_PREFERENCES_KEY = "KNOWN_LISTS";
    private static PersistenceManager instance;

    private SharedPreferences sharedPreferences;

    private PersistenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PersistenceManager.ID_PREFS, Context.MODE_PRIVATE);
    }

    public static PersistenceManager getInstance(Context context) {
        if (instance == null) {
            instance = new PersistenceManager(context);
        }

        return instance;
    }

    public void persistKnownID(String listID) {
        Log.d("PERSISTENCE_MANAGER", "Persisting ID: " + listID);
        Set<String> knownIDs = retrieveKnownIDs();
        knownIDs.add(listID);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(KNOWN_LISTS_SHARED_PREFERENCES_KEY, knownIDs);

        editor.apply();
        editor.commit();
    }

    public void removeKnownID(String listID) {
        Log.d("PERSISTENCE_MANAGER", "Removing ID: " + listID);
        Set<String> knownIDs = retrieveKnownIDs();
        knownIDs.remove(listID);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(KNOWN_LISTS_SHARED_PREFERENCES_KEY, knownIDs);

        editor.apply();
        editor.commit();
    }

    public Set<String> retrieveKnownIDs() {
        // Turns out you need to copy the set you get back into a new HashSet, otherwise shit breaks
        return new HashSet<>(sharedPreferences.getStringSet(KNOWN_LISTS_SHARED_PREFERENCES_KEY, new HashSet<String>()));
    }
}
