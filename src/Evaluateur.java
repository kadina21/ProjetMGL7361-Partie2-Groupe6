import java.util.Scanner;

public class Evaluateur extends Scientifique{

    public void evaluer(Article A){
        Commentaire com = new Commentaire();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le contenu de votre commentaire: \n");
        com.setContenu(scanner.nextLine());
        System.out.printf("Veuillez preciser si votre commentaire est Majeur ou Mineur ou vide: \n");
        String type = scanner.nextLine();
        if (type=="mineur")
        {
            com.setType(Commentaire.TypeComment.mineur);
        } else if (type=="majeur") {
            com.setType(Commentaire.TypeComment.majeur);
        }else if (type=="") {
            com.setType(Commentaire.TypeComment.vide);
        }
    }
    public Evaluateur(String nom, String prenom, String domaine, String emploi) {
        super(nom, prenom, domaine, emploi);
    }
}
