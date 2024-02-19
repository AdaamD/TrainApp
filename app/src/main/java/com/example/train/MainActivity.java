package com.example.train;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import logiqueTrain.AdaptateurTrajetsTrain;
import logiqueTrain.TrajetTrain;

public class MainActivity extends AppCompatActivity {

    private List<TrajetTrain> listeTrajets;
    private RecyclerView recyclerView;
    private AdaptateurTrajetsTrain adaptateur;
    private EditText editTextDepart, editTextArrivee;
    private Button buttonRechercher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser les vues
        editTextDepart = findViewById(R.id.TextDepart);
        editTextArrivee = findViewById(R.id.TextArrivee);
        buttonRechercher = findViewById(R.id.buttonRechercher);

        // Initialiser les trajets de train
        initialiserTrajets();

        // Configuration du RecyclerView
        recyclerView = findViewById(R.id.recyclerViewHoraires);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptateur = new AdaptateurTrajetsTrain(listeTrajets, this);
        recyclerView.setAdapter(adaptateur);

        // Gérer le clic sur le bouton Rechercher
        buttonRechercher.setOnClickListener(view -> rechercherHorairesDeTrain());
    }

    // Initialiser les trajets de train de test
    private void initialiserTrajets() {
        listeTrajets = new ArrayList<>();
        listeTrajets.add(new TrajetTrain("Paris", "Lyon", "08:00", "12:00"));
        listeTrajets.add(new TrajetTrain("Marseille", "Lille", "10:30", "14:45"));
        listeTrajets.add(new TrajetTrain("Toulouse", "Strasbourg", "13:15", "18:30"));
        // Ajoutez d'autres trajets de train si nécessaire
    }

    // Méthode pour rechercher les horaires de train
    private void rechercherHorairesDeTrain() {
        // Récupérer les villes de départ et d'arrivée saisies par l'utilisateur
        String villeDepart = editTextDepart.getText().toString().trim();
        String villeArrivee = editTextArrivee.getText().toString().trim();

        // Vérifier si les champs de saisie sont vides
        if (villeDepart.isEmpty() || villeArrivee.isEmpty()) {
            Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
        } else {
            // Filtrer la liste des trajets en fonction des villes de départ et d'arrivée
            List<TrajetTrain> trajetsFiltres = filtrerTrajets(villeDepart, villeArrivee);

            // Mettre à jour l'adaptateur avec les trajets filtrés
            adaptateur.setTrajets(trajetsFiltres);
            adaptateur.notifyDataSetChanged();
        }
    }

    // Méthode pour filtrer la liste des trajets en fonction des villes de départ et d'arrivée
    private List<TrajetTrain> filtrerTrajets(String villeDepart, String villeArrivee) {
        List<TrajetTrain> trajetsFiltres = new ArrayList<>();
        for (TrajetTrain trajet : listeTrajets) {
            if (trajet.getVilleDepart().equalsIgnoreCase(villeDepart) &&
                    trajet.getVilleArrivee().equalsIgnoreCase(villeArrivee)) {
                trajetsFiltres.add(trajet);
            }
        }
        return trajetsFiltres;
    }
}
