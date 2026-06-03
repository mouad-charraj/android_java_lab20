package com.example.lab20;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity_mouad";
    private Button btnLoadContacts_mouad, btnLoadFromServer_mouad, btnSyncContacts_mouad, btnSearch_mouad;
    private EditText etKeyword_mouad;
    private RecyclerView recyclerViewContacts_mouad;
    private ContactAdapter_mouad adapter_mouad;
    private final List<Contact_mouad> contactList_mouad = new ArrayList<>();
    private ContactApi_mouad contactApi_mouad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoadContacts_mouad = findViewById(R.id.btnLoadContacts_mouad);
        btnLoadFromServer_mouad = findViewById(R.id.btnLoadFromServer_mouad);
        btnSyncContacts_mouad = findViewById(R.id.btnSyncContacts_mouad);
        btnSearch_mouad = findViewById(R.id.btnSearch_mouad);
        etKeyword_mouad = findViewById(R.id.etKeyword_mouad);
        recyclerViewContacts_mouad = findViewById(R.id.recyclerViewContacts_mouad);

        recyclerViewContacts_mouad.setLayoutManager(new LinearLayoutManager(this));
        adapter_mouad = new ContactAdapter_mouad(contactList_mouad);
        recyclerViewContacts_mouad.setAdapter(adapter_mouad);

        contactApi_mouad = RetrofitClient_mouad.getClient_mouad().create(ContactApi_mouad.class);

        btnLoadContacts_mouad.setOnClickListener(v -> checkPermissionAndLoadContacts_mouad());
        btnLoadFromServer_mouad.setOnClickListener(v -> loadFromServer_mouad());
        btnSyncContacts_mouad.setOnClickListener(v -> syncContactsToServer_mouad());
        btnSearch_mouad.setOnClickListener(v -> searchContacts_mouad());
    }

    private void checkPermissionAndLoadContacts_mouad() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            loadContacts_mouad();
        } else {
            requestPermissionLauncher_mouad.launch(Manifest.permission.READ_CONTACTS);
        }
    }

    private final androidx.activity.result.ActivityResultLauncher<String> requestPermissionLauncher_mouad =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    loadContacts_mouad();
                } else {
                    Toast.makeText(this, "Permission lecture contacts refusee", Toast.LENGTH_SHORT).show();
                }
            });

    private void loadContacts_mouad() {
        contactList_mouad.clear();

        try (Cursor cursor_mouad = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER},
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )) {

            if (cursor_mouad != null && cursor_mouad.getCount() > 0) {
                int nameIdx = cursor_mouad.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int numberIdx = cursor_mouad.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                while (cursor_mouad.moveToNext()) {
                    String name = (nameIdx != -1) ? cursor_mouad.getString(nameIdx) : "Inconnu";
                    String phone = (numberIdx != -1) ? cursor_mouad.getString(numberIdx) : "N/A";
                    contactList_mouad.add(new Contact_mouad(name, phone));
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Erreur lecture contacts", e);
            Toast.makeText(this, "Erreur lecture contacts : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        adapter_mouad.updateData_mouad(contactList_mouad);

        if (contactList_mouad.isEmpty()) {
            Toast.makeText(this, "Aucun contact trouve sur le telephone", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Contacts charges : " + contactList_mouad.size(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadFromServer_mouad() {
        contactApi_mouad.getAllContacts_mouad().enqueue(new Callback<List<Contact_mouad>>() {
            @Override
            public void onResponse(@NonNull Call<List<Contact_mouad>> call, @NonNull Response<List<Contact_mouad>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    contactList_mouad.clear();
                    contactList_mouad.addAll(response.body());
                    adapter_mouad.updateData_mouad(contactList_mouad);
                    Toast.makeText(MainActivity.this, "Contacts serveur : " + contactList_mouad.size(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Erreur serveur : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Contact_mouad>> call, @NonNull Throwable t) {
                Log.e(TAG, "Erreur reseau getAll", t);
                Toast.makeText(MainActivity.this, "Erreur reseau : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void syncContactsToServer_mouad() {
        if (contactList_mouad.isEmpty()) {
            Toast.makeText(this, "La liste est vide. Chargez les contacts du telephone d'abord.", Toast.LENGTH_SHORT).show();
            return;
        }

        final int total_mouad = contactList_mouad.size();
        final int[] processed_mouad = {0};
        final int[] success_count_mouad = {0};

        Toast.makeText(this, "Synchronisation de " + total_mouad + " contacts...", Toast.LENGTH_SHORT).show();

        for (Contact_mouad contact_mouad : contactList_mouad) {
            contactApi_mouad.insertContact_mouad(contact_mouad).enqueue(new Callback<ApiResponse_mouad>() {
                @Override
                public void onResponse(@NonNull Call<ApiResponse_mouad> call, @NonNull Response<ApiResponse_mouad> response) {
                    processed_mouad[0]++;
                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess_mouad()) {
                        success_count_mouad[0]++;
                    }
                    checkSyncProgress(processed_mouad[0], success_count_mouad[0], total_mouad);
                }

                @Override
                public void onFailure(@NonNull Call<ApiResponse_mouad> call, @NonNull Throwable t) {
                    processed_mouad[0]++;
                    checkSyncProgress(processed_mouad[0], success_count_mouad[0], total_mouad);
                }
            });
        }
    }

    private void checkSyncProgress(int processed, int success, int total) {
        if (processed == total) {
            Toast.makeText(this, "Synchronisation terminee : " + success + "/" + total + " reussis", Toast.LENGTH_LONG).show();
        }
    }

    private void searchContacts_mouad() {
        String keyword_mouad = etKeyword_mouad.getText().toString().trim();

        if (keyword_mouad.isEmpty()) {
            Toast.makeText(this, "Saisir un nom ou un numero", Toast.LENGTH_SHORT).show();
            return;
        }

        contactApi_mouad.searchContacts_mouad(keyword_mouad).enqueue(new Callback<List<Contact_mouad>>() {
            @Override
            public void onResponse(@NonNull Call<List<Contact_mouad>> call, @NonNull Response<List<Contact_mouad>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter_mouad.updateData_mouad(response.body());
                    Toast.makeText(MainActivity.this, "Resultats : " + response.body().size(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Erreur recherche : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Contact_mouad>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Erreur lors de la recherche : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
