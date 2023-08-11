/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class WordsArray {

    private String[] words = new String[21];

    public WordsArray(Words w) {
        words[0] = w.getA1();
        words[1] = w.getA2();
        words[2] = w.getA3();
        words[3] = w.getA4();
        words[4] = w.getA();
        words[5] = w.getB1();
        words[6] = w.getB2();
        words[7] = w.getB3();
        words[8] = w.getB4();
        words[9] = w.getB();
        words[10] = w.getC1();
        words[11] = w.getC2();
        words[12] = w.getC3();
        words[13] = w.getC4();
        words[14] = w.getC();
        words[15] = w.getD1();
        words[16] = w.getD2();
        words[17] = w.getD3();
        words[18] = w.getD4();
        words[19] = w.getD();
        words[20] = w.getF();
    }

    public String[] getWords() {
        return words;
    }
}
