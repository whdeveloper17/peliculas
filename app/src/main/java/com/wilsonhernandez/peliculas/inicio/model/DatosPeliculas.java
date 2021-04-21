package com.wilsonhernandez.peliculas.inicio.model;

import java.util.List;

public class DatosPeliculas {
   private int page;
   private List<ResultadoDatos> results;

    public DatosPeliculas(int page, List<ResultadoDatos> results) {
        this.page = page;
        this.results = results;
    }

    @Override
    public String toString() {
        return "DatosPeliculas{" +
                "page=" + page +
                ", result=" + results +
                '}';
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ResultadoDatos> getResult() {
        return results;
    }

    public void setResult(List<ResultadoDatos> results) {
        this.results = results;
    }
}
class ResultadoDatos{
    private String backdrop_path;
    private boolean adult;
    private int id;
    private String original_language;
    private String original_title;
    private String overview;
    private String poster_path;
    private String release_date;
    private String title;

    public ResultadoDatos(String backdrop_path, boolean adult, int id, String original_language, String original_title, String overview, String poster_path, String release_date, String title) {
        this.backdrop_path = backdrop_path;
        this.adult = adult;
        this.id = id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
    }

    @Override
    public String toString() {
        return "ResultadoDatos{" +
                "backdrop_path='" + backdrop_path + '\'' +
                ", adult=" + adult +
                ", id=" + id +
                ", original_language='" + original_language + '\'' +
                ", original_title='" + original_title + '\'' +
                ", overview='" + overview + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
