package com.joada.idioma;

public class Language<L extends String, R extends Number, S extends Number>  {
    private L language;
    private R skillLevel;
    private S lookingToLearn;
    public Language(L l, R r, S s){
        this.language = l;
        this.skillLevel = r;
        this.lookingToLearn = s;
    }
    public L getL(){ return language; }
    public R getR(){ return skillLevel; }
    public void setL(L l){ this.language = l; }
    public void setR(R r){ this.skillLevel = r; }
    public S getS() { return lookingToLearn; }
    public void setS(S s) { this.lookingToLearn = s; }


}
