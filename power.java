import java.util.Random;
import java.util.Scanner;

public class power
{
        static Scanner sc = new Scanner(System.in);
        static int G_compteur=0;
        static int G_stockJoueur=0;
        static double G_tirProgramme=0; 
        static double G_tirJoueur = 0;
		static int G_stockProgramme = new Random().nextInt(1000) + 500;
		static boolean G_stockJoueurTouche = false;
        static boolean G_stockProgrammeTouche = false;
        static float G_angleProgramme=0;
        static float G_puissanceProgramme=0;
        static double G_tirProgrammeMin=0;
        static double G_tirProgrammeMax=10000;
		static final int VITESSE_MAX = 150; //(m/s);
		static final int PESANTEUR = 10 ; //(m/s^2)
		static final int HIT_BOX = 10;
		static final int POWER_MIN = 50;
		static final int POWER_MAX = 100;
		static final int DISTANCE_MIN = 500;
		static final int DISTANCE_MAX = 1500;
		static final int ANGLE_MIN = 25;
		static final int ANGLE_MAX = 75;
		static final int CANON_JOUEUR = 0; //( m )
		static final int CANON_PROGRAMME = 0; // ( m )

        public static void main(String[] args) 
    {   	
        placementStock();
        jeuPartie();
    }

    private static void placementStock ()
    {
        System.out.println("Deux camps s'opposent en face a face sur un champ de bataille, un joueur et l'ordinateur");
        System.out.println("********************************************");
        System.out.println("Les deux camps disposent d'un canon et d'un stock d'obus. L'objectif est de detruire le stock d'obus de l'adversaire");
        System.out.println("*********************************************");
        System.out.println("Commencez par placer votre stock. ");
        System.out.println("************************************************");
        System.out.println("Celui de votre adversaire sera place aleatoirement entre 500m et 1500m du canon adverse.");
        System.out.println("************************************************");
        System.out.println("Entrez une position entre 500m et 1500m afin de positionner votre stock par rapport au canon adverse.");
        System.out.println("*************************************************");
        while ((G_stockJoueur > DISTANCE_MAX) | ( G_stockJoueur < DISTANCE_MIN)) 
        {
            System.out.println("Veuillez saisir une valeur entre 500m et 1500m");
            G_stockJoueur = sc.nextInt();

        }
        if ((G_stockJoueur < DISTANCE_MAX) | (G_stockJoueur > DISTANCE_MIN))
        {
            System.out.println("Position enregitree a " +G_stockJoueur+ "  metres");
        }

    }

    private static void jeuPartie () {

        while (G_stockJoueurTouche == false && G_stockProgrammeTouche == false)
        {
            tourJoueur();
            tourProgramme();
            indicationVictoire();	
            indicationTir();

            G_compteur = G_compteur + 1;
            System.out.println("Vous avez effctue "+G_compteur+" tirs");
        }
        if (G_stockJoueurTouche == true && G_stockProgrammeTouche == true)
        {
            System.out.println("*******************************************");
            System.out.println("VOUS VOUS ETES ENTRETUES!");
            System.out.println("*******************************************");
            System.out.println("Le stock du programme etait stocke a "+ G_stockProgramme+" metres de votre canon");
            System.out.println("********************************************");
            System.out.println("Le stock du joueur etait stocke a "+ G_stockJoueur +" metres du joueur adverse");
        }
        else 
        {
            if (G_stockJoueurTouche == true && G_stockProgrammeTouche == false)
            {
                System.out.println("********************************************");
                System.out.print("VOUS AVEZ PERDU !");
                System.out.println("*********************************************");
                System.out.println("Le stock du programme etait stocke a "+ G_stockProgramme +" metres de votre canon");
                System.out.println("*********************************************");
                System.out.println("Le stock du joueur etait stocke a "+ G_stockJoueur +" metres du joueur adverse");
            }
            else
            {
                if (G_stockProgrammeTouche == true && G_stockJoueurTouche == false);
                {
                    System.out.println("*********************************************");
                    System.out.println("VOUS AVEZ GAGNE !");
                    System.out.println("**********************************************");
                    System.out.println("Le stock du programme etait stocke a "+ G_stockProgramme +" metres de votre canon");
                    System.out.println("***********************************************");
                    System.out.println("Le stock du joueur etait stocke a "+ G_stockJoueur +" metres du joueur adverse");
                }
            }
        }
    }



