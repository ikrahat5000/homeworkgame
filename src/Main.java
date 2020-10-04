import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static int[] heroesHealth = {260, 250, 240, 500};
    public static int[] heroesDamage = {20, 25, 15};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medical"};
    public static int healing = 10; // we have created separated int variable  for medic

    public static void main(String[] args) {
        // write your code here
        printStatistics();
        while (!isGameFinished()) { // while will run until it is true (!)
            round();
        }

    }

    public static void medicsHeal() { // we have created  method for medic
        Random r = new Random();
        int num = r.nextInt(heroesDamage.length);
        if (heroesHealth[num] < 100 && heroesHealth[num] > 0 && heroesHealth[3] > 0) {
            heroesHealth[num] += healing; // heroesHealth[num] = heroesHealth[num] + healing;
            System.out.println("Medic has healed " + heroesAttackType[num]);
        }

    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss choose " + bossDefence);


    }

    public static void round() {
        changeBossDefence();
        heroesHits();
        if (bossHealth > 0) {  // checks if the boss is alive, only when boss is alive executes next line
            bossHits();
        }
        medicsHeal();// we have added
        printStatistics();

    }


    public static void heroesHits() {
        for (int i = 0; i < heroesDamage.length; i++) {

            if (heroesHealth[i] > 0 && bossHealth > 0) { // we check here heroes health if he can fight or not
                if (heroesAttackType[i] == bossDefence) {
                    Random r = new Random();
                    int coeff = r.nextInt(10) + 2;
                    System.out.println("Critical Damage " + heroesDamage[i] * coeff);

                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }

        }
    }
    //------------------

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }

        }
    }

    //-------------------

    public static void printStatistics() {
        System.out.println("-------------------");

        System.out.println("Boss health: " + bossHealth);

        for (int i = 0; i < heroesAttackType.length; i++) {

            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]);

        }

        System.out.println("-------------------");

    }

    public static boolean isGameFinished() {

        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if(allHeroesDead){
            System.out.println("Boss won!!!"); // ------------------
        }

        return allHeroesDead;
    }

}

