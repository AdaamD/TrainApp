package logiqueTrain;

// Nom de la classe : AdaptateurTrajetsTrain.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.train.R;

import java.util.List;

public class AdaptateurTrajetsTrain extends RecyclerView.Adapter<AdaptateurTrajetsTrain.ViewHolder> {
    private List<TrajetTrain> trajets;
    private Context context;

    // Constructeur
    public AdaptateurTrajetsTrain(List<TrajetTrain> trajets, Context context) {
        this.trajets = trajets;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trajet_train, parent, false); //  définit l'apparence de chaque élément de la liste via le layout item_trajet_train.xml
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrajetTrain trajet = trajets.get(position);
        holder.textViewDepart.setText("Départ de : " + trajet.getVilleDepart());
        holder.textViewArrivee.setText("Arrivée à : " + trajet.getVilleArrivee());
        holder.textViewHeureDepart.setText("Départ prévu à : " + trajet.getHeureDepart());
        holder.textViewHeureArrivee.setText("Arrivée estimée à : " + trajet.getHeureArrivee());

    }

    @Override
    public int getItemCount() {
        return trajets.size();
    }

    public void setTrajets(List<TrajetTrain> trajets) {
        this.trajets = trajets;
    }


    // Classe interne ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDepart;
        TextView textViewArrivee;
        TextView textViewHeureDepart;
        TextView textViewHeureArrivee;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDepart = itemView.findViewById(R.id.TextDepart);
            textViewArrivee = itemView.findViewById(R.id.TextArrivee);
            textViewHeureDepart = itemView.findViewById(R.id.HeureDepart);
            textViewHeureArrivee = itemView.findViewById(R.id.HeureArrivee);
        }
    }
}
