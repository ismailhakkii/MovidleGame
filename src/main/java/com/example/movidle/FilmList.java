package com.example.movidle;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilmList {

    private List<Film> filmListesi;

    public FilmList() {
        filmListesi = new ArrayList<>();
        try {
            // Dosyanızın tam yolu
            String csvFile = "src/main/resources/com/example/movidle/imdb_top_250 (3).csv";
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            br.readLine(); // Header satırını atlar.
            while ((line = br.readLine()) != null) {
                String[] filmDetails = line.split(";"); // '\t' ile ayrılmış kolonlar.
                if(filmDetails.length < 7) {
                    continue; // Eksik bilgili satırı atlayıp bir sonraki satıra geç
                }
                String title = filmDetails[1];
                int year = Integer.parseInt(filmDetails[2]);
                String genre = filmDetails[3];
                String origin = filmDetails[4];
                String director = filmDetails[5];
                String star = filmDetails[6];
                filmListesi.add(new Film(title, year, genre, origin, director, star));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // choose random film
    public Film randomFilm() {
        if (filmListesi.size() == 0) {
            return null; // or throw an exception
        }
        int index = (int) (Math.random() * filmListesi.size());
        return filmListesi.get(index);
    }

    public List<Film> getAllFilm() {
        List<Film> allFilm = new ArrayList<>();
        allFilm = filmListesi;
        return allFilm;
    }

}
