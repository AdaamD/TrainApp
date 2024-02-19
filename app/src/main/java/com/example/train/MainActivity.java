package com.example.train;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

        // Initialiser la liste des trajets à vide
        listeTrajets = new ArrayList<>();

        // Configuration du RecyclerView
        recyclerView = findViewById(R.id.recyclerViewHoraires);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptateur = new AdaptateurTrajetsTrain(listeTrajets, this);
        recyclerView.setAdapter(adaptateur);

        // Gérer le clic sur le bouton Rechercher
        buttonRechercher.setOnClickListener(view -> rechercherHorairesDeTrain());

        // Button reset
        // Récupérer la référence du bouton de réinitialisation
        Button buttonReset = findViewById(R.id.buttonReset);

        // Ajouter un écouteur de clic au bouton de réinitialisation
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Réinitialiser les champs d'entrée
                editTextDepart.setText("");
                editTextArrivee.setText("");

                // Réinitialiser l'affichage
                adaptateur.setTrajets(new ArrayList<>());
            }
        });
    }

    // Méthode pour rechercher les horaires de train
    private void rechercherHorairesDeTrain() {
        // Récupérer les villes de départ et d'arrivée saisies par l'utilisateur
        String villeDepart = editTextDepart.getText().toString().trim();
        String villeArrivee = editTextArrivee.getText().toString().trim();

        // Vérifier si les champs de saisie sont vides
        if (villeDepart.isEmpty() || villeArrivee.isEmpty()) {
            Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return; // Sortir de la méthode si les champs sont vides
        }

        // Effacer les trajets précédents
        listeTrajets.clear();

        // Initialiser les trajets de train
        initialiserTrajets();

        // Filtrer la liste des trajets en fonction des villes de départ et d'arrivée
        List<TrajetTrain> trajetsFiltres = filtrerTrajets(villeDepart, villeArrivee);

        // Trier les trajets par heure de départ
        Collections.sort(trajetsFiltres, new Comparator<TrajetTrain>() {
            @Override
            public int compare(TrajetTrain t1, TrajetTrain t2) {
                return t1.getHeureDepart().compareTo(t2.getHeureDepart());
            }
        });

        // Mettre à jour l'adaptateur avec les trajets filtrés
        adaptateur.setTrajets(trajetsFiltres);
        adaptateur.notifyDataSetChanged();
    }


    // Méthode pour initialiser les trajets de train de test
    private void initialiserTrajets() {
        listeTrajets.add(new TrajetTrain("Paris", "Lyon", "08:00", "12:00"));
        listeTrajets.add(new TrajetTrain("Marseille", "Lille", "10:30", "14:45"));
        listeTrajets.add(new TrajetTrain("toulouse  ", "Strasbourg", "13:15", "18:30"));
        listeTrajets.add(new TrajetTrain("Toulouse", "Montpellier", "07:30", "10:15"));
        listeTrajets.add(new TrajetTrain("Montpellier", "Toulouse", "11:00", "13:45"));
        listeTrajets.add(new TrajetTrain("Toulouse", "Montpellier", "14:30", "17:15"));
        listeTrajets.add(new TrajetTrain("Montpellier", "Paris", "08:15", "12:30"));
        listeTrajets.add(new TrajetTrain("Paris", "Montpellier", "13:00", "17:15"));
        listeTrajets.add(new TrajetTrain("Montpellier", "Paris", "17:45", "22:00"));
        listeTrajets.add(new TrajetTrain("Montpellier", "Paris", "09:30", "13:45"));
        listeTrajets.add(new TrajetTrain("Marseille", "Toulouse", "11:45", "15:30"));
        listeTrajets.add(new TrajetTrain("Lyon", "Montpellier", "14:00", "18:15"));
        listeTrajets.add(new TrajetTrain("Toulouse", "Paris", "16:30", "20:45"));
        listeTrajets.add(new TrajetTrain("Paris", "Marseille", "18:00", "22:15"));
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
