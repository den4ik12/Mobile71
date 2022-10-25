package com.example.lab711;

public class Game {
    int[][] a = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    int player = 1;
    int run = 0;

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public void restart(){
        player = 1;
        run = 1;
        for(int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                a[i][j] = 0;
    }

    public void set(int i, int j) {
        a[i][j] = player;
    }

    public int get(int i, int j) {
        return a[i][j];
    }

    public void changePlayer() {
        player = player == 1 ? 2 : 1;
    }

    public int getPlayer() {
        return player;
    }

    public int check() {
        int state = 0;
        //горизонтали
        if (a[0][0] != 0 && a[0][0] == a[0][1] && a[0][1] == a[0][2]) state = 1;
        else if (a[1][0] != 0 && a[1][0] == a[1][1] && a[1][1] == a[1][2]) state = 2;
        else if (a[2][0] != 0 && a[2][0] == a[2][1] && a[2][1] == a[2][2]) state = 3;

        //вертикали
        else if (a[0][0] != 0 && a[0][0] == a[1][0] && a[1][0] == a[2][0]) state = 4;
        else if (a[0][1] != 0 && a[0][1] == a[1][1] && a[1][1] == a[2][1]) state = 5;
        else if (a[0][2] != 0 && a[0][2] == a[1][2] && a[1][2] == a[2][2]) state = 6;

        //диагонали
        else if (a[0][0] != 0 && a[0][0] == a[1][1] && a[1][1] == a[2][2]) state = 7;
        else if (a[0][2] != 0 && a[0][2] == a[1][1] && a[1][1] == a[2][0]) state = 8;

        else if (a[0][0] * a[0][1] * a[0][2] * a[1][0] * a[1][1] * a[1][2] * a[2][0] * a[2][1] * a[2][2] != 0)
            state = 9;

        if (state != 0) run = 0;
        else run = 1;
        return state;
    }
}
