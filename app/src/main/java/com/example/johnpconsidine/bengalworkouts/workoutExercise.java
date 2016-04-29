package com.example.johnpconsidine.bengalworkouts;

/**
 * Created by johnpconsidine on 4/23/16.
 */
public class workoutExercise {
   public boolean timeWorkout;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;



    public workoutExercise (boolean timeWorkout) {
        this.timeWorkout = timeWorkout;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String time; // only applicable if a time workuot
    public String reps; // only applicable if a reps workout

}
