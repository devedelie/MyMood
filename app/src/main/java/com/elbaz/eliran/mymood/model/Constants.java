package com.elbaz.eliran.mymood.model;

import com.elbaz.eliran.mymood.R;

/**
 * This class made to store constant array variables
 */
public class Constants {
    public static final String[] emailSubjects = {"Hey, je suis triste et je voulais partager avec toi.",
            "Hey, je suis déçu et je voulais le partager avec toi.",
            "Hey, aujourd'hui, je me sens normal et je voulais le partager avec toi.",
            "Hey, je suis de bonne humeur et je voulais le partager avec toi.",
            "Hey, aujourd'hui, je suis de très bonne humeur et je voulais le partager avec toi."};

    public static final  String dataNotAvailable = "N / A (aucune donnée n'a encore été enregistrée)";

    public static final String[] moodDaysAgo = {"7DaysAgo","6DaysAgo","5DaysAgo","4DaysAgo","3DaysAgo","2DaysAgo","1DaysAgo"};

    public static final String[] commentDaysAgo = {"comment7DaysAgo","comment6DaysAgo","comment5DaysAgo","comment4DaysAgo","comment3DaysAgo","comment2DaysAgo","comment1DaysAgo"};

    public static final String[] historyDaysText = {"Il y a une semaine", "Il y a six jours", "Il y a cinq jours", "Il y a quatre jours","Il y a trois jours", "Avent-hier", "Hier"};

    public static final int[] historyColors = {R.color.SadSmiley, R.color.DisappointedSmiley, R.color.NormalSmiley,
            R.color.HappySmiley, R.color.SuperHappySmiley};
    
}