    private static void tourJoueur() {
        float L_angleTirJoueur=0;
        float L_puissanceTirJoueur=0;

        while ((L_angleTirJoueur > ANGLE_MAX) | (L_angleTirJoueur < ANGLE_MIN))
        {
            System.out.println("Veuillez saisir un angle entre 25 et 75 degres");
            L_angleTirJoueur = sc.nextFloat();
            System.out.println("********************************************");
        }
        if ((L_angleTirJoueur < ANGLE_MAX) && (L_angleTirJoueur > ANGLE_MIN))
        {
            System.out.println("Angle de "+ L_angleTirJoueur +" degres enregistre");           
        }	
        
        while ((L_puissanceTirJoueur> POWER_MAX) | (L_puissanceTirJoueur<POWER_MIN))
        {
            System.out.println("Veuillez saisir une puissance entre 50 et 100");
            L_puissanceTirJoueur = sc.nextFloat( );
        }
        if ((L_puissanceTirJoueur < POWER_MAX) && (L_puissanceTirJoueur > POWER_MIN))
        {
            System.out.println("Puissance de "+L_puissanceTirJoueur+" enregistree");
            System.out.println("********************************************");
        }
        shootRangeJoueur(L_angleTirJoueur, L_puissanceTirJoueur);
    }

    private static void tourProgramme() {
        float L_angleProgramme=0;
        float L_puissanceTirProgramme=0;
        if(G_compteur==0){          
            L_angleProgramme = new Random().nextInt(50) + 25;
            L_puissanceTirProgramme = new Random().nextInt(50) + 50;
            shootRangeProgramme(L_angleProgramme, L_puissanceTirProgramme);
            System.out.println("Le programme a tire a "+G_tirProgramme+" metres");
            System.out.println("********************************************");
        }else{            
            if(G_tirProgramme>G_stockJoueur+HIT_BOX){
            G_tirProgrammeMax=G_tirProgramme;
            do{
                L_angleProgramme = new Random().nextInt(50) + 25;
                L_puissanceTirProgramme = new Random().nextInt(50) + 50;
                shootRangeProgramme(L_angleProgramme, L_puissanceTirProgramme);
            }while(G_tirProgramme>=G_tirProgrammeMax || G_tirProgramme<=G_tirProgrammeMin);
            System.out.println("Le programme a tire a "+G_tirProgramme+" metres");
            System.out.println("********************************************");
            }else{
                if(G_tirProgramme<G_stockJoueur-HIT_BOX){
                    G_tirProgrammeMin=G_tirProgramme;  
                }
                do{
                    L_angleProgramme = new Random().nextInt(50) + 25;
                    L_puissanceTirProgramme = new Random().nextInt(50) + 50;
                    shootRangeProgramme(L_angleProgramme, L_puissanceTirProgramme);
                }while(G_tirProgramme>=G_tirProgrammeMax || G_tirProgramme<=G_tirProgrammeMin);
                System.out.println("Le programme a tire a "+G_tirProgramme+" metres");
                System.out.println("********************************************");                                  
            }                                                    
        }   
    }

    private static void shootRangeJoueur(float L_angleTirJoueur, float L_puissanceTirJoueur) {
        double L_range = 0;
        L_range = Math.abs(((Math.pow(VITESSE_MAX*(L_puissanceTirJoueur/100),2))*(Math.sin(2*L_angleTirJoueur)))/PESANTEUR);
        G_tirJoueur =  L_range;
        System.out.println("Vous avez tire a "+L_range+" metres");
        System.out.println("********************************************");

    }
    private static void shootRangeProgramme(float L_angleProgramme, float L_puissanceTirProgramme) {
        double L_range = 0;
        L_range = Math.abs(((Math.pow(VITESSE_MAX*(L_puissanceTirProgramme/100),2))*(Math.sin(2*L_angleProgramme)))/PESANTEUR);
        G_tirProgramme = L_range;  
    }


    private static void indicationTir () {
        if (G_tirJoueur < G_stockProgramme - HIT_BOX)
        {
            System.out.println("Votre tir etait trop court");
            System.out.println("********************************************");
        }
        else
        {
            if (G_tirJoueur > G_stockProgramme + HIT_BOX)
            {
                System.out.println("Votre tir etait trop long");
                System.out.println("********************************************");
            }
        }
    }

    private static void indicationVictoire() {
        if (((G_tirJoueur > G_stockProgramme - HIT_BOX) && (G_tirJoueur < G_stockProgramme + HIT_BOX)) && ((G_tirProgramme > G_stockJoueur - HIT_BOX) && (G_tirProgramme < G_stockJoueur + HIT_BOX)))
        {
            G_stockProgrammeTouche = true;
            G_stockJoueurTouche = true;
        }
        else
        {
            if (((G_tirJoueur > G_stockProgramme - HIT_BOX) && (G_tirJoueur < G_stockProgramme + HIT_BOX)) && ((G_tirProgramme < G_stockJoueur - HIT_BOX) | (G_tirProgramme > G_stockJoueur + HIT_BOX)))
            {
                G_stockProgrammeTouche = true;
                G_stockJoueurTouche = false;
            }
            else
            {
                if (((G_tirJoueur < G_stockProgramme - HIT_BOX) | (G_tirJoueur > G_stockProgramme + HIT_BOX)) && ((G_tirProgramme > G_stockJoueur - HIT_BOX) && (G_tirProgramme < G_stockJoueur + HIT_BOX)))
                {
                    G_stockJoueurTouche = true;
                    G_stockProgrammeTouche = false;
                }
            }
        }
    }	
}
